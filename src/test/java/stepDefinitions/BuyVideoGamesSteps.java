package stepDefinitions;

import dataReader.LoadProperties;
import pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BuyVideoGamesSteps {

    /***********************************************GLOBAL_VARIABLES*************************************************/

    private HomePage hp = new HomePage(Hooks.driver);
    private LoginPage lp = new LoginPage(Hooks.driver);
    private VideoGamesPage vgp = new VideoGamesPage(Hooks.driver);
    private ShoppingCartPage scp = new ShoppingCartPage(Hooks.driver);
    private AddAddressPage aap = new AddAddressPage(Hooks.driver);
    private CheckoutPage cp = new CheckoutPage(Hooks.driver);
    private String username = LoadProperties.userTestData.getProperty("username");
    private String password = LoadProperties.userTestData.getProperty("password");


    /************************************************TEST_STEPS******************************************************/

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

    @When("logs-in using valid username and valid password")
    public void enterLoginCredentials()
    {
        lp.login(username,password);
    }

    @Then("user is redirected to shipping address details")
    public void shippingAddressScreen()
    {
        aap.shippingAddressDetailsDisplay();
    }

    @When("^inserts full-name \"(.*)\" and mobile-number \"(.*)\"$")
    public void insertNameAndMobile(String name, String mobile)
    {
        aap.insertFullName(name);
        aap.insertMobileNumber(mobile);
    }

    @And("^inserts street-name \"(.*)\" and building-number \"(.*)\"$")
    public void insertStreetAndBuilding(String street, String building)
    {
        aap.insertStreetName(street);
        aap.insertBuildingNumber(building);
    }

    @And("^inserts City as \"(.*)\"$")
    public void insertCityName(String cityName) throws InterruptedException {
        aap.selectCity(cityName);
    }

    @And("^inserts district as \"(.*)\"$")
    public void insertDistrictName(String districtName) throws InterruptedException {
        aap.selectDistrict(districtName);
    }

    @And("^adds \"(.*)\" landmark to the address$")
    public void addLandmarkToAddress(String landmarkName)
    {
        aap.addLandmark(landmarkName);
    }

    @And("chooses address-type as Home")
    public void chooseAddressType()
    {
        aap.chooseAddressType();
    }

    @And("confirm Adding the address to the delivery-shipment")
    public void addAddress()
    {
        aap.addAddressToDelivery();
    }

    @Then("check order total amount")
    public void checkTotalOrder()
    {
        cp.compareTotalAmount();
    }

}
