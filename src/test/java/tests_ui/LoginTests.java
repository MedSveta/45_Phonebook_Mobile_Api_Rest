package tests_ui;

import config.AppiumConfig;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactScreen;
import screens.ErrorScreen;
import screens.SplashScreen;

import static helpers.RandomUtils.*;

public class LoginTests extends AppiumConfig {

    AuthenticationScreen authenticationScreen;

    @BeforeMethod
    public void openLoginForm() {
        new SplashScreen(driver).goToAuthScreen(6);
        authenticationScreen = new AuthenticationScreen(driver);
    }

    @Test
    public void loginPositiveTest() {
        UserDtoLombok user = UserDtoLombok.
                builder()
                .username("s3se6py31a@mail.com")
                .password("Poiuyt123!")
                .build();
        authenticationScreen.typeAuthenticationForm(user);
        authenticationScreen.clickBtnLogin();
        Assert.assertTrue(new ContactScreen(driver).validateHeader());
    }

    @Test
    public void loginNegativeTestUnregisteredWrongEmail() {
        UserDtoLombok user = UserDtoLombok.
                builder()
                .username(generateEmail(10))
                .password("Poiuyt123!")
                .build();
        authenticationScreen.typeAuthenticationForm(user);
        authenticationScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage
                        ("Login or Password incorrect", 5));
    }

    @Test
    public void loginNegativeTestWrongPassword() {
        UserDtoLombok user = UserDtoLombok.
                builder()
                .username("s3se6py31a@mail.com")
                .password("Podfgk123!")
                .build();
        authenticationScreen.typeAuthenticationForm(user);
        authenticationScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage
                        ("Login or Password incorrect", 5));
    }

}
