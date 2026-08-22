package ui_tests;

import dataprovider.data_providers.UserDataProvider;
import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Homepage;
import pages.PopUpPage;
import pages.RegistrationPage;
import static utils.UserFaker.*;

public class RegistrationTest extends AppManager {
    RegistrationPage registrationPage;
    @BeforeMethod
    public void goToRegistrationPage(){
        new Homepage(getDriver()).clickBtnSighUp();
        registrationPage=new RegistrationPage(getDriver());

    }
    @Test
    public void registrationPositiveTest(){
        UserLombok user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxIAgree();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("You are logged in success"));

    }
//    @Test(dataProvider = "dataProviderWrongPasswordOrEmail",
//            dataProviderClass = UserDataProvider.class)
//    public void registrationNegativeWrongPasswordFieldsTest(UserLombok user){
//
//        loginPage.typeLoginRegistrationForm(user);
//        loginPage.clickBtnRegistration();
//        Assert.assertTrue(loginPage.closeAlert()
//                .contains("Wrong email or password format"));
//    }
    @Test(dataProvider = "dataProviderForRegistrationWrongPasswordOrEmail",
            dataProviderClass = UserDataProvider.class)
    public void registrationNegativeWrongPasswordOrEmailTest(UserLombok user){
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxIAgree();
        registrationPage.clickBtnYalla();

    }
}
