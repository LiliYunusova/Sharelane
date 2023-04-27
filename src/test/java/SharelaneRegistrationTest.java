import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SharelaneRegistrationTest {
    WebDriver driver;

    @Test
    public void fiveDigitsZipCodeTest(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        // open main page https://www.sharelane.com/cgi-bin/register.py
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        // input five digits to zip code field
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        //click continue button
        driver.findElement(By.cssSelector("[value='Continue']")).click();
        // check Register button
        WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
        Assert.assertTrue(registerButton.isDisplayed(), "Register button is not displayed");
    }
}
