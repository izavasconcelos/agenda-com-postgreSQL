package com.izavasconcelos.coreengineering.tema10.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {

    private static final String url = "jdbc:postgresql://localhost:5432/db_tema10";
    private static final String user = "postgres";
    private static final String password = "Tema@10";
    private static Connection connect = null;

    private static Connection createConnection() {

        try {
            connect = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connect;
    }

    public static Connection getConnect() {
        if(connect == null){
            connect = createConnection();
        }
        return connect;
    }

}
