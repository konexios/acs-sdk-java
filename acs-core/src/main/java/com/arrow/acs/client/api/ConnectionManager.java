/*******************************************************************************
 * Copyright (c) 2017 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *******************************************************************************/
package com.arrow.acs.client.api;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.PreDestroy;

import org.apache.commons.lang3.Validate;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.arrow.acs.Loggable;

public class ConnectionManager extends Loggable {

	private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 200;
	private static final int DEFAULT_MAX_PER_ROUTE_CONNECTIONS = 50;
	private static final long DEFAULT_CHECK_EXPIRED_CONNECTION_INTERVAL_MS = 30000;

	private static final class SingletonHolder {
		static final ConnectionManager SINGLETON = new ConnectionManager();
	}

	public static ConnectionManager getInstance() {
		return SingletonHolder.SINGLETON;
	}

	private PoolingHttpClientConnectionManager connectionManager;
	private Timer checkExpiredTimer;

	private int maxTotalConnections = DEFAULT_MAX_TOTAL_CONNECTIONS;
	private int maxPerRouteConnections = DEFAULT_MAX_PER_ROUTE_CONNECTIONS;
	private long checkExpiredConnectionIntervalMs = DEFAULT_CHECK_EXPIRED_CONNECTION_INTERVAL_MS;

	private ConnectionManager() {
		connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(maxTotalConnections);
		connectionManager.setDefaultMaxPerRoute(maxPerRouteConnections);
		restartExpiredTimer();
	}

	public CloseableHttpClient getConnection() {
		Validate.notNull(connectionManager, "connection manager is not available");
		return HttpClients.custom().setConnectionManager(connectionManager).setConnectionManagerShared(true).build();
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

	public long getCheckExpiredConnectionIntervalMs() {
		return checkExpiredConnectionIntervalMs;
	}

	public void setCheckExpiredConnectionIntervalMs(long checkExpiredConnectionIntervalMs) {
		this.checkExpiredConnectionIntervalMs = checkExpiredConnectionIntervalMs;
	}

	@PreDestroy
	public void shutdown() {
		String method = "shutdown";
		if (checkExpiredTimer != null) {
			checkExpiredTimer.cancel();
			checkExpiredTimer = null;
		}
		if (connectionManager != null) {
			try {
				logInfo(method, "shutting down connectionManager ...");
				connectionManager.close();
				connectionManager = null;
			} catch (Throwable t) {
			}
		}
	}

	private void restartExpiredTimer() {
		String method = "restartExpiredTimer";

		if (checkExpiredTimer != null) {
			logInfo(method, "cancelling current timer ...");
			checkExpiredTimer.cancel();
			checkExpiredTimer = null;
		}

		logDebug(method, "checkExpiredConnectionIntervalMs: %d", checkExpiredConnectionIntervalMs);
		if (checkExpiredConnectionIntervalMs > 0) {
			checkExpiredTimer = new Timer(true);
			checkExpiredTimer.scheduleAtFixedRate(new TimerTask() {
				AtomicBoolean running = new AtomicBoolean(false);

				@Override
				public void run() {
					if (connectionManager != null) {
						if (running.compareAndSet(false, true)) {
							try {
								logDebug(method, "connectionManager.closeExpiredConnections() ...");
								connectionManager.closeExpiredConnections();
							} catch (Throwable t) {
							}
						}
					} else {
						logWarn(method, "connectionManager is not available!");
					}
				}
			}, 0, checkExpiredConnectionIntervalMs);
		} else {
			logWarn(method, "timer is NOT scheduled, checkExpiredConnectionIntervalMs: %d",
			        checkExpiredConnectionIntervalMs);
		}
	}
}
