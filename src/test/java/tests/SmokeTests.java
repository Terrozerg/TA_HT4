package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static pageObject.PageObject.DEFAULT_WAIT_TIME;

public class SmokeTests extends BaseTest {

    @Test
    public void testBasePage(){
        Assert.assertTrue(getHomePage().isSearchAllButtonDisplayed());
        Assert.assertTrue(getHomePage().isSearchAllBarDisplayed());
        Assert.assertTrue(getHomePage().isCartDisplayed());
        Assert.assertTrue(getHomePage().isSignInRegisterButtonDisplayed());
        Assert.assertTrue(getHomePage().arePopularItemsDisplayed());
        Assert.assertTrue(getHomePage().areTrendingBrandsDisplayed());
    }

    @Test
    public void testSearch() {
        String searchText = "shorts";

        List<String> resultText =
                getHomePage().inputTextToSearchAllItems(searchText)
                        .getSearchAllBarSuggestionsText();

        Assert.assertTrue(resultText.contains(searchText));

        getHomePage().clickSearchAllButton();

        Assert.assertTrue(getSearchPage().checkIfSearchedItemsArePresent(searchText));
    }

    @Test
    public void testCategoriesSearch() {
        String searchText = "Sandals";
        String category = "Sale";
        String expectedSearchResult = "Clarks";
        String sortValue = "price-desc";

        getHomePage().clickCategory(category)
                .clickInnerCategory(searchText);

        Assert.assertTrue(getSearchPage().checkIfSearchedItemsArePresent(expectedSearchResult));

        Assert.assertTrue(getSearchPage().selectSortByOption(sortValue)
                .checkIfSortByPriceDescendingWorks());
    }

    @Test
    public void testCartCheckout() {
        String itemName = "Havaianas";

        getHomePage().waitForPageToLoad(DEFAULT_WAIT_TIME);
        getHomePage().clickPopularItem(itemName);
        getItemPage().closePopupDialog()
                .chooseAnyOptions()
                .clickAddToCartButton();

        String cartItemText =
                getCart().getCartItemDescriptionText();

        Assert.assertTrue(cartItemText.contains(itemName));

        getCart().clickCheckoutButton();

        getLogin().waitForPageToLoad(DEFAULT_WAIT_TIME);
        Assert.assertTrue(getLogin().isZapposSignInButtonVisible());
        Assert.assertTrue(getLogin().isAmazonSignInButtonVisible());
        Assert.assertTrue(getLogin().isGoogleSignInButtonVisible());
        Assert.assertTrue(getLogin().isZapposRegisterButtonVisible());

        getLogin().clickZapposRegisterButton();
        getRegisterPage().inputTextIntoNameField("1")
                .inputTextIntoPasswordField("1")
                .inputTextIntoPasswordCheckField("1")
                .clickCreateButton();

        Assert.assertTrue(getRegisterPage().isAlertMessagePresent());
    }

    @Test
    public void testStyleRoom(){
        String category = "New";
        String section = "The Style Room";
        String gender = "Men";

        getHomePage().clickCategory(category)
                .clickInnerCategorySection(section);

        getStyleRoomPage().waitForPageToLoad(DEFAULT_WAIT_TIME);
        List<String> outerTrends = getStyleRoomPage()
                .selectGenderCategory(gender)
                .getTrendsListText();

        getStyleRoomPage().clickSeaAllTrendsButton();
        List<String> innerTrends = getTrends().getTrendsListText();

        Assert.assertEquals(outerTrends, innerTrends);
    }

    @Test
    public void testSocials() {
        String linkName = "facebook";

        Assert.assertTrue(getHomePage().checkIfSocialLinksArePresent());

        getHomePage().clickSocialLink(linkName);

        getSocialPage().waitForPageToLoad(DEFAULT_WAIT_TIME);
        Assert.assertTrue(getSocialPage().getPageUrl().contains(linkName));
    }
}
