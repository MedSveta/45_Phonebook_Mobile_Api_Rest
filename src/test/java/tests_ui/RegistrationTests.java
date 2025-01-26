package tests_ui;

import config.AppiumConfig;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactScreen;
import screens.ErrorScreen;
import screens.SplashScreen;

import static helpers.RandomUtils.*;

public class RegistrationTests extends AppiumConfig {


    @Test
    public void registrationPositiveTest(){
        UserDtoLombok user = UserDtoLombok.builder()
                .username(generateEmail(10))
                .password("Poiuyt123!")
                .build();
        new SplashScreen(driver).goToAuthScreen(6);
        AuthenticationScreen authenticationScreen = new AuthenticationScreen(driver);
        authenticationScreen.typeAuthenticationForm(user);
        authenticationScreen.clickBtnRegistration();
        Assert.assertTrue(new ContactScreen(driver).validateHeader());
    }

    @Test
    public void registrationNegativeTest_WrongEmail(){
        UserDtoLombok user = UserDtoLombok.builder()
                .username(generateString(10))
                .password("Poiuyt123!")
                .build();
        new SplashScreen(driver).goToAuthScreen(6);
        AuthenticationScreen authenticationScreen = new AuthenticationScreen(driver);
        authenticationScreen.typeAuthenticationForm(user);
        authenticationScreen.clickBtnRegistration();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("username=must be a well-formed email address"
                        , 5));
    }

    @Test
    public void registrationNegativeTest_WrongPassword(){
        UserDtoLombok user = UserDtoLombok.builder()
                .username(generateEmail(10))
                .password("Poiuyt123")
                .build();
        new SplashScreen(driver).goToAuthScreen(6);
        AuthenticationScreen authenticationScreen = new AuthenticationScreen(driver);
        authenticationScreen.typeAuthenticationForm(user);
        authenticationScreen.clickBtnRegistration();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("password= At least 8 characters; " +
                                "Must contain at least 1 uppercase letter" +
                                ", 1 lowercase letter, " +
                                "and 1 number; " +
                                "Can contain special characters [@$#^&*!]"
                        , 5));
    }
}
