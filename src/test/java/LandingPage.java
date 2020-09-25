
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LandingPage {
    private WebDriver driver = new ChromeDriver();

    @Before
    public void setUp(){
        driver.get("https://www.google.com/");
    }

    @Test
    public void testCurrentURL(){
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals("https://www.google.com/", actualURL);
    }

    @Test
    public void testTitle(){
        String actualTitle = driver.getTitle();
        Assert.assertEquals("Google", actualTitle);

    }

    @Test
    public void testGmailLink(){
        driver.findElement(By.linkText("Gmail")).click();
        String gmailTitle = driver.getTitle();
        Assert.assertEquals("Gmail - Email from Google", gmailTitle);
        Assert.assertTrue(driver.getCurrentUrl().contains("gmail"));
    }

    @Test
    public void testLanguageChangeToFrench(){
        driver.findElement(By.linkText("Français")).click();
        Assert.assertEquals("fr-CA", driver.findElement(By.tagName("html")).getAttribute("lang"));
    }

    @Test
    public void testGoogleAppsDropDownList(){
        driver.findElement(By.id("gbwa")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.switchTo().frame(0);
        driver.findElement(By.className("j1ei8c")).click();
        Assert.assertEquals("Google Account", driver.getTitle());
    }

    @Test
    public void testGoogleSearch(){
        driver.findElement(By.name("q")).sendKeys("canada");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("gNO89b")));

        List<WebElement> suggestionsList = driver.findElements(By.className("erkvQe"));
        suggestionsList.size();
        Assert.assertTrue("Google search returned no suggestions", suggestionsList.size() > 0);

        driver.findElement(By.className("gNO89b")).click();
        Utility.takeScreenshot(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains("/search"));
    }

    @After
    public void tearDown(){
        driver.quit();

    }

}

