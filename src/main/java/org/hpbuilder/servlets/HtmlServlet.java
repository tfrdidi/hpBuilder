package org.hpbuilder.servlets;

import org.hpbuilder.model.Site;
import org.hpbuilder.misc.SiteManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Redirects the requests to their actual handler.
 */
public class HtmlServlet extends AbstractServlet {

    private SiteManager siteManager;

    private SiteManager getSiteManager() {
        if (siteManager== null) {
            siteManager = new SiteManager();
        }
        return siteManager;
    }

    @Override
    protected void myGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = getUriFromRequest(req);
        Site site = getSiteManager().getSite(uri);
        if(site != null) {
            PrintWriter out = resp.getWriter();
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            out.write(site.getContent());
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            throw new IOException("Problem reading file with uri " + uri);
        }
    }

    private String getUriFromRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if ("/".equals(uri) || "\\".equals(uri)) {
            uri = getSiteManager().getStartSiteUri();
        } else if (!uri.contains("/sites/content")) {
            uri = "/sites/content" + uri;
        }
        if (!uri.endsWith(".html")) {
            uri = uri + ".html";
        }
        return uri;
    }
}
