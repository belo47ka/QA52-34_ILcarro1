package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import static utils.PropertiesReader.*;
public class Homepage extends Basepage{
    public Homepage(WebDriver driver){
        setDriver(driver);
        //driver.get("https://ilcarro.web.app/search");
        driver.get(getProperty("base.properties","baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10),this);

    }
    @FindBy(css = "*[ng-reflect-router-link='login']")
    WebElement btnLogin;
    @FindBy(xpath = "//a[text()=' Sign up ']")
    WebElement btnSignUp;
    @FindBy(xpath = "//*[@href='/let-car-work']")
    WebElement btnLetTheCarWork;

    public void clickBtnLogin(){
        btnLogin.click();
    }
    public void clickBtnSighUp(){
        btnSignUp.click();
    }
    public void clickBtnLetTheCarWork(){
        btnLetTheCarWork.click();
    }


}
