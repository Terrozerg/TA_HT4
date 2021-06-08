package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.PageObject;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class StyleRoomPage extends PageObject {

    @FindBy(xpath = "//div[@class='carousel-container']//a[contains(@class,'sub-nav-item')]//p")
    private List<WebElement> trendsList;

    @FindBy(xpath = "//div[@class='see-all-container']/a")
    private WebElement seeAllTrendsButton;

    @FindBy(xpath = "//div[@class='middle']//div[@class='left']//a")
    private List<WebElement> genderCategories;

    public StyleRoomPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getTrendsListText(){
        return trendsList.stream()
                .peek(this::scrollIntoView)
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void clickSeaAllTrendsButton(){
        seeAllTrendsButton.click();
    }

    public StyleRoomPage selectGenderCategory(String gender){
        genderCategories.stream()
                .filter(item->item.getText().equalsIgnoreCase(gender))
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
        .click();

        return this;
    }
}
