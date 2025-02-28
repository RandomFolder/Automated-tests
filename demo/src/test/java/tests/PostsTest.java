package tests;

import java.util.ArrayList;
import java.util.Random;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import constants.PostsTestConstants;
import constants.SharedConstants;
import io.restassured.response.Response;
import models.Post;
import requests.PostsRequest;
import task3.utils.DataHandler;
import task3.utils.HttpRequests;
import task3.utils.JSONHandler;
import task3.utils.RandomDataGenerator;

public class PostsTest
{
    @Test
    public void runAllMessagesTest()
    {
        Response response = PostsRequest.performGet();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "The received status code is not " + HttpStatus.SC_OK);
        Assert.assertTrue(response.contentType().contains(SharedConstants.JSON_CONTENT_TYPE), "Response content type is not json");

        ArrayList<Integer> idValues = response.jsonPath().get(PostsTestConstants.ID_PARAMETER_PATH);
        Assert.assertTrue(DataHandler.isIntsArraySorted(idValues), "posts are not sorted in the ascending order");
    }


    @Test
    public void runSingleMessageTest()
    {
        Response response = PostsRequest.performGet(PostsTestConstants.POST_SEARCH_ID);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "The received status code is not " + HttpStatus.SC_OK);

        Post post = HttpRequests.convertResponseToModel(response, Post.class);
        Assert.assertEquals(post.getUserId(), PostsTestConstants.POST_SEARCH_USER_ID, "The received user ID is not " + PostsTestConstants.POST_SEARCH_USER_ID);
        Assert.assertEquals(post.getId(), PostsTestConstants.POST_SEARCH_ID, "The received ID is not " + PostsTestConstants.POST_SEARCH_ID);
        Assert.assertNotEquals(post.getTitle(), PostsTestConstants.EMPTY_STRING, "Title is empty");
        Assert.assertNotEquals(post.getBody(), PostsTestConstants.EMPTY_STRING, "Body is empty");
    }


    @Test
    public void runNonexistentMessageTest()
    {
        Response response = PostsRequest.performGet(PostsTestConstants.NONEXISTENT_POST_ID);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Received status code is not " + HttpStatus.SC_NOT_FOUND);
        Assert.assertEquals(response.asString(), PostsTestConstants.EMPTY_RESPONSE_BODY, "Received body is not " + PostsTestConstants.EMPTY_RESPONSE_BODY);
    }


    @Test
    public void runAddPostTest()
    {
        String randomTitle = RandomDataGenerator.generateRandomStringFromAlphabet((new Random()).nextInt(PostsTestConstants.MAX_STRING_LENGTH), PostsTestConstants.ALPHABET);
        String randomBody = RandomDataGenerator.generateRandomStringFromAlphabet((new Random()).nextInt(PostsTestConstants.MAX_STRING_LENGTH), PostsTestConstants.ALPHABET);
        Post post = Post.createMessage(PostsTestConstants.NEW_POST_USER_ID, randomTitle, randomBody);
        String requestBody = JSONHandler.objectToJSONString(post);

        Response response = PostsRequest.performPost(requestBody);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED, "Received status code is not " + HttpStatus.SC_CREATED);

        Post responseMessage = HttpRequests.convertResponseToModel(response, Post.class);
        Assert.assertEquals(responseMessage.getUserId(), post.getUserId(), "Response userId is not equal to message userId");
        Assert.assertEquals(responseMessage.getTitle(), post.getTitle(), "Response title is not equal to message title");
        Assert.assertEquals(responseMessage.getBody(), post.getBody(), "Response body is not equal to message body");
        Assert.assertNotNull(responseMessage.getId(), "ID is empty");
    }
}
