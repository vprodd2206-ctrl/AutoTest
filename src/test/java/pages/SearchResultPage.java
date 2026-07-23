package pages;

import basesClass.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultPage extends BasePage {
    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    private final String FIRST_GOODS = "//a[@class='product-card__title'])[1]";

    public WebElement firstGoods() {
        return visibilityOfElementByXpath(FIRST_GOODS);
    }
}
