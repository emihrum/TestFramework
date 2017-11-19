package com.liligo.test.end_to_end.steps;

import org.junit.runner.RunWith;

import net.serenitybdd.cucumber.CucumberWithSerenity;

import cucumber.api.CucumberOptions;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = { "classpath:features" }, 
        glue = { "com/liligo/test/end_to_end/steps" }, 
        strict = true, 
        monochrome = true, 
        plugin = { "pretty" }
        )
public class TestRunner {

}
