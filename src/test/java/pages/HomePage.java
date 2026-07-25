package pages;

import basesClass.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private final String ALLO_LOGO = "//a[@class='v-logo']";
    private final String CATALOG_BUTTON = "//div[@class='mh-catalog-btn']";
    private final String SEARCH_FIELD = "//input[@id='search-form__input']";
    private final String SEARCH_BUTTON = "//button[contains(@class, 'search-form__submit-button')]";
    private final String BUYERS_BUTTON = "//span[@class='mh-button__title' and contains(text(), 'Покупцям')]";
    private final String DROPDOWN_MENU = "//div[@class='mh-button__dropdown']";
    private final String DELIVERY_OPTION = "//div[@class='mh-button__dropdown']//a[contains(@href, 'shipment_payment')]";
    private final String HELP_MENU_CONTAINER = "//div[@class='sp-main-tab']";
    private final String HOW_TO_ORDER_BUTTON = "//button[@class='sp-tablinks active' and contains(text(), 'Як оформити замовлення')]";


    public WebElement alloLogo() {
        return visibilityOfElementByXpath(ALLO_LOGO);
    }

    public WebElement catalogButton() {
        return visibilityOfElementByXpath(CATALOG_BUTTON);
    }

    public WebElement searchField() {
        return visibilityOfElementByXpath(SEARCH_FIELD);
    }

    public WebElement searchButton() {
        return visibilityOfElementByXpath(SEARCH_BUTTON);
    }

    public void alloLogoDisplayed() {
        alloLogo().isDisplayed();
    }

    public void enterValuesInSearchField(String value) {
        searchField().sendKeys(value);
    }

    public void clickSearchButton() {
        searchButton().click();
    }

    public WebElement buyersButton() {
        return visibilityOfElementByXpath(BUYERS_BUTTON);
    }

    public WebElement dropdownMenu() {
        return visibilityOfElementByXpath(DROPDOWN_MENU);
    }

    public WebElement deliveryOption() {
        return visibilityOfElementByXpath(DELIVERY_OPTION);
    }

    public WebElement helpMenuContainer() {
        return visibilityOfElementByXpath(HELP_MENU_CONTAINER);
    }

    public WebElement howToOrderButton() {
        return visibilityOfElementByXpath(HOW_TO_ORDER_BUTTON);
    }

    public void clickBuyersButton() {
        buyersButton().click();
    }

    public void clickDeliveryOption() {
        deliveryOption().click();
    }
}
