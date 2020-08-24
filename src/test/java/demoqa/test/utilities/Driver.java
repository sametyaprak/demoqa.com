package demoqa.test.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    //Driver class, driver instance'yi baslatmak icin kullanilir.(Singleton Driver)
    //Ihtiyacmiz oldugunda driver'i kurmak ve baslatmak icin kullanırız.

    private Driver(){
        // baska obje olusturulmasını istemediginiz icin create ediyoruz.
    }

    // driver instance olusturalim
    static WebDriver driver;

    public static WebDriver getDriver(){

        if (driver==null){
            switch (ConfigReader.getProperty("browser")){
                case     "chrome" :          WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case    "firefox" :          WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case    "internetExplorer":  WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case    "opera":              WebDriverManager.operadriver().setup();
                    driver = new OperaDriver();
                    break;
                case    "safari":           WebDriverManager.getInstance(SafariDriver.class).setup();
                    driver = new SafariDriver();
                    break;
                case    "headless-chrome" : WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
        }
        return driver;
    }
    public static void closeDriver(){
        if(driver!=null){
            driver.quit();
            driver=null;
            // driver'in null olarak atanması onemli.
            //boylelikle browseer tekrar baslatilabilir.
            //multi browser test icin gerekli olabilir.

        }
    }


}
