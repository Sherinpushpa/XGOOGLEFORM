package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

   Wrappers wrapper;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */
         
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @Test
    public void testCase01() throws InterruptedException {
        wrapper = new Wrappers(driver);
        //Open the URL
        wrapper.openUrl("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        Thread.sleep(2000);
        wrapper.enterText(By.xpath("//div[@class='AgroKb']//input[@type='text']"), "Crio Learner");
        Thread.sleep(1000);

        wrapper.enterText(By.xpath("//textarea[@aria-label='Your answer']"), "I want to be the best QA Engineer! " + wrapper.epochTime());
        Thread.sleep(1000);

        wrapper.experienceRating(By.xpath("//div[contains(@class,'nWQGrd')]//div[@data-value='> 10']"));
        Thread.sleep(1000);

        wrapper.scrollToElement(By.xpath("//div[contains(@class,'nWQGrd')]//div[@data-value='> 10']"));
        Thread.sleep(1000);

        wrapper.clickElement(By.xpath("//div[@aria-label='Java']"));

        wrapper.clickElement(By.xpath("//div[@aria-label='Selenium']"));

        wrapper.clickElement(By.xpath("//div[@aria-label='TestNG']"));
        Thread.sleep(1000);

        wrapper.clickElement(By.xpath("(//div[@role='listbox'])[1]"));
        Thread.sleep(1000);

        wrapper.dropDown(By.xpath("//div[@role='option']/span"));
        Thread.sleep(1000);

        wrapper.enterText(By.xpath("//input[@type='date']"), wrapper.dateCalculator());
        Thread.sleep(1000);

        wrapper.enterText(By.xpath("//input[@aria-label='Hour']"), "07");
        wrapper.enterText(By.xpath("//input[@aria-label='Minute']"), "30");

        wrapper.clickElement(By.xpath("//span[text()='Submit']"));

        wrapper.successMessage(By.xpath("//div[text()='Thanks for your response, Automation Wizard!']"));
        
        Thread.sleep(5000);

    }


    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}