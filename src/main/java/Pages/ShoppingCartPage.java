package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class ShoppingCartPage {


     /*********************************************GLOBAL_VARIABLES************************************************/

    private WebDriver driver;

    /*********************************************CONSTRUCTORS****************************************************/

    public ShoppingCartPage(WebDriver driver)
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

    @FindBy(xpath = "//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']")
    List<WebElement> cartItemsPrices;

    @FindBy(xpath = "//span[@id='sc-subtotal-amount-buybox']/span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']")
    WebElement subtotal;

    @FindBy(css = "input[type='submit'][value='Proceed to checkout']")
    WebElement proceedToBuyBtn;

    /*******************************************METHODS**********************************************************/

    public void confirmSubtotal()
    {
        float sum = 0.0f;

        for(WebElement product: cartItemsPrices)
        {
            float productPrice;
            System.out.println(product.getText());
            productPrice = Float.parseFloat(product.getText().replace(",","").replace("EGP",""));
            sum+=productPrice;
        }
        float totalAmount = Float.parseFloat(subtotal.getText().replace(",","").replace("EGP",""));

        Assert.assertEquals(sum,totalAmount,"Correct Total Amount of Products Assertion ");
    }

    public void proceedToBuy()
    {
        proceedToBuyBtn.click();
    }






}
