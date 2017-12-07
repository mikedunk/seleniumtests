package com.mikedunk.selenium;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

//import static com.mikedunk.selenium.LoginTest.username;

//import static com.mikedunk.selenium.LoginTest.driver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransferTest {


     static    WebDriver driver = new ChromeDriver();

     static Login login = new Login(driver);

    Transfer transfer= new Transfer(driver);
    static  String baseUrl = "http://45.79.162.80:4203/login";
    static String username = "simpasaiki02@gmail.com";
    static String password = "password";


    @BeforeAll


    static void setUp() {

        driver.navigate().to(baseUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        login.logonToSite(username,password);
       // WebElement webElement = driver.findElement(By.id("logout"));

       Wait <WebDriver> wait = new FluentWait(driver)

                    .pollingEvery(5, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class)
                    .withMessage("waiting for page to load");

        wait.until((Function<WebDriver, Object>) driver -> driver.findElement(By.id("logout")));

       /** WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .withMessage("im waiting for page  to load")
                .until(ExpectedConditions.presenceOfElementLocated(By.id("logout")));**/

        String logout = driver.findElement(By.id("logout")).getText();
        //asserts if there is a logout button
        Assertions.assertEquals("Logout",logout, "logout exists...user is logged on");


    }



       @Test
       void checkLinks(){
      //  WebDriver driver = new ChromeDriver();

      driver.findElement(transfer.xtransfer).click();
      driver.findElement(transfer.xmanageBeneficiary).click();
      driver.findElement(transfer.addNewBeneficiary).click();
      driver.findElement(transfer.logoutButton).click();
      //be sure to dismiss modal
      Assertions.assertTrue(true);


    }


    @Test
    void makeSingleTransfer(){
        driver.findElement(transfer.xtransfer).click();
        driver.findElement(transfer.xsingleTransfer).click();
        driver.findElement(transfer.singleTransferToProvidus);
        transfer.setSingleTransferProvidusAccountInitiator();

        transfer.setSingleTransferProvidusAccountAmount("0123456789");
        transfer.setSingleTransferProvidusUser("0123456789");
        Boolean bool = driver.findElement(transfer.singleTransferProvidusAccountError).isEnabled();




    }




    @AfterAll
    static void tearDown() {
       driver.close();
       driver.quit();
    }

}

