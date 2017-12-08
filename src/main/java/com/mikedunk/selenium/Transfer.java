package com.mikedunk.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.IntStream;

public class Transfer {




    WebDriver driver;

    public Transfer(WebDriver driver ){

        this.driver = driver;
    }


















        /**All the touchable clickable hoverable writable fields in the transfer module
         *
         * Add New Beneficiary Module
         *
         * **/




    By xtransfer = By.xpath("//*[@id=\"sidebar-nav\"]/money-nav/ul[1]/li[3]/a");
    By xmanageBeneficiary = By.xpath("//*[@id=\"sidebar-nav\"]/money-nav/ul[1]/li[3]/ul/li[1]/a/span");
    By xsingleTransfer =    By.xpath("//*[@id=\"sidebar-nav\"]/money-nav/ul[1]/li[3]/ul/li[2]/a/span");
    By xbulkTransfer =     By.xpath("//*[@id=\"sidebar-nav\"]/money-nav/ul[1]/li[3]/ul/li[3]/a/span");
    By managebeneficiarySearch = By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/beneficiary-management-view/div/section[2]/div/div[1]/div/input");
    By beneficiaryEdit =       By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/beneficiary-management-view/div/section[2]/div/div[2]/md-card/div/table/tbody/tr[1]/td[4]/button[1]/i");
    By addBeneficiaryAcctNo = By.xpath("//*[@id=\"myModal\"]/div[2]/div/div[2]/beneficiary-add-edit-view/div/div[1]/div/input");
    By addBeneficiaryAcctNoErrorDiv = By.xpath("//*[@id=\"myModal\"]/div[2]/div/div[2]/beneficiary-add-edit-view/div/div[1]/div/em");
    By addBeneficiaryName = By.xpath("//*[@id=\"myModal\"]/div[2]/div/div[2]/beneficiary-add-edit-view/div/div[3]/div[1]/div/input");
    By submitNewBeneficiary = By.xpath("//*[@id=\"myModal\"]/div[2]/div/div[2]/beneficiary-add-edit-view/div/div[4]/div/button");
    By newBeneficiarySuccessful = By.xpath("//*[@id=\"myModal\"]/div[2]/div/div[2]/alert-component/div/div/div/div[1]/div/p[1]");//or you can use partial linktext
    By okDissmissModalAfterSuccess= By.xpath("//*[@id=\"myModal\"]/div[2]/div/div[2]/alert-component/div/div/div/div[2]/div/button");

    By singleTransferProvidusSelectInitiator=By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[1]/div[1]/div/div/sui-select/div[3]/sui-select-option");

    By singleAccountTransferFrom = By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[1]/div[1]/div/div/sui-select/input");
    By dismissModal = By.xpath("//*[@id=\"Shape\"]");// this so far dismisses all modals;
    By transAlertModalDismiss = By.xpath("//*[@id=\"myModal\"]/div[2]/div/div[2]/alert-component/div/div/div/div[2]/div/button");
  //  By dismissModal1 = By.id("Shape");




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
            /**Single Transfer Modes Link Tex... faster than xpathst**/
    By singleTransferToProvidusLinkText = By.partialLinkText(" Providous Bank Account");
    By singleTransferToOtherBanksLinkText = By.partialLinkText("Other Banks Account");
    By singleTransferToBeneficiaryLinkText = By.partialLinkText("Saved Beneficiary");
    By singleTransferBack = By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/md-card-subtitle/span/a");


    By singleTransferProvidusAccountError= By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[1]/div[2]/div[1]/div/p");
    By singleTransferProvidusAccountNarration = By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[1]/div[3]/div/div/input");
    By singleTransferProvidusAccountAmount = By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[1]/div[2]/div[2]/div/input");

    By singleTransferProvidusDestinationUser =By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[1]/div[2]/div[1]/div/input");
    By singleTransferProvidusInitiator =  By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[1]/div[1]/div/div/sui-select/input");
    By singleTransferProvidusTransferButton = By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[2]/div/div/button/span");
    By singleTransferProvidusRadioRecurring = By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[1]/div[4]/div/div[3]/div/label");
    By singleTransferProvidusRadioScheduled = By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[1]/div[4]/div/div[2]/div/label");
    By singleTransferProvidusRadioInstant = By.xpath("//*[@id=\"content-wrapper\"]/merchant-money/single-transfers-view/div/section[2]/div[1]/div[1]/md-card/div/form/div[1]/div[4]/div/div[1]/div/label");
    By singleTransferProvidusModalCompleteButton = By.xpath("//*[@id=\"myModal\"]/div[2]/div/div/confirm-single-transfer-view/div/div[3]/div/div[2]/button/span");

    By transferOtherBanksCommercialBank = By.xpath("//*[@id=\"myModal\"]/div[2]/div/div/app-select-category-view/div[2]/div/div/table/tbody/tr[1]/td/p");


    // By addNewBeneficiary = By.cssSelector("#logout");
    // By      logoutButton = By.xpath("//*[@id=\"logout\"]");

    By addNewBeneficiary = By.partialLinkText("Add new beneficiary");
    By logoutButton = By.linkText("logout");



    public void setSingleTransferProvidusAccountInitiator(){

        driver.findElement(singleTransferProvidusInitiator).click();
        driver.findElement(singleTransferProvidusSelectInitiator).click();

    }
    public void setSingleTransferProvidusAccountAmount(String  amount){
        driver.findElement(singleTransferProvidusAccountAmount).sendKeys(amount);


    }
    public void  setSingleTransferProvidusUser(String acct){
    driver.findElement(singleTransferProvidusDestinationUser).sendKeys(acct);

    }

}