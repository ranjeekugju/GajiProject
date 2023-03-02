package ScreensPack;

import FrameworkPack.TestBaseAdmin;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends TestBaseAdmin {

    @FindBy(xpath = "//input")
    public WebElement mobileEnter;

    @FindBy(xpath = "//div[contains(text(),'SMS')]")
    public WebElement smsLink;

    @FindBy(xpath = "//div[@id='moe-push-div']/div[@id='desktopBannerWrapped']")
    public WebElement popUp;

    @FindBy(xpath = "//div[contains(text(),'Sepertinya, nomor HP ')]")
    public WebElement notFoundText;

    public LoginPage enterMobileNumber(String s) {
        mobileEnter.sendKeys(s);
        return this;
    }

    public LoginPage clickSMSLink() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("inside  click on sms btn");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", smsLink);
        Thread.sleep(500);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", smsLink);

//        System.out.println("inside  click on sms btn");
        System.out.println("trying to click sms link");

        return this;
    }

    public String getElementText (){
        String text =getElementText(notFoundText);
        return text;
    }


}
