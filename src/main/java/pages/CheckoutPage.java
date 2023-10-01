package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

/**
 * @author Mohamed_Amr
 */
public class CheckoutPage extends PageBase {
    /*********************************************GLOBAL_VARIABLES************************************************/

    int x;
    String[][] table = new String[6][2];


    /*********************************************CONSTRUCTORS****************************************************/

    public CheckoutPage(WebDriver driver)
    {
        super(driver);
    }

    /*********************************************WEB_ELEMENTS********************************************************/

    @FindBy(css = "#subtotals-marketplace-table>table[class='a-normal small-line-height']")
    WebElement orderSummaryTable;

    @FindBy(xpath = "//div[@class='a-column a-span10']/h3[contains(.,'Offers')]")
    WebElement offersTitle;


    /***********************************************METHODS**********************************************************/

    /**
     * this method takes all the data that are in the table-html-element, and put it in 2D Array, so i can use the pieces of data in every cell as I want
     * I did this method as static, thus I can invoke in the "compareTotalAmount()" method in this class (CheckoutPage class)
     */
    public  void returnOrderSummaryTable()
    {
        List<WebElement> rows = orderSummaryTable.findElements(By.tagName("tr"));
        System.out.println("Number of rows = "+ rows.size());
        x = rows.size();

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
        waitElementVisibility(10, offersTitle);
        waitElementVisibility(10, orderSummaryTable);

        returnOrderSummaryTable();

        if(x<6)
        {
            Double itemsTotalAmount = Double.parseDouble(table[0][1].replace("EGP","").replace(",",""));
            Double shippingAndHandling = Double.parseDouble(table[1][1].replace("EGP","").replace(",",""));
//            Double promotionApplied = Double.parseDouble(table[3][1].replace("-EGP","").replace(",",""));

            Double expectedOrderTotal = itemsTotalAmount + shippingAndHandling;
            Double actualOrderTotal = Double.parseDouble(table[3][1].replace("EGP","").replace(",",""));

            Assert.assertEquals(actualOrderTotal,expectedOrderTotal,"total price confirmation Assertion msg");
        }

        else {
            Double itemsTotalAmount = Double.parseDouble(table[0][1].replace("EGP","").replace(",",""));
            Double shippingAndHandling = Double.parseDouble(table[1][1].replace("EGP","").replace(",",""));

            Assert.assertEquals(Double.parseDouble(table[2][1].replace("EGP","").replace(",","")),
                    itemsTotalAmount+shippingAndHandling,"Total before promotion is applied assertion");

            Double promotionApplied = Double.parseDouble(table[3][1].replace("-EGP","").replace(",",""));

            Double expectedOrderTotal = (itemsTotalAmount + shippingAndHandling)-promotionApplied;
            Double actualOrderTotal = Double.parseDouble(table[5][1].replace("EGP","").replace(",",""));

            Assert.assertEquals(actualOrderTotal,expectedOrderTotal,"total price confirmation Assertion msg");
        }


    }



}
