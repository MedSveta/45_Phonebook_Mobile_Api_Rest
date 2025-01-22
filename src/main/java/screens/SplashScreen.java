package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class SplashScreen extends BaseScreen{

    public SplashScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.sheygam.contactapp:id/version_text")
    AndroidElement versionApp;

    public boolean validateVersionApp(){
        return versionApp.getText().equals("Version 1.0.0");
    }
}
