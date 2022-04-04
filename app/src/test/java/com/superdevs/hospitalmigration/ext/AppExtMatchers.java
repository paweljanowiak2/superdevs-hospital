package com.superdevs.hospitalmigration.ext;

import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;

import java.util.UUID;

public class AppExtMatchers {
    // normally move to separate class, limited time here
    public static Matcher<String> isAnUUID() {
        return new DiagnosingMatcher<>() {
            @Override
            protected boolean matches(Object item, Description mismatchDescription) {
                if (item instanceof String aItemString) {
                    try {
                        //noinspection ResultOfMethodCallIgnored
                        UUID.fromString(aItemString);
                        return true;
                    } catch (Exception ignored) {
                        mismatchDescription.appendValue(item).appendText(" is not");
                    }
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("an uuid value ");
            }
        };
    }
}
