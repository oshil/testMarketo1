package marketo.com.pages;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class MainPage extends Page {
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    @FindBy(id="homeTile-1030-btnEl")
    WebElement leadDatabaseButton;

    @FindBy(xpath="//*[@id='logged-in-as']//tr[2]/td[2]")
    WebElement loggedinasButton;

    @FindBy(xpath="//*[@class='x-menu-item-text'][contains(text(),'Logout')]")
    WebElement logoutButton;

    @FindBy(xpath="//*[@class='x-tree-node-anchor']/span[contains(text(),'Lead Database')]")
    WebElement leadDatabaseLable;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.PAGE_URL = "https://app-sjqe.marketo.com/#MM0A1";
        PropertyConfigurator.configure("log4j.properties");
    }

    public MainPage openMainPage(WebDriver driver, String baseUrl) {
        driver.get(PAGE_URL);
        return this;
    }

    public MainPage waitUntilMainPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(leadDatabaseButton);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean isOnMainPage() {
        Log.info("Wait for load Main page");
        waitUntilMainPageIsLoaded();
        return exists(leadDatabaseButton);
    }

    public boolean isOnLeadDatabasePage() {
        Log.info("Wait for load Lead Database page");
        return exists(leadDatabaseLable);
    }

    public MainPage waitUntilLeadDatabaseIsLoaded() {
        try {
            waitUntilElementIsLoaded(leadDatabaseLable);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public MainPage clickOnLeadDatabaseButton() {
        Log.info("Click on Lead Database Button");
        clickElement(leadDatabaseButton);
        return this;
    }

    public MainPage clickOnLogoutButton() {
        Log.info("Click on Logout button");
        clickElement(loggedinasButton);
        clickElement(logoutButton);
        return this;
    }

}