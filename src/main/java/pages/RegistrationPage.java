package pages;

import dto.UserLombok;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegistrationPage extends Basepage {
    public RegistrationPage(WebDriver driver){
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10),this);

    }
    @FindBy(xpath = "//*[@ng-reflect-name='firstName']")
    WebElement inputFirstName;
    @FindBy(xpath = "//*[@ng-reflect-name='lastName']")
    WebElement inputLastName;
    @FindBy(xpath = "//*[@ng-reflect-name='email']")
    WebElement inputEmail;
    @FindBy(xpath = "//*[@ng-reflect-name='password']")
    WebElement inputPassword;
    @FindBy(xpath = "//*[@class='checkbox-container']")
    WebElement checkBoxIAgree;
    @FindBy(xpath = "//*[@type='submit']")
    WebElement btnYalla;
    @FindBy(xpath = "//label[@for='terms-of-use']")
    WebElement checkBoxLabel;

    public void typeRegistrationForm(UserLombok user){
        inputFirstName.sendKeys(user.getFirstName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());

    }
    public void clickCheckBoxIAgree(){
        checkBoxIAgree.click();
    }
    public void clickBtnYalla(){
        btnYalla.click();
    }
//    public void clickCheckBoxTermsOfUse(){
//        JavascriptException js = (JavascriptException) driver;
//        js.executeScript("arguments[0].click;", ch
//    }
    public void clickCheckBoxWithActions(){
        int x = checkBoxLabel.getSize().getWidth();
        int y = checkBoxLabel.getSize().getHeight();
        System.out.println(x + "X" + y);
        Actions actions = new Actions(driver);
        actions.moveToElement(checkBoxLabel, - x/10*3,-y/2).click().perform();

    }
}
