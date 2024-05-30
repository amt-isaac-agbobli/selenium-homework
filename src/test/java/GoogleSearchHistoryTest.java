
import com.isaac.GoogleHomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoogleSearchHistoryTest {
    private WebDriver driver;
    private GoogleHomePage googleHomePage;

    @BeforeClass
    void setWebDriverManager() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--lang=eng");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", "./myDownloads");
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        googleHomePage = new GoogleHomePage(driver);
    }

    @BeforeMethod
    void clearCookies() {
        driver.manage().deleteAllCookies();
    }

    @Test(description = "Google Search")
    void googleSearch() throws InterruptedException {
        googleHomePage.navigateTo();
        googleHomePage.performSearch("selenium");
    }

    @Test(description = "Google Search history")
    void googleSearchHistoryTest() throws InterruptedException {
        googleHomePage.navigateTo();

        Arrays.asList("selenium", "webdriver", "xpath").forEach(googleHomePage::performSearch);

        List<WebElement> searchHistory = googleHomePage.checkSearchHistory();

        searchHistory.forEach(webElement -> System.out.println(webElement.getText()));

        searchHistory.stream()
                .map(WebElement::getText)
                .filter(text -> text.contains("selenium"))
                .forEach(System.out::println);

    }

    @AfterClass
    void closeBrowser() {
        driver.close();
    }
}
