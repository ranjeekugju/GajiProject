package ScreensPack;

import ScreensPack.LoginPage;
import FrameworkPack.TestBaseAdmin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class GajiHomePage extends TestBaseAdmin {

    @FindBy(xpath = "//img[@alt='logo GajiGesa']")
    public WebElement gajiLogo;

    @FindBy(xpath = "/html/body/div[1]/section/div/div[1]/div/div/div/a/img")
    public WebElement gajigesaLogo;
    @FindBy(xpath = "//ul[@id='menu-1-cee6252']//a[contains(text(),'Login')]")
    public WebElement loginBtn;
    @FindBy(xpath = "//ul[@id='menu-1-cee6252']//a[contains(text(),'Contact Us')]")
    public WebElement contactUS;

    public LoginPage clickLoginBTn() throws InterruptedException {
        Thread.sleep(3000);

        String expected = "Employee Wellness Platform - GajiGesa";
        String actual = getTitleText();
        verifyResult(actual, expected);

        System.out.println("Inside click on login btn");
//        clickElementSelenium(loginBtn);
        click(loginBtn);

        return new LoginPage();
    }

    public LoginPage clickContactUsBTn() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Inside contact us  btn");
        clickElementSelenium(contactUS);
        return new LoginPage();
    }



}
