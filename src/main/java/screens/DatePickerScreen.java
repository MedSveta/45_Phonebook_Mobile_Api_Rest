package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;

public class DatePickerScreen extends BaseScreen {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.getYear());
    }

    public DatePickerScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.sheygam.contactapp:id/dateBtn")
    AndroidElement btnChangeDate;
    @FindBy(id = "android:id/date_picker_header_year")
    AndroidElement btnYear;
    @FindBy(id = "android:id/prev")
    AndroidElement btnMonthPrev;
    @FindBy(id = "android:id/next")
    AndroidElement btnMonthNext;
    @FindBy(id = "android:id/button1")
    AndroidElement btnOk;
    @FindBy(id = "com.sheygam.contactapp:id/dateTxt")
    AndroidElement dateResult;

    public void typeDate(String date) {
        btnChangeDate.click();

        String[] arrayDate = date.split(" ");
        LocalDate localDate = LocalDate.now();
        //year==============================
        if (localDate.getYear() != Integer.parseInt(arrayDate[2]))
            btnYear.click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@text='" + arrayDate[2] + "']")))
                .click();
        btnMonthPrev.click(); // bug
        //month=========================================
        if (localDate.getMonth().getValue() != numberMonth(arrayDate[1])) {
            if (numberMonth(arrayDate[1]) > localDate.getMonth().getValue()) {
                int num = numberMonth(arrayDate[1]) - localDate.getMonth().getValue();
                for (int i = 0; i < num; i++) {
                    btnMonthNext.click();
                }
            } else {
                int num = localDate.getMonth().getValue() - numberMonth(arrayDate[1]);
                for (int i = 0; i < num; i++) {
                    btnMonthPrev.click();
                }
            }
        }
        driver.findElement(By
                .xpath(
                        "//android.view" +
                                ".View[@content-desc='" + date + "']")).click();
    }

    public void clickBtnOk() {
        btnOk.click();
    }

    public boolean validateDate(String date) {
        //20 March 2026    20/03/2026
        String dateResultText = dateResult.getText();
        System.out.println("dateResultText --> " + dateResultText);
        String[] arrayDateResult = dateResultText.split("/");
        System.out.println("date -->" +date);
        String[] arrayDate = date.split(" ");
        if (arrayDate[0].equals(arrayDateResult[0]) && numberMonth(arrayDate[1])
                == Integer.parseInt(arrayDateResult[1])
                && arrayDate[2].equals(arrayDateResult[2]))
            return true;
        else
            return false;
    }

    private int numberMonth(String month) {
        int m = 0;
        switch (month) {
            case "January":
                m = 1;
                break;
            case "February":
                m = 2;
                break;
            case "March":
                m = 3;
                break;
            case "April":
                m = 4;
                break;
            case "May":
                m = 5;
                break;
            case "June":
                m = 6;
                break;
            case "July":
                m = 7;
                break;
            case "August":
                m = 8;
                break;
            case "September":
                m = 9;
                break;
            case "October":
                m = 10;
                break;
            case "November":
                m = 11;
                break;
            case "December":
                m = 12;
                break;
        }
        return m;
    }
}
