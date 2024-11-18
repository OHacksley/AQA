package AutoTest1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AbraMarketLoginTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\zavmv\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test(priority = 1)
    public void testAbraMarketLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        driver.get("https://dev.abra-market.com/login");

        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement loginButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.LoginForm_button_submit__RF4wu")));
        emailField.sendKeys("famezm@yandex.ru");
        passwordField.sendKeys("Test123*");

        loginButton.click();

        wait.until(ExpectedConditions.urlToBe("https://dev.abra-market.com/"));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://dev.abra-market.com/", "Вход не выполнен: URL неверный");
        System.out.println("Вход выполнен успешно!");
    }

    @Test(priority = 2, dependsOnMethods = {"testAbraMarketLogin"})
    public void testNavigateToNews() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement newsLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.HeaderMenuItem_item__noyAd[href='/news']")));

        newsLink.click();
        wait.until(ExpectedConditions.urlContains("/news"));
        System.out.println("Переход на страницу новостей успешен!");


        //проверка элементов на странице новостей (доделать)
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".coming soon...")));
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }
}