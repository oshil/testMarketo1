package marketo.com;


import marketo.com.pages.LogLog4j;
import marketo.com.pages.LoginPage;
import marketo.com.pages.MainPage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class MainTest extends TestNgTestBase {

    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());
    public LoginPage loginPage;
    public MainPage mainPage;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        PropertyConfigurator.configure("log4j.properties");
        try {
            loginPage.login(LoginTest.USER, LoginTest.PASSWORD);
            mainPage.waitUntilMainPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TtansferToLeadDatabasePage() {
        Log.info("Checking that Lead Database Page is loaded");
        try {
            mainPage
                    .clickOnLeadDatabaseButton()
                    .waitUntilLeadDatabaseIsLoaded();
            assertTrue("The Lead Database Page was not loaded", mainPage.isOnLeadDatabasePage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.log("the Lead Database Page was loaded");
    }
}