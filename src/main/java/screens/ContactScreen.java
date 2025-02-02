package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
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

    public void goToEditScreen(){
        System.out.println("y ->"+ firstContact.getLocation().getY());
        System.out.println("x ->"+ firstContact.getLocation().getX());
        System.out.println("h ->"+ firstContact.getSize().getHeight());
        System.out.println("w ->"+ firstContact.getSize().getWidth());

        int yLeftUpCorner = firstContact.getLocation().getY();
        int heightElement = firstContact.getSize().getHeight();
    }

    public boolean validateHeader() {
        return textInElementPresent(headerContactScreen, "Contact list", 5);
    }

    public void clickBtnAddNewContact(){
        clickWait(btnAddNewContact, 5);
    }

    public boolean validatePopMessage(){
        return textInElementPresent(popUpMessage, "Contact was added!", 5);
    }
}
