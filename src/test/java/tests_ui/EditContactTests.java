package tests_ui;

import config.AppiumConfig;
import dto.ContactDtoLombok;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import static helpers.RandomUtils.*;
import static helpers.RandomUtils.generateString;

public class EditContactTests extends AppiumConfig {
    UserDtoLombok user = UserDtoLombok.
            builder()
            .username("s3se6py31a@mail.com")
            .password("Poiuyt123!")
            .build();

    ContactDtoLombok contact = ContactDtoLombok.builder()
            .name("$$$" + generateString(4))
            .lastName("$$$"+generateString(7))
            .email("$$$"+generateEmail(10))
            .phone(generatePhone(10))
            .address(generateString(8))
            .description(generateString(20))
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
    public void editContactPositiveTest(){
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("$$$" + generateString(4))
                .lastName("$$$"+generateString(7))
                .email("$$$"+generateEmail(10))
                .phone(generatePhone(10))
                .address(generateString(8))
                .description(generateString(20))
                .build();
        contactScreen.goToEditScreen();
        EditContactScreen editContactScreen = new EditContactScreen(driver);
        editContactScreen.typeEditContactForm(contact);
        Assert.assertTrue(new ContactScreen(driver)
                .validatePopMessage("Contact was updated!"));
    }
    @Test
    public void editContactNegativeTest(){
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("")
                .lastName("$$$"+generateString(7))
                .email("$$$"+generateEmail(10))
                .phone(generatePhone(10))
                .address(generateString(8))
                .description(generateString(20))
                .build();
        contactScreen.goToEditScreen();
        EditContactScreen editContactScreen = new EditContactScreen(driver);
        editContactScreen.typeEditContactForm(contact);
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("name=must not be blank",3));
    }
}
