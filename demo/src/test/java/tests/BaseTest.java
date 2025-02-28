package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import aquality.selenium.browser.AqualityServices;
import constants.SharedConstants;
import task4.utils.FileHandler;

public class BaseTest {
    @BeforeMethod
    public void setupBrowser() {
        AqualityServices.getBrowser();
        AqualityServices.getBrowser().maximize();
        AqualityServices.getBrowser().goTo(SharedConstants.WEB_RESOURCE_PATH);
        AqualityServices.getBrowser().waitForPageToLoad();
    }
    
    @AfterMethod
    public void closeBrowser() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }

    @AfterTest
    public void deleteDownloadDir() {
        FileHandler.deleteDirectory(AqualityServices.getBrowserProfile().getDriverSettings().getDownloadDir());
    }
}
