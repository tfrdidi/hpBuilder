package org.hpbuilder.misc;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;

import java.io.*;

/**
 * Collection of static functions to read file content
 */
public class Reader {

    public static String getFileContentAsString(String path)
            throws IOException {
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path), "UTF-8"));
        int len;
        int bufferLen = 1024;
        final char[] buffer = new char[bufferLen];
        do {
            len = in.read(buffer);
            stringBuffer.append(new String(buffer).substring(0, len));
        } while (len == bufferLen);
        in.close();

        return stringBuffer.toString();
    }

    public static Image getFileContentAsImage(String path)
            throws IOException {

        InputStream in = new FileInputStream(path);
        int len;
        int bufferLen = 1024*1024; // 1 MB
        final byte[] buffer = new byte[bufferLen];
        do {
            len = in.read(buffer);
        } while (len == bufferLen);
        in.close();

        return ImagesServiceFactory.makeImage(buffer);
    }
}
