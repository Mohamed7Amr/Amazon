package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * @author Mohamed_Amr
 */
public class LoginPage extends PageBase {

    /*********************************************GLOBAL_VARIABLES************************************************/


    /*********************************************CONSTRUCTORS****************************************************/

    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    /*********************************************WEB_ELEMENTS********************************************************/

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
        writeText(email_mobileTxt, username);
        clickWebElement(continueBtn);

        writeText(passwordTxt, password);
        clickWebElement(signInBtn);
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
