package stepDefinitions;

import dataReader.LoadProperties;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import java.time.Duration;

public class Hooks {
    /********************************************ATTRIBUTES*************************************/
    public static WebDriver driver;
    private String browser = LoadProperties.envConfig.getProperty("browser");
    private String url = LoadProperties.envConfig.getProperty("URL");

    /*********************************************METHODS****************************************************/

    @Before
    public void setUpEnvironment()
    {
        switch (browser)
        {
            case "chrome": driver = new ChromeDriver();break;
            case "firefox": driver = new FirefoxDriver();break;
            case "edge": driver = new EdgeDriver();break;
            default: driver = new ChromeDriver();break;
        }

        driver.get("https://www.amazon.eg/");
        driver.manage().window().maximize();
        driver.navigate().refresh();
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        String expectedResult1 = "https://www.amazon.eg/";
        String actualResult1 = driver.getCurrentUrl();
        Assert.assertEquals(actualResult1,expectedResult1,"SETUP ENVIRONMENT ASSERTION 1");
    }

//    @After
//    public void clearUpEnvironment() throws InterruptedException {
//        Thread.sleep(5000);
//        driver.quit();
//    }
}
