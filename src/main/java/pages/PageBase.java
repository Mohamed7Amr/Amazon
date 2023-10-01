package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PageBase {

    protected WebDriver driver;

    public PageBase(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*****************************************************REPEATED_USER_ACTIONS*********************************************/

    public void clickWebElement(WebElement ele)
    {
        ele.click();
    }

    public void writeText(WebElement ele, String text)
    {
        ele.sendKeys(text);
    }

    /*****************************************************ACTIONS_METHODS*********************************************/

    public void hoverOnElement(WebElement ele)
    {
        Actions action = new Actions(driver);
        action.moveToElement(ele).perform();
    }

    /*****************************************************JS_INJECTION*********************************************/

    public void jsClick(WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()",element);
    }

    public void jsScroll(WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()",element);
    }

    /*****************************************************EXPLICIT_WAIT*********************************************/

    public void waitElementVisibility(int seconds, WebElement ele)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitElementsVisibility(int seconds, List<WebElement> eles)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfAllElements(eles));
    }

    /*****************************************************DATA_TYPES*********************************************/


    public void displayType(int var)
    {
        System.out.println("Integer Type");
    }
    public void displayType(String var)
    {
        System.out.println("StringType");
    }
    public void displayType(Double var)
    {
        System.out.println("Double Type");
    }
    public void displayType(Float var)
    {
        System.out.println("Float Type");
    }

}
