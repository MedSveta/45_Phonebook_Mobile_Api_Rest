package screens;

import dto.UserDtoLombok;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class AuthenticationScreen extends BaseScreen {

    public AuthenticationScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.sheygam.contactapp:id/inputEmail")
    AndroidElement inputEmail;
    @FindBy(id = "com.sheygam.contactapp:id/inputPassword")
    AndroidElement inputPassword;
    @FindBy(id = "com.sheygam.contactapp:id/regBtn")
    AndroidElement btnRegistration;
    @FindBy(id = "com.sheygam.contactapp:id/loginBtn")
    AndroidElement btnLogin;
    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView")
    AndroidElement textAuthentication;

    public void typeAuthenticationForm(UserDtoLombok user) {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickBtnRegistration() {
        btnRegistration.click();
    }

    public void clickBtnLogin() {
        btnLogin.click();
    }
    public boolean isAuthScreenOpen(){
        return textInElementPresent(textAuthentication, "Authentication", 3);
    }

}
