package unitTests;

import Helpers.JsInjection;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This is a unit-test class that is like for a shortcut to reach the Add-Address flow easier and faster
 */

public class AddressInfo {

    WebDriver driver = new ChromeDriver();
    Actions actions = new Actions(driver);

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    JsInjection jsi = new JsInjection(driver);

    @Test
    public void fillAddressInfo() throws InterruptedException {
        driver.get("https://www.amazon.eg/dp/B0BXXZTSW7?ref_=Oct_DLandingS_D_8a5c2ede_NA&th=1");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        actions.moveToElement(driver.findElement(By.className("icp-nav-link-inner"))).perform();
        driver.findElement(By.xpath("//a[@href='#switch-lang=en_AE']/span[@class='nav-text']/i[@class='icp-radio']")).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("span[id='submit.add-to-cart-announce']"))));
        jsi.jsClick(driver.findElement(By.cssSelector("input[title = 'Add to Shopping Cart']")));
        driver.findElement(By.id("sc-buy-box-ptc-button")).click();
        driver.findElement(By.id("ap_email")).sendKeys("fihones827@mahmul.com");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("ap_password")).sendKeys("12345678");
        driver.findElement(By.id("signInSubmit")).click();

        WebElement cityTxtBox =  driver.findElement(By.id("address-ui-widgets-enterAddressCity"));
        cityTxtBox.click();

        Thread.sleep(3000);
        cityTxtBox.sendKeys("ramm");
        Thread.sleep(3000);

        List<WebElement> cityOptions = driver.findElements(By.tagName("li"));
        for(int i = 0; i<cityOptions.size(); i++)
        {
            System.out.println(cityOptions.get(i).getText());
            if(cityOptions.get(i).getText() == "Abou Rawash, Giza")
            {
                jsi.jsClick(cityOptions.get(i));
                break;
            }
        }

//        cityTxtBox.sendKeys("Cairo");
//
//            Thread.sleep(3000);
//
//        cityTxtBox.sendKeys(Keys.ARROW_DOWN);
//
            Thread.sleep(3000);

        cityTxtBox.sendKeys(Keys.ENTER);

            Thread.sleep(3000);

        cityTxtBox.sendKeys(Keys.TAB);


        WebElement districtTxtBox = driver.findElement(By.id("address-ui-widgets-enterAddressDistrictOrCounty"));
        districtTxtBox.sendKeys("1 1st Settlement");

            Thread.sleep(2000);


        districtTxtBox.sendKeys(Keys.ARROW_DOWN);


            Thread.sleep(2000);


        districtTxtBox.sendKeys(Keys.ENTER);
//        districtTxtBox.sendKeys(Keys.TAB);

        WebElement landmarkTxtBox = driver.findElement(By.id("address-ui-widgets-landmark"));
        jsi.jsScroll(landmarkTxtBox);
        landmarkTxtBox.sendKeys("Cairo-Festival");
        landmarkTxtBox.sendKeys(Keys.TAB);


    }
    
}
