package com.mikedunk.selenium;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;

public class Transfer {




    WebDriver driver;
    ExtentTest logger;

    public Transfer(WebDriver driver ){

        this.driver = driver;
    }

        /**All the touchable clickable hoverable writable fields in the transfer module
         *                       Add New Beneficiary Module

         * **/

    By xtransfer = By.xpath("//*[@id=\"sidebar-nav\"]/money-nav/ul[1]/li[3]/a");
    By xmanageBeneficiary = By.xpath("//*[@id=\"sidebar-nav\"]/money-nav/ul[1]/li[3]/ul/li[1]/a/span");
    By xsingleTransfer =    By.xpath("//*[@id=\"sidebar-nav\"]/money-nav/ul[1]/li[3]/ul/li[2]/a/span");

    By singleTransferProvidusSelectInitiator=By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[1]/div[1]/div/div/sui-select/div[3]/sui-select-option");


    By histRecentSingleTransfer = By.xpath("//*[@id=\"md-tab-label-0-0\"]");
    By histRecentSingleTransfer1 = By.partialLinkText(" Recent Single Transfer");
    By hisPendingTransfer = By.xpath("//*[@id=\"md-tab-label-0-1\"]");
    By hisScheduledTransfer = By.xpath("//*[@id=\"md-tab-label-0-2\"]");
    By hisRecurringTransfers = By.xpath("//*[@id=\"md-tab-label-0-3\"]");


                   /**SIngle TRansfer Module Clickables
                    *
                    * Main Modes X-paths..resolves but slower
                    *
                    * **/

    By singleTransferToProvidus = By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div/div[1]/button/div/span");
    By singleTransferToOtherBanks = By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div/div[2]/button/div/span");
    By singleTransferToBeneficiary = By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div/div[3]/button/div/span");

    By singleTransferBack = By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/md-card-subtitle/span/a");
    By singleTransferProvidusAccountNarration = By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[1]/div[3]/div/div/input");
    By singleTransferProvidusAccountAmount = By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[1]/div[2]/div[2]/div/input");

    By singleTransferProvidusDestinationUser =By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[1]/div[2]/div[1]/div/input");
    By singleTransferProvidusInitiator =  By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[1]/div[1]/div/div/sui-select/input");
    By singleTransferProvidusTransferButton = By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[2]/div/div/button/span");
    By singleTransferProvidusRadioRecurring = By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[1]/div[4]/div/div[3]/div/label");
    By singleTransferProvidusRadioScheduled = By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[1]/div[4]/div/div[2]/div/label");
    By singleTransferProvidusRadioInstant = By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[1]/div[4]/div/div[1]/div/label");
    By singleTransferProvidusModalCompleteButton = By.xpath("//*[@id=\"myModal\"]/div[2]/div/div/confirm-single-transfer-view/div/div[3]/div/div[2]/button/span");
    By closeModalSelector = By.cssSelector("#myModal > div.modal-dialog.modal-md > div > div > button");





    public void setSingleTransferProvidusAccountInitiator() throws WebDriverException{

        driver.findElement(singleTransferProvidusInitiator).clear();
        driver.findElement(singleTransferProvidusInitiator).click();
        driver.findElement(singleTransferProvidusSelectInitiator).click();

    }
    public void setSingleTransferProvidusAccountAmount(String  amount)throws WebDriverException{

        driver.findElement(singleTransferProvidusAccountAmount).clear();
        driver.findElement(singleTransferProvidusAccountAmount).sendKeys(amount);


    }
    public void  setSingleTransferProvidusUser(String acct)throws WebDriverException{

        driver.findElement(singleTransferProvidusDestinationUser).clear();
        driver.findElement(singleTransferProvidusDestinationUser).sendKeys(acct);

    }

    public void setSingleTransferProvidusNarration(String narration)throws WebDriverException{

        driver.findElement(singleTransferProvidusAccountNarration).clear();
        driver.findElement(singleTransferProvidusAccountNarration).sendKeys(narration);

    }
    /**This method not only checks and sets OTP it also checks the result of the test..if it was successful or failed **/
    public void checkIfModalExistsAndSetOTP(String otp, ExtentTest logger)throws WebDriverException{
       try {

           driver.findElement(By.xpath("//*[@id=\"myModal\"]/div[2]/div/div/confirm-single-transfer-view/div/div[3]/div/div[1]/div/input")).sendKeys(otp);//OTP
           driver.findElement(singleTransferProvidusModalCompleteButton).click();

           String transferStatus= driver.findElement(By.xpath("//*[@id=\"myModal\"]/div[2]/div/div[2]/alert-component/div/div/div/div[1]/div/p[1]")).getText();
           System.out.println("Transaction Status:  "+transferStatus);
           logger.info("Transaction status:"+transferStatus);
           driver.switchTo().activeElement();
           driver.findElement(closeModalSelector).click();

           //WebElement element1 = driver.findElement(closeModalSelector);
           //JavascriptExecutor executor = (JavascriptExecutor)driver;
           //executor.executeScript("arguments[0].click()", element1);

           WebElement element = driver.findElement(singleTransferBack);
           JavascriptExecutor executor1 = (JavascriptExecutor)driver;
           executor1.executeScript("arguments[0].click()", element);


        }catch (NoSuchElementException e){


           // e.getMessage();
            try {
                driver.findElement(singleTransferBack).click();
                System.out.println("Modal didn't load cos of incorrect input");
                logger.info("Wrong Input Combination");
            }catch(WebDriverException ew){
                ew.getMessage();
                System.out.println("Invalid OTP");
                logger.info("No OTP Supplied");
                driver.findElement(closeModalSelector).click();
                WebElement element = driver.findElement(singleTransferBack);
               JavascriptExecutor executor1 = (JavascriptExecutor)driver;
               executor1.executeScript("arguments[0].click()", element);

            }

        }




    }
    public void testRadioButtons()throws WebDriverException{
        driver.findElement(singleTransferProvidusRadioScheduled).click();
        driver.findElement(singleTransferProvidusRadioInstant).click();
        driver.findElement(singleTransferProvidusTransferButton).click();
    }

    public void navigateToProvidus()throws WebDriverException{
        driver.findElement(xsingleTransfer).click();
        driver.findElement(singleTransferToProvidus).click();

    }

    public void attemptSingleTransfer(String amount, String destinationAccount, String narration)throws WebDriverException {

        setSingleTransferProvidusUser(destinationAccount);
        setSingleTransferProvidusAccountAmount(amount);
        setSingleTransferProvidusNarration(narration);

        driver.findElement(singleTransferProvidusTransferButton).click();
    }
}