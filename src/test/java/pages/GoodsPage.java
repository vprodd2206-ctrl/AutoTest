package pages;

import basesClass.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoodsPage extends BasePage {

    public GoodsPage(WebDriver driver) {
        super(driver);
    }

    private final String PRODUCT_HEADER_TITLE = "//h1[@class='p-view__header-title']";

    public WebElement productHeaderTitle() {
        return visibilityOfElementByXpath(PRODUCT_HEADER_TITLE);
    }

    public String getNameProductHeaderTitle() {
        return productHeaderTitle().getText();
    }

}
