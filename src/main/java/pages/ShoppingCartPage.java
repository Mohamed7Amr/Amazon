package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;


/**
 * @author Mohamed_Amr
 */
public class ShoppingCartPage extends PageBase {


     /*********************************************GLOBAL_VARIABLES************************************************/

    WebDriverWait wait;

    /*********************************************CONSTRUCTORS****************************************************/

    public ShoppingCartPage(WebDriver driver)
    {
        super(driver);
    }

    /*********************************************WEB_ELEMENTS********************************************************/

    /**
     * Some notations regarding variables naming:
     * 1) variables' suffix "Btn" means it's a Button element.
     */

    @FindBy(xpath = "//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']")
    List<WebElement> cartItemsPrices;

    @FindBy(xpath = "//span[@id='sc-subtotal-amount-buybox']/span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']")
    public WebElement subtotal;

    @FindBy(css = "input[type='submit'][value='Proceed to checkout']")
    WebElement proceedToBuyBtn;

    /*******************************************METHODS**********************************************************/

    /**
     * User checks if the sum of the prices of the products are equal to the one displayed in the subtotal web-element
     */
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
        jsClick(proceedToBuyBtn);
    }


}
