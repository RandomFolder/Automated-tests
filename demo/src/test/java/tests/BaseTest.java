package tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import aquality.selenium.browser.AqualityServices;
import constants.SharedConstants;
import exam.utils.FileHandler;

public class BaseTest {
    protected String currentTestName;
    protected String currentMethodName;

    @BeforeTest
    public void clearLogs() {
        FileHandler.clearFile(SharedConstants.PATH_TO_LOG);
    }

    @BeforeMethod
    public void setupBrowser(ITestResult result) {
        AqualityServices.getBrowser();
        AqualityServices.getBrowser().maximize();
        AqualityServices.getBrowser().goTo(SharedConstants.WEB_RESOURCE_PATH);

        currentTestName = result.getMethod().getDescription();

        String className = result.getMethod().getInstance().toString().split(SharedConstants.CLASS_NAME_SEPARATOR)[0];
        String methodName = result.getMethod().getMethodName();
        currentMethodName = className + SharedConstants.METHOD_NAME_PATH_SEPARATOR + methodName;
    }
    
    @AfterMethod
    public void closeBrowser() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }
}
