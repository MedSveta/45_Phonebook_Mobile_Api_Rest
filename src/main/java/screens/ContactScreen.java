package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.FindBy;

public class ContactScreen extends BaseScreen {
    public ContactScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath
            = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView")
    AndroidElement headerContactScreen;
    @FindBy(id = "com.sheygam.contactapp:id/add_contact_btn")
    AndroidElement btnAddNewContact;
    @FindBy(xpath = "/hierarchy/android.widget.Toast")
    AndroidElement popUpMessage;
    @FindBy(id = "com.sheygam.contactapp:id/rowName")
    AndroidElement firstContact;
    @FindBy(id = "android:id/button1")
    AndroidElement popUpBtnYes;
    @FindBy(xpath = "//*[@text='Logout']")
    AndroidElement btnLogout;
    @FindBy(xpath = "//*[@text='Date picker']")
    AndroidElement btnDatePicker;
    @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    AndroidElement btnMoreOptions;


    int yLeftUpCorner = firstContact.getLocation().getY();
    int heightElement = firstContact.getSize().getHeight();
    int wightElement = firstContact.getSize().getWidth();
    TouchAction<?> touchAction = new TouchAction<>(driver);

    public void clickBtnYes() {
        clickWait(popUpBtnYes, 3);
    }

    public void goToDatePicker() {
        btnMoreOptions.click();
        clickWait(btnDatePicker, 5);
    }
    public void logout(){
        clickWait(btnMoreOptions, 3);
        clickWait(btnLogout, 3);
    }

    public void goToEditScreen() {
        System.out.println("y ->" + firstContact.getLocation().getY());
        System.out.println("x ->" + firstContact.getLocation().getX());
        System.out.println("h ->" + firstContact.getSize().getHeight());
        System.out.println("w ->" + firstContact.getSize().getWidth());


        touchAction.longPress(PointOption.point(wightElement / 6 * 5
                        , (yLeftUpCorner + heightElement / 2)))
                .moveTo(PointOption.point(wightElement / 6
                        , (yLeftUpCorner + heightElement / 2))).release().perform();
    }

    public void deleteContact() {
        touchAction.longPress(PointOption.point(wightElement / 6
                        , (yLeftUpCorner + heightElement / 2)))
                .moveTo(PointOption.point(wightElement / 6 * 5
                        , (yLeftUpCorner + heightElement / 2))).release().perform();
    }

    public boolean validateHeader() {
        return textInElementPresent(headerContactScreen, "Contact list", 5);
    }

    public void clickBtnAddNewContact() {
        clickWait(btnAddNewContact, 5);
    }

    public boolean validatePopMessage() {
        return textInElementPresent(popUpMessage, "Contact was added!", 5);
    }

    public boolean validatePopMessage(String s) {
        return textInElementPresent(popUpMessage, s, 5);
    }
}
