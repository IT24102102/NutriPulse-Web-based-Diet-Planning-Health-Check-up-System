package com.healthpath.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseTest implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM sys.tables", Integer.class);
            System.out.println("Connected! Number of tables in DB: " + count);
        } catch (Exception e) {
            System.err.println("DB connection failed: " + e.getMessage());
        }
    }
}
