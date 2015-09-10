package marketo.com;


import marketo.com.pages.LogLog4j;
import marketo.com.pages.LoginPage;
import marketo.com.pages.MainPage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class LoginTest extends TestNgTestBase{
    public static String USER = "rtpinterview01@marketo.com";
    public static String PASSWORD = "xGTrgjJ4Ew";
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    public LoginPage loginPage;
    public MainPage mainPage;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        PropertyConfigurator.configure("log4j.properties");
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethodSetUp() {
        try {
            loginPage.openLoginPage()
                    .waitUntilLoginPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void LoginSuccess() {
        Log.info("Checking that all correct data added successfully");
        try {
            loginPage
                    .login(USER,PASSWORD);
            mainPage.waitUntilMainPageIsLoaded();
            assertTrue("The Main Page doesn't open", mainPage.isOnMainPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Login successful");
             mainPage.clickOnLogoutButton();
             loginPage.waitUntilLoginPageIsLoaded();
    }

    @Test(groups = {"smoke", "negative"})
    public void LoginWithWrongCredentials() {
        Log.info("Checking inability lodin with wrong credentials");
        try {
            loginPage
                    .login("aaaaa","111111")
                    .waitUntilErrorMessageIsLogIsLoaded();
            assertTrue("The Credentials are valid", loginPage.alertMessageWrongCredentials());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("Not logged in successful");
    }

}
