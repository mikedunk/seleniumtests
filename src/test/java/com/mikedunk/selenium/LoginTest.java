package com.mikedunk.selenium;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
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


    private static void navigate(){

       driver.navigate().to( baseUrl);
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    };


    public static void init(){
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

       Login login= new Login(driver);
       login.logonToSite(username,password);

       String logout = driver.findElement(By.id("logout")).getText();
       Assertions.assertEquals("Logout",logout);
       driver.findElement(By.id("logout")).click();



    }
    @Test
     void falseIdLogin(){
        //this method tests invalid login/password combo
        login = new Login(driver);
        login.logonToSite("invalid", "#########");
        Boolean bool = driver.findElement(login.errorDiv).isEnabled();
        System.out.println(bool);
        Assertions.assertTrue(bool);

    }

    @Test
     void correctUserInvalidPass(){
        login = new Login(driver);
        login.logonToSite(username, "\u00A0");
        Boolean bool = driver.findElement(login.errorDiv).isEnabled();
        System.out.println(bool);
        Assertions.assertTrue(bool);
    }


    @Test
    void correctPassInvalidUser(){
        login = new Login(driver);

        login.logonToSite("invalidUser",password);
        Boolean bool = driver.findElement(login.errorDiv).isEnabled();
        System.out.println(bool);
        Assertions.assertTrue(bool);
    }

    @Test
    void blankInput()  {
        login = new Login(driver);

        login.logonToSite("","");
        Boolean bool =  driver.findElement(login.xsubmit).isEnabled();
        Assertions.assertFalse(bool);
    }



    @Test
    void noPassword(){
        login = new Login(driver);
        login.setUsername("username");
        Boolean bool =  driver.findElement(login.xsubmit).isEnabled();
        Assertions.assertFalse(bool);
    }

    @Test
    void noUsername(){
        login = new Login(driver);
        login.logonToSite("",password);
        Boolean bool =  driver.findElement(login.xsubmit).isEnabled();
        Assertions.assertFalse(bool);
    }

    @Test
    void signUp(){
        //implement sign up or create new user 

    }



    @AfterAll
    static void tearDown() {
        //LOG and send mail
        driver.close();
        driver.quit();

    }

}