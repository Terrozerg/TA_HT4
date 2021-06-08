package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import pages.*;
import pages.basePages.HomePage;
import pages.basePages.ItemPage;
import pages.basePages.SearchPage;
import pages.containers.Cart;
import pages.containers.Login;
import pages.containers.Trends;
import utils.CapabilityFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    private CapabilityFactory capabilityFactory = new CapabilityFactory();

    private static final String SITE_URL = "https://www.zappos.com/";

    @BeforeMethod
    @Parameters(value = {"browser"})
    public void setUp(@Optional("firefox") String browser) throws MalformedURLException {
        driver.set(new RemoteWebDriver(
                new URL("http://192.168.1.233:4444/wd/hub"),
                capabilityFactory.getCapabilities(browser)));
        getDriver().manage().window().maximize();
        getDriver().get(SITE_URL);
    }

    @AfterMethod
    public void shutDown(){
        getDriver().quit();
    }

    @AfterClass
    public void terminate(){
        driver.remove();
    }

    public WebDriver getDriver(){
        return driver.get();
    }

    public HomePage getHomePage(){
        return new HomePage(getDriver());
    }

    public ItemPage getItemPage(){
        return new ItemPage(getDriver());
    }

    public RegisterPage getRegisterPage(){
        return new RegisterPage(getDriver());
    }

    public SearchPage getSearchPage(){
        return new SearchPage(getDriver());
    }

    public SocialPage getSocialPage(){
        return new SocialPage(getDriver());
    }

    public Cart getCart(){
        return new Cart(getDriver());
    }

    public Login getLogin(){
        return new Login(getDriver());
    }

    public StyleRoomPage getStyleRoomPage(){
        return new StyleRoomPage(getDriver());
    }

    public Trends getTrends(){
        return new Trends(getDriver());
    }
}
