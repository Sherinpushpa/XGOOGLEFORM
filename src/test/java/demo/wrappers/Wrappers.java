package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    WebDriver driver;

    public Wrappers (WebDriver driver) {
        this.driver = driver;
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public void clickElement(By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
    }

    public void enterText(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
       // WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public long epochTime() {
        long epoch = System.currentTimeMillis()/1000;
        return epoch;
    }

    public void experienceRating(By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
    }

    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void dropDown(By locator) {
        List <WebElement> options = driver.findElements(locator);
        System.out.println(options.size());
        for (WebElement option : options) {
            if (option.getText().equals("Mrs")) {
                System.out.println(option.getText());
                option.click();
                break;
            }
        }
    }

    public String dateCalculator() {
        LocalDateTime date7DaysAgo = LocalDateTime.now().minusDays(7);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date7DaysAgo.format(format);
        return formattedDate;
    }

    public void successMessage(By locator) {
        WebElement element = driver.findElement(locator);
        System.out.println("Successful message : " + element.getText());
    }

}
