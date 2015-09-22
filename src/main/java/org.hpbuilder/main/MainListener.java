package org.hpbuilder.main;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by didi on 21.09.2015.
 */
public class MainListener implements ServletContextListener {

    final Logger log = LoggerFactory.getLogger(MainListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("");
    }
}
