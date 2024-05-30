package com.isaac;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GoogleHomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public GoogleHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void navigateTo() {
        driver.get("https://www.google.com");
    }

    public void performSearch(String query) {
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchInput.sendKeys(query);
        searchInput.submit();
        driver.navigate().back();
    }

    public void checkSearchHistory() throws InterruptedException {
        driver.findElement(By.name("q")).click();
        Thread.sleep(1000L);

        List<WebElement> searchHistory = driver.findElements(By.xpath("//*[@id=\"c7mM1c\"]/div[1]/span"));
        searchHistory.forEach(search -> System.out.println(search.getText()));
    }
}
