package pages.containers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.PageObject;

public class Login extends PageObject {

    @FindBy(id = "zapposSignIn")
    private WebElement zapposSignInButton;

    @FindBy(id = "amazonSignIn")
    private WebElement amazonSignInButton;

    @FindBy(id = "googleSignIn")
    private WebElement googleSignInButton;

    @FindBy(id = "zapposRegister")
    private WebElement zapposRegisterButton;

    public Login(WebDriver driver) {
        super(driver);
    }

    public boolean isZapposSignInButtonVisible(){
        return zapposSignInButton.isDisplayed();
    }

    public boolean isAmazonSignInButtonVisible(){
        return amazonSignInButton.isDisplayed();
    }

    public boolean isGoogleSignInButtonVisible(){
        return googleSignInButton.isDisplayed();
    }

    public boolean isZapposRegisterButtonVisible(){
        return zapposRegisterButton.isDisplayed();
    }

    //go to register page
    public void clickZapposRegisterButton(){
        zapposRegisterButton.click();
    }
}
