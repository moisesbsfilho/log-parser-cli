package com.ef.core.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionFactory.class);

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/wallet_hub?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
        } catch (SQLException e) {
            LOGGER.error("Error on try to connect to database", e);
        }
        return null;
    }

}
