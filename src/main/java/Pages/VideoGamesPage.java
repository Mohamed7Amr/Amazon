package Pages;

import Helpers.JsInjection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.List;

/**
 * @author Mohamed_Amr
 */
public class VideoGamesPage {

    /*********************************************GLOBAL_VARIABLES************************************************/
    private WebDriver driver;
    JsInjection jsi;


    /*********************************************CONSTRUCTORS****************************************************/

    public VideoGamesPage(WebDriver driver)
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


    @FindBy(xpath = "//span[contains(text(),'All customers get FREE Shipping on orders shipped by Amazon')]")
    WebElement freeShippingChbox;

    @FindBy(xpath = "//span[@class='a-size-base a-color-base'][contains(text(),'New')]")
    WebElement newConditionLink;

    @FindBy(xpath = "//span[@class='a-dropdown-label'][contains(text(),'Sort by:')]")
    WebElement sortDDL;

    @FindBy(xpath= "a[@id='s-result-sort-select_2'][contains(text(),'Price: High to Low')]")//
    WebElement highToLowLi;

    @FindBy(className = "a-price-whole")
    List<WebElement>videoGamesPrices;

    @FindBy(id = "add-to-cart-button")
    WebElement addToCartBtn;

    @FindBy(xpath = "//a[contains(text(),'Next')]")
    WebElement nextPageBtn;

    @FindBy(id = "nav-cart-count-container")
    WebElement cartBtn;

    /*******************************************METHODS**********************************************************/

    public void doubleBack()
    {
        driver.navigate().back();
        driver.navigate().back();
    }

    /**
     * User filters the products that can be free-shipped
     * @return void
     */
    public void chooseFreeShipping()
    {
        freeShippingChbox.click();
    }

    /**
     * User filters the products to be all new products
     */
    public void chooseNewCondition()
    {
        jsi = new JsInjection(driver);

        jsi.jsScroll(newConditionLink);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        newConditionLink.click();
    }

    /**
     * User sorts products by price from high to low
     */
    public void sortPriceHighLow()
    {
        jsi = new JsInjection(driver);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        sortDDL.click();
        Select optionsList = new Select(driver.findElement(By.id("s-result-sort-select")));
        optionsList.selectByVisibleText("Price: High to Low");
    }

    /**
     * User adds products that are only less than 15k EGP to the cart
     */
    public void addToCart()
    {
        jsi = new JsInjection(driver);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int i;
        int numberOfItems = 0;
        for(i=0; i<videoGamesPrices.size(); i*=1)
        {
            videoGamesPrices = driver.findElements(By.className("a-price-whole"));

            if(Double.parseDouble(videoGamesPrices.get(i).getText().replace(",","")) < 15000)
            {
                videoGamesPrices.get(i).click();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (addToCartBtn.isDisplayed())
                {
                    addToCartBtn.click();
                    numberOfItems++;
                    i++;

                    try
                    {
                        String expectedResult = "Added to Cart";
                        String actualResult = driver.findElement(By.xpath("//div[@class='a-fixed-left-grid-inner']")).getText();
                        Assert.assertTrue(actualResult.contains(expectedResult),"Added To Cart msg ASSERTION");
                        System.out.println(numberOfItems + " is the number of items added so far to cart");

                         Thread.sleep(2000);

                         doubleBack();
                    }
                    catch(AssertionError assertionError)
                    {
                        System.out.println(numberOfItems + " is the product that has not been added to cart");
                        driver.navigate().back();
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                else
                {
                    driver.navigate().back();
                }
            }
            else if (Double.parseDouble(videoGamesPrices.get(i).getText().replace(",","")) > 15000)
            {
                i++;
                continue;
            }
            else
            {
                nextPage();
                i = 0;
                videoGamesPrices = driver.findElements(By.className("a-price-whole"));
            }
        }
    }

    /**
     * User goes forward for the next page of the search-results of the products he is looking for; page number 2,3,etc..
     */
    public void nextPage()
    {
        nextPageBtn.click();
    }

    public void goToCart()
    {
        cartBtn.click();
    }

    /*************************************************Assertions*******************************************************/

    public void videoGamesScreenConfirmation()
    {
        String expectedResult1 = "https://www.amazon.eg/-/en/gp/browse.html?node=18022560031&ref_=nav_em_vg_all_0_2_16_2";
        String actualResult1 = driver.getCurrentUrl();
        Assert.assertEquals(actualResult1,expectedResult1,"videoGamesScreenURLAssertion");

        String expectedResult2 = "Video Games";
        String actualResult2 = driver.findElement(By.xpath("//b[contains(text(),'Video Games')]")).getText();
        Assert.assertTrue(actualResult2.contains(expectedResult2));

    }


}
