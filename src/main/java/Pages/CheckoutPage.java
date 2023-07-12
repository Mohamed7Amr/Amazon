package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckoutPage {

    /*********************************************GLOBAL_VARIABLES************************************************/

    private WebDriver driver;

    /*********************************************CONSTRUCTORS****************************************************/

    public CheckoutPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    /*********************************************WEB_ELEMENTS********************************************************/

    /*Some notations regarding variables naming:
1) variables' suffix "Li" means it's an ordered-list-item element.
2) variables' suffix "Categ" means it's a category element.
3) variables' suffix "Chbox" means it's a checkbox element.
4) variables' suffix "Rdo" means it's a checkbox element
     */

//    @FindBy(xpath = "//h2[contains(text(),'Add a new address')]")
//    WebElement addNewAddressTitle;

    @FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressFullName'][@type='text']")
    WebElement fullNameTxt;

    @FindBy(id = "address-ui-widgets-enterAddressPhoneNumber")
    WebElement mobileNumberTxt;

    @FindBy(xpath = "//input[@name='address-ui-widgets-enterAddressLine1'][@type='text']")
    WebElement streetNameTxt;

    @FindBy(xpath = "//input[@id='address-ui-widgets-enter-building-name-or-number'][@type='text']")
    WebElement buildingNameTxt;

    @FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressCity'][@type='text']")
    WebElement cityTxt;

    @FindBy(className = "autoOp")
    List<WebElement> cityDDL;

    @FindBy(xpath = "//input[@id=address-ui-widgets-enterAddressDistrictOrCounty'][@type='text']")
    WebElement districtDDL;

    @FindBy(xpath = "//input[@id=address-ui-widgets-addr-details-res-radio-input'][@type='radio']")
    WebElement addressTypeRdo;

    /*******************************************METHODS**********************************************************/

    public void jsClick(WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click",element);
    }

    public void jsScroll(WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()",element);
    }


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

    public void selectCity()
    {
        cityTxt.click();
        cityTxt.click();
//        cityDDL.sendKeys("New");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        Select listItems = new Select(cityDDL);

        int i;
        for(i=0; i<=cityDDL.size(); i++)
        {
            if(cityDDL.get(i).getText() == "New Cairo City, ")
            {
//                jsClick(cityDDL.get(i));
                cityDDL.get(i).click();
            }
            else
            {
                continue;
            }
        }

//        listItems.selectByVisibleText("New Cairo City, ");
//        listItems.se
    }

    public void selectDistrict()
    {
        districtDDL.click();
        districtDDL.sendKeys("1");
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        Select listItems = new Select(districtDDL);
        listItems.selectByVisibleText("1 (1st Settlement)");
    }

    public void chooseAddressType()
    {
        addressTypeRdo.click();
    }


    /*******************************************ASSERTIONS**********************************************************/

    public void shippingAddressDetailsDisplay()
    {
        String expectedResult = "Add a new address";
        String actualResult = driver.findElement(By.xpath("//h2[contains(text(),'Add a new address')]")).getText();
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

}
