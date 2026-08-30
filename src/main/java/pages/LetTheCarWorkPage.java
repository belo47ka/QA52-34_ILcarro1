package pages;

import dto.CarLombok;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import utils.enums.Fuel;
import java.io.File;

public class LetTheCarWorkPage extends Basepage{
        public LetTheCarWorkPage(WebDriver driver){
            PageFactory.initElements(new AjaxElementLocatorFactory(driver,10),this);

        }
        @FindBy(xpath = "//button[@type='submit']")
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
        @FindBy(xpath = "//textarea[@id='about']")
         WebElement inputAbout;
         @FindBy(xpath = "//input[@id='pickUpPlace']")
         WebElement location;
         @FindBy(id = "photos")
         WebElement inputImage;

//        public void typeAddCarForm(CarLombok carLombok){
//            inputLocation.sendKeys(carLombok.getLocation());
////            inputLocation.sendKeys(Keys.ARROW_DOWN);
////            inputLocation.sendKeys(Keys.ENTER);
//            inputManufacture.sendKeys(carLombok.getManufacture());
//            inputModel.sendKeys(carLombok.getModel());
//            inputYear.sendKeys(carLombok.getYear());
//            Select select = new Select(selectFuel);
//            select.selectByVisibleText(carLombok.getFuel());
//            inputSeats.sendKeys(carLombok.getSeats());
//            inputCarClass.sendKeys(carLombok.getCarClass());
//            inputCarRegistrationNumber.sendKeys(carLombok.getCarRegistrationNumber());
//            inputPrice.sendKeys(carLombok.getPrice());
//
//
//
//        }

       public void typeAddNewCarForm(CarLombok car){
           inputLocation.sendKeys(car.getLocation());
           inputManufacture.sendKeys(car.getManufacture());
           inputModel.sendKeys(car.getModel());
           inputYear.sendKeys(car.getYear());
           chooseFuel(car.getFuel());
           inputSeats.sendKeys(Integer.toString(car.getSeats()));
           inputCarClass.sendKeys(car.getCarClass());
           inputCarRegistrationNumber.sendKeys(car.getCarRegistrationNumber());
           inputPrice.sendKeys(Double.toString(car.getPrice()));
           inputAbout.sendKeys(car.getAbout());

       }

    private void chooseFuel(Fuel fuel) {
        selectFuel.click();
        driver.findElement(By.xpath(fuel.getLocator())).click();
    }

    public void downloadImage(String fileName) {
        inputImage.sendKeys(new File("src/test/resources/"
                + fileName).getAbsolutePath());
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

}
