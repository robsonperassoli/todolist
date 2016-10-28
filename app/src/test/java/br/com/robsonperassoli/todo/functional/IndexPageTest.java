package br.com.robsonperassoli.todo.functional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class IndexPageTest extends FunctionalTests {

    @Test
    public void testTitleExists() throws InterruptedException {
        getDriver().navigate()
                .to(getRootUrl());

        List<WebElement> titleElements = getDriver().findElements(By.cssSelector("h3.text-muted"));

        assertEquals("TODO", titleElements.get(0).getText());
    }

}
