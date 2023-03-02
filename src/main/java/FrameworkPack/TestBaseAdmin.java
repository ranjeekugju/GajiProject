package FrameworkPack;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBaseAdmin {

    public static WebDriver driver;
    public static Properties OR = new Properties();
    public static Properties Config = new Properties();
    public static FileInputStream fis;
    public static Logger log;
    public static Select select;
    public static WebDriverWait wait;
    public static Actions action;
    public static String win;

    public TestBaseAdmin() {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @BeforeSuite
    public void setUp() throws IOException {
//        Logger log = Logger.getLogger(TestBaseAdmin.class);
//        PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
        if (driver == null) {
            fis = new FileInputStream("./src/test/resources/properties/config.admin.properties");
            Config.load(fis);
//            log.info("Config.properties file loaded!");
            WebDriverManager.chromedriver().setup();
//            log.debug("Chrome browser launched!");
            System.out.println("Before Chrome driver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            // driver = new ChromeDriver(chromeOptions);
            driver = new ChromeDriver(options);
            driver.get(Config.getProperty("url"));
            System.out.println("After url");
//            log.info("Navigated to :" + Config.getProperty("url"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//            wait = new WebDriverWait(driver, 10);
        }
    }

    public void waitForVisibility(WebElement e) {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void waitForClickability(WebElement e){
        wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(e));

    }

    public static String getElementText(WebElement e) {
//        test.info("Returning the text::" + e.getText());
        return e.getText();
    }

    public static String getTitleText() {
//        test.info("Returning the text::" + e.getText());
        return driver.getTitle();
    }

    public static void verifyResult(String actual, String expected) {
        Assert.assertEquals(actual, expected);
//        test.info("Expected Result is::" + expected + " Actual Result is::" + actual);
    }

    public static void verifyTrue(String string1, String string2) {
        Assert.assertTrue(string1.contains(string2));
//        test.info("Expected Result is::" + string1 + "\nActual Result is::" + string2);
    }
    public void click(WebElement e) {
        try {
            waitForVisibility(e);
            waitForClickability(e);
//            test.info("Clicking on Element: " + e.getText());
            e.click();
        } catch(Exception ex) {
            e.click();
        }

    }

    public boolean present(WebElement e) {
        boolean flag = false;
        try {
            waitForVisibility(e);
            flag= true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    public boolean waitForElementVisible(WebElement element) {
        boolean flag = false;
        try {
//            WebDriverWait wait = new WebDriverWait(driver,pageElementLoadWait);
            wait.until(ExpectedConditions.visibilityOfAllElements(element));
            flag = true;
        }
        catch(Exception e){
            e.printStackTrace();

        }
        return flag;
    }

    public boolean waitForElementClickable(WebElement element) {
        boolean flag = false;
        try {
//            WebDriverWait wait = new WebDriverWait(driver, pageElementLoadWait);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public boolean navigateURL(String siteUrl) {
        boolean flag = false;
        try {
            driver.navigate().to(siteUrl);
            flag = true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }


    public boolean clickElementSelenium(WebElement element) {
        boolean flag = false;
        try {
            if (waitForElementClickable(element)) {
                element.click();
                flag= true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public void enterText(WebElement element,String Text) {
        waitForElementClickable(element);
        try {
            if (element != null ) {
                element.clear();
                element.sendKeys(Text);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean selectElementByText (WebElement element,String input) {
        waitForElementClickable(element);
        boolean flag= false;
        try {

            if(element != null) {
                Select dropdwn = new Select(element);
                dropdwn.selectByVisibleText(input);
                flag= true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }


    public boolean jsClick(WebElement element) {
        boolean flag = false;
        waitForElementClickable(element);
        try {

            JavascriptExecutor exe = (JavascriptExecutor) driver;
            exe.executeScript("arguments[0].click();", element);
            flag = true;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    public void submit(WebElement element) {
        boolean flag = false;
        waitForElementClickable(element);
        try {
            element.submit();
            flag = true;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
//		return flag;
    }


    @AfterSuite
    public void tearDown() {
        driver.quit();
    }

}
