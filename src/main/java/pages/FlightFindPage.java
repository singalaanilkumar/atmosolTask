package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FlightFindPage {

    WebDriver driver;

    @FindBy(xpath = "//select[@name='fromPort']")
    WebElement DepartmentDropdown;

    @FindBy(xpath = "//select[@name='toPort']")
    WebElement destinationDropdown;

    @FindBy(xpath = "//input[@value='Find Flights']")
    WebElement ClickFlight;


    public FlightFindPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static void selectDropDownValue(WebElement element, String type, String value) {
        Select select = new Select(element);
        switch (type) {
            case "index":
                select.selectByIndex(Integer.parseInt(value));
                break;
            case "value":
                select.selectByValue(value);
                break;
            case "visibletext":
                select.selectByVisibleText(value);
                break;
            default:
                System.out.println("pass the correct selection criteria");
                break;
        }
    }

    public void HandleDepartDropdown() {
        selectDropDownValue(DepartmentDropdown, "value", "Mexico City");
    }

    public void HandleDestiDropdown() {
        selectDropDownValue(destinationDropdown, "value", "London");
    }

    public void ClickOnFlight() {
        ClickFlight.click();
    }

}
