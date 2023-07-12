package StepDefinitions;

import io.cucumber.java.*;
import org.openqa.selenium.By;
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
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        driver.get("https://www.amazon.eg/");
        driver.manage().window().maximize();

        String expectedResult1 = "https://www.amazon.eg/";
        String actualResult1 = driver.getCurrentUrl();
        Assert.assertEquals(actualResult1,expectedResult1,"SETUP ENVIRONMENT ASSERTION 1");

        try
        {
            String expectedResult2 = "مشترياتك";
            String actualResult2 = driver.findElement(By.xpath("//span[@class='nav-line-2'][contains(text(),'مشترياتك')]")).getText();
            Assert.assertTrue(actualResult2.contains(expectedResult2));
        }
        catch (Exception e)
        {
            driver.navigate().refresh();
        }
    }

    @After
    public void clearUpEnvironment() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}
