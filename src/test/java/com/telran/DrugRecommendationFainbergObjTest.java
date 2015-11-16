package com.telran;

import com.telran.pages.DoctorsPage;
import com.telran.pages.DrugRecomendationPage;
import com.telran.pages.LoginIrinaPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Alex on 12.11.2015.
 */
public class DrugRecommendationFainbergObjTest {

    public static String username = "4337Doctor";
    public static String password = "LinkCare!!11";
    public static String testText1 = "Test Text 1234";

    public WebDriver driver;

    public DrugRecomendationPage patientPage;
    public DoctorsPage mainPage;
    public LoginIrinaPage loginPage;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginIrinaPage.class);
        mainPage = PageFactory.initElements(driver, DoctorsPage.class);
        patientPage = PageFactory.initElements(driver, DrugRecomendationPage.class);
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethodSetUp() {
        try {
            loginPage
                    .openLoginPage(driver)
                    .waitUntilLoginPageIsLoaded()
                    .fillUsernameField(username)
                    .fillPasswordField(password)
                    .clickOnLoginButton();
            mainPage.waitUntilMainPageIsLoaded()
                    .clickOnGoToPatientButton();

            patientPage
                    .waitUntilTestPageIsLoaded();


            //loginPage.waitUntilTestPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"smoke", "positive"})
    public void FillElements() {
        patientPage.waitUntilTestPageIsLoaded();
        try {
            //mainPage.fillElements();
            patientPage.pressExpandElement()
                    .pressAddLink()
                    .fillTextField(testText1)
                    .pressCommitAddLink();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (patientPage.isTextInsertedOk(testText1))
            System.out.println("TextInsertedOk");


    }


}
