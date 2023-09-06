package Pages;

import Helpers.JsInjection;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * @author Mohamed_Amr
 */
public class AddAddressPage {

    /*********************************************GLOBAL_VARIABLES************************************************/

    private WebDriver driver;

    JsInjection jsi;

    Actions actions;

    /*********************************************CONSTRUCTORS****************************************************/

    public AddAddressPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    /*********************************************WEB_ELEMENTS********************************************************/

    /**
     * Some notations regarding variables naming:
     * 1) variables' suffix "Li" means it's an ordered-list-item element.
     * 2) variables' suffix "Categ" means it's a category element.
     * 3) variables' suffix "Chbox" means it's a checkbox element.
     */

    @FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressFullName'][@type='text']")
    WebElement fullNameTxt;

    @FindBy(id = "address-ui-widgets-enterAddressPhoneNumber")
    WebElement mobileNumberTxt;

    @FindBy(xpath = "//input[@name='address-ui-widgets-enterAddressLine1'][@type='text']")
    WebElement streetNameTxt;

    @FindBy(xpath = "//input[@id='address-ui-widgets-enter-building-name-or-number'][@type='text']")
    WebElement buildingNameTxt;

    @FindBy(id = "address-ui-widgets-enterAddressCity")
    WebElement cityTxtBox;

    @FindBy(id = "address-ui-widgets-enterAddressDistrictOrCounty")
    WebElement districtTxtBox;

    @FindBy(id = "address-ui-widgets-landmark")
    WebElement landmarkTxtBox;

    @FindBy(css = "#address-ui-widgets-form-submit-button>span.a-button-inner>input.a-button-input")
    WebElement addressTypeRdo;

    @FindBy(id = "address-ui-widgets-form-submit-button-announce")
    WebElement addAddressBtn;


    /*******************************************METHODS**********************************************************/

    /**
     * User inserts his first and last names
     * @param name is a string for his first and last names
     */
    public void insertFullName(String name)
    {
        fullNameTxt.sendKeys(name);
    }

    public void insertMobileNumber(String mobile)
    {
        mobileNumberTxt.sendKeys(mobile);
    }

    public void insertStreetName(String name)
    {
        streetNameTxt.sendKeys(name);
    }

    public void insertBuildingNumber(String building)
    {
        buildingNameTxt.sendKeys(building);
    }

    public void selectCity(String cityName) throws InterruptedException {
        cityTxtBox.click();

            Thread.sleep(3000);

        cityTxtBox.sendKeys(cityName);

            Thread.sleep(3000);

        cityTxtBox.sendKeys(Keys.ARROW_DOWN);

            Thread.sleep(3000);

        cityTxtBox.sendKeys(Keys.ENTER);

            Thread.sleep(3000);

        cityTxtBox.sendKeys(Keys.TAB);
    }

    public void selectDistrict(String districtName) throws InterruptedException {
        districtTxtBox.sendKeys(districtName);

            Thread.sleep(2000);

        districtTxtBox.sendKeys(Keys.ARROW_DOWN);

            Thread.sleep(2000);

        districtTxtBox.sendKeys(Keys.ENTER);

    }

    public void addLandmark(String landmarkName)
    {
        jsi = new JsInjection(driver);
        jsi.jsScroll(landmarkTxtBox);
        landmarkTxtBox.sendKeys(landmarkName);
        landmarkTxtBox.sendKeys((Keys.TAB));
    }

    public void chooseAddressType()
    {
        addressTypeRdo.click();
    }

    public void addAddressToDelivery()
    {
        jsi = new JsInjection(driver);
        jsi.jsScroll(addAddressBtn);
        jsi.jsClick(addAddressBtn);
    }




    /*******************************************ASSERTIONS**********************************************************/

    public void shippingAddressDetailsDisplay()
    {
        String expectedResult = "Add a new address";
        String actualResult = driver.findElement(By.xpath("//h2[contains(text(),'Add a new address')]")).getText();
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

}
