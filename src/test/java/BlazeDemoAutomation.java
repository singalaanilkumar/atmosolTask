import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

public class BlazeDemoAutomation {
    WebDriver driver;


    @BeforeTest
    public void lauchBrowserAndNavigateUrl() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://blazedemo.com/index.php");
    }

    @Test(priority = 1)
    public void ValidateTitleHome() {
        HomePage hp = new HomePage(driver);

        // Check if the title is displayed as "Welcome to the Simple Travel Agency!"
        String expectedTitle = "Welcome to the Simple Travel Agency!";
        String actualTitle = hp.GetTileOfBlaze().getText();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Title verification passed.");
        } else {
            System.out.println("Title verification failed.");
        }
        hp.ClickOnDestination();

    }

    @Test(priority = 2)
    public void ClickHyperLinkAndNavigateBack() {
        ChildPage cp = new ChildPage(driver);
        /** Verify if the URL contains "vacation" */
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("vacation")) {
            System.out.println("URL verification passed.");
        } else {
            System.out.println("URL verification failed.");
        }
        cp.NavigateBack();
    }

    @Test(priority = 3)
    public void SelectDropDownAndFlight() {
        FlightFindPage Fp = new FlightFindPage(driver);
        Fp.HandleDepartDropdown();
        Fp.HandleDestiDropdown();
        Fp.ClickOnFlight();
    }

    @Test(priority = 4)
    public void ClickLowestPriceFlight() {
        LowestFlightPrice Lp = new LowestFlightPrice(driver);
        Lp.FindingLessPriceFlight();
    }

    @Test(priority = 5)
    public void PurchaseFlight() {
        SubmitFormOfFlight Sf = new SubmitFormOfFlight(driver);
        Sf.ValidateTotalCostField();
        Sf.FillFlightForm();
    }

    @Test(priority = 6)
    public void ValidatePurchaseID() {
        PurchaseConfirmationPage pc = new PurchaseConfirmationPage(driver);
        String purchaseId = pc.purchaseId().getText();
        Reporter.log("Purchase ID is: " + purchaseId, true);
    }


    @AfterTest
    public void Quitbrowser() {
        driver.quit();
    }
}
