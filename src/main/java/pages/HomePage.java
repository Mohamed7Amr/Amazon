package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;


/**
 * This is a Web-page Class for Amazon-Home(Landing page) that includes all the required elements and methods that deal some actions
 * upon these elements
 * @author Mohamed_Amr
 */

public class HomePage extends PageBase{

    /*********************************************CONSTRUCTORS****************************************************/

/**
 * This constructor is made for i will use the WebDriver-driver parameter (sent from Hooks-class to steps-class) to be used as parameter w/
 * PageFactory-method, so it can be used w/ @FindBy to locate elements, but I still declare a global variable of WebDriver datatype -where I
 * can pass to it the value of the driver just in-case I want to use it with any User-Action class or further needs.
 */

    public  HomePage(WebDriver driver) {
        super(driver);
    }

    /*********************************************WEB_ELEMENTS********************************************************/


    WebElement localizationBtn = driver.findElement(By.className("icp-nav-link-inner"));

    @FindBy (xpath = "//a[@href='#switch-lang=en_AE']/span[@class='nav-text']/i[@class='icp-radio']")
    WebElement englishOption;

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

        waitElementVisibility(10,localizationBtn);
        hoverOnElement(localizationBtn);
        takeScreenShot("hovering_Lang_Btn.png");
        jsClick(englishOption);
    }


    /**
     * User opens All Main Menu that contains all categories and sub-categories
     */
    public void clickAllBtn()
    {
        /**
         * Sleep is a must here because un expected behavior happens from the website if it does not exist
         */

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        hoverOnElement(allMainMenu);
        jsClick(allMainMenu);
    }

    /**
     * The user chooses VideoGames Category from "All" that displays the Main-Menu
     */
    public void openVideoGamesLi()
    {
        waitElementVisibility(5, seeAllLi);
        jsClick(seeAllLi);
        jsClick(videoGamesLi);
    }

    /**
     * the user chooses subcategory "All Video Games" from "VideoGames" category
     */
    public void clickAllVideoGamesLi()
    {
        jsClick(allVideoGamesLi);
    }


}
