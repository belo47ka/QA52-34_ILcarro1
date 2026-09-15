package ui_tests;

import dto.CarLombok;
import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Homepage;
import pages.LetTheCarWorkPage;
import pages.Loginpage;
import pages.PopUpPage;
import utils.enums.Fuel;
import utils.enums.HeaderMenu;
import static utils.CarFactory.*;

import static utils.PropertiesReader.getProperty;

public class LetTheCarWorkTests extends AppManager {
    Loginpage loginpage;
    LetTheCarWorkPage letTheCarWorkPage;
    SoftAssert softAssert = new SoftAssert();
    @BeforeMethod(alwaysRun = true)
    public void LoginAndOpenPage(){
        logger.info("Start Registration Test");
        new Homepage(getDriver()).clickBtnLogin();
        //loginpage = new Homepage(getDriver()).clickHeaderButtons(HeaderMenu.LOGIN);
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        Loginpage loginpage = new Loginpage(getDriver());
        loginpage.typeLoginForm(user);
        loginpage.clickLoginbtnYalla();
        new Homepage(getDriver()).clickBtnLetTheCarWork();
        letTheCarWorkPage = new Homepage(getDriver()).clickHeaderButtons(HeaderMenu.LEY_THE_CAR_WORK);
    }
//    @Test
//    public void AddCarPositiveWithAuthorization(){
//        CarLombok carLombok = CarLombok.builder()
//                .location("Haifa")
//                .manufacture("Toyota")
//                .model("Corolla")
//                .year("2022")
//                .fuel("Petrol")
//                .seats("5")
//                .carClass("C")
//                .carRegistrationNumber("123456")
//                .price("145")
//                 .build();
//        letTheCarWorkPage.typeAddNewCarForm(carLombok);
//        //letTheCarWorkPage.clickBtnSubmitWithJS();
//
//    }
    @DataProvider(name = "wrongYear")
    public Object [][] wrongYear(){
        return new Object[][]{
                {"0"},{"99"},{"123"},{"100000000000000000"},{"-122"},{"abs"},{"1234ere33"},
                {"123#er!@#$"},{"wrongYear"},{"WRONGYEAR"},{"-122Wrong#"}
        };
    }
    @Test(dataProvider = "wrongYear")
    public void WrongYEarNegativeTest(String wrongYear){
        CarLombok car = CarLombok.builder()
                .location("Haifa")
                .manufacture("Toyota")
                .model("Corolla")
                .year(wrongYear)
                .fuel(Fuel.DIESEL)
                .seats(5)
                .carClass("C")
                .carRegistrationNumber("12345")
                .price(1.0)
                .about("privet")
                .build();
        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.clickBtnSubmitWithJS();
        Assert.assertTrue(letTheCarWorkPage.isWrongYearDisplayed());
    }
    @Test
    public void ClickOnlySubmitNegative(){
        letTheCarWorkPage.clickBtnSubmitWithJS();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("Car adding failed"));
    }
    @Test
    public void WrongYearFieldNegative(){
        CarLombok car = CarLombok.builder()
                .location("Haifa")
                .manufacture("Toyota")
                .model("Corolla")
                .year("2022000000000000000000000000000")
                .fuel(Fuel.DIESEL)
                .seats(5)
                .carClass("C")
                .carRegistrationNumber("12345")
                .price(1.0)
                .about("privet")
                .build();
        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.clickBtnSubmitWithJS();
    }
    @Test
    public void OneFieldWithNullNegative(){
        CarLombok car = CarLombok.builder()
                .location("Haifa")
                .manufacture("Toyota")
                .model("Corolla")
                .year("2022")
                .fuel(null)
                .seats(5)
                .carClass("C")
                .carRegistrationNumber("12345")
                .price(1.0)
                .about("privet")
                .build();
        letTheCarWorkPage.typeAddNewCarFormNull(car);
        letTheCarWorkPage.clickBtnSubmitWithJS();
    }
    @Test
    public void OneFieldBlankNegative(){
        CarLombok car = CarLombok.builder()
                .location("Haifa")
                .manufacture("Toyota")
                .model("Corolla")
                .year("2022")
                .fuel(Fuel.DIESEL)
                .seats(5)
                .carClass("")
                .carRegistrationNumber("12345")
                .price(1.0)
                .about("privet")
                .build();
        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.clickBtnSubmitWithJS();
    }
    @Test
    public void ClickAllFieldsButtonSubmitNegative(){
        letTheCarWorkPage.clickSllFields();
        letTheCarWorkPage.clickBtnSubmitWithJS();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("Car adding failed"));


    }
    @Test(groups = {"smoke","regress","car","positive"})
    public void addNewCarPositiveTest(){
        CarLombok car = positiveCar();
        System.out.println(car);
        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.downloadImage("cat2.jpg");
        letTheCarWorkPage.clickBtnSubmitWithJS();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("Car adding failed"));


    }

}
