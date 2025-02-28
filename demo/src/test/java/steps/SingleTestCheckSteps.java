package steps;

import java.util.ArrayList;
import java.util.List;
import aquality.selenium.browser.AqualityServices;
import constants.SingleTestCheckConstants;
import models.NewTestInfo;
import pages.SingleTestPage;

public class SingleTestCheckSteps {
    private static final SingleTestPage TEST_PAGE = new SingleTestPage();

    public static NewTestInfo getTestInfo() {
        AqualityServices.getLogger().info("receiving test info from the current page");

        String projectName = TEST_PAGE.getParameterValue(SingleTestCheckConstants.PROJECT_NAME);
        String testName = TEST_PAGE.getParameterValue(SingleTestCheckConstants.TEST_NAME);
        String methodName = TEST_PAGE.getParameterValue(SingleTestCheckConstants.METHOD_NAME);
        String env = TEST_PAGE.getParameterValue(SingleTestCheckConstants.ENV);

        List<String> logs = new ArrayList<>();
        for (int i = 0; i < TEST_PAGE.getLogLinesCount(); i++) {
            logs.add(TEST_PAGE.getLogLine(i).trim());
        }

        String imageAsBase64 = TEST_PAGE.getImageAsBase64String();

        if (imageAsBase64 != null) {
            String[] imageSplit = imageAsBase64.split(SingleTestCheckConstants.COMA);
            imageAsBase64 = imageSplit[imageSplit.length - 1];
        }

        return new NewTestInfo(projectName, testName, methodName, env, logs, imageAsBase64);
    }
}
