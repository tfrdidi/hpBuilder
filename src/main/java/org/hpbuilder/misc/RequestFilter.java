package org.hpbuilder.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by didi on 13.10.2015.
 */
public class RequestFilter {

    private static final Logger log = LoggerFactory.getLogger(RequestFilter.class);
    public static String ACCEPT = "Accept";

    public static String getRequestType(HttpServletRequest request) {

        String acceptHeader = null;
        String key;

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            key = (String) headerNames.nextElement();
            if(ACCEPT.equals(key)) {
                acceptHeader = request.getHeader(key);
            }
        }

        log.info(acceptHeader, acceptHeader);

        // get first element
        if(acceptHeader != null) {
            String primaryAcceptType = acceptHeader.substring(0, acceptHeader.indexOf(","));
            log.info(primaryAcceptType, primaryAcceptType);
        }



        return null;
    }
}
