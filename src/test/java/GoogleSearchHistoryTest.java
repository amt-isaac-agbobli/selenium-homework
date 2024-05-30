
import com.isaac.GoogleHomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
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
    void googleSearch() {
        try {
            googleHomePage.navigateTo();
            googleHomePage.performSearch("selenium");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Google Search history")
    void googleSearchHistoryTest() {
        try {
            googleHomePage.navigateTo();

            Arrays.asList("selenium", "webdriver", "xpath").forEach(googleHomePage::performSearch);

            driver.navigate().refresh();

            googleHomePage.navigateToHistory();

            boolean isSeleniumPresent = googleHomePage.isTextInHistory("selenium");
            Assert.assertTrue(isSeleniumPresent, "Selenium is not in the search history");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    void closeBrowser() {
        driver.close();
    }
}
