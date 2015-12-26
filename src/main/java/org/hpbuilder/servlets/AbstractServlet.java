package org.hpbuilder.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Contains all Services like performance measures that are done for all <code>HttpServletRequest</code>s
 *
 * Created by didi on 22.09.2015.
 */
public abstract class AbstractServlet extends HttpServlet {

    private final Logger log = LoggerFactory.getLogger(AbstractServlet.class);

    final int CACHE_DURATION_IN_SECONDS = 60 * 60 * 2; // 2 hours
    final long CACHE_DURATION_IN_MS = CACHE_DURATION_IN_SECONDS  * 1000;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("test");

        myGet(req, resp);

        long now = System.currentTimeMillis();
        resp.addHeader("Cache-Control", "max-age=" + CACHE_DURATION_IN_SECONDS);
        resp.setDateHeader("Expires", now + CACHE_DURATION_IN_MS);
    }

    protected abstract void myGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
