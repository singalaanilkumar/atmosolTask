package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static pages.FlightFindPage.selectDropDownValue;

public class SubmitFormOfFlight {
    WebDriver driver;

    @FindBy(id = "inputName")
    WebElement name;

    @FindBy(id = "address")
    WebElement address;

    @FindBy(id = "city")
    WebElement city;

    @FindBy(id = "state")
    WebElement state;

    @FindBy(id = "zipCode")
    WebElement zipcode;

    @FindBy(xpath = "//select[@id='cardType']")
    WebElement cardTypeDropdown;

    @FindBy(id = "creditCardNumber")
    WebElement CreditCardNum;

    @FindBy(id = "creditCardYear")
    WebElement Year;

    @FindBy(id = "nameOnCard")
    WebElement nameOfCard;

    @FindBy(xpath = "//input[@value='Purchase Flight']")
    WebElement purchaseFlight;

    @FindBy(xpath = "//p[5]")
    WebElement totalcost;


    public SubmitFormOfFlight(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void FillFlightForm() {
        name.sendKeys("Anil Kumar");
        address.sendKeys("kothakota village From vidapanakal");
        city.sendKeys("anantapur");
        state.sendKeys("Andhra Pradesh");
        zipcode.sendKeys("515870");
        selectDropDownValue(cardTypeDropdown, "value", "visa");
        CreditCardNum.sendKeys("6789067345231267");
        Year.clear();
        Year.sendKeys("2023");
        nameOfCard.sendKeys("anil kumar");
        purchaseFlight.click();
    }

    public void ValidateTotalCostField() {

        if (totalcost.isDisplayed()) {
            System.out.println("Total Cost field verification passed.");
        } else {
            System.out.println("Total Cost field verification failed.");
        }

    }


}
