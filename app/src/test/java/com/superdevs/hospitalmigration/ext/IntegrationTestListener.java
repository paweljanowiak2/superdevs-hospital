package com.superdevs.hospitalmigration.ext;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import static com.superdevs.hospitalmigration.ext.AppTestExt.clearDb;

public class IntegrationTestListener implements TestExecutionListener {
    @Override
    public void afterTestExecution(TestContext testContext) {
        var integrationTest = testContext.getTestClass().getAnnotation(IntegrationTest.class);
        var shouldSkipClearDb = integrationTest.skipClearDb();
        if (!shouldSkipClearDb) {
            clearDb();
        }
    }
}
