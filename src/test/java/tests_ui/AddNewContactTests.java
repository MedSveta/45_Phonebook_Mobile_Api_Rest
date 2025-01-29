package tests_ui;

import config.AppiumConfig;
import dto.ContactDtoLombok;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import static helpers.RandomUtils.*;

public class AddNewContactTests extends AppiumConfig {
    UserDtoLombok user = UserDtoLombok.
            builder()
            .username("s3se6py31a@mail.com")
            .password("Poiuyt123!")
            .build();
    AuthenticationScreen authenticationScreen;
    ContactScreen contactScreen;
    AddNewContactScreen addNewContactScreen;

    @BeforeMethod
    public void openLoginAndOpenContactScreen() {
        new SplashScreen(driver).goToAuthScreen(6);
        authenticationScreen = new AuthenticationScreen(driver);
        authenticationScreen.typeAuthenticationForm(user);
        authenticationScreen.clickBtnLogin();
        contactScreen = new ContactScreen(driver);
        contactScreen.clickBtnAddNewContact();
        addNewContactScreen = new AddNewContactScreen(driver);

    }

    @Test
    public void addNewContactPositiveTest() {
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("@@@" + generateString(4))
                .lastName(generateString(7))
                .email(generateEmail(10))
                .phone(generatePhone(10))
                .address(generateString(8))
                .description(generateString(20))
                .build();
        addNewContactScreen.typeContactForm(contact);
        addNewContactScreen.clickBtnCreateContact();
        Assert.assertTrue(new ContactScreen(driver).validatePopMessage());
    }

    @Test
    public void addNewContactNegativeTest_EmptyFieldLastName() {
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("@@@" + generateString(4))
                .lastName("")
                .email(generateEmail(10))
                .phone(generatePhone(10))
                .address(generateString(8))
                .description(generateString(20))
                .build();
        addNewContactScreen.typeContactForm(contact);
        addNewContactScreen.clickBtnCreateContact();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("lastName=must not be blank"
                , 5));
    }
}
