package marketo.com.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;


public class LoginPage extends Page {

    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    @FindBy(id="loginUsername")
    WebElement userName;

    @FindBy(id="loginPassword")
    WebElement password;

    @FindBy(id="loginButton")
    WebElement loginButton;

    @FindBy(id="rememberMe")
    WebElement rememberMeCheckbox;

    @FindBy(id="custLoginMessage")
    WebElement messageError;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.PAGE_URL = "https://app-sjqe.marketo.com/";
        PageFactory.initElements(driver, this);
    }

    public LoginPage openLoginPage() {
        Log.info("Opening login page");
        driver.get(PAGE_URL);
        return this;
    }

    public LoginPage waitUntilLoginPageIsLoaded() {
        try {
            waitUntilElementIsLoaded(userName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }return this;
    }

    public LoginPage waitUntilErrorMessageIsLogIsLoaded() {
        try {
            waitUntilElementIsLoaded(messageError);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }return this;
    }

    public boolean isOnLoginPage() {
        waitUntilLoginPageIsLoaded();
        return exists(userName);
    }

    public LoginPage fillUserField(String user) {
        setElementText(userName, user);
        Log.info("entering userName: " + user + " ");
        return this;
    }

    public LoginPage fillPasswordField(String p) {
        setElementText(password, p);
        Log.info("entering password: " + p + " ");
        return this;
    }

    public LoginPage clickOnLogin() {
        clickElement(loginButton);
        return this;
    }

    public LoginPage clickOnCheckBox() {
        clickElement(rememberMeCheckbox);
        return this;
    }

    public LoginPage login(String user, String password) {
        openLoginPage();
        waitUntilLoginPageIsLoaded();
        fillUserField(user);
        fillPasswordField(password);
        clickOnLogin();
        return this;
    }

    public boolean alertMessageWrongCredentials() {
        return exists(messageError);
    }
}
