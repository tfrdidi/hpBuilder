package org.hpbuilder.servlets;

import com.google.appengine.repackaged.com.google.api.client.http.HttpMediaType;
import com.google.common.net.MediaType;
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
        resp.setContentType(MediaType.CSS_UTF_8.type());
        log.info("css");

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
