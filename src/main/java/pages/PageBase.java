package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class PageBase {

    /*********************************************GLOBAL_VARIABLES****************************************************/

    protected WebDriver driver;
    private Actions action;
    private JavascriptExecutor js;
    private WebDriverWait wait;

    /*********************************************CONSTRUCTORS****************************************************/
    public PageBase()
    {

    }
    public PageBase(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**********************************************REPEATED_USER_ACTIONS*********************************************/

    protected void clickWebElement(WebElement ele)
    {
        ele.click();
    }

    protected void writeText(WebElement ele, String text)
    {
        ele.sendKeys(text);
    }

    /*****************************************************ACTIONS_METHODS*********************************************/

    protected void hoverOnElement(WebElement ele)
    {
        action = new Actions(driver);
        action.moveToElement(ele).perform();
    }

    /*****************************************************JS_INJECTION*********************************************/

    protected void jsClick(WebElement element)
    {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()",element);
    }

    protected void jsScroll(WebElement element)
    {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()",element);
    }

    /*****************************************************EXPLICIT_WAIT*********************************************/

    protected void waitElementVisibility(int seconds, WebElement ele)
    {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    protected void waitElementsVisibility(int seconds, List<WebElement> eles)
    {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfAllElements(eles));
    }

    /***********************************************SCREENSHOT_INTERFACE*********************************************/

    protected void takeScreenShot(String ssNameExtension)  {
        TakesScreenshot ss = (TakesScreenshot) driver;
        File sourceFile = ss.getScreenshotAs(OutputType.FILE);
        File targetFile = new File("Screenshots/"+ ssNameExtension);
        try {
            FileUtils.copyFile(sourceFile,targetFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**************************************************TESTNG_LISTENERS*********************************************/

//    public void onTestFailure(ITestListener result)
//    {
//
//    }


    /*****************************************************DATA_TYPES*********************************************/


    protected void displayType(int var)
    {
        System.out.println("Integer Type");
    }
    protected void displayType(String var)
    {
        System.out.println("StringType");
    }
    protected void displayType(Double var)
    {
        System.out.println("Double Type");
    }
    protected void displayType(Float var)
    {
        System.out.println("Float Type");
    }

}
