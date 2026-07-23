package tests;

import basesClass.TestInit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import static java.lang.Thread.sleep;

public class HomePageTest extends TestInit {

    public String alloUrl = "https://allo.ua";

    @Test
    public void checkAlloButtonDisplay() {

        HomePage homePage = new HomePage(driver);
        openUrl(alloUrl);

        homePage.alloLogo();

        Assert.assertTrue(homePage.alloLogo().isDisplayed());

    }

    @Test
    public void checkFenDisplay() throws InterruptedException {

        openUrl(alloUrl);
        sleep(5000);

        WebElement searchInput = driver.findElement(By.xpath("//input[@id='search-form__input']"));
        Assert.assertTrue(searchInput.isDisplayed(), "Поле пошуку не відображається на сторінці!");
        searchInput.sendKeys("Фен");

        WebElement searchButton = driver.findElement(By.xpath("//button[contains(@class, 'search-form__submit-button')]"));
        searchButton.click();

        sleep(5000);

        WebElement firstProductTitle = driver.findElement(By.xpath("(//a[@class='product-card__title'])[1]"));
        String productText = firstProductTitle.getText();

        Assert.assertTrue(productText.toLowerCase().contains("фен"),
                "Перший товар у списку не містить слово 'Фен'! Знайдено товар: " + productText);

    }

    @Test
    public void testAlloProductDetailVerification() throws InterruptedException {

        openUrl(alloUrl);

        WebElement alloLogo = driver.findElement(By.xpath("//a[@class='v-logo']"));
        Assert.assertTrue(alloLogo.isDisplayed(), "Логотип АЛЛО не відображається на головній сторінці!");

        WebElement searchInput = driver.findElement(By.xpath("//input[@id='search-form__input']"));
        searchInput.sendKeys("AirPods 3");

        WebElement searchButton = driver.findElement(By.xpath("//button[contains(@class, 'search-form__submit-button')]"));
        searchButton.click();

        sleep(5000);

        WebElement firstProduct = driver.findElement(By.xpath("(//a[@class='product-card__title'])[1]"));
        String firstProductTitleText = firstProduct.getText();
        Assert.assertTrue(firstProductTitleText.toLowerCase().contains("airpods"),
                "Перший товар у результатах не є AirPods! Знайдено: " + firstProductTitleText);

        String savedProductName = firstProductTitleText;

        firstProduct.click();

        sleep(5000);

        WebElement detailProductTitle = driver.findElement(By.xpath("//h1"));
        String actualProductName = detailProductTitle.getText();

        Assert.assertEquals(actualProductName, savedProductName,
                "Назва товару на детальній сторінці не збігається з назвою в результатах пошуку!");

    }

    @Test
    public void testAlloBuyersSectionVerification() throws InterruptedException {

        openUrl(alloUrl);
        sleep(3000);

        WebElement buyersButton = driver.findElement(By.xpath("//span[contains(text(), 'Покупцям')]"));
        Assert.assertTrue(buyersButton.isDisplayed(), "Кнопка 'Покупцям' не відображається!");
        buyersButton.click();
        sleep(2000);

        WebElement dropdownMenu = driver.findElement(By.xpath("//div[contains(@class, 'mh-button__dropdown')]"));
        Assert.assertTrue(dropdownMenu.isDisplayed(), "Випадаюче меню 'Покупцям' не відкрилося!");

        WebElement deliveryOption = driver.findElement(By.xpath("//div[contains(@class, 'mh-button__dropdown')]//a[contains(@href, 'shipment_payment')]"));
        Assert.assertTrue(deliveryOption.isDisplayed(), "Пункт 'Доставка та оплата' не відображається в меню!");
        deliveryOption.click();
        sleep(5000);

        String tabTitle = driver.getTitle();
        Assert.assertTrue(tabTitle.toLowerCase().contains("доставка"),
                "Тайтл вкладки браузера не містить згадки про доставку! Поточний тайтл: " + tabTitle);

        WebElement helpMenuContainer = driver.findElement(By.xpath("//div[@class='sp-main-tab']"));
        Assert.assertTrue(helpMenuContainer.isDisplayed(), "Блок меню розділів допомоги 'sp-main-tab' не відображається на сторінці!");

        WebElement howToOrderButton = driver.findElement(By.xpath("//div[@class='sp-main-tab']//button[contains(text(), 'Як оформити замовлення')]"));
        Assert.assertTrue(howToOrderButton.isDisplayed(), "Кнопка 'Як оформити замовлення?' не відображається!");

        Assert.assertTrue(howToOrderButton.getText().contains("Як оформити замовлення"),
                "Текст на кнопці не відповідає очікуваному! Знайдено: " + howToOrderButton.getText());
    }
}