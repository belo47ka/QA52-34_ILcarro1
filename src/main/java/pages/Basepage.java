package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public abstract class Basepage {
    static WebDriver driver;
    public Logger logger = LoggerFactory.getLogger(Basepage.class);

    public void setDriver(WebDriver wd){driver=wd;}

    public boolean isTextInElementPresent(WebElement element, String text){
        try {return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElement(element, text));}
        catch (RuntimeException e){
//            e.printStackTrace();
//            System.out.println("created exeption");
            logger.error("create exeption",e);
        }
        return false;
    }

    public String closeAlert(){
        Alert alert = new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }
    public boolean isElementDisplayed(WebElement element){
        return element.isDisplayed();
    }
    @FindBy(xpath = "//div[@class='error']")
    List<WebElement> listErrors;

    public boolean isTextInErrorPresent(String text){
        if(listErrors==null|| listErrors.isEmpty())
            return false;
        for (WebElement element:listErrors){
            if (element.getText().contains(text))
                return true;
        }
        return false;
    }




    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
