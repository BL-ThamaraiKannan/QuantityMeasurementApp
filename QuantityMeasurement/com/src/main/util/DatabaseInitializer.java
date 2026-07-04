package com.src.main.util;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void init() {

        String sql = """
            CREATE TABLE IF NOT EXISTS quantity_measurement (
                id INT AUTO_INCREMENT PRIMARY KEY,
                operation VARCHAR(50),
                result VARCHAR(255),
                error VARCHAR(255),
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
        """;

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (Exception e) {
            throw new RuntimeException("Table creation failed", e);
        }
    }
}
