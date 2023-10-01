package stepDefinitions;

import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.HomePage;

import java.time.Duration;

public class Hooks {
    /********************************************ATTRIBUTES*************************************/
    public static WebDriver driver;
//    HomePage hp;

    /*********************************************METHODS****************************************************/

    @Before
    public void setUpEnvironment()
    {
        driver = new ChromeDriver();
//        hp = new HomePage(driver);
        driver.get("https://www.amazon.eg/");
        driver.manage().window().maximize();
        driver.navigate().refresh();
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        String expectedResult1 = "https://www.amazon.eg/";
        String actualResult1 = driver.getCurrentUrl();
        Assert.assertEquals(actualResult1,expectedResult1,"SETUP ENVIRONMENT ASSERTION 1");
    }

    @After
    public void clearUpEnvironment() throws InterruptedException {
//        Thread.sleep(5000);
//        driver.quit();
    }
}
