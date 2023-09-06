package Pages;

import Helpers.JsInjection;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * This is a Web-page Class for Amazon-Home(Landing page) that includes all the required elements and methods that deal some actions
 * upon these elements
 * @author Mohamed_Amr
 */

public class HomePage {

    /*********************************************GLOBAL_VARIABLES************************************************/
    private WebDriver driver;
    WebDriverWait wait;

    JsInjection jsi;

    Actions actions;



    /*********************************************CONSTRUCTORS****************************************************/

/**
 * This constructor is made for i will use the WebDriver-driver parameter (sent from Hooks-class to steps-class) to be used as parameter w/
 * PageFactory-method, so it can be used w/ @FindBy to locate elements, but I still declare a global variable of WebDriver datatype -where I
 * can pass to it the value of the driver just in-case I want to use it with any User-Action class or further needs.
 */

    public  HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*********************************************WEB_ELEMENTS********************************************************/

/*Some notations regarding variables naming:
1) variables' suffix "Li" means it's an ordered-list-item element.
2) variables' suffix "Categ" means it's a category element.
 */

    @FindBy(className = "icp-nav-link-inner")
    WebElement localizationBtn;

    @FindBy (xpath = "//a[@href='#switch-lang=en_AE']/span[@class='nav-text']/i[@class='icp-radio']")
    WebElement englishOption;

    /**
     * I still haven't used these 2 web-elements because it will only be used if i start my test-case with logging-in first then do the rest of
     * the steps, and i shall do that if i am able to pass the captcha.
     */

//    @FindBy(xpath = "//span[@class='nav-line-2 '][contains(text(),'Account & Lists')]")
//    WebElement account_and_ListsBtn;
//
//    @FindBy(xpath = "//span[@class='nav-action-inner'][contains(text(),'Sign in')]")
//    WebElement signInBtn;

    @FindBy(xpath = "//i[@class='hm-icon nav-sprite']")
    WebElement allMainMenu;

    @FindBy(xpath = "//div[contains(text(),'see all')]")
    WebElement seeAllLi;

    @FindBy(xpath = "//a[@class='hmenu-item'] /div[contains(text(),'Video Games')]")
    WebElement videoGamesLi;

    @FindBy (xpath = "//a[@class='hmenu-item'][contains(text(),'All Video Games')]")
    WebElement allVideoGamesLi;


    /*******************************************METHODS**********************************************************/

    /**
     * User chooses English as preferred language for the interface
     */
    public void chooseEnglishLanguage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        jsi = new JsInjection(driver);

        actions = new Actions(driver);

        wait.until(ExpectedConditions.elementToBeClickable(localizationBtn));

        actions.moveToElement(localizationBtn).perform();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        jsi.jsClick(englishOption);

    }
    /**
     * I still haven't used these 2 web-elements because it will only be used if i start my test-case with logging-in first then do the rest of
     * the steps, and i shall do that if i am able to pass the captcha.
     */

//    public void hoverOnAccounts_And_Lists()
//    {
//        actions.moveToElement(account_and_ListsBtn).perform();
//    }

//    public void clickSignInBtn()
//    {
//        signInBtn.click();
//    }

    /**
     * User opens All Main Menu that contains all categories and sub-categories
     */
    public void clickAllBtn()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        actions.moveToElement(allMainMenu).click().perform();
    }

    /**
     * The user chooses VideoGames Category from "All" that displays the Main-Menu
     */
    public void openVideoGamesLi()
    {
        jsi = new JsInjection(driver);
        seeAllLi.click();
        jsi.jsClick(videoGamesLi);
    }

    /**
     * the user chooses subcategory "All Video Games" from "VideoGames" category
     */
    public void clickAllVideoGamesLi()
    {
        allVideoGamesLi.click();
    }


}
