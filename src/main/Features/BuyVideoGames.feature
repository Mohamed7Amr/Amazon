@regression

Feature: Logged-in user adds products to the cart

  #Scenario1
  Scenario: Logged-in user can add video games to the cart
    When clicks on All menu
    And clicks on video games
    And clicks all video games list item
    Then user is surfing Video Games webpage
    When clicks on free shipping checkbox
    And clicks on New condition
    And sorts the products by price from high to low
    And adds any product to cart that its cost below 15k EGP
    When clicks on cart
    Then all products are there with their subtotal correctly calculated
    Given User clicks on proceed to purchase and be redirected to login page
    When logs-in using valid username and valid password
    Then user is redirected to shipping address details
    When inserts full-name "John Doe" and mobile-number "1113599511"
    And inserts street-name "talaat harb" and building-number "8"
    And inserts City as "Cairo"
    And inserts district as "1 1st Settlement"
    And adds "Cairo-Festival" landmark to the address
    And chooses address-type as Home
    And confirm Adding the address to the delivery-shipment
    Then check order total amount


