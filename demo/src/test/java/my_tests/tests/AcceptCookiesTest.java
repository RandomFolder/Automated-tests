package my_tests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import my_tests.constants.SharedConstants;
import my_tests.pages.GamePage;
import my_tests.pages.MainPage;

public class AcceptCookiesTest extends BaseTest
{
    @Test
    public void runAcceptCookiesTest()
    {
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main page wasn't found");
        mainPage.goToNextPage();

        GamePage gamePage = new GamePage();
        gamePage.waitForAcceptCookiesForm(SharedConstants.DEFAULT_TIMEOUT, SharedConstants.POLLING_INTERVAL);
        gamePage.getAcceptCookiesForm().acceptCookies();
        Assert.assertFalse(gamePage.getAcceptCookiesForm().state().isDisplayed(), "Accept cookies form is still visible");
    }
}
