package tests_ui;

import config.AppiumConfig;
import dto.UserDtoLombok;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactScreen;
import screens.DatePickerScreen;
import screens.SplashScreen;

public class DatePickerTests extends AppiumConfig {
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
        contactScreen.goToDatePicker();

    }

    @Test
    public void datePickerPositiveTest() {
        DatePickerScreen datePickerScreen
                = new DatePickerScreen(driver);
        datePickerScreen.typeDate("20 March 2026");

    }
}
