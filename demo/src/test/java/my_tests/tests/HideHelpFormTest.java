package my_tests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import my_tests.constants.HideHelpFormTestConstants;
import my_tests.constants.SharedConstants;
import my_tests.pages.GamePage;
import my_tests.pages.MainPage;

public class HideHelpFormTest extends BaseTest
{
    @Test
    public void runHideHelpFormTest()
    {
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main page wasn't found");
        mainPage.goToNextPage();

        GamePage gamePage = new GamePage();
        gamePage.getHelpForm().pressHideButton();
        gamePage.getHelpForm().waitUntilFormIsHidden(HideHelpFormTestConstants.HELP_FORM_DISAPPEAR_TIMEOUT, SharedConstants.POLLING_INTERVAL);
        Assert.assertFalse(gamePage.getHelpForm().isTitleVisible(), "Help form is still visible");
    }    
}
