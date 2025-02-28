package my_tests.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import aquality.selenium.browser.AqualityServices;
import my_tests.constants.SharedConstants;
import task1.utils.FileExplorerHandler;

public class BaseTest
{
    @BeforeMethod
    public void setupBrowser()
    {
        AqualityServices.getBrowser();
        AqualityServices.getBrowser().maximize();
        AqualityServices.getBrowser().goTo(SharedConstants.WEB_RESOURCE_PATH);
        AqualityServices.getBrowser().waitForPageToLoad();
    }


    @AfterMethod
    public void closeBrowser()
    {
        if (AqualityServices.isBrowserStarted())
        {
            AqualityServices.getBrowser().quit();
        }
    }


    @AfterTest
    public void deleteDownloadDir()
    {
        FileExplorerHandler.deleteDirectory(AqualityServices.getBrowserProfile().getDriverSettings().getDownloadDir());
    }
}
