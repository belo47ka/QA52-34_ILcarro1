package pages;

import org.openqa.selenium.WebDriver;

public abstract class Basepage {
    static WebDriver driver;

    public void setDriver(WebDriver wd){driver=wd;}

    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
