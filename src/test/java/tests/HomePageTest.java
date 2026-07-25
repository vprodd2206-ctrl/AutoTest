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
        Assert.assertTrue(expectedNameFirstAirPods.contains("AirPods 4"));

        searchResultPage.clickFirstAirPods();

        String actualNameAirPods = goodsPage.getNameProductHeaderTitle();

        Assert.assertEquals(actualNameAirPods, expectedNameFirstAirPods);
    }

    @Test
    public void testAlloBuyersSectionVerification() {

        HomePage homePage = new HomePage(driver);

        openUrl(alloUrl);

        Assert.assertTrue(homePage.buyersButton().isDisplayed());
        homePage.clickBuyersButton();

        Assert.assertTrue(homePage.dropdownMenu().isDisplayed());

        Assert.assertTrue(homePage.deliveryOption().isDisplayed());
        homePage.clickDeliveryOption();

        String tabTitle = driver.getTitle();

        Assert.assertTrue(tabTitle.toLowerCase().contains("доставка"));
        Assert.assertTrue(homePage.helpMenuContainer().isDisplayed());
        Assert.assertTrue(homePage.howToOrderButton().isDisplayed());
        Assert.assertTrue(homePage.howToOrderButton().getText().contains("Як оформити замовлення"));
    }
}