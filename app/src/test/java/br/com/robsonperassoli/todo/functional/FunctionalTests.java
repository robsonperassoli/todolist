package br.com.robsonperassoli.todo.functional;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FunctionalTests {

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

    public WebDriver getDriver() {
        return driver;
    }

    public String getRootUrl() {
        return "http://localhost:".concat(serverPort.toString());
    }

}
