package ui_tests;

import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Homepage;

import java.time.LocalDate;

public class SearchCarTests extends AppManager {
    Homepage homepage;
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


    }
}
