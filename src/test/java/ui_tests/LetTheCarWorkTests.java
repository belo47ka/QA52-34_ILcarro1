package ui_tests;

import dto.CarLombok;
import dto.UserLombok;
import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Homepage;
import pages.LetTheCarWorkPage;
import pages.Loginpage;

import static utils.PropertiesReader.getProperty;

public class LetTheCarWorkTests extends AppManager {
    LetTheCarWorkPage letTheCarWorkPage;
    SoftAssert softAssert = new SoftAssert();
    @BeforeMethod
    public void LoginAndOpenPage(){
        logger.info("Start Registration Test");
        new Homepage(getDriver()).clickBtnLogin();
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        Loginpage loginpage = new Loginpage(getDriver());
        loginpage.typeLoginForm(user);
        loginpage.clickLoginbtnYalla();
        new Homepage(getDriver()).clickBtnLetTheCarWork();
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());
    }
    @Test
    public void AddCarPositiveWithAuthorization(){
        CarLombok carLombok = CarLombok.builder()
                .location("Haifa")
                .manufacture("Toyota")
                .model("Corolla")
                .year("2022")
                .fuel("Petrol")
                .seats("5")
                .carClass("C")
                .carRegistrationNumber("123456")
                .price("145")
                 .build();
        letTheCarWorkPage.typeAddCarForm(carLombok);
        //letTheCarWorkPage.clickBtnSubmitWithJS();

    }

}
