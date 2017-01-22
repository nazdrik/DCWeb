package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.By.xpath;

/**
 * Created by nazdrik on 14.01.2017 at 18:01.
 */
public class LoginPage extends AbstractPage{


    @FindBy(xpath ="//input[@name='email']")
    private WebElement userLogin;

    @FindBy(xpath = "//form[@name='loginForm']/div[1]/p[1]")
    private WebElement invalidEmailErrorMessage;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement userPassword;

    @FindBy(xpath ="//input[@name='commit']")
    private WebElement loginButton;

    @FindBy(partialLinkText = "Forgot Password?")
    private WebElement forgotPasswordLink;

    @FindBy(className = "login-link")
    private WebElement requestAnAccount;

    @FindBy(className = "toast-error")
    private WebElement popupError;

    public void enterLogin(String login){
        userLogin.sendKeys(login);
    }

    public void enterPassword(String password){
        userPassword.sendKeys(password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public String getErrorMessage(){
        return invalidEmailErrorMessage.getText();
    }

    public Boolean getPopupErrorMessage(){
        return  popupError.isDisplayed();
    }

    public Boolean loginButtonIsDisabled(){
       return loginButton.isDisplayed();
    }

    public void loginIntoTheApp(String login, String password){
        enterLogin(login);
        enterPassword(password);
        clickLoginButton();
    }
}