import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlloTest {

    private WebDriver driver;

    @Test
    public void testAlloLogoVisibility() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://allo.ua/");

        Thread.sleep(5000);

        WebElement alloLogo = driver.findElement(By.xpath("//a[@class='v-logo']"));

        Assert.assertTrue(alloLogo.isDisplayed());
        driver.quit();
    }

    @Test
    public void testAlloSearchFunctionality() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://allo.ua/");

        Thread.sleep(5000);

        WebElement searchInput = driver.findElement(By.xpath("//input[@id='search-form__input']"));

        Assert.assertTrue(searchInput.isDisplayed(), "Поле пошуку не відображається на сторінці!");

        searchInput.sendKeys("Фен");

        WebElement searchButton = driver.findElement(By.xpath("//button[contains(@class, 'search-form__submit-button')]"));
        searchButton.click();

        Thread.sleep(5000);

        WebElement firstProductTitle = driver.findElement(By.xpath("(//a[@class='product-card__title'])[1]"));
        String productText = firstProductTitle.getText();

        Assert.assertTrue(productText.toLowerCase().contains("фен"),
                "Перший товар у списку не містить слово 'Фен'! Знайдено товар: " + productText);
        driver.quit();
    }
}
