package ui_tests;

import dataprovider.data_providers.UserDataProvider;
import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Homepage;
import pages.Loginpage;
import utils.UserFaker;

import static utils.PropertiesReader.*;

public class LoginTests extends AppManager {
    Loginpage loginpage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void gotoLoginPage() {
        new Homepage(getDriver()).clickBtnLogin();
        loginpage = new Loginpage(getDriver());
    }

    @Test
    public void loginPositiveTestWithFakerTest() {
        UserLombok user = UserFaker.positiveUser();
        System.out.println(user);
        loginpage.typeLoginForm(user);
        loginpage.clickLoginbtnYalla();


    }

    @Test
    public void loginNegativeEmailFieldTest() {
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties", "negativeEmail"))
                .password(getProperty("base.properties", "password"))
                .build();
        Loginpage loginpage = new Loginpage(getDriver());
        loginpage.typeLoginForm(user);
        loginpage.clickLoginbtnYalla();
        softAssert.assertFalse(loginpage.isBtnYallaEnabled(), "validate isBtnYallaEnabled");
        softAssert.assertTrue(loginpage.isTextInErrorPresent("Email is required"),
                "validate message:Email is required");
    }

    @Test
    public void loginNegativePasswordFieldTest() {
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "negativePassword"))
                .build();
        Loginpage loginpage = new Loginpage(getDriver());
        loginpage.typeLoginForm(user);
        loginpage.clickLoginbtnYalla();
        softAssert.assertFalse(loginpage.isBtnYallaEnabled(), "validate isBtnYallaEnabled");

    }

    @Test
    public void loginPositiveTests() {
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        Loginpage loginpage = new Loginpage(getDriver());
        loginpage.typeLoginForm(user);
        loginpage.clickLoginbtnYalla();
        Assert.assertTrue(loginpage.isPopUpSuccessLoginDisplayed());
    }

    @Test
    public void loginNegativeWrongEmailTest() {
        UserLombok user = UserLombok.builder()
                .username("est321@gmail.com")
                .password(getProperty("base.properties", "password"))
                .build();
        loginpage.typeLoginForm(user);
        loginpage.clickLoginbtnYalla();
        Assert.assertTrue(loginpage.isPopUpLoginFailedDisplayed());
    }

    @Test
    public void loginNegativeWrongPasswordTest() {
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties", "email"))
                .password("West12345$")
                .build();
        loginpage.typeLoginForm(user);
        loginpage.clickLoginbtnYalla();
        //Assert.assertTrue(loginpage.isPopUpSuccessLoginDisplayed());
    }

    @Test
    public void loginNegativeEmptyAllFieldsWithoutClickTest() {
        loginpage.clickLoginbtnYalla();
        Assert.assertFalse(loginpage.isBtnYallaEnabled());
    }

    @Test
    public void loginNegativeEmptyAllFieldsWithClickTest() {
        UserLombok user = UserLombok.builder()
                .username("")
                .password("")
                .build();
        loginpage.typeLoginForm(user);
        loginpage.clickLoginbtnYalla();
        softAssert.assertFalse(loginpage.isBtnYallaEnabled(), "validate isBtnYallaEnabled");
        System.out.println("test working");
        softAssert.assertTrue(loginpage.isTextInErrorPresent("Email is required"),
                "validate message:Email is required");
        softAssert.assertTrue(loginpage.isTextInErrorPresent("Password is required"),
                "validate message:Password is required");
        softAssert.assertAll();

    }

    @Test(dataProvider = "dataProviderForLoginWrongPasswordOrEmail",
            dataProviderClass = UserDataProvider.class)
    public void loginNegativeWrongPasswordOrEmailTest(UserLombok user) {
        loginpage.typeLoginForm(user);
        loginpage.clickLoginbtnYalla();
        //Assert.assertTrue(loginpage.isPopUpLoginFailedDisplayed());


    }
}
