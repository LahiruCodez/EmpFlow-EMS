package com.company.ems.db;

import com.company.ems.util.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseUtils {
    private DatabaseUtils() {}

    /**
     * Check if the database is reachable by opening a connection and executing a small validation query.
     *
     * @return true if a connection and validation query succeed, false otherwise
     */
    public static boolean isDatabaseAvailable() {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) return false;
            String validationQuery = validationQueryFor(conn);
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(validationQuery)) {
                return rs.next();
            }
        } catch (SQLException e) {
            return false;
        }
    }

    private static String validationQueryFor(Connection conn) throws SQLException {
        String product = conn.getMetaData().getDatabaseProductName().toLowerCase();
        if (product.contains("oracle")) return "SELECT 1 FROM DUAL";
        return "SELECT 1";
    }
}
