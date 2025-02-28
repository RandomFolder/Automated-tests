package requests;

import constants.SharedConstants;
import io.restassured.response.Response;
import task3.utils.HttpRequests;

public class UsersRequest
{
    private static final String REQUEST = "/users";
    
    public static Response performGet()
    {
        return HttpRequests.getRequest(SharedConstants.URL, REQUEST);
    }


    public static Response performGet(int id)
    {
        return HttpRequests.getRequest(SharedConstants.URL, REQUEST + "/" + id);
    }
}
