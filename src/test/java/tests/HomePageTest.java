package tests;

import basesClass.TestInit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GoodsPage;
import pages.HomePage;
import pages.SearchResultPage;

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
    public void checkFenDisplay() {

        HomePage homePage = new HomePage(driver);
        SearchResultPage searchResultPage = new SearchResultPage(driver);

        openUrl(alloUrl);

        Assert.assertTrue(homePage.searchField().isDisplayed());

        homePage.searchField().sendKeys("Фен");
        homePage.searchButton().click();

        Assert.assertTrue(searchResultPage.firstGoods().getText().contains("Фен"));

    }

    @Test
    public void testAlloProductDetailVerification() {

        HomePage homePage = new HomePage(driver);
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        GoodsPage goodsPage = new GoodsPage(driver);

        String airPods = "AirPods 4";

        openUrl(alloUrl);

        homePage.alloLogoDisplayed();
        homePage.enterValuesInSearchField(airPods);
        homePage.clickSearchButton();

        String expectedNameFirstAirPods = searchResultPage.getNameFirstAirPods();
        System.out.println(expectedNameFirstAirPods + "    очікуваний результат");
        Assert.assertTrue(expectedNameFirstAirPods.contains("AirPods 4"));

        searchResultPage.clickFirstAirPods();

        String actualNameAirPods = goodsPage.getNameProductHeaderTitle();
        System.out.println(actualNameAirPods + "    актуальний результат");
        Assert.assertEquals(actualNameAirPods, expectedNameFirstAirPods);

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