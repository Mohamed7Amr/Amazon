# Foodics(Amazon) Project - UI Automated Tests
# Important Note: Before trying to run the script or re-run the script, sign-in to amazon with username:"fihones827@mahmul.com" and password:"12345678", and delete any saved addresses from Account-settings. 

This repository will be used to create and execute automated tests to the Foodics-Amazon Project.  
To be able to do that, below we can find some insights about:
1. System Requirements
2. Project Structure
3. How to run tests

## 1. System Requirements
In order to be able to work with this repository and run the automated tests, we need to previously install the 
following tools and plugins:
- Java - https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
- Maven - https://maven.apache.org/install.html
- Intellij Idea - https://www.jetbrains.com/idea/download/#section=windows
  
  - Dependencies
        |_ cucumber-testng <7.11.2>
        |_ selenium-java <4.12.0>
        |_ cucumber-java <7.11.2>
        
        

After install the above tools and plugins, make sure you have a local settings.xml file under your .m2 folder and, 
that the settings.xml file is connecting to a maven repository so that Intellij can export all the needed dependencies.    


## 2. Project Structure
    src
        |_ main
            |_ java
                |_ Helpers
                    |_ DataType
                    |_ JsInjections
                |_ Helpers
                    |_ AddAddressPage
                    |_ CheckoutPage
                    |_ HomePage
                    |_ LoginPage
                    |_ ShoppingCartPage
                    |_ VideoGamesPage
            |_ Features
                |_ BuyVideoGames.feature
                
        |_ test
            |_ java
                |_ Runners
                    |_TestRunner
                |_ StepDefintions
                    |_BuyVideoGamesSteps
                    |_Hooks
                |_unitTests
                    |_AddressInfo
    target
    TessData
    pom.xml
    

There are 2 important groups of files to be placed to create a test:
- **Feature**
  - Can be found under: src/main/resources/features
  - Written in Gherkin language and mirroring business scenarios using a readable and accessible language for everybody.
- **StepDefinitions**
  - Can be found under: src/test/java/org.example/stepDefs
  - They are written in Java language and, they define what is supposed to be done for each of the steps from one 
    business scenario described in the Feature file.
    
Aside from these, we also have some other folders and/or files important to keep the project working:
- **pom.xml**
  - As this is a Maven project, which is base on the concept of a Project Object Model (POM),
  we must have a pom.xml file to manage all the required dependencies and plugins
- **target**
  - This is a folder which contains our code (after packaging it) and our reports
  - The report is located under the target file as "cucumber.html"

## 3. How to run tests
There are 2 ways to run tests locally:
- Opening the feature from the features file, clicking in the play button (at left) of the feature or of the scenario.
- Using the testRunner to run and generate the report needed(based on configurations).
