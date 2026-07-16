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
        Assert.assertTrue(alloLogo.isDisplayed(), "Логотип АЛЛО не відображається на головній сторінці!");

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

    @Test
    public void testAlloProductDetailVerification() throws InterruptedException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://allo.ua/");

        WebElement alloLogo = driver.findElement(By.xpath("//a[@class='v-logo']"));
        Assert.assertTrue(alloLogo.isDisplayed(), "Логотип АЛЛО не відображається на головній сторінці!");

        WebElement searchInput = driver.findElement(By.xpath("//input[@id='search-form__input']"));
        searchInput.sendKeys("AirPods 3");

        WebElement searchButton = driver.findElement(By.xpath("//button[contains(@class, 'search-form__submit-button')]"));
        searchButton.click();

        Thread.sleep(5000);

        WebElement firstProduct = driver.findElement(By.xpath("(//a[@class='product-card__title'])[1]"));
        String firstProductTitleText = firstProduct.getText();
        Assert.assertTrue(firstProductTitleText.toLowerCase().contains("airpods"),
                "Перший товар у результатах не є AirPods! Знайдено: " + firstProductTitleText);

        String savedProductName = firstProductTitleText;

        firstProduct.click();

        Thread.sleep(5000);

        WebElement detailProductTitle = driver.findElement(By.xpath("//h1"));
        String actualProductName = detailProductTitle.getText();

        Assert.assertEquals(actualProductName, savedProductName,
                "Назва товару на детальній сторінці не збігається з назвою в результатах пошуку!");

        driver.quit();
    }
}