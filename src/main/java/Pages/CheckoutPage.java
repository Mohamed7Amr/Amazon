package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

/**
 * @author Mohamed_Amr
 */
public class CheckoutPage {
    /*********************************************GLOBAL_VARIABLES************************************************/
    WebDriver driver;
    static String[][] table = new String[6][2];
    WebDriverWait wait;

    /*********************************************CONSTRUCTORS****************************************************/

    public CheckoutPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    /*********************************************WEB_ELEMENTS********************************************************/

    @FindBy(css = "#subtotals-marketplace-table>table[class='a-normal small-line-height']")
   static  WebElement orderSummaryTable;

//   static List<WebElement> rows = orderSummaryTable.findElements(By.tagName("tr"));

    /***********************************************METHODS**********************************************************/

    /**
     * this method takes all the data that are in the table-html-element, and put it in 2D Array, so i can use the pieces of data in every cell as I want
     * I did this method as static, thus I can invoke in the "compareTotalAmount()" method in this class (CheckoutPage class)
     */
    public static void returnOrderSummaryTable()
    {
        List<WebElement> rows = orderSummaryTable.findElements(By.tagName("tr"));
        System.out.println("Number of rows = "+ rows.size());

        for (int i =0; i< rows.size(); i*=1) {
            for (WebElement row : rows) {
                List<WebElement> cols = row.findElements(By.tagName("td"));

                for (int j = 0; j < cols.size(); j *= 1) {
                    for (WebElement col : cols) {

                        table[i][j] = col.getText();
                        System.out.print(table[i][j] + "\t");
                        j++;
                    }
                    System.out.println();
                }
                i++;
            }
        }
    }

    /**
     * this method makes sure that the total amount of the products is calculated correctly with the added delivery-fees (if applicable),
     * also with any deducted promotions (if applicable).
     * it must use the Double class to parse strings data that are displayed in the DOM (amounts of money) to numbers of Double data-type.
     */
    public void compareTotalAmount()
    {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(orderSummaryTable));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        returnOrderSummaryTable();

        Double itemsTotalAmount = Double.parseDouble(table[0][1].replace("EGP","").replace(",",""));
        Double shippingAndHandling = Double.parseDouble(table[1][1].replace("EGP","").replace(",",""));

        Double promotionApplied = Double.parseDouble(table[3][1].replace("-EGP","").replace(",",""));//-

        Double expectedOrderTotal = (itemsTotalAmount + shippingAndHandling)-promotionApplied;
        Double actualOrderTotal = Double.parseDouble(table[5][1].replace("EGP","").replace(",",""));

        Assert.assertEquals(actualOrderTotal,expectedOrderTotal,"total price confirmation Assertion msg");


    }



}
