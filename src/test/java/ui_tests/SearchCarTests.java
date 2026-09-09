package ui_tests;

import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Homepage;
import pages.PopUpPage;

import java.time.LocalDate;

public class SearchCarTests extends AppManager {
    Homepage homepage;
    SoftAssert softAssert = new SoftAssert();
    @BeforeMethod
    public void openHomePage(){
        homepage=new Homepage(getDriver());
    }
    @Test
    public void searchCarPositiveTests(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homepage.typeSearchForm(city,startDate,endDate);
        homepage.submitSearchWithJS();
        Assert.assertTrue(homepage.noAvailableCarsMethod()
                .contains("No available cars"),"Search result message is not displayed");


    }
    @Test
    public void emptyCityFieldNegativeTest(){
        String city = "";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homepage.typeSearchForm(city,startDate,endDate);
        homepage.submitSearchWithJS();
        Assert.assertTrue(homepage.noAvailableCarsMethod()
                .contains("No available cars"),"Search result message is not displayed");
    }
    @Test
    public void wrongDateNegativeTest(){
        String city = "Tel Aviv";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now();
        homepage.typeSearchForm(city,startDate,endDate);
        homepage.submitSearchWithJS();
        Assert.assertTrue(homepage.errorMesWrongDate().contains("Second date must be after first date"));
    }
    @Test
    public void searchCarPositiveTests1(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homepage.typeSearchForm(city,startDate,endDate);
        homepage.submitSearchWithJS();
        Assert.assertTrue(homepage.isUrlContainsText("results"));


    }
    @Test
    public void searchCarPositiveTestsWithCalendar(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homepage.typeSearchFormWithCalendar(city,startDate,endDate);
        homepage.submitSearchWithJS();
        Assert.assertTrue(homepage.isUrlContainsText("results"));


    }


}
