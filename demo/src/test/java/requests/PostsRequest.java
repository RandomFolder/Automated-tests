package requests;

import constants.SharedConstants;
import io.restassured.response.Response;
import task3.utils.HttpRequests;

public class PostsRequest
{
    private static final String REQUEST = "/posts";

    public static Response performGet()
    {
        return HttpRequests.getRequest(SharedConstants.URL, REQUEST);
    }


    public static Response performGet(int id)
    {
        return HttpRequests.getRequest(SharedConstants.URL, REQUEST + "/" + id);
    }


    public static Response performPost(String requestBody)
    {
        return HttpRequests.postRequest(SharedConstants.URL, REQUEST, requestBody, SharedConstants.JSON_CONTENT_TYPE);
    }
}