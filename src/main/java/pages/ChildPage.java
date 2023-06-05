package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChildPage {
    WebDriver driver;

    @FindBy(linkText = "Travel The World")
    WebElement navigate;

    public ChildPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void NavigateBack() {
        navigate.click();
    }
}
