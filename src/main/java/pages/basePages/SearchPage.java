package pages.basePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchPage extends BasePage<SearchPage> {

    @FindBy(xpath = "//div[@id='searchPage']//a")
    private List<WebElement> searchResultItems;

    @FindBy(xpath = "//select[@id='searchSort']")
    private WebElement sortByOption;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected SearchPage getThis() {
        return this;
    }

    public boolean checkIfSearchedItemsArePresent(String name) {
        waitForElementToLoad(sortByOption, DEFAULT_WAIT_TIME);

        return searchResultItems.stream()
                .anyMatch(item -> item.getText().contains(name));
    }

    public boolean checkIfSortByPriceDescendingWorks() {
        waitForElementsToLoad(searchResultItems, DEFAULT_WAIT_TIME);

        Iterator<WebElement> itemsIter = searchResultItems.iterator();
        WebElement first = itemsIter.next();

        Pattern pattern = Pattern.compile("(\\$[\\d]+)");
        Matcher matcher;

        while (itemsIter.hasNext()) {
            WebElement curr = itemsIter.next();

            matcher = pattern.matcher(first.getText());
            String one = matcher.find() ? matcher.group(1) : " ";

            matcher = pattern.matcher(curr.getText());
            String two = matcher.find() ? matcher.group(1) : " ";

            if (one.compareTo(two) < 0) {
                return false;
            }

            first = curr;
        }

        return true;
    }

    public SearchPage selectSortByOption(String name) {
        waitForElementToLoad(sortByOption, DEFAULT_WAIT_TIME);

        Select select = new Select(sortByOption);
        select.selectByValue(name);

        return getThis();
    }
}
