package com.hexaware.util;

import com.hexaware.exception.DbConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    static {
        try{
            Class.forName(DbProperties.getDriver());
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public static Connection getDbConnection() throws DbConnectionException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DbProperties.getDbUrl(), DbProperties.getProps());
        } catch (SQLException e) {
            System.out.println();
            e.printStackTrace();
            throw new DbConnectionException(HexaConstants.CANNOT_OPEN_CONNECTION, e);
        }
        return conn;
    }
    public static void closeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            }catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

    }


}
