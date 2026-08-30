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
    @FindBy(xpath = "//*[@id='email']")
    WebElement inputEmail;
    @FindBy(css = "*[type='password']")
    WebElement inputPassword;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement buttonYalla;
    @FindBy(xpath = "//h1[text() ='Logged in']")
    WebElement popUpSuccessLogin;
    @FindBy(xpath = "//h1[text() ='Login failed']")
    WebElement popUpLoginFailed;

    public void typeLoginForm(UserLombok user){
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }
    public void clickLoginbtnYalla(){
        buttonYalla.click();
    }



    public boolean isPopUpSuccessLoginDisplayed(){
        return isElementDisplayed(popUpSuccessLogin);
    }
    public boolean isPopUpLoginFailedDisplayed(){
        return isElementDisplayed(popUpLoginFailed);
    }
    public boolean isBtnYallaEnabled(){
        return buttonYalla.isEnabled();
    }


}
