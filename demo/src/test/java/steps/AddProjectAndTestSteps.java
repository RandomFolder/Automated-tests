package steps;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import aquality.selenium.browser.AqualityServices;
import constants.SharedConstants;
import exam.utils.FileHandler;
import exam.utils.JSONHandler;
import exam.utils.SystemInfo;
import io.restassured.response.Response;
import models.NewTestInfo;
import models.TestAttachment;
import pages.HomePage;
import pages.forms.AddProjectForm;
import requests.AddImageRequest;
import requests.AddLogRequest;
import requests.AddTestRequest;

public class AddProjectAndTestSteps {
    private static final HomePage HOME_PAGE = new HomePage();
    private static final AddProjectForm ADD_PROJECT_FORM = new AddProjectForm();

    public static boolean addProject(String projectName) {
        AqualityServices.getLogger().info("adding project " + projectName);

        HOME_PAGE.clickAddButton();
        AqualityServices.getBrowser().tabs().switchToLastTab();

        ADD_PROJECT_FORM.enterProjectName(projectName);
        ADD_PROJECT_FORM.clickSaveButton();
        boolean result = ADD_PROJECT_FORM.projectGotCreated();
        AqualityServices.getBrowser().tabs().closeTab();
        AqualityServices.getBrowser().tabs().switchToLastTab();
        AqualityServices.getBrowser().getDriver().navigate().refresh();

        return result;
    }

    public static void addLog(String testId, List<String> logs) {
        AqualityServices.getLogger().info("adding logs to test with id = " + testId);

        for (String line : logs) {
            AddLogRequest.addLogToTest(testId, line);
        }
    }

    public static void addImage(String testId) {
        byte[] image = AqualityServices.getBrowser().getScreenshot();
        TestAttachment imageData = new TestAttachment(image, SharedConstants.IMAGE_CONTENT_TYPE);
        String requestBody = JSONHandler.objectToJSONString(imageData);

        AddImageRequest.addImage(testId, requestBody);
    }

    public static int addTest(NewTestInfo info) {
        AqualityServices.getLogger().info("adding test to project " + info.projectName());

        Response addTestResponse = AddTestRequest.addTest(info);
        String testId = addTestResponse.asString();

        return Integer.parseInt(testId);
    }

    public static NewTestInfo collectTestInfo(String projectName, String testName, String methodName) {
        byte[] image = AqualityServices.getBrowser().getScreenshot();
        String env = SystemInfo.getName();
        ArrayList<String> logs = FileHandler.readTextFileAsList(SharedConstants.PATH_TO_LOG);

        return new NewTestInfo(projectName, testName, methodName, env, logs, Base64.getEncoder().encodeToString(image));
    }

    public static boolean addProjectFormDisappeared() {
        return ADD_PROJECT_FORM.state().waitForNotDisplayed();
    }
}
