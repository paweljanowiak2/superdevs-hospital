package com.superdevs.hospitalmigration;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class PatientCsvTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        //noinspection SqlWithoutWhere
        jdbcTemplate.execute("delete from staff_member");
    }

    @Test
    void name() {
        var response = given()
                .header("x-staff-uuid", App.INITIAL_SECURITY_KEY)
                .body(createStaffMemberMap())
                .post("/api/staff-members");

        response.then().assertThat()
                        .body("id", is(1));

//        response.body().print()
//
//                .then()
//                        .statusCode(201);
//        when().request("GET", "/api/patients/1").then().statusCode(200);
//
//        RestAssured.get("/events?id=390").then()
//                .assertThat()
//                .body("odds.price", hasItems("1.30", "5.25"));
    }

    private Object createStaffMemberMap() {
        var map = new HashMap<>();
        map.put("name", "JohnDoe");
        map.put("registrationDate", "2020-02-02");

        return map;
    }
}
