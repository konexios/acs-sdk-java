/*******************************************************************************
 * Copyright 2021 Konexios, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.konexios.acs.client.api;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import com.konexios.acs.AcsUtils;
import com.konexios.acs.Loggable;

public class ConnectionManager extends Loggable {

	private final static String ENV_CONNECTION_MANAGER_MAX_TOTAL_CONNECTIONS = "acs.connectionManager.maxTotalConnections";
	private final static String ENV_CONNECTION_MANAGER_MAX_PER_ROUTE_CONNECTIONS = "acs.connectionManager.maxPerRouteConnections";
	private final static String ENV_CONNECTION_MANAGER_SOCKET_TIMEOUT_SECS = "acs.connectionManager.socketTimeoutSecs";
	private final static String ENV_CONNECTION_MANAGER_KEEP_ALIVE_SECS = "acs.connectionManager.keepAliveSecs";

	private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 200;
	private static final int DEFAULT_MAX_PER_ROUTE_CONNECTIONS = 200;
	private static final int DEFAULT_VALIDATE_AFTER_INACTIVITY_MS = 5000;
	private static final int DEFAULT_SOCKET_TIMEOUT_SECS = 60;
	private static final int DEFAULT_KEEP_ALIVE_SECS = 10;

	private static final long DEFAULT_CLOSE_IDLE_CONNECTION_SECS = 30L;
	private static final long DEFAULT_CONNECTION_MONITOR_DELAY_SECS = 30L;
	private static final long DEFAULT_CONNECTION_MONITOR_INTERVAL_SECS = 5L;

	private static final class SingletonHolder {
		static final ConnectionManager SINGLETON = new ConnectionManager();
	}

	public static ConnectionManager getInstance() {
		return SingletonHolder.SINGLETON;
	}

	private PoolingHttpClientConnectionManager connectionManager;
	private CloseableHttpClient sharedClient;

	private int maxTotalConnections;
	private int maxPerRouteConnections;
	private int socketTimeoutSecs;
	private int keepAliveSecs;

	private ScheduledFuture<?> connectionMonitor;

	private ConnectionManager() {
		String method = "ConnectionManager";

		maxTotalConnections = AcsUtils.getSystemProperty(ENV_CONNECTION_MANAGER_MAX_TOTAL_CONNECTIONS,
				DEFAULT_MAX_TOTAL_CONNECTIONS);
		maxPerRouteConnections = AcsUtils.getSystemProperty(ENV_CONNECTION_MANAGER_MAX_PER_ROUTE_CONNECTIONS,
				DEFAULT_MAX_PER_ROUTE_CONNECTIONS);
		socketTimeoutSecs = AcsUtils.getSystemProperty(ENV_CONNECTION_MANAGER_SOCKET_TIMEOUT_SECS,
				DEFAULT_SOCKET_TIMEOUT_SECS);
		keepAliveSecs = AcsUtils.getSystemProperty(ENV_CONNECTION_MANAGER_KEEP_ALIVE_SECS, DEFAULT_KEEP_ALIVE_SECS);

		connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(maxTotalConnections);
		connectionManager.setDefaultMaxPerRoute(maxPerRouteConnections);
		connectionManager.setValidateAfterInactivity(DEFAULT_VALIDATE_AFTER_INACTIVITY_MS);
		connectionManager.setDefaultSocketConfig(
				SocketConfig.custom().setSoKeepAlive(true).setSoTimeout(socketTimeoutSecs * 1000).build());

		ConnectionKeepAliveStrategy keepAliveStrategy = new ConnectionKeepAliveStrategy() {
			@Override
			public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
				HeaderElementIterator it = new BasicHeaderElementIterator(
						response.headerIterator(HTTP.CONN_KEEP_ALIVE));
				while (it.hasNext()) {
					HeaderElement he = it.nextElement();
					String param = he.getName();
					String value = he.getValue();
					if (value != null && param.equalsIgnoreCase("timeout")) {
						return Long.parseLong(value) * 1000;
					}
				}
				return keepAliveSecs * 1000;
			}
		};

		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(socketTimeoutSecs * 1000)
				.setConnectionRequestTimeout(socketTimeoutSecs * 1000).setSocketTimeout(socketTimeoutSecs * 1000)
				.build();

		// initialize shared client
		logInfo(method, "creating shared client ...");
		sharedClient = HttpClients.custom().setConnectionManager(connectionManager).setConnectionManagerShared(true)
				.setKeepAliveStrategy(keepAliveStrategy).setDefaultRequestConfig(requestConfig).build();

		logInfo(method, "starting connection monitor thread ...");
		connectionMonitor = Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
			try {
				connectionManager.closeExpiredConnections();
			} catch (Exception e) {
			}
			try {
				connectionManager.closeIdleConnections(DEFAULT_CLOSE_IDLE_CONNECTION_SECS, TimeUnit.SECONDS);
			} catch (Exception e) {
			}
		}, DEFAULT_CONNECTION_MONITOR_DELAY_SECS, DEFAULT_CONNECTION_MONITOR_INTERVAL_SECS, TimeUnit.SECONDS);

		logInfo(method, "ready");
	}

	public CloseableHttpClient getSharedClient() {
		AcsUtils.notNull(sharedClient, "connection pool is not available");
		return sharedClient;
	}

	public int getMaxTotalConnections() {
		return maxTotalConnections;
	}

	public void setMaxTotalConnections(int maxTotalConnections) {
		this.maxTotalConnections = maxTotalConnections;
	}

	public int getMaxPerRouteConnections() {
		return maxPerRouteConnections;
	}

	public void setMaxPerRouteConnections(int maxPerRouteConnections) {
		this.maxPerRouteConnections = maxPerRouteConnections;
	}

	@PreDestroy
	public void shutdown() {
		String method = "shutdown";
		if (connectionMonitor != null) {
			try {
				connectionMonitor.cancel(true);
			} catch (Throwable t) {
			}
			connectionMonitor = null;
		}
		if (connectionManager != null) {
			try {
				sharedClient = null;
				logInfo(method, "shutting down connectionManager ...");
				connectionManager.close();
				connectionManager = null;
			} catch (Throwable t) {
			}
		}
	}
}
