package pages.containers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.PageObject;

import java.util.List;
import java.util.stream.Collectors;

public class Trends extends PageObject {

    @FindBy(xpath = "//div[@class='chip']//a")
    private List<WebElement> trendsList;

    @FindBy(xpath = "//div[@class='content']//button")
    private WebElement closeButton;

    public Trends(WebDriver driver) {
        super(driver);
    }

    public List<String> getTrendsListText(){
        waitForElementsToLoad(trendsList, DEFAULT_WAIT_TIME);

        return trendsList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
