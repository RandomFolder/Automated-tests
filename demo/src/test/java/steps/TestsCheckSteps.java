package steps;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import constants.SharedConstants;
import constants.TestsCheckConstants;
import exam.utils.HttpRequests;
import exam.utils.JSONHandler;
import io.restassured.response.Response;
import models.TestData;
import pages.TestsPage;
import requests.AllTestsRequest;

public class TestsCheckSteps {
    private static final TestsPage TESTS_PAGE = new TestsPage();

    public static List<TestData> getTests(int projectId) {
        AqualityServices.getLogger().info("making an API call to receive all tests of project with id = " + projectId);
        
        Response allTestsResponse = AllTestsRequest.getAllTests(String.valueOf(projectId));

        return JSONHandler.isJSONValid(allTestsResponse.asString()) ? HttpRequests.convertResponseToListOfModels(allTestsResponse, SharedConstants.EMPTY_STRING, TestData.class) : null;
    }

    public static List<TestData> getTestsOnTestsPage() {
        AqualityServices.getLogger().info("receiving all tests on the current page");

        List<TestData> result = new ArrayList<>();
        List<ILabel> tests = TESTS_PAGE.getTests();
        List<ILabel> tableHeaders = TESTS_PAGE.getTableHeaders();
        Map<String, Integer> headersIndeces = new HashMap<String, Integer>() {};

        for (int i = 0; i < tableHeaders.size(); i++) {
            headersIndeces.put(tableHeaders.get(i).getText(), i + 1);
        }

        for (int i = 0; i < tests.size(); i++) {
            String name = TESTS_PAGE.getValueByHeader(tests.get(i), headersIndeces.get(TestsCheckConstants.NAME_HEADER));
            String method = TESTS_PAGE.getValueByHeader(tests.get(i), headersIndeces.get(TestsCheckConstants.METHOD_HEADER));
            String status = TESTS_PAGE.getValueByHeader(tests.get(i), headersIndeces.get(TestsCheckConstants.STATUS_HEADER));
            String startTime = TESTS_PAGE.getValueByHeader(tests.get(i), headersIndeces.get(TestsCheckConstants.START_HEADER));
            String endTime = TESTS_PAGE.getValueByHeader(tests.get(i), headersIndeces.get(TestsCheckConstants.END_HEADER));
            if (endTime == SharedConstants.EMPTY_STRING) {
                endTime = null;
            }
            String duration = TESTS_PAGE.getValueByHeader(tests.get(i), headersIndeces.get(TestsCheckConstants.DURATION_HEADER));

            TestData test = new TestData(duration, method, name, startTime, endTime, status);
            result.add(test);
        }

        return result;
    }

    public static List<TestData> sortTestByStartTime(List<TestData> tests, Integer elementsLimit) {
        List<TestData> modifiableList = new ArrayList<TestData>(tests);

        Collections.sort(modifiableList, new Comparator<TestData>() {
            DateFormat df = new SimpleDateFormat(TestsCheckConstants.DATE_FORMAT);
            @Override
            public int compare(TestData s1, TestData s2) {
                try {
                    return df.parse(s2.startTime()).compareTo(df.parse(s1.startTime()));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });

        if (elementsLimit != null) {
            modifiableList = modifiableList.stream().limit(elementsLimit).collect(Collectors.toList());
        }

        return modifiableList;
    }
}
