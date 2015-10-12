package org.hpbuilder.servlets;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        myGet(req, resp);
    }

    protected abstract void myGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
