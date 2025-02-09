package tests_ui;

import config.AppiumConfig;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactScreen;
import screens.SplashScreen;

public class LogoutTests extends AppiumConfig {
    UserDtoLombok user = UserDtoLombok.
            builder()
            .username("s3se6py31a@mail.com")
            .password("Poiuyt123!")
            .build();
    ContactScreen contactScreen;

    @BeforeMethod
    public void openLoginAndOpenContactScreen() {
        new SplashScreen(driver).goToAuthScreen(6);
        AuthenticationScreen authenticationScreen = new AuthenticationScreen(driver);
        authenticationScreen.typeAuthenticationForm(user);
        authenticationScreen.clickBtnLogin();
        contactScreen = new ContactScreen(driver);
    }

    @Test
    public void logoutPositiveTest(){
        contactScreen.logout();
        Assert.assertTrue(new AuthenticationScreen(driver)
                .isAuthScreenOpen());

    }
}
