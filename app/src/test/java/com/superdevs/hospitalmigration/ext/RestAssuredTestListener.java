package com.superdevs.hospitalmigration.ext;

import io.restassured.RestAssured;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import java.util.Objects;

public class RestAssuredTestListener implements TestExecutionListener {
    @Override
    public void beforeTestClass(TestContext testContext) {
        var environment = testContext.getApplicationContext().getEnvironment();
        var portAsString = Objects.requireNonNull(environment.getProperty("local.server.port"));
        RestAssured.port = Integer.parseInt(portAsString);
    }
}
