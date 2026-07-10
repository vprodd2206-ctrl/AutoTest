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
}
