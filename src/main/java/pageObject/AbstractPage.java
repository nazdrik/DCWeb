package pageObject;

import org.openqa.selenium.WebDriver;

/**
 * Created by nazdrik on 17.01.2017 at 20:24.
 */
public abstract class AbstractPage {
    public WebDriver driver;

    public void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void refreshPage() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();
    }


    public void back(){
        driver.navigate().back();
    }

}
