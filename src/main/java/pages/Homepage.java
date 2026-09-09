package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.LocalDate;

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
    @FindBy(id="city")
    WebElement inputCity;
    @FindBy(id="dates")
    WebElement inputDates;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYallaSearch;
    @FindBy(xpath = "//*[@class='no-cars-label ng-star-inserted']")
    WebElement noAvailableCarsMessage;
    @FindBy(xpath = "(//div[@class='error'])[2]")
    WebElement errorMesWrongDate;
    @FindBy(xpath = "//button[@aria-label='Choose month and year']")
    WebElement btnYearCalendar;


    public String noAvailableCarsMethod(){
        return noAvailableCarsMessage.getText();
    }
    public String errorMesWrongDate(){
        return errorMesWrongDate.getText();
    }

    public void clickBtnLogin(){
        btnLogin.click();
    }
    public void clickBtnSighUp(){
        btnSignUp.click();
    }
    public void clickBtnLetTheCarWork(){
        btnLetTheCarWork.click();
    }
    public void typeSearchForm(String city, LocalDate startDate,LocalDate endDAte){
        inputCity.sendKeys(city);
        System.out.println(startDate);
        System.out.println(endDAte);
        // 2026-09-05
        System.out.println(startDate.getMonthValue());
        System.out.println(startDate.getDayOfMonth());
        String dates = startDate.getMonthValue() +"/"+ startDate.getDayOfMonth()+"/"+startDate.getYear()+" - "
                +endDAte.getMonthValue()+"/"+endDAte.getDayOfMonth()+"/"+endDAte.getYear();
        System.out.println(dates);
        inputDates.sendKeys(dates);
    }
    public void typeSearchFormWithCalendar(String city, LocalDate startDate,LocalDate endDAte){
        inputCity.sendKeys(city);
        inputDates.click();
        typeCalendar(startDate);
        typeCalendar(endDAte);

    }
    private void typeCalendar(LocalDate date){
        btnYearCalendar.click();
        //System.out.println(date.getYear());
        String year = Integer.toString(date.getYear());
        WebElement btnYear = driver.findElement(By.xpath("//td[@aria-label='"+year+"']"));
        btnYear.click();
        //td[@aria-label="November 2026"] //td[@aria-label='"+month+" " +year+"']
        //System.out.println(date.getMonth());
        String month = createMonth(date.getMonth().toString());
        System.out.println(month);
        WebElement btnMonth = driver.findElement(By.xpath("//td[@aria-label=\"September 2026\"]"));
        btnMonth.click();
        System.out.println(date.getDayOfMonth());
        String day = String.valueOf(date.getDayOfMonth());
        WebElement btnDay = driver.findElement(By.xpath("//td[@aria-label='" + month + " " + day + ", " + year + "']"));
        btnDay.click();

    }
    //SEPTEMBER-.>september
    private String createMonth(String month){
        return new StringBuilder().append(month.substring(0,1).toUpperCase())
                .append(month.substring(1).toLowerCase()).toString();
    }
    public void submitSearchWithJS(){
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("document.querySelector(\"button[type='submit']\")" +
                    ".removeAttribute('disabled')");
            btnYallaSearch.click();
        }
    }




