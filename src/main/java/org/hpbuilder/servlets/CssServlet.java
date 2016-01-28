package org.hpbuilder.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by didi on 13.10.2015.
 */
public class CssServlet extends AbstractServlet {

    final Logger log = LoggerFactory.getLogger(CssServlet.class);

    @Override
    protected void myGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        resp.setContentType("UTF8");
        log.info("css");

        if(!uri.endsWith(".css")) {
            uri = uri + ".css";
        }
        uri = uri.substring(1);

        streamFileInResponse(uri, resp);
    }
}
