import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SharelaneRegistrationTest {
    WebDriver driver;

    @Test
    public void fiveDigitsZipCodeTest() {
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

    @Test
    public void fourDigitsZipCodeTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("1234");
        driver.findElement(By.cssSelector("[value = 'Continue']")).click();
        String actualResult = driver.findElement(By.xpath("//body/center/table/tbody/tr[4]/td/span")).getText();
        //поиск по css селектору
        //String actualResult1 = driver.findElement(By.cssSelector("[class = 'error_message']")).getText();
        String expectedResult = "Oops, error on page. ZIP code should have 5 digits";

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void sixDigitsZipCodeTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("123456");
        driver.findElement(By.cssSelector("[value = 'Continue']")).click();
        String expectedResult = "Oops, error on page. ZIP code should have 5 digits";
        String actualResult = driver.findElement(By.xpath("//body/center/table/tbody/tr[4]/td/span")).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void signUpTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value = 'Continue']")).click();
        driver.findElement(By.name("first_name")).sendKeys("Mary");
        driver.findElement(By.name("email")).sendKeys("rtr@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("123456");
        driver.findElement(By.name("password2")).sendKeys("123456");
        WebElement buttonRegistry = driver.findElement(By.cssSelector("[value = 'Register']"));
        buttonRegistry.click();
        String actualResult = driver.findElement(By.cssSelector("[class = 'confirmation_message']")).getText();
        String expectedResult = "Account is created!";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void signUpWithoutFirstNameTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value = 'Continue']")).click();
        driver.findElement(By.name("email")).sendKeys("rtr@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("123456");
        driver.findElement(By.name("password2")).sendKeys("123456");
        WebElement buttonRegistry = driver.findElement(By.cssSelector("[value = 'Register']"));
        buttonRegistry.click();
        String actualResult = driver.findElement(By.cssSelector("[class = 'error_message']")).getText();
        String expectedResult = "Oops, error on page. Some of your fields have invalid data or email was previously used";

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void signUpWithEmptyDataTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value = 'Continue']")).click();
        driver.findElement(By.cssSelector("[value = 'Register']")).click();
        String actualResult = driver.findElement(By.cssSelector("[class = 'error_message']")).getText();
        String expectedResult = "Oops, error on page. Some of your fields have invalid data or email was previously used";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void signUpWithoutPasswordTest(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value = 'Continue']")).click();
        driver.findElement(By.name("email")).sendKeys("rtr@mail.ru");
        driver.findElement(By.name("password2")).sendKeys("123456");
        WebElement buttonRegistry = driver.findElement(By.cssSelector("[value = 'Register']"));
        buttonRegistry.click();
        String actualResult = driver.findElement(By.cssSelector("[class = 'error_message']")).getText();
        String expectedResult = "Oops, error on page. Some of your fields have invalid data or email was previously used";
        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void signUpWithInvalidEmailDataTest(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value = 'Continue']")).click();
        driver.findElement(By.name("first_name")).sendKeys("Mary");
        driver.findElement(By.name("email")).sendKeys("rtr");
        driver.findElement(By.name("password1")).sendKeys("123456");
        driver.findElement(By.name("password2")).sendKeys("123456");
        driver.findElement(By.cssSelector("[value = 'Register']")).click();

        String actualResult = driver.findElement(By.cssSelector("[class = 'error_message']")).getText();
        String expectedResult = "Oops, error on page. Some of your fields have invalid data or email was previously used";

        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void loginInTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value = 'Continue']")).click();
        driver.findElement(By.name("first_name")).sendKeys("Mary");
        driver.findElement(By.name("email")).sendKeys("rtr@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("123456");
        driver.findElement(By.name("password2")).sendKeys("123456");
        WebElement buttonRegistry = driver.findElement(By.cssSelector("[value = 'Register']"));
        buttonRegistry.click();
        String emailText = driver.findElement(By.xpath("//body/center/table/tbody/tr[6]/td/table/tbody/tr[4]/td/" +
                "table/tbody/tr/td[2]")).getText();
        String passwordText = driver.findElement(By.xpath("//body/center/table/tbody/tr[6]/td/table/tbody/tr[4]" +
                "/td/table/tbody/tr[2]/td[2]")).getText();
        driver.findElement(By.xpath("//body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td/p/a[@href = './main.py']"))
                .click();
        driver.findElement(By.name("email")).sendKeys(emailText);
        driver.findElement(By.name("password")).sendKeys(passwordText);
        driver.findElement(By.cssSelector("[value = 'Login']")).click();
        Thread.sleep(500);
        WebElement linkLogOut = driver.findElement(By.cssSelector("[href ='./log_out.py']"));

        Assert.assertTrue(linkLogOut.isDisplayed(), "Logout link is not displayed");
    }

    @Test
    public void addToShoppingCartTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value = 'Continue']")).click();
        driver.findElement(By.name("first_name")).sendKeys("Mary");
        driver.findElement(By.name("email")).sendKeys("rtr@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("123456");
        driver.findElement(By.name("password2")).sendKeys("123456");
        WebElement buttonRegistry = driver.findElement(By.cssSelector("[value = 'Register']"));
        buttonRegistry.click();
        String emailText = driver.findElement(By.xpath("//body/center/table/tbody/tr[6]/td/table/tbody/tr[4]/td/" +
                "table/tbody/tr/td[2]")).getText();
        String passwordText = driver.findElement(By.xpath("//body/center/table/tbody/tr[6]/td/table/tbody/tr[4]" +
                "/td/table/tbody/tr[2]/td[2]")).getText();
        driver.findElement(By.xpath("//body/center/table/tbody/tr[6]/td/table/tbody/tr[2]/td/p/a[@href = './main.py']"))
                .click();
        driver.findElement(By.name("email")).sendKeys(emailText);
        driver.findElement(By.name("password")).sendKeys(passwordText);
        driver.findElement(By.cssSelector("[value = 'Login']")).click();
        Thread.sleep(500);
        driver.findElement(By.name("keyword")).sendKeys("The Analects of Confucius");
        driver.findElement(By.cssSelector("[value = 'Search']")).click();
        driver.findElement(By.cssSelector("[href = './add_to_cart.py?book_id=6']")).click();

        String actualResult = driver.findElement(By.cssSelector("[class = 'confirmation_message']")).getText();
        String expectedResult = "Book was added to the Shopping Cart";

        Assert.assertEquals(actualResult,expectedResult);
    }
}
