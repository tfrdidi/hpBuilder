package org.hpbuilder.servlets;

import com.google.appengine.api.images.*;
import org.hpbuilder.images.*;
import org.hpbuilder.misc.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Servlet for images. Offers different Algorithms to run on the images
 * before they are served. As their execution time is so minimal, it is
 * better to perform them on runtime as to save images in each sizes.
 */
public class ImageServlet extends AbstractServlet {

    private HashMap<String, ImageAlgorithm> algorithmHashMap;

    protected void loadAlgorithms() {
        algorithmHashMap = new HashMap<>();
        algorithmHashMap.put("cheight", new CropVertically());
        algorithmHashMap.put("cwidth", new CropHorizontally());
        algorithmHashMap.put("swidth", new ScaleHorizontally());
        algorithmHashMap.put("sheight", new ScaleVertically());
        algorithmHashMap.put("compress", new Compress());
    }

    @Override
    protected void myGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String relativePath = req.getRequestURI().substring(1);
        Image result = Reader.getFileContentAsImage(relativePath);
        String queryString = req.getQueryString();

        if(algorithmHashMap == null) {
            loadAlgorithms();
        }

        if(queryString != null) {
            String[] parameters = queryString.split("&");
            for (String parameter : parameters) {
                String[] tmp = parameter.split("=");
                if (algorithmHashMap.containsKey(tmp[0])) {
                    result = algorithmHashMap.get(tmp[0]).run(result, Integer.valueOf(tmp[1]));
                }
            }
        }
        if(result != null) {
            // compress
            result = new Compress().run(result, 90);

            resp.getOutputStream().write(result.getImageData());
        }
        else {
            resp.setStatus(500);
        }
    }
}
