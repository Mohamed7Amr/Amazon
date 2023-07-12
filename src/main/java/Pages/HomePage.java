package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class HomePage {

    /*********************************************GLOBAL_VARIABLES************************************************/
    private WebDriver driver;


    /*********************************************CONSTRUCTORS****************************************************/

//Needed because i will send the driver to be used as parameter w/ PageFactory so it can be used w/ @FindBy to locate elements

    public  HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    /*********************************************SETTERS_&_GETTERS**************************************************/




    /*********************************************WEB_ELEMENTS********************************************************/

/*Some notations regarding variables naming:
1) variables' suffix "Li" means it's an ordered-list-item element.
2) variables' suffix "Categ" means it's a category element.
 */


    @FindBy(className = "icp-nav-link-inner")
    WebElement localizationBtn;


    @FindBy(xpath = "//a[@href='#switch-lang=ar_AE']/span[@class='nav-text']/i[@class='icp-radio icp-radio-active']")
    WebElement arabicActiveOption;

    @FindBy (xpath = "//a[@href='#switch-lang=en_AE']/span[@class='nav-text']/i[@class='icp-radio']")
    WebElement englishOption;

    @FindBy(xpath = "//span[@class='nav-line-2 '][contains(text(),'Account & Lists')]")
    WebElement account_and_ListsBtn;

    @FindBy(xpath = "//span[@class='nav-action-inner'][contains(text(),'Sign in')]")
    WebElement signInBtn;

    @FindBy(xpath = "//i[@class='hm-icon nav-sprite']")
    WebElement allBtn;

    @FindBy(xpath = "//div[contains(text(),'see all')]")
    WebElement seeAllLi;


    @FindBy(xpath = "//a[@class='hmenu-item'] /div[contains(text(),'Video Games')]")
    WebElement videoGamesLi;

    public WebElement accessoriesCategoryEle()
    {
        return driver.findElement(By.xpath("//a[@class='hmenu-item'] /div[contains(text(),'Video Games')]"));
    }

    @FindBy (xpath = "//a[@class='hmenu-item'][contains(text(),'All Video Games')]")
    WebElement allVideoGamesLi;


    /*******************************************METHODS**********************************************************/



    public void jsClick(WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()",element);
    }


    public void chooseEnglishLanguage() {
        Actions actions = new Actions(driver);
        actions.moveToElement(localizationBtn).perform();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        jsClick(englishOption);

    }


    public void hoverOnAccounts_And_Lists()
    {
        Actions actions = new Actions(driver);
        actions.moveToElement(account_and_ListsBtn).perform();
    }

    public void clickSignInBtn()
    {
        signInBtn.click();
    }

    public void clickAllBtn()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(allBtn));

        Actions actions = new Actions(driver);
        actions.moveToElement(allBtn).click().perform();
    }

    public void clickSeeAllLi()
    {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        seeAllLi.click();
    }


    public void clickVideoGamesLi()
    {
        jsClick(videoGamesLi);
    }

    public void clickAllVideoGamesLi()
    {
        allVideoGamesLi.click();
    }

    public void openVideoGamesLi()
    {
        seeAllLi.click();
        jsClick(videoGamesLi);
    }


}
