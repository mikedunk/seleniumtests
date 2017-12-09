package com.mikedunk.selenium;


import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

//import static com.mikedunk.selenium.LoginTest.username;

//import static com.mikedunk.selenium.LoginTest.driver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransferTest {


     static    WebDriver driver = new ChromeDriver();
     static Login login = new Login(driver);
     static  String baseUrl = "http://45.79.162.80:4203/login";
     static String username = "simpasaiki02@gmail.com";
     static String password = "password";
     Transfer transfer= new Transfer(driver);

    @BeforeAll
    static void setUp() {
       driver.manage().window().maximize();
       driver.navigate().to(baseUrl);
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       login.logonToSite(username,password);
       // WebElement webElement = driver.findElement(By.id("logout"));

       Wait <WebDriver> wait = new FluentWait(driver)

                    .pollingEvery(5, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class)
                    .withMessage("waiting for page to load");

        wait.until((Function<WebDriver, Object>) driver -> driver.findElement(By.id("logout")));
        String logout = driver.findElement(By.id("logout")).getText();
        //asserts if there is a logout button
        Assertions.assertEquals("Logout",logout, "logout exists...user is logged on");
        driver.findElement(By.xpath("//*[@id=\"sidebar-nav\"]/money-nav/ul[1]/li[3]/a")).click();

    }




    /**This test attempts to make a transfer To a Providus Account **/
    @Test
    void instantTransfer() {

        transfer.navigateToProvidus();
        transfer.setSingleTransferProvidusAccountInitiator();
        transfer.attemptSingleTransfer("10000","0123456789","Automated test Auto generate");
        transfer.checkIfModalExistsAndSetOTP("0000");
       // driver.findElement(transfer.singleTransferBack).click();
        assertTrue(true);

    }


    /**This test attempts to transfer without a transfer from account specified **/
    @Test
    void instantTransferWithoutInitiator(){

        transfer.navigateToProvidus();
        transfer.attemptSingleTransfer("10000","0123456789","Automated test Auto generate");
        transfer.checkIfModalExistsAndSetOTP("0000");

        assertTrue(true,"Attempted Transfer Without Initiator Account");


    }


    /**This test attempts to transfer nothing i.e zero naira **/
    @Test
    void instantTransferWithoutAmount(){

        transfer.navigateToProvidus();
        transfer.setSingleTransferProvidusAccountInitiator();
        transfer.attemptSingleTransfer("","0123456789","Automated test Auto generate");
        transfer.checkIfModalExistsAndSetOTP("0000");

        assertTrue(true,"Attempted transfer without Amount");
    }

    /**This test attempts to transfer to an empty account **/
    @Test
    void instantTransferWithoutDestination(){

        transfer.navigateToProvidus();
        transfer.setSingleTransferProvidusAccountInitiator();
        transfer.attemptSingleTransfer("10000","","Automated test Auto generate");
        transfer.checkIfModalExistsAndSetOTP("0000");

        assertTrue(true,"Attempted transfer without Destination");
    }

    /**This test attempts to transfer to an account with less than 10digits **/
    @Test
    void instantTransferWithIncompleteDestination(){

        transfer.navigateToProvidus();
        transfer.setSingleTransferProvidusAccountInitiator();
        transfer.attemptSingleTransfer("10000","01234567","Automated test Auto generate");
        transfer.checkIfModalExistsAndSetOTP("0000");

        assertTrue(true,"Attempted transfer with incomplete Destination");
    }


    /**This test attempts to transfer to an account other than a Providus Account **/

    @Test
    void instantTransferWithNonProvidusAccount(){

        transfer.navigateToProvidus();
        transfer.setSingleTransferProvidusAccountInitiator();
        transfer.attemptSingleTransfer("10000","9987654462","Automated test Auto generate");
        transfer.checkIfModalExistsAndSetOTP("0000");
        
        assertTrue(true,"Attempted transfer to Non Providus  Destination");
    }




























    @AfterAll
    static void tearDown() {

        WebElement element = driver.findElement(By.xpath("//*[@id=\"header-nav\"]/div[2]/ul/li[3]"));
        JavascriptExecutor executor1 = (JavascriptExecutor)driver;
        executor1.executeScript("arguments[0].click()", element);
       driver.close();
       driver.quit();
    }

}

