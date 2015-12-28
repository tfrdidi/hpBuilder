package org.hpbuilder.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.MessageFormat;

/**
 * Redirects the requests to their actual handler.
 * <p/>
 * Created by didi on 22.09.2015.
 */
public class HtmlServlet extends AbstractServlet {

    final Logger log = LoggerFactory.getLogger(HtmlServlet.class);


    @Override
    protected void myGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();
        log.info("Requested URI {}", uri);
        if ("/".equals(uri) || "\\".equals(uri)) {
            uri = "/sites/generic/start.html";
        } else if (!uri.contains("/sites/generic")) {
            uri = "/sites/generic" + uri;
        }

        if (!uri.endsWith(".html")) {
            uri = uri + ".html";
        }

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF8");

        PrintWriter out = resp.getWriter();
        if (uri.contains("generic")) {
            try {
                log.info("start content creation");
                String masterPage = getFileContent("sites/generic/master.html");
                uri = uri.substring(1);
                String content = getFileContent(uri);
                String page = MessageFormat.format(masterPage, content);
                log.info("finished content creation");
                out.write(page);
                resp.setStatus(HttpServletResponse.SC_OK);
            }
            catch (IOException e) {
                log.info("Problem reading file", e);
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            try {
                out.write(getFileContent(uri));
                resp.setStatus(HttpServletResponse.SC_OK);
            } catch (Exception e) {
                log.info("Problem reading file", e);
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } finally {
                out.close();
            }
        }
    }

    private String getFileContent(String path)
            throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path), "UTF8"));
        int len;
        int bufferLen = 1024 * 1;
        final char[] buffer = new char[bufferLen];
        do {
            len = in.read(buffer);
            stringBuffer.append(new String(buffer).substring(0, len));
        } while (len == bufferLen);
        in.close();

        return stringBuffer.toString();
    }

}
