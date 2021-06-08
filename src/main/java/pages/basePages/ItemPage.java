package pages.basePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ItemPage extends BasePage<ItemPage> {

    @FindBy(xpath = "//form[@method='POST']//select")
    private List<WebElement> optionSelectors;

    @FindBy(xpath = "//button[@data-track-value='Add-To-Cart']")
    private WebElement addToCartButton;

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ItemPage getThis() {
        return this;
    }

    public ItemPage chooseAnyOptions(){
        waitForElementsToLoad(optionSelectors, DEFAULT_WAIT_TIME);

        optionSelectors.forEach(option->new Select(option).selectByIndex(1));

        return getThis();
    }

    //go to cart
    public void clickAddToCartButton(){
        waitForElementToLoad(addToCartButton, 10);

        addToCartButton.click();
    }
}
