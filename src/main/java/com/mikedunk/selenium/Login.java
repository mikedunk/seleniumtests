package com.mikedunk.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

  private   WebDriver driver;


  public By xusername = By.xpath("//*[@id=\"bs-carousel\"]/div[3]/div/div/div/div/div[1]/div[2]/form/div[1]/input");
  public By xpassword = By.xpath("/html/body/app-root/login/div/div[3]/div/div/div/div/div[1]/div[2]/form/div[2]/div/input");
  public By xcreateNew = By.xpath("/html/body/app-root/login/div/div[3]/div/div/div/div/div[2]/h5/a/u");// cant be tested cos the link is disabled
  public By xsubmit = By.xpath("/html/body/app-root/login/div/div[3]/div/div/div/div/div[1]/div[2]/form/button");
  public By errorDiv = By.xpath("/html/body/app-root/login/div/div[3]/div/div/div/div/div[1]/div[2]/form/div[1]/p");
  public By xlogout = By.xpath("//*[@id=\"logout\"]");

 // public By xeye = By.xpath("//*[@id=\"bs-carousel\"]/div[3]/div/div/div/div/div[1]/div[2]/form/div[2]/div/a/i");


    public Login(WebDriver driver){

        this.driver = driver;
    }


    public void setUsername(String username){

        driver.findElement(xusername).clear();
        driver.findElement(xusername).sendKeys(username);
    }


    public void setPassword(String password){

        driver.findElement(xpassword).clear();
        driver.findElement(xpassword).sendKeys(password);
    }


    public void clickLogin(){
        driver.findElement(xsubmit).click();
    }



    public void logonToSite(String username, String password){
        this.setUsername(username);
        this.setPassword(password);
        driver.findElement(By.id("bs-carousel")).click();
        this.clickLogin();

    }

    public String checkIfLoggedOn() throws WebDriverException{
        try {
            String logout = driver.findElement(By.id("logout")).getText();
            System.out.println(logout);
            driver.findElement(By.id("logout")).click();
            return logout;
        }catch (NoSuchElementException e){
            return "notLoggedOn";
        }


    }
}
