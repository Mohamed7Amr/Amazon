package Pages;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Mohamed_Amr
 */
public class LoginPage {

    /*********************************************GLOBAL_VARIABLES************************************************/

    private WebDriver driver;

    /*********************************************CONSTRUCTORS****************************************************/

    public LoginPage(WebDriver driver)
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

    @FindBy(id = "ap_email")
    WebElement email_mobileTxt;

    @FindBy(id = "continue")
    WebElement continueBtn;

    @FindBy(id = "ap_password")
    WebElement passwordTxt;

    @FindBy(id = "signInSubmit")
    WebElement signInBtn;


    /*******************************************METHODS**********************************************************/

    /**
     * User signs-in with his valid credentials to access his account
     * @param username is the username of which the user used to sign-up (create account on Amazon)
     * @param password is the password of which the user used to sign-up (create account on Amazon)
     * @return void
     */
    public void login(String username, String password)
    {
        email_mobileTxt.sendKeys(username);
        continueBtn.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        passwordTxt.sendKeys(password);
        signInBtn.click();
    }

    /**
     * that method would be applied if there was still a captcha
     */
//    public void solveCaptcha() throws IOException {
//        WebElement imageElement = driver.findElement(By.xpath("//img[@src='http://cdn.magento-demo.lexiconn.com/skin/frontend/rwd/default/images/media/logo.png'"));
//        File src = imageElement.getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(src, new File("./target/captcha.png"));
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        ITesseract image = new Tesseract();
//        try {
//            String str = image.doOCR(new File("./target/captcha.png"));
//        } catch (TesseractException e) {
//            throw new RuntimeException(e);
//        }
//    }



}
