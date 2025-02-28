package tests;

import java.sql.Timestamp;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import aquality.selenium.browser.AqualityServices;
import constants.AddProjectAndTestConstants;
import constants.RegistrationConstants;
import constants.TestsCheckConstants;
import exam.utils.CookieManager;
import io.restassured.response.Response;
import models.NewTestInfo;
import models.TestData;
import pages.HomePage;
import pages.TestsPage;
import requests.GenerateTokenRequest;
import steps.AddProjectAndTestSteps;
import steps.SingleTestCheckSteps;
import steps.TestsCheckSteps;

public class ReportingPortalTest extends BaseTest {
    @Test(description = "My test")
    public void runReportingPortalTest() {
        Response tokenResponse = GenerateTokenRequest.generateToken(RegistrationConstants.CREATE_TOKEN_PARAMS);
        String token = tokenResponse.asString();
        Assert.assertEquals(tokenResponse.getStatusCode(), HttpStatus.SC_OK, "token wasn't created");

        CookieManager.addCookie(RegistrationConstants.COOKIE_NAME, token);
        AqualityServices.getBrowser().getDriver().navigate().refresh();
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.state().waitForDisplayed(), "Projects page didn't open");
        Assert.assertEquals(homePage.getVersion(), RegistrationConstants.VARIANT, "version is not equal to" + RegistrationConstants.VARIANT);

        int projectId = homePage.getProjectId(TestsCheckConstants.PROJECT_NAME);
        homePage.goToProject(TestsCheckConstants.PROJECT_NAME);
        TestsPage testsPage = new TestsPage();
        int testsCount = testsPage.getTestsCountOnCurrentPage();
        List<TestData> tests = TestsCheckSteps.getTests(projectId);
        Assert.assertNotNull(tests, "Request didn't return tests in expected format");

        List<TestData> testsSorted = TestsCheckSteps.sortTestByStartTime(tests, testsCount);
        List<TestData> testsOnThePage = TestsCheckSteps.getTestsOnTestsPage();
        List<TestData> testsOnThePageSorted = TestsCheckSteps.sortTestByStartTime(testsOnThePage, testsCount);

        Assert.assertEquals(testsOnThePage, testsOnThePageSorted, "Tests on the page are not sorted");
        Assert.assertEquals(testsSorted, testsOnThePage, "Tests on the page are not equal to those received by API call");

        AqualityServices.getBrowser().getDriver().navigate().back();
        String fullProjectName = AddProjectAndTestConstants.PROJECT_NAME + new Timestamp(System.currentTimeMillis());
        Assert.assertTrue(AddProjectAndTestSteps.addProject(fullProjectName), "Project wasn't added");
        Assert.assertTrue(AddProjectAndTestSteps.addProjectFormDisappeared(), "Add project form didn't disappear");
        Assert.assertTrue(homePage.projectExists(fullProjectName), "Project does not exist");

        homePage.goToProject(fullProjectName);
        testsPage.waitUntilGlyphIsPresent();
        NewTestInfo testInfo = AddProjectAndTestSteps.collectTestInfo(fullProjectName, this.currentTestName, this.currentMethodName);
        int newTestId = AddProjectAndTestSteps.addTest(testInfo);
        AddProjectAndTestSteps.addLog(String.valueOf(newTestId), testInfo.logs());
        AddProjectAndTestSteps.addImage(String.valueOf(newTestId));
        Assert.assertTrue(testsPage.testExists(newTestId), "New test wasn't added");

        testsPage.goToTest(newTestId);
        NewTestInfo testInfoFromTestPage = SingleTestCheckSteps.getTestInfo();
        Assert.assertEquals(testInfo, testInfoFromTestPage, "Data does not match");
    }
}
