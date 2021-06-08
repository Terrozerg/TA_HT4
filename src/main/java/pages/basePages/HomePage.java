package pages.basePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public class HomePage extends BasePage<HomePage> {

    @FindBy(xpath = "//div[@data-slot-id='primary-9']//a")
    private List<WebElement> popularItems;

    @FindBy(xpath = "//div[@data-slot-id='primary-12']//a")
    private List<WebElement> trendingBrands;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected HomePage getThis() {
        return this;
    }

    public boolean arePopularItemsDisplayed(){
        return popularItems.stream()
                .peek(item->getActions().moveToElement(item).build().perform())
                .allMatch(WebElement::isDisplayed);
    }

    public boolean areTrendingBrandsDisplayed(){
        return trendingBrands.stream()
                .peek(item->getActions().moveToElement(item).build().perform())
                .allMatch(WebElement::isDisplayed);
    }

    //go to item page
    public void clickPopularItem(String name) {
        popularItems.stream()
                .filter(item -> item.getAttribute("aria-label").contains(name))
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .click();
    }
}
