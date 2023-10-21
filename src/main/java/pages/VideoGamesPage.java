package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.List;

/**
 * @author Mohamed_Amr
 */
public class VideoGamesPage extends PageBase {

    /*********************************************CONSTRUCTORS****************************************************/

    public VideoGamesPage(WebDriver driver)
    {
        super(driver);
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
    WebElement sortDDLBtn;

    @FindBy(xpath = "(//ul[@class='a-nostyle a-list-link'])[1]")
    WebElement sortByUl;

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
       jsScroll(newConditionLink);
       jsClick(newConditionLink);
    }

    /**
     * User sorts products by price from high to low
     */
    public void sortPriceHighLow()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        clickWebElement(sortDDLBtn);

        List<WebElement> sortByItems = sortByUl.findElements(By.tagName("a"));
        for(WebElement item: sortByItems)
        {
            if(item.getText().equals("Price: High to Low"))
            {
                clickWebElement(item);
            }
        }

    }

    int numberOfItems = 0;
    public void choose15KPriceProducts() {

        int i;
        for (i = 0; i < videoGamesPrices.size(); i *= 1)
        {

//            videoGamesPrices = driver.findElements(By.className("a-price-whole"));

            if (Double.parseDouble(videoGamesPrices.get(i).getText().replace(",", "")) <= 15000)
            {
                clickWebElement(videoGamesPrices.get(i));
                numberOfItems++;

                if (addToCartBtn.isDisplayed())
                {
                    jsClick(addToCartBtn);
                    i++;

                    try {
                        String expectedResult = "Added to Cart";
                        String actualResult = driver.findElement(By.xpath("//div[@class='a-fixed-left-grid-inner']")).getText();
                        Assert.assertTrue(actualResult.contains(expectedResult), "Added To Cart msg ASSERTION");
                        System.out.println(numberOfItems + " is the number of items added so far to cart");

                        Thread.sleep(2000);

                        doubleBack();
                    } catch (AssertionError assertionError) {
                        System.out.println(numberOfItems + " is the product that has not been added to cart");
                        driver.navigate().back();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    driver.navigate().back();
                }
            }

            else if (Double.parseDouble(videoGamesPrices.get(i).getText().replace(",", "")) > 15000)
            {
                i++;
            }
        }
    }
    /**
     * User adds products that are only less than 15k EGP to the cart
     */
    public void addToCart()
    {
        choose15KPriceProducts();
        while(numberOfItems == 0)
        {
            nextPage();
            waitElementsVisibility(10, videoGamesPrices);
            choose15KPriceProducts();
        }
    }

    /**
     * User goes forward for the next page of the search-results of the products he is looking for; page number 2,3,etc..
     */
    public void nextPage()
    {
        jsScroll(nextPageBtn);
        jsClick(nextPageBtn);
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
