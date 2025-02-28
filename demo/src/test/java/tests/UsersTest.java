package tests;

import java.util.List;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import constants.SharedConstants;
import constants.UsersTestConstants;
import io.restassured.response.Response;
import models.User;
import requests.UsersRequest;
import task3.utils.HttpRequests;
import task3.utils.JSONHandler;

public class UsersTest
{
    @Test
    public void runAllUsersTest()
    {
        Response response = UsersRequest.performGet();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Received status code is not " + HttpStatus.SC_OK);
        Assert.assertTrue(response.contentType().contains(SharedConstants.JSON_CONTENT_TYPE), "Response body is not json");

        List<User> users = HttpRequests.convertResponseToListOfModels(response, UsersTestConstants.USERS_JSON_PATH, User.class);
        User neededUser = users.stream()
                            .filter(user -> (user.getId() == UsersTestConstants.USER_ID))
                            .findAny()
                            .orElse(null);
        User user = JSONHandler.readFile(UsersTestConstants.PATH_TO_USER_DATA, User.class);
        Assert.assertTrue(neededUser.equals(user), "Received user's data does not match the test data");
    }


    @Test
    public void runSingleUserTest()
    {
        Response response = UsersRequest.performGet(UsersTestConstants.USER_ID);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Received status code is not " + HttpStatus.SC_OK);

        Response responseFromPrevStep = UsersRequest.performGet();
        List<User> users = HttpRequests.convertResponseToListOfModels(responseFromPrevStep, UsersTestConstants.USERS_JSON_PATH, User.class);
        User neededUserFromPrevStep = users.stream()
                                        .filter(user -> (user.getId() == UsersTestConstants.USER_ID))
                                        .findAny()
                                        .orElse(null);
        User receivedUser = HttpRequests.convertResponseToModel(response, User.class);
        Assert.assertTrue(receivedUser.equals(neededUserFromPrevStep), "Received user does not match the user from the previous step");
    }
}
