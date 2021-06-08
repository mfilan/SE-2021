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
        driver.get("http://localhost:8080/accounts/controller?action=db_reset");
        driver.get("http://localhost:8080/accounts/controller");
        assertEquals(driver.getCurrentUrl(), "http://localhost:8080/accounts/controller");
        driver.findElement(By.linkText("Register")).click();
        assertEquals("Register", driver.findElement(By.xpath("//div[@id='contentSingle']/h3")).getText());
        assertEquals("username:", driver.findElement(By.xpath("//div[@id='contentSingle']/form/table/tbody/tr/td")).getText());
        driver.findElement(By.name("username")).sendKeys("Test1");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("repeat_password")).sendKeys("password");
        driver.findElement(By.name("name")).sendKeys("John Doe");
        driver.findElement(By.name("addressData")).sendKeys("john.doe@test.com");
        driver.findElement(By.name("submit")).click();
        assertEquals("Login", driver.findElement(By.xpath("//div[@id='contentSingle']/h3")).getText());
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("Test1");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("submit")).click();
        assertEquals("John Doe, john.doe@test.com", driver.findElement(By.xpath("//div[@id='container']/div[2]")).getText());

    }


}
