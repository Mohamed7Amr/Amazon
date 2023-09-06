package StepDefinitions;

import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Hooks {
    /********************************************ATTRIBUTES*************************************/
    public static WebDriver driver;

    /*********************************************METHODS****************************************************/

    @Before
    public void setUpEnvironment()
    {
        driver = new ChromeDriver();
        driver.get("https://www.amazon.eg/");
        driver.manage().window().maximize();
        driver.navigate().refresh();

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
