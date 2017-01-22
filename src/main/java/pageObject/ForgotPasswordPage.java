package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by nazdrik on 16.01.2017 at 21:09.
 */
public class ForgotPasswordPage extends AbstractPage {

    @FindBy(xpath = "//form[@name='forgotPasswordForm']/div[1]/input[1]")
    private WebElement emailAddress;

    @FindBy(xpath = "//input[@name='commit']")
    private WebElement resetPasswordButton;

    @FindBy(className = "login-link")
    private WebElement loginLink;

    public void enterEmail(String email) {
        emailAddress.sendKeys(email);
    }

    public void clickResetPasswordButton(){
        resetPasswordButton.click();
    }

    public void clickLoginButton(){
        loginLink.click();
    }

    public void resertPassword(String email){
        enterEmail(email);
        clickResetPasswordButton();
    }



}
