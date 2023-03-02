package AdminPage;

import FrameworkPack.TestBaseAdmin;
import ScreensPack.GajiHomePage;
import ScreensPack.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class testLogin extends TestBaseAdmin {

    @Test()
    @Parameters({ "mobileNumber"})
    public void loginTest(String mobileNumber) throws InterruptedException {
        System.out.println("started login test");
        LoginPage login = new LoginPage();
        GajiHomePage gaji = new GajiHomePage();
        gaji.clickLoginBTn().enterMobileNumber(mobileNumber);

        String expected = "Login";
        String actual = getTitleText();
        verifyResult(actual, expected);

        login.clickSMSLink();
        Thread.sleep(2000);


        String expected2 = " ini tidak ada di database kami. Cek ulang nomor yang Anda masukkan atau klik Cek Data Anda supaya kami bisa lakukan pengecekan.";
        String actual2 = login.getElementText();
        verifyTrue(actual2, expected2);

        System.out.println("Successfully Clicked on sms");

   }
}
