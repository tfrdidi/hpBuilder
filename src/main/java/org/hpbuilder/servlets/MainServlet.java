package org.hpbuilder.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Redirects the requests to their actual handler.
 *
 * Created by didi on 22.09.2015.
 */
public class MainServlet extends AbstractServlet {

    final Logger log = LoggerFactory.getLogger(MainServlet.class);

    @Override
    protected void myGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("Requested URI {}", req.getRequestURI());

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        BufferedReader br = new BufferedReader(new FileReader("test.html"));
        int len;
        final char[] buffer = new char[1024 * 8];
        do {
            len = br.read(buffer);
            out.write(buffer);
        } while (len <= 0);
        out.close();
        resp.setStatus(HttpServletResponse.SC_OK);
    }

}
