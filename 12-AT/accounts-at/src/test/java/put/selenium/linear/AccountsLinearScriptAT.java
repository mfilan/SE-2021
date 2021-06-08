package put.selenium.linear;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import put.selenium.utils.ScreenshotAndQuitOnFailureRule;


public class AccountsLinearScriptAT {

    private WebDriver driver;

    @Rule
    public ScreenshotAndQuitOnFailureRule screenshotOnFailureAndWebDriverQuitRule =
            new ScreenshotAndQuitOnFailureRule();


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        this.driver = new FirefoxDriver();
        screenshotOnFailureAndWebDriverQuitRule.setWebDriver(driver);
        driver.get("http://localhost:8080/accounts/controller?action=db_reset");
    }

    @Test
    public void successfulUserRegistration() throws Exception {
        driver.get("http://localhost:8080/accounts/controller?action=db_reset");
        driver.get("http://localhost:8080/accounts/controller");
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("Test1");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("repeat_password")).clear();
        driver.findElement(By.name("repeat_password")).sendKeys("password");
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys("John Doe");
        driver.findElement(By.name("addressData")).clear();
        driver.findElement(By.name("addressData")).sendKeys("john.doe@test.com");
        driver.findElement(By.name("submit")).click();
    }


}
