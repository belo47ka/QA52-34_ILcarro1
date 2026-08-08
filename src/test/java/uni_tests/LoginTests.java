package uni_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Homepage;
import pages.Loginpage;

public class LoginTests extends AppManager {
    @BeforeMethod
    public void gotoLoginPage(){
        new Homepage(getDriver()).clickBtnLogin();
    }
    @Test
    public void loginPositiveTests(){
        UserLombok user = UserLombok.builder()
                .email("test321@gmail.com")
                .password("Test12345$")
                .build();
        Loginpage loginpage = new Loginpage(getDriver());
        loginpage.typeLoginForm(user);
        loginpage.clickLoginbtn();
    }
}
