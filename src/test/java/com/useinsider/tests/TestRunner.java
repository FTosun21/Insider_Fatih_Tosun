package com.useinsider.tests;

import org.testng.TestNG;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    @Test
    public void testRunner() {
        TestNG testng = new TestNG();
        List<String> suites = new ArrayList<>();
        suites.add("testRunner.xml");
        testng.setTestSuites(suites);
        testng.run();
    }
}
