import com.sun.org.glassfish.gmbal.Description;
import org.apache.commons.logging.Log;
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
import static org.testng.Assert.assertTrue;


/**
 * Created by nazdrik on 14.01.2017 at 18:39.
 */
public class LogInLogOutTest  extends TestBase {


    private static Logger Log = Logger.getLogger(Log.class.getName());

    @BeforeMethod
    public void setUp() throws Exception {

        DOMConfigurator.configure("log4j.xml");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://designcarta.com");
    }


    @Description("Enter correct data")
    @Test (priority = 1, alwaysRun = true)
    public void loginTest(){

        login.loginIntoTheApp(Constants.Username, Constants.Password);
        login.sleep();
        assertTrue(home.getTitle().contains("Featured Items Feed"));
        Log.info("User logs in successfully, PASS");
    }

    @Description("Enter only log in")
    @Test (priority = 2, alwaysRun = true)
    public void loginOnlyWithEmail() {
        Log.info("Trying to login using only login");
        login.loginIntoTheApp(Constants.Username, "");
        login.sleep();
        assertThat("Login button is disabled", login.loginButtonIsDisabled(), is(true)) ;
        Log.info("Login button is disabled, PASS");
    }

    @Description("Enter only password")
    @Test (priority = 3, alwaysRun = true)
    public void loginOnlyWithPassword(){
        Log.info("Trying to login using only password");
        login.loginIntoTheApp("", Constants.Password);
        login.sleep();
        assertThat("Login button is disabled", login.loginButtonIsDisabled(), is(true)) ;
        Log.info("Login button is disabled, PASS");
    }

    @Description("Login with incorrect username format")
    @Test (priority = 4, alwaysRun = true)
    public void loginWithIncorrectUsernameFormat(){
        Log.info("Trying to login using incorrect username format");
        login.loginIntoTheApp(Constants.InvalidUsernameFormat, "");
        login.sleep();
        assertThat("The email address is not valid or in the wrong format", login.getErrorMessage().contains("The email address is not valid or in the wrong format"));
        Log.info("The email address is not valid or in the wrong format, PASS");
    }

    @Description("Login with incorrect username")
    @Test (priority = 5, alwaysRun = true)
    public void loginWithIncorrectUserName(){
        Log.info("Trying to login using incorrect username");
        login.loginIntoTheApp(Constants.InvalidUsername, Constants.Password);
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return login.getPopupErrorMessage();
                } catch (NoSuchElementException e) {
                    return false;
                }
            }
        });
        assertThat("Incorrect login or password.", login.getPopupErrorMessage(), is(true) );
        Log.info("Incorrect login , PASS");
    }

    @Description("Login with incorrect password")
    @Test (priority = 6, alwaysRun = true)
    public void loginWithIncorrectPassword(){
        Log.info("Trying to login using incorrect password");
        login.loginIntoTheApp(Constants.Username, Constants.InvalidPassword);
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return login.getPopupErrorMessage();
                } catch (NoSuchElementException e) {
                    return false;
                }
            }
        });
        assertThat("Incorrect login or password.", login.getPopupErrorMessage(), is(true));
        Log.info("Incorrect password. , PASS");
    }

    @Description("Login and Log out test")
    @Test (priority = 7, alwaysRun = true)
    public void logInLogOut() throws Exception {
        Log.info("Log in - log out check ");
        login.loginIntoTheApp(Constants.Username, Constants.Password);
        login.sleep();
        Log.info("Login successfully, PASS");
        assertTrue(home.getTitle().contains("Featured Items Feed"));
        home.logOut();
        Log.info("Log out successfully, PASS");

    }

}



