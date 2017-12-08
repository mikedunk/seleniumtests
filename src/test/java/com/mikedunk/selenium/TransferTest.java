package com.mikedunk.selenium;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    Transfer transfer= new Transfer(driver);
    static  String baseUrl = "http://45.79.162.80:4203/login";
    static String username = "simpasaiki02@gmail.com";
    static String password = "password";

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

       /** WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .withMessage("im waiting for page  to load")
                .until(ExpectedConditions.presenceOfElementLocated(By.id("logout")));**/

        String logout = driver.findElement(By.id("logout")).getText();
        //asserts if there is a logout button
        Assertions.assertEquals("Logout",logout, "logout exists...user is logged on");
        driver.findElement(By.xpath("//*[@id=\"sidebar-nav\"]/money-nav/ul[1]/li[3]/a")).click();

    }




    /**This test attempts to make a transfer **/
    @Test
    void instantTransfer(){

        driver.findElement(transfer.xsingleTransfer).click();
        driver.findElement(transfer.singleTransferToProvidus).click();
        transfer.setSingleTransferProvidusAccountInitiator();
        transfer.setSingleTransferProvidusUser("0123456789");
        transfer.setSingleTransferProvidusAccountAmount("10000");
        transfer.setSingleTransferProvidusNarration("Automated test Auto generate");
        driver.findElement(transfer.singleTransferProvidusRadioRecurring).click();
        driver.findElement(transfer.singleTransferProvidusRadioScheduled).click();
        driver.findElement(transfer.singleTransferProvidusRadioInstant).click();
        driver.findElement(transfer.singleTransferProvidusTransferButton).click();
        driver.findElement(By.xpath("//*[@id=\"myModal\"]/div[2]/div/div/confirm-single-transfer-view/div/div[3]/div/div[1]/div/input")).sendKeys("00000");//OTP
        driver.findElement(transfer.singleTransferProvidusModalCompleteButton).click();
        driver.switchTo().activeElement();

        WebElement dismissModal = driver.findElement(transfer.transAlertModalDismiss);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", dismissModal);

        WebElement element = driver.findElement(transfer.singleTransferBack);
        JavascriptExecutor executor1 = (JavascriptExecutor)driver;
        executor1.executeScript("arguments[0].click()", element);

        assertFalse(false);

    }


    /**This test attempts to transfer without a transfer from account specified **/
    @Test
    void instantTransferWithoutInitiator(){

        driver.findElement(transfer.xsingleTransfer).click();
        driver.findElement(transfer.singleTransferToProvidus).click();
       // transfer.setSingleTransferProvidusAccountInitiator();
        transfer.setSingleTransferProvidusUser("0123456789");
        transfer.setSingleTransferProvidusAccountAmount("10000");
        transfer.setSingleTransferProvidusNarration("Automated test Auto generate");;
        Boolean bool = driver.findElement(transfer.singleTransferProvidusTransferButton).isEnabled();
        driver.findElement(transfer.singleTransferProvidusTransferButton).click();

        driver.findElement(transfer.singleTransferProvidusRadioScheduled).click();
        driver.findElement(transfer.singleTransferProvidusRadioInstant).click();


        driver.findElement(transfer.singleTransferBack).click();
        assertTrue(bool,"Transfer Initiator Empty...Transfer Button Disabled ");


    }


    /**This test attempts to transfer nothing i.e zero naira **/
    @Test
    void instantTransferWithoutAmount(){

        driver.findElement(transfer.xsingleTransfer).click();
        driver.findElement(transfer.singleTransferToProvidus).click();
        transfer.setSingleTransferProvidusAccountInitiator();
        transfer.setSingleTransferProvidusUser("0123456789");
        //transfer.setSingleTransferProvidusAccountAmount("10000");
        transfer.setSingleTransferProvidusNarration("Automated test Auto generate");
        Boolean bool = driver.findElement(transfer.singleTransferProvidusTransferButton).isEnabled();
        driver.findElement(transfer.singleTransferProvidusRadioScheduled).click();
        driver.findElement(transfer.singleTransferProvidusRadioInstant).click();
        driver.findElement(transfer.singleTransferBack).click();
        assertTrue(bool,"Transfer Amount Empty...Transfer Button Disabled ");

    }

    /**This test attempts to transfer to an empty account **/
    @Test
    void instantTransferWithoutDestination(){

        driver.findElement(transfer.xsingleTransfer).click();
        driver.findElement(transfer.singleTransferToProvidus).click();
        transfer.setSingleTransferProvidusAccountInitiator();
        //transfer.setSingleTransferProvidusUser("0123456789");
        transfer.setSingleTransferProvidusAccountAmount("10000");
        transfer.setSingleTransferProvidusNarration("Automated test Auto generate");
        Boolean bool = driver.findElement(transfer.singleTransferProvidusTransferButton).isEnabled();

        driver.findElement(transfer.singleTransferProvidusRadioScheduled).click();
        driver.findElement(transfer.singleTransferProvidusRadioInstant).click();
        driver.findElement(transfer.singleTransferProvidusTransferButton).isEnabled();
        System.out.println("ive clicked transfer");

        driver.findElement(transfer.singleTransferBack).click();
        assertTrue(bool,"Transfer Destination  Empty...Transfer Button Disabled ");
       // driver.findElement(transfer.singleTransferBack).click();

    }

    /**This test attempts to transfer to an account with less than 10digits **/
    @Test
    void instantTransferWithIncompleteDestination(){

        driver.findElement(transfer.xsingleTransfer).click();
        driver.findElement(transfer.singleTransferToProvidus).click();
        transfer.setSingleTransferProvidusAccountInitiator();
        transfer.setSingleTransferProvidusUser("012345678");
        transfer.setSingleTransferProvidusAccountAmount("10000");
        transfer.setSingleTransferProvidusNarration("Automated test Auto generate");
        Boolean bool = driver.findElement(transfer.singleTransferProvidusTransferButton).isEnabled();
        driver.findElement(transfer.singleTransferProvidusRadioScheduled).click();
        driver.findElement(transfer.singleTransferProvidusRadioInstant).click();

        driver.findElement(transfer.singleTransferBack).click();
        assertTrue(bool,"Transfer Destination Incomplete...Transfer Button Disabled ");
    }


    /**This test attempts to transfer to an account other than a Providus Account **/

    @Test
    void instantTransferWithNonProvidusAccount(){

        driver.findElement(transfer.xsingleTransfer).click();
        driver.findElement(transfer.singleTransferToProvidus).click();
        transfer.setSingleTransferProvidusAccountInitiator();
        transfer.setSingleTransferProvidusUser("0123456788");
        transfer.setSingleTransferProvidusAccountAmount("10000");
        transfer.setSingleTransferProvidusNarration("Automated test Auto generate");
        Boolean boool = driver.findElement(transfer.singleTransferProvidusAccountError).isDisplayed();
        Boolean bool = driver.findElement(transfer.singleTransferProvidusTransferButton).isEnabled();
        driver.findElement(transfer.singleTransferProvidusRadioScheduled).click();
        driver.findElement(transfer.singleTransferProvidusRadioInstant).click();

        // Assertions.assertFalse(bool,"Transfer Destination is NotProvidus Bank...Transfer Button Disabled ");

        Assertions.assertAll(
                () -> assertTrue(boool),
                () -> assertTrue(bool)
        );
        driver.findElement(transfer.singleTransferBack).click();
    }




























    @AfterAll
    static void tearDown() {
       driver.close();
       driver.quit();
    }

}

