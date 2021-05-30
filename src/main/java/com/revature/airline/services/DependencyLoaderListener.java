package com.revature.airline.services;

import eorm.exceptions.DBConnectionException;
import eorm.utils.ConnectionFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;

/**
 * This class is tied to the startup and shutdown of tomcat. Just implement
 *      the ServletContextListener and put whatever logic into the overridden
 *      methods. Make sure you inform tomcat of this class by including it
 *      in your deployment descriptor (web.xml) under the listener tag.
 */
public class DependencyLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Connection conn;

        {
            try {
                conn = ConnectionFactory.getConnection ("project0.cksippr4cmc5.us-east-1.rds.amazonaws.com", 5432, "postgres", "project1", "jfallon", "revature", "org.postgresql.Driver");
            } catch (DBConnectionException e) {
                e.printStackTrace ();
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

