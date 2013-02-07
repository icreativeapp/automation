package test;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
/**
 *
 * @author Ross Rowe
 */

public class test1{

    private WebDriver driver;

    /**
     * If the tests can rely on the username/key to be supplied by environment variables or the existence
     * of a ~/.sauce-ondemand file, then we don't need to specify them as parameters, just create a new instance
     * of {@link SauceOnDemandAuthentication} using the no-arg constructor.
     * @param username
     * @param key
     * @param os
     * @param browser
     * @param browserVersion
     * @param method
     * @throws Exception
     */
    @Parameters({"username", "key", "os", "browser", "browserVersion"})
    @BeforeMethod
  public void setUp(@Optional("icreativeapp") String username,
			  @Optional("8e40a4f9-07bd-4bdb-88f2-806eb88c63ab") String key,
			  @Optional("XP") String os,
			  @Optional("firefox") String browser,
			  @Optional("5.0") String browserVersion,
			  Method method) throws Exception {


        DesiredCapabilities capabillities = new DesiredCapabilities();
        capabillities.setBrowserName(browser);
        capabillities.setCapability("version", browserVersion);
        capabillities.setCapability("platform", Platform.valueOf(os));
        capabillities.setCapability("name", method.getName());
        this.driver = new RemoteWebDriver(
					  new URL("http://" + username + ":" + key + "@ondemand.saucelabs.com:80/wd/hub"),
					  capabillities);
    }

    @Test
	public void basic() throws Exception {
    	

    	driver.get("http://markavip.com");
    	driver.findElement(By.className("do_modal")).click();
    	WebDriverWait wait=new WebDriverWait(driver, 25);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login-form")));
    	Assert.assertTrue(driver.findElement(By.className("login-form")).isDisplayed());
    	
       /* driver.get("http://www.markavip.com/");
        driver.findElement(By.id("super-featured-wrapper")).click();
        //assertEquals("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more", driver.getTitle());
    */
    
    
    }

    @AfterMethod
	public void tearDown() throws Exception {
        driver.quit();
    }
}
