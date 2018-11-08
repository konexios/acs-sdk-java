/*******************************************************************************
 * Copyright (c) 2018 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *******************************************************************************/
package com.arrow.acs;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

public class AcsUtils {
    public final static String EMPTY_TRING = "";

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return cs != null && cs.length() > 0;
    }

    public static <T extends CharSequence> T notEmpty(final T chars, final String message, final Object... values) {
        if (chars == null) {
            throw new NullPointerException(String.format(message, values));
        }
        if (chars.length() == 0) {
            throw new IllegalArgumentException(String.format(message, values));
        }
        return chars;
    }

    public static <T> T notNull(final T object, final String message, final Object... values) {
        if (object == null) {
            throw new NullPointerException(String.format(message, values));
        }
        return object;
    }

    public static String trimToEmpty(final String str) {
        return str == null ? EMPTY_TRING : str.trim();
    }

    public static String trimToNull(final String str) {
        String temp = trimToEmpty(str);
        return temp.equals(EMPTY_TRING) ? null : temp;
    }

    public static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    public static String streamToString(final InputStream is, final Charset charset) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) != -1) {
            bos.write(buffer, 0, length);
        }
        return bos.toString(charset.name());
    }

    public static List<String> streamToLines(final InputStream is, final Charset charset) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset))) {
            return reader.lines().collect(Collectors.toList());
        }
    }

    public static long fastCopy(final InputStream src, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        try {
            return fastCopy(src, fos);
        } finally {
            fos.close();
        }
    }

    public static long fastCopy(final InputStream src, final OutputStream dest) throws IOException {
        final ReadableByteChannel inputChannel = Channels.newChannel(src);
        final WritableByteChannel outputChannel = Channels.newChannel(dest);
        return fastCopy(inputChannel, outputChannel);
    }

    public static void close(InputStream is) {
        try {
            if (is != null)
                is.close();
        } catch (Throwable t) {
        }
    }

    public static void close(Closeable closeable) {
        try {
            if (closeable != null)
                closeable.close();
        } catch (Throwable t) {
        }
    }

    private static long fastCopy(final ReadableByteChannel src, final WritableByteChannel dest) throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);

        long size = 0;
        int read = 0;
        while ((read = src.read(buffer)) != -1) {
            size += read;
            buffer.flip();
            dest.write(buffer);
            buffer.compact();
        }

        buffer.flip();

        while (buffer.hasRemaining()) {
            dest.write(buffer);
        }

        return size;
    }
}
