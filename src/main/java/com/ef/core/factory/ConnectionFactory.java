package com.ef.core.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class ConnectionFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionFactory.class);

    public static Connection getConnection() {

        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("connection.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            return DriverManager.getConnection(
                    "jdbc:mysql://"+ properties.getProperty("db.host") +":"+ properties.getProperty("db.port") + "/" + properties.getProperty("db.name") + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false",
                    properties.getProperty("db.user"),
                    properties.getProperty("db.pwd"));
        } catch (SQLException e) {
            LOGGER.error("Error on try to connect to database", e);
        } catch (IOException e) {
            LOGGER.error("Error on try to read properties file", e);
        }
        return null;
    }

}
