package pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class PageObject {
    private WebDriver driver;
    private Actions actions;
    private String savedWindowHandle;

    public static final int DEFAULT_WAIT_TIME = 60;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.actions = new Actions(driver);

        savedWindowHandle = driver.getWindowHandle();
    }

    public void waitForElementToLoad(WebElement element, int timeOut) {
        new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementsToLoad(List<WebElement> elements, int timeOut) {
        new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public WebElement waitForElementToUnload(WebElement element, int timeOut) {
        new WebDriverWait(driver, timeOut)
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.invisibilityOf(element));

        return element;
    }

    public WebElement waitForElementToBeClickable(WebElement element, int timeOut) {
        new WebDriverWait(driver, timeOut)
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.elementToBeClickable(element));

        return element;
    }

    public void waitForPageToLoad(int timeOut) {
        new WebDriverWait(driver, timeOut).until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    public void scrollIntoView(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: \"end\", inline: \"nearest\"});", element);
    }

    public void switchToNewWindow(){
        savedWindowHandle = driver.getWindowHandle();

        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        waitForPageToLoad(DEFAULT_WAIT_TIME);
    }

    public void switchWindowBack(){
        driver.switchTo().window(savedWindowHandle);

        waitForPageToLoad(DEFAULT_WAIT_TIME);
    }

    public String getPageUrl(){
        return driver.getCurrentUrl();
    }

    public Actions getActions() {
        return actions;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
