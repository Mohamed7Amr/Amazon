package Pages;


import Helpers.DataType;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class VideoGamesPage {

    /*********************************************GLOBAL_VARIABLES************************************************/
    private WebDriver driver;
    DataType dt = new DataType();


    /*********************************************CONSTRUCTORS****************************************************/

    public VideoGamesPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    /*********************************************WEB_ELEMENTS********************************************************/

    /*Some notations regarding variables naming:
1) variables' suffix "Li" means it's an ordered-list-item element.
2) variables' suffix "Categ" means it's a category element.
3) variables' suffix "Chbox" means it's a checkbox element.
     */


    @FindBy(xpath = "//span[contains(text(),'All customers get FREE Shipping on orders shipped by Amazon')]")
    WebElement freeShippingChbox;

    @FindBy(xpath = "//span[@class='a-size-base a-color-base'][contains(text(),'New')]")
    WebElement newConditionLink;

    @FindBy(xpath = "//span[@class='a-dropdown-prompt'][contains(text(),'Featured')]")//a-autoid-0-announce
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

    public void doubleBack()
    {
        driver.navigate().back();
        driver.navigate().back();
    }

    public void chooseFreeShipping()
    {
        freeShippingChbox.click();
    }

    public void chooseNewCondition()
    {

        jsScroll(newConditionLink);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        newConditionLink.click();
    }

    public void sortPriceHighLow()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        jsClick(sortDDL);
        sortDDL.click();
        Select optionsList = new Select(driver.findElement(By.id("s-result-sort-select")));
        optionsList.selectByVisibleText("Price: High to Low");
//        jsClick(highToLowLi);
//        highToLowLi.click();
    }

    public void addToCart()
    {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        for (WebElement price : videoGamesPrices)
//        {
//            System.out.println(Double.parseDouble(price.getText().replace(",","")));
//            System.out.println(videoGamesPrices.size());
//        }

        int i;
        int numberOfItems = 0;
        for(i=0; i<videoGamesPrices.size(); i++)
        {
            List<WebElement> products = driver.findElements(By.className("a-price-whole"));

            if(Double.parseDouble(products.get(i).getText().replace(",",""))<15000)
            {

                products.get(i).click();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (addToCartBtn.isDisplayed())
                {
                    numberOfItems++;
                    addToCartBtn.click();

                    //making sure that every product is added to cart
//                    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//                    try
//                    {
////                        SoftAssert soft = new SoftAssert();
//                        String expectedResult = "Added to Cart";
//                        String actualResult = driver.findElement(By.xpath("//div[@class='a-fixed-left-grid-inner']")).getText();
//                        Assert.assertTrue(actualResult.contains(expectedResult),"Added To Cart msg ASSERTION");
//                        System.out.println(numberOfItems++ + " is the number of items added so far to cart");
//                    }
//                    catch(AssertionError e)
//                    {
//                        System.out.println(products.get(i).getText() +" is the product that has not been added to cart");
//                        driver.navigate().back();
//                    }


                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e)
                    {
                        throw new RuntimeException(e);
                    }
                    doubleBack();
                }
                else
                {
                    driver.navigate().back();
                }
            }
        }
        if(numberOfItems == 0)
        {
            nextPage();
        }
        else
        {
            jsScroll(cartBtn);
//            cartBtn.click();
        }


    }

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
