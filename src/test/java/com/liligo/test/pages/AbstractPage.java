package com.liligo.test.pages;

import org.springframework.stereotype.Component;

import net.serenitybdd.core.pages.PageObject;

@Component
public abstract class AbstractPage extends PageObject {
    public void openUrl(String url) {
        getDriver().get(url);
    }
}
