package pages.containers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.PageObject;

import java.util.List;
import java.util.stream.Collectors;

public class Cart extends PageObject {

    @FindBy(xpath = "//a[@data-te='TE_CART_PRODUCTCLICKED']/span")
    private List<WebElement> cartItemDescription;

    @FindBy(xpath = "//form[@action='/checkout/initiate']//button[@type='submit']")
    private WebElement checkoutButton;

    public Cart(WebDriver driver) {
        super(driver);
    }

    public String getCartItemDescriptionText() {
        waitForElementToLoad(checkoutButton, DEFAULT_WAIT_TIME);

        return cartItemDescription.stream()
                .map(WebElement::getText)
                .collect(Collectors.joining());
    }

    public void clickCheckoutButton() {
        waitForElementToLoad(checkoutButton, DEFAULT_WAIT_TIME);

        checkoutButton.click();
    }
}
