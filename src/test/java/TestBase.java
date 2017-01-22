import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import pageObject.ForgotPasswordPage;
import org.testng.annotations.BeforeMethod;
import pageObject.HomePage;
import pageObject.LoginPage;

/**
 * Created by nazdrik on 17.01.2017 at 20:21.
 */
public class TestBase {

    public WebDriver driver;
    public LoginPage login;
    public HomePage home;
    public ForgotPasswordPage forgotPassword;


    @BeforeMethod
    public void beforeAllTests() {
        driver = new FirefoxDriver();
        login = PageFactory.initElements(driver, LoginPage.class);
        home = PageFactory.initElements(driver, HomePage.class);
        forgotPassword = PageFactory.initElements(driver, ForgotPasswordPage.class);
    }

    @AfterMethod
    public void tearDown() throws Exception {

        driver.quit();

    }
}