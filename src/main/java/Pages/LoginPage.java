package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LoginPage {

    /*********************************************GLOBAL_VARIABLES************************************************/

    private WebDriver driver;

    /*********************************************CONSTRUCTORS****************************************************/

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    /*********************************************SETTERS_&_GETTERS**************************************************/


    /*********************************************WEB_ELEMENTS********************************************************/

    /*Some notations regarding variables naming:
1) variables' suffix "Li" means it's an ordered-list-item element.
2) variables' suffix "Categ" means it's a category element.
3) variables' suffix "Chbox" means it's a checkbox element.
     */

    @FindBy(id = "ap_email")
    WebElement email_mobileTxt;

    @FindBy(id = "continue")
    WebElement continueBtn;

    @FindBy(id = "ap_password")
    WebElement passwordTxt;

    @FindBy(id = "signInSubmit")
    WebElement signInBtn;


    /*******************************************METHODS**********************************************************/

    public void login(String username, String password)
    {
        email_mobileTxt.sendKeys(username);
        continueBtn.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        passwordTxt.sendKeys(password);
        signInBtn.click();
    }

}
