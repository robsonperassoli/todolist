package br.com.robsonperassoli.todo.functional;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class IndexPageTest extends FunctionalTests {

    @Test
    public void testTitleExists() throws InterruptedException {
        getDriver().navigate()
                .to(getRootUrl());

        List<WebElement> titleElements = getDriver().findElements(By.cssSelector("h3.text-muted"));

        assertEquals("TODO", titleElements.get(0).getText());
    }

}
