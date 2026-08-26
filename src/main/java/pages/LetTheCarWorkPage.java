package pages;

import dto.CarLombok;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class LetTheCarWorkPage extends Basepage{
        public LetTheCarWorkPage(WebDriver driver){
            PageFactory.initElements(new AjaxElementLocatorFactory(driver,10),this);

        }
        @FindBy(xpath = "//buttun[@type='submit']")
        WebElement btnSubmit;
        @FindBy(xpath = "//*[@href='/let-car-work']")
        WebElement btnLetTheCarWork;
        @FindBy(xpath = "//*[@id='pickUpPlace']")
        WebElement inputLocation;
        @FindBy(xpath = "//*[@id='make']")
        WebElement inputManufacture;
        @FindBy(xpath = "//*[@id='model']")
        WebElement inputModel;
        @FindBy(xpath = "//*[@id='year']")
        WebElement inputYear;
        @FindBy(xpath = "//*[@id='fuel']")
        WebElement selectFuel;
        @FindBy(xpath = "//*[@id='seats']")
        WebElement inputSeats;
        @FindBy(xpath = "//*[@id='class']")
        WebElement inputCarClass;
        @FindBy(xpath = "//*[@id='serialNumber']")
        WebElement inputCarRegistrationNumber;
        @FindBy(xpath = "//*[@id='price']")
        WebElement inputPrice;
        @FindBy(xpath = "//*[@type='submit']")
        WebElement btnSubmit1;
        public void typeAddCarForm(CarLombok carLombok){
            inputLocation.sendKeys(carLombok.getLocation());
//            inputLocation.sendKeys(Keys.ARROW_DOWN);
//            inputLocation.sendKeys(Keys.ENTER);
            inputManufacture.sendKeys(carLombok.getManufacture());
            inputModel.sendKeys(carLombok.getModel());
            inputYear.sendKeys(carLombok.getYear());
            Select select = new Select(selectFuel);
            select.selectByVisibleText(carLombok.getFuel());
            inputSeats.sendKeys(carLombok.getSeats());
            inputCarClass.sendKeys(carLombok.getCarClass());
            inputCarRegistrationNumber.sendKeys(carLombok.getCarRegistrationNumber());
            inputPrice.sendKeys(carLombok.getPrice());
            btnSubmit1.click();


        }

        public void clickBtnSubmitWithJS(){
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("document.querySelector(\"button[type='submit']\")" +
                    ".removeAttribute('disabled')");
            btnSubmit.click();
        }
        public void clickBtnLetTheCarWork(){
            btnLetTheCarWork.click();
        }
        public void buttonSubmit1click(){
            btnSubmit1.click();
        }
}
