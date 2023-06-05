package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

public class LowestFlightPrice {

    WebDriver driver;

    @FindBy(xpath = "//table[@class='table']//tbody//tr")
    List<WebElement> FlightFrom;

    public LowestFlightPrice(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void FindingLessPriceFlight() {
        int rows = FlightFrom.size();
        System.out.println("number of rows in FlightForm:" + rows); //5

        /**  capture prices then store in array */

        String pricesArr[] = new String[rows]; //0-4

        for (int r = 1; r <= rows; r++) {
            String price = driver.findElement(By.xpath("//table[@class='table']//tbody//tr[" + r + "]/td[6]")).getText();
            pricesArr[r - 1] = price; //adding price into array
        }

        /** Sort prices then find lower price value */

        for (String arrvalue : pricesArr) {
            System.out.println(arrvalue);
        }

        Arrays.sort(pricesArr); // this will able to sort strings. so no need to convert to number
        String lowestPrice = pricesArr[0];
        System.out.println("Lower price:" + lowestPrice);

       /** Find record in table having lower price */

        for (int r = 1; r <= rows; r++) {
            String price = driver.findElement(By.xpath("//table[@class='table']//tbody//tr[" + r + "]/td[6]")).getText();

            if (price.equals(lowestPrice)) {
                driver.findElement(By.xpath("//table[@class='table']//tbody//tr[" + r + "]/td[1]//input")).click();
                break;
            }

        }

    }


}
