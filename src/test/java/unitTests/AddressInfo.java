package unitTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;
import pages.PageBase;
import stepDefinitions.Hooks;

import java.io.IOException;
import java.time.Duration;
import java.util.List;


/**
 * This is a unit-test class that is like for a shortcut to reach the Add-Address flow easier and faster
 */

public class AddressInfo extends PageBase {


    /*********************************************CONSTRUCTORsr****************************************************/
    public AddressInfo(WebDriver driver) {
        super(Hooks.driver);
    }


    @Test
    public void fillAddressInfo() throws InterruptedException, IOException {

        driver.get("https://www.amazon.eg/dp/B0BXXZTSW7?ref_=Oct_DLandingS_D_8a5c2ede_NA&th=1");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        hoverOnElement(driver.findElement(By.className("icp-nav-link-inner")));
//        takeScreenShot();
        driver.findElement(By.xpath("//a[@href='#switch-lang=en_AE']/span[@class='nav-text']/i[@class='icp-radio']")).click();
        Thread.sleep(3000);
//        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("span[id='submit.add-to-cart-announce']"))));
        jsClick(driver.findElement(By.cssSelector("input[title = 'Add to Shopping Cart']")));
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
                jsClick(cityOptions.get(i));
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
        jsScroll(landmarkTxtBox);
        landmarkTxtBox.sendKeys("Cairo-Festival");
        landmarkTxtBox.sendKeys(Keys.TAB);


    }
    
}
