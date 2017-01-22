package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by nazdrik on 15.01.2017 at 12:45.
 */
public class HomePage extends AbstractPage {

    private static WebElement element = null;

    public static WebElement avatar(WebDriver driver){

        element = driver.findElement(By.className("user-avatar"));
        return element;

    }

    public static WebElement lnk_logOut(WebDriver driver){

        element = driver.findElement(By.linkText("Sign Out"));
        return element;

    }

    @FindBy(className = "user-avatar")
    private WebElement userAvatar;

    @FindBy(linkText = "Sign Out")
    private WebElement signOutLink;

    @FindBy(linkText = "Featured Items Feed")
    private WebElement homePageTitle;

    public void clickAnAvatar(){
        userAvatar.click();
    }

    public void clickLogOut(){
        signOutLink.click();
    }

    public void logOut(){
        clickAnAvatar();
        clickLogOut();
    }

    public String getTitle() {
        return homePageTitle.getText();
    }

}

