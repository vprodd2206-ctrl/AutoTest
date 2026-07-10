import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AlloTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testAlloLogoVisibility() {
        driver.get("https://allo.ua/");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement alloLogo = driver.findElement(By.xpath("//a[@class='v-logo']"));

        Assert.assertTrue(alloLogo.isDisplayed());

        System.out.println("Тест завершився успішно: логотип знайдено і він відображається!");
        // Створення Pull Request для перевірки ДЗ
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
