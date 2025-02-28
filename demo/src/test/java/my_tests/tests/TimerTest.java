package my_tests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import my_tests.constants.TimerTestConstants;
import my_tests.pages.GamePage;
import my_tests.pages.MainPage;

public class TimerTest extends BaseTest
{
    @Test
    public void runTimerTest()
    {
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main page wasn't found");
        mainPage.goToNextPage();

        GamePage gamePage = new GamePage();
        Assert.assertEquals(gamePage.getTimerCurrentTime(), TimerTestConstants.EXPECTED_TIME_VALUE, "Current time is not " + TimerTestConstants.EXPECTED_TIME_VALUE);
    }    
}
