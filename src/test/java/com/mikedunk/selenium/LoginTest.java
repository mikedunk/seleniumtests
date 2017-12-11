package com.mikedunk.selenium;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.jupiter.api.*;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import  org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
 class  LoginTest {



   public static String username;
   public static String password;
   public static String baseUrl;
   private static  WebDriver driver = new ChromeDriver();
   private static   Login login;
   static ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("src/testreports/logineports.html");
   static ExtentReports extent ;
   ExtentTest logger ;


    private static void navigate()throws WebDriverException{

       driver.navigate().to( baseUrl);
      // driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    };


    public static void init(){
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        try {
            File file = new File("src/test/test.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

            username = properties.getProperty("seleniumtests.username");
            password = properties.getProperty("seleniumtests.password");
            baseUrl = properties.getProperty("seleniumtests.baseUrl");

            }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @BeforeAll
    static  void setUp() {
       init();
       navigate();

    }


    @Test
   void validLogin() {
        //this method tests valid login credentials
       logger = extent.createTest("Valid Login ", "Logs In to App With Right Credentials") ;
       Login login= new Login(driver);
       login.logonToSite(username,password);
       String status =login.checkIfLoggedOn();
       Assertions.assertEquals("Logout",status);
       logger.log(Status.PASS,"Logged on successfully" );




    }
    @Test
     void falseIdLogin(){
        //this method tests invalid login/password combo
        logger = extent.createTest("Invalid Credentials","Attempts a login with wrong Credentials");
        login = new Login(driver);
        login.logonToSite("invalid", "#########");
        String status1 =login.checkIfLoggedOn();
       if (status1 =="logOut") {
           Assertions.assertNotEquals("Logout", status1);
           logger.log(Status.FAIL, "logged in");
       }
       else  logger.log(Status.PASS, "Couldn't log in...Invalid Credentials ");
    }

    @Test
     void correctUserInvalidPass(){
       logger = extent.createTest("Invalid Password ", "Attempts to Login with Invalid Password") ;
       login = new Login(driver);
       login.logonToSite(username, "\u00A0");
        String status1 =login.checkIfLoggedOn();
        if (status1 =="logOut") {
            Assertions.assertNotEquals("Logout", status1);
            logger.log(Status.FAIL, "logged in");
        }
        else  logger.log(Status.PASS, "Couldn't log in...Invalid Credentials ");
    }

    @Test
    void correctPassInvalidUser(){
        logger = extent.createTest("Invalid User ", "Attempts to Login with Invalid Username") ;
        login = new Login(driver);

        login.logonToSite("invalidUser",password);
        String status1 =login.checkIfLoggedOn();
        if (status1 =="logOut") {
            Assertions.assertNotEquals("Logout", status1);
            logger.log(Status.FAIL, "logged in");
        }
        else  logger.log(Status.PASS, "Couldn't log in...Invalid Credentials ");
    }

    @Test
    void blankInput() {
        logger = extent.createTest("Blank Input ", "Attempts to Login with Blank Credentials") ;
        login = new Login(driver);
        login.logonToSite("", "");
        String status1 =login.checkIfLoggedOn();
        if (status1 =="logOut") {
            Assertions.assertNotEquals("Logout", status1);
            logger.log(Status.FAIL, "logged in");
        }
        else  logger.log(Status.PASS, "Couldn't log in...Invalid Credentials ");
    }


    @Test
    void noPassword(){
        logger = extent.createTest("No Password ", "Attempts to Login without Password") ;
        login = new Login(driver);
        login.logonToSite(username,"");
        String status1 =login.checkIfLoggedOn();
        if (status1 =="logOut") {
            Assertions.assertNotEquals("Logout", status1);
            logger.log(Status.FAIL, "logged in");
        }
        else  logger.log(Status.PASS, "Couldn't log in...Invalid Credentials ");
    }

    @Test
    void noUsername(){
        logger = extent.createTest("No Username ", "Attempts to Login with No Username") ;
        login = new Login(driver);
       // login.setUsername("");
        login.logonToSite("", "password");
        String status1 =login.checkIfLoggedOn();
        if (status1 =="logOut") {
            Assertions.assertNotEquals("Logout", status1);
            logger.log(Status.FAIL, "logged in");
        }
        else  logger.log(Status.PASS, "Couldn't log in...Invalid Credentials ");
    }

    @Test
    void signUp(){
        //implement sign up or create new user 

    }
    @AfterEach
     void clear (){
        driver.findElement(login.xpassword).clear();
        driver.findElement(login.xusername).clear();

    }



    @AfterAll
    static void tearDown() {
        //LOG and send mail
        extent.flush();
        driver.close();
        driver.quit();

    }

}