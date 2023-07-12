package StepDefinitions;

import Pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;

public class BuyVideoGamesSteps {

    HomePage hp = new HomePage(Hooks.driver);

    LoginPage lp = new LoginPage(Hooks.driver);
    VideoGamesPage vgp = new VideoGamesPage(Hooks.driver);
    ShoppingCartPage scp = new ShoppingCartPage(Hooks.driver);
    CheckoutPage cp = new CheckoutPage(Hooks.driver);


//    @Given("^User logs-in to his amazon account using username \"(.*)\" and password \"(.*)\"$")
//    public void sigIn()
//    {
//        lp.login();
//    }

    @When("clicks on All menu")
    public void openAllMenu()
    {
        hp.chooseEnglishLanguage();
        hp.clickAllBtn();
    }

    @And("clicks on video games")
    public void chooseVideoGames()
    {
        hp.openVideoGamesLi();
    }

    @And("clicks all video games list item")
    public void chooseAllVideoGames()
    {
        hp.clickAllVideoGamesLi();
    }

    @Then("user is surfing Video Games webpage")
    public void surfVideoGamesScreen()
    {
        vgp.videoGamesScreenConfirmation();
    }

    @When("clicks on free shipping checkbox")
    public void chooseFreeShipping()
    {
        vgp.chooseFreeShipping();
    }

    @And("clicks on New condition")
    public void chooseConditionNew()
    {
        vgp.chooseNewCondition();
    }

    @And("sorts the products by price from high to low")
    public void sortPriceHighToLow()
    {
        vgp.sortPriceHighLow();
    }

    @And("adds any product to cart that its cost below 15k EGP")
    public void addVideoGamesProducts()
    {
        vgp.addToCart();
    }

    @And("clicks on cart")
    public void openCart()
    {
        vgp.goToCart();
    }

    @Then("all products are there with their subtotal correctly calculated")
    public void allProductsInCart()
    {
        scp.confirmSubtotal();
    }

    @Given("User clicks on proceed to purchase and be redirected to login page")
    public void proceedToPurchase()
    {
        scp.proceedToBuy();
    }

    @When("^logs-in using username \"(.*)\" and password \"(.*)\"$")
    public void enterLoginCredentials(String username, String password)
    {
        lp.login(username,password);
    }

    @Then("user is redirected to shipping address details")
    public void shippingAddressScreen()
    {
        cp.shippingAddressDetailsDisplay();
    }

    @When("^inserts full-name \"(.*)\" and mobile-number \"(.*)\"$")
    public void insertNameAndMobile(String name, String mobile)
    {
        cp.insertFullName(name);
        cp.insertMobileNumber(mobile);
    }

    @And("^inserts street-name \"(.*)\" and building-number \"(.*)\"$")
    public void insertStreetAndBuilding(String street, String building)
    {
        cp.insertStreetName(street);
        cp.insertBuildingNumber(building);
    }

    @And("inserts City as New Cairo City")
    public void insertCityName()
    {
        cp.selectCity();
    }

    @And("inserts district as 1 (1st Settlement)")
    public void insertDistrictName()
    {
        cp.selectDistrict();
    }

    @And("chooses address-type as Home")
    public void chooseAddressType()
    {
        cp.chooseAddressType();
    }

}
