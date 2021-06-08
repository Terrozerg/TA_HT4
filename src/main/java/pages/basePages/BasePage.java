package pages.basePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.PageObject;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public abstract class BasePage<T extends BasePage<T>> extends PageObject {

    @FindBy(xpath = "//form[@role='search']//input[@id='searchAll']")
    private WebElement searchAllBar;

    @FindBy(xpath = "//form[@role='search']//ul")
    private WebElement searchAllBarSuggestions;

    @FindBy(xpath = "//form[@role='search']//button[@type='submit']")
    private WebElement searchAllButton;

    @FindBy(xpath = "//nav/ul/li")
    private List<WebElement> categories;

    private WebElement innerCategory;

    @FindBy(xpath = "//footer//ul[@class='Jb-z']//a")
    private List<WebElement> socialLinks;

    @FindBy(xpath = "//a[@href='/cart']")
    private WebElement cart;

    @FindBy(xpath = "//a[@aria-label='Sign In']")
    private WebElement signInRegisterButton;

    @FindBy(xpath = "//aside[@role='dialog']")
    private WebElement popupDialog;

    public BasePage(WebDriver driver) {
        super(driver);
    }

    protected abstract T getThis();

    public T inputTextToSearchAllItems(String input){
        searchAllBar.clear();
        searchAllBar.sendKeys(input);

        return getThis();
    }

    public List<String> getSearchAllBarSuggestionsText(){
        searchAllBar.click();

        waitForElementToLoad(searchAllBarSuggestions, DEFAULT_WAIT_TIME);

        return searchAllBarSuggestions.findElements(By.tagName("li")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean isSearchAllButtonDisplayed(){
        return searchAllButton.isDisplayed();
    }

    public boolean isSearchAllBarDisplayed(){
        return searchAllBar.isDisplayed();
    }

    public boolean isCartDisplayed(){
        return cart.isDisplayed();
    }

    public boolean isSignInRegisterButtonDisplayed(){
        return signInRegisterButton.isDisplayed();
    }

    //go to search page
    public void clickSearchAllButton(){
        searchAllButton.click();
    }

    public T clickCategory(String name){
        innerCategory = categories.stream()
                .filter(item->item.findElement(By.tagName("a"))
                        .getText()
                        .contains(name))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        innerCategory.click();

        return getThis();
    }

    //go to search/category page
    public void clickInnerCategory(String element){
        innerCategory.findElements(By.xpath("//div[@data-headercategory]//li/a"))
                .stream()
                .filter(item -> item.getText().equalsIgnoreCase(element))
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .click();
    }

    public void clickInnerCategorySection(String section){
        innerCategory.findElements(By.xpath("//div[@data-headercategory]//section/a"))
                .stream()
                .filter(item -> item.getText().equalsIgnoreCase(section))
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .click();
    }

    public boolean checkIfSocialLinksArePresent(){
        return socialLinks.stream()
                .allMatch(WebElement::isDisplayed);
    }

    public void clickSocialLink(String link){
        WebElement socialLink = socialLinks.stream()
                .filter(item->item.getText().equalsIgnoreCase(link))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        socialLink.click();

        switchToNewWindow();
    }

    public T closePopupDialog(){
        waitForElementToLoad(popupDialog, DEFAULT_WAIT_TIME);

        popupDialog.findElement(By.xpath("//button[@aria-label='Close']"))
                .click();

        return getThis();
    }
}
