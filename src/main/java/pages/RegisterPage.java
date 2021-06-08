package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.PageObject;

public class RegisterPage extends PageObject {

    @FindBy(xpath = "//input[@id='ap_customer_name']")
    private WebElement nameField;

    @FindBy(xpath = "//input[@id='ap_email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='ap_password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='ap_password_check']")
    private WebElement passwordCheckField;

    @FindBy(xpath = "//input[@id='continue']")
    private WebElement createButton;

    @FindBy(className = "a-alert-heading")
    private WebElement alertMessage;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public RegisterPage inputTextIntoNameField(String text){
        nameField.clear();
        nameField.sendKeys(text);

        return this;
    }

    public RegisterPage inputTextIntoPasswordField(String text){
        passwordField.clear();
        passwordField.sendKeys(text);

        return this;
    }

    public RegisterPage inputTextIntoPasswordCheckField(String text){
        passwordCheckField.clear();
        passwordCheckField.sendKeys(text);

        return this;
    }

    public void clickCreateButton(){
        createButton.click();
    }

    public boolean isAlertMessagePresent(){
        return alertMessage.isDisplayed();
    }
}
