package pages;

import dto.UserLombok;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Loginpage extends Basepage{
    public Loginpage(WebDriver driver){
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10),this);

    }
    @FindBy(xpath = "//input[@id='email']")
    WebElement inputEmail;
    @FindBy(css = "*[type='password']")
    WebElement inputPassword;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement buttonYalla;

    public void typeLoginForm(UserLombok user){
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
    }
    public void clickLoginbtn(){
        buttonYalla.click();
    }
}
