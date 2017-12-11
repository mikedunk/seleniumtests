package com.mikedunk.selenium;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
;

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

     static ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("src/testreports/singletransfertestreports.html");
     static ExtentReports extent ;
     ExtentTest logger ;


    @BeforeAll
    static void setUp() throws InvalidElementStateException {
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
        Assertions.assertEquals("Logout",logout, "user exists...user is logged on");
        driver.findElement(By.xpath("//*[@id=\"sidebar-nav\"]/money-nav/ul[1]/li[3]/a")).click();
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        //htmlReporter.setAppendExisting(true);-> enable if you want it to append
        htmlReporter.config().setDocumentTitle("Selenium Tests ");
        htmlReporter.config().setReportName("Single Transfer Test");
       // extent.setSystemInfo("Environment", "Automation Testing");

       }




    /**This test attempts to make a transfer To a Providus Account **/
    @Test
    void instantTransfer() {

        logger = extent.createTest("Instant Transfer", "Attempts to make a Transfer");
        transfer.navigateToProvidus();
        transfer.setSingleTransferProvidusAccountInitiator();
        transfer.attemptSingleTransfer("10000","0123456789","Automated test Auto generate");
        transfer.checkIfModalExistsAndSetOTP("0000", logger);
        assertTrue(true);
        logger.log(Status.PASS , "transfer Was Unsuccessful due to  Invalid OTP");


    }

    /**This test attempts to transfer without OTP**/
     @Test
    void instantTransferWithoutOTP() {
        logger = extent.createTest("Single Transfer Without OTP","Attempt to make a tranfer Without an OTP");
        transfer.navigateToProvidus();
        transfer.setSingleTransferProvidusAccountInitiator();
        transfer.attemptSingleTransfer("10000","0123456789","Automated test Auto generate");
        transfer.checkIfModalExistsAndSetOTP("", logger);
        assertTrue(true);
        logger.log(Status.PASS , "Transfer Was Unsuccessful : No OTP Supplied");

    }


    /**This test attempts to transfer without a transfer from account specified **/
    @Test
    void instantTransferWithoutInitiator(){
        logger = extent.createTest("Single Transfer Without Initiator Account ","Attempts to make a transfer Without an Initiator Account");
        transfer.navigateToProvidus();
        transfer.attemptSingleTransfer("10000","0123456789","Automated test Auto generate");
        transfer.checkIfModalExistsAndSetOTP("0000", logger);
        assertTrue(true,"Attempted Transfer Without Initiator Account");
        logger.log(Status.PASS , "Transfer Was Unsuccessful due to Invalid Transfer Initiator Account");


    }


    /**This test attempts to transfer nothing i.e zero naira **/
    @Test
    void instantTransferWithoutAmount(){
        logger = extent.createTest("SingleTransfer Without OTP","Attempt to make a tranfer Without an OTP");
        transfer.navigateToProvidus();
        transfer.setSingleTransferProvidusAccountInitiator();
        transfer.attemptSingleTransfer("","0123456789","Automated test Auto generate");
        transfer.checkIfModalExistsAndSetOTP("0000", logger);
        assertTrue(true,"Attempted transfer without Amount");
        logger.log(Status.PASS , "transfer Was Unsuccessful because of Invalid OTP");
    }

    /**This test attempts to transfer to an empty account **/
    @Test
    void instantTransferWithoutDestination(){
        logger = extent.createTest("Single Transfer Without Destination","Attempts to make a transfer Without a Destination Account");
        transfer.navigateToProvidus();
        transfer.setSingleTransferProvidusAccountInitiator();
        transfer.attemptSingleTransfer("10000","","Automated test Auto generate");
        transfer.checkIfModalExistsAndSetOTP("0000", logger);
        assertTrue(true,"Attempted transfer without Destination");
        logger.log(Status.PASS , "Transfer Was Unsuccessful : No Destination Provided");
    }

    /**This test attempts to transfer to an account with less than 10digits **/
    @Test
    void instantTransferWithIncompleteDestination(){
        logger = extent.createTest("Single Transfer to Incomplete Destination","Attempts to make a transfer to an Incomplete Account Number ");
        transfer.navigateToProvidus();
        transfer.setSingleTransferProvidusAccountInitiator();
        transfer.attemptSingleTransfer("10000","01234567","Automated test Auto generate");
        transfer.checkIfModalExistsAndSetOTP("0000", logger);
        assertTrue(true,"Attempted transfer with incomplete Destination");
        logger.log(Status.PASS , "Transfer Was Unsuccessful :Incomplete Destination");
    }


    /**This test attempts to transfer to an account other than a Providus Account **/

    @Test
    void instantTransferWithNonProvidusAccount(){
        logger = extent.createTest("Single Transfer to Non Providus Account ","Attempts to make a transfer to another Bank");
        transfer.navigateToProvidus();
        transfer.setSingleTransferProvidusAccountInitiator();
        transfer.attemptSingleTransfer("10000","9987654462","Automated test Auto generate");
        transfer.checkIfModalExistsAndSetOTP("0000", logger);
        assertTrue(true,"Attempted transfer to Non Providus  Destination");
        logger.log(Status.PASS , "Transfer Was Unsuccessful: Unrecognized Destination");
    }








    @AfterAll
    static void tearDown() {
        extent.flush();

        WebElement element = driver.findElement(By.xpath("//*[@id=\"header-nav\"]/div[2]/ul/li[3]"));
        JavascriptExecutor executor1 = (JavascriptExecutor)driver;
        executor1.executeScript("arguments[0].click()", element);

       driver.close();
       driver.quit();
    }

}

