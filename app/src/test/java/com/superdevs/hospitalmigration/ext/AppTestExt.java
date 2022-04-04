package com.superdevs.hospitalmigration.ext;

import org.springframework.jdbc.core.JdbcTemplate;

import static com.superdevs.hospitalmigration.ext.TestContextHolder.getBean;

public class AppTestExt {
    public static void clearDb() {
        var jdbcTemplate = getBean(JdbcTemplate.class);
        jdbcTemplate.execute("TRUNCATE TABLE staff_member");
        jdbcTemplate.execute("TRUNCATE TABLE patient");
    }
}
