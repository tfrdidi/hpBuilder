package org.hpbuilder.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Redirects the requests to their actual handler.
 *
 * Created by didi on 22.09.2015.
 */
public class HtmlServlet extends AbstractServlet {

    final Logger log = LoggerFactory.getLogger(HtmlServlet.class);

    @Override
    protected void myGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();
        log.info("Requested URI {}", uri);
        if("/".equals(uri) || "\\".equals(uri)) {
            uri = "start.html";
        }

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF8");

        PrintWriter out = resp.getWriter();
        try (
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(uri), "UTF8"))) {
            int len;
            final char[] buffer = new char[1024 * 8];
            do {
                len = in.read(buffer);
                out.write(buffer);
            } while (len <= 0);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        catch (Exception e) {
            log.info("Problem reading file", e);
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        finally {
            out.close();
        }
    }

}
