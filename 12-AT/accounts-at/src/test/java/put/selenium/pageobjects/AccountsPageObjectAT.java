package put.selenium.pageobjects;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import put.selenium.pageobjects.repository.*;
import put.selenium.utils.ScreenshotAndQuitOnFailureRule;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class AccountsPageObjectAT {

    private WebDriver driver;

    private ResetDatabase resetDatabse;
    private MainMenu mainMenu;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private MainPage mainPage;
    private LoggedInUserInformation loggedInUserInfo;

    private String hostURL;

    @Rule
    public ScreenshotAndQuitOnFailureRule screenshotOnFailureAndWebDriverQuitRule =
            new ScreenshotAndQuitOnFailureRule();


    @Before
    public void setUp() throws Exception {

        Properties properties = new Properties();
        InputStream in = getClass().getResourceAsStream("selenium.properties");
        properties.load(in);
        in.close();

        this.hostURL=properties.getProperty("host.url");

        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        this.driver = new FirefoxDriver();
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        screenshotOnFailureAndWebDriverQuitRule.setWebDriver(driver);

        resetDatabse = new ResetDatabase(driver, this.hostURL);
        mainMenu = new MainMenu(driver);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver, this.hostURL);
        loggedInUserInfo = new LoggedInUserInformation(driver);

        resetDatabse.resetDatabase();
    }

    @Test
    public void successfulUserRegistration() throws Exception {
        //TODO
//        mainMenu.click
//

    }


}
