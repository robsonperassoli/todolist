package br.com.robsonperassoli.todo.functional;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexPageTest {

    @LocalServerPort
    private Integer serverPort;

    private WebDriver driver;

    @Before
    public void openBrowser() {
        if (System.getProperty("phantomjs.binary.path") != null) {
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setJavascriptEnabled(true);
            driver = new PhantomJSDriver(dc);
        } else {
            throw new RuntimeException("Invalid PhantomJS path.");
        }
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void testTitleExists() throws InterruptedException {
        driver.navigate().to("http://localhost:" + serverPort);

        List<WebElement> titleElements = driver.findElements(By.cssSelector("h3.text-muted"));

        assertEquals("TODO", titleElements.get(0).getText());
    }

}
