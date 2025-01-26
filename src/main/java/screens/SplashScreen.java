package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SplashScreen extends BaseScreen{

    public SplashScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.sheygam.contactapp:id/version_text")
    AndroidElement versionApp;


    public boolean validateVersionApp(){
        return versionApp.getText().equals("Version 1.0.0");
    }

    public void goToAuthScreen(int time){
        try{
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions
                            .presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView")));
        } catch (Exception e) {
            System.out.println("element Auth not find");
        }
    }
}
