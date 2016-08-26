package xyz.vangamo.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum BaseDB {
    INSTANCE;

    private Connection dbConnection = null;
    private static final String DB_CONNECTION_STRING = "jdbc:mariadb://localhost:3306/base?user=igarrido&password=TKMy1M";
    BaseDB() {
        }

    public Connection getConnection() {
        if( null == this.dbConnection ) {
/*
            try {
                Class.forName(DB_DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
*/
            try {
/*
                this.dbConnection = DriverManager.getConnection( DB_CONNECTION );
*/
                this.dbConnection = DriverManager.getConnection( DB_CONNECTION_STRING );
                }
            catch (SQLException e) {
                System.out.println(e.getMessage());
                }
            }
        return this.dbConnection;
        }
    }
