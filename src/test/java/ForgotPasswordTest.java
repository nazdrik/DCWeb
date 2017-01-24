import com.sun.org.glassfish.gmbal.Description;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.Constants;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by alexey on 24/01/17.
 */
public class ForgotPasswordTest extends TestBase {

    private static Logger Log = Logger.getLogger(org.apache.commons.logging.Log.class.getName());

    @BeforeMethod
    public void setUp() throws Exception {

        DOMConfigurator.configure("log4j.xml");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://designcarta.com");
    }

    @Description("Reset password functionality checking")
    @Test(priority = 1, alwaysRun = true)
    public void resetPasswordTest(){
        Log.info("Trying to reset password");
        login.clickForgotPasswordLink();
        login.sleep();
        forgotPassword.enterEmail(Constants.Username);
        forgotPassword.clickResetPasswordButton();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return forgotPassword.getSuccessMessage();
                } catch (NoSuchElementException e) {
                    return false;
                }
            }
        });
        assertThat("Please check your email or text messages for password reset information", forgotPassword.getSuccessMessage(), is(true));
        Log.info("Please check your email or text messages for password reset information , PASS");


    }

    @Description("Trying to reset password with incorrect/ not existing email ")
    @Test(priority = 1, alwaysRun = true)
    public void resetPasswordWithIncorrectEmailTest(){
        Log.info("Trying to reset password using not existing/ incorrect email");
        login.clickForgotPasswordLink();
        login.sleep();
        forgotPassword.enterEmail(Constants.InvalidUsername);
        forgotPassword.clickResetPasswordButton();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return forgotPassword.getPopupErrorMessage();
                } catch (NoSuchElementException e) {
                    return false;
                }
            }
        });
        assertThat( forgotPassword.getPopupErrorMessage(), is(true));
        Log.info("Ops something went wrong , PASS");


    }

}
