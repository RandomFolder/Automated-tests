package constants;

import models.UserData;
import task4.utils.JSONHandler;

public class AuthorizationStepsConstants {
    public static final String EMAIL;
    public static final String PASSWORD;
    
    static {
        UserData userData = JSONHandler.readFile("src/test/resources/user_data.json", UserData.class);
        EMAIL = userData.getEmail();
        PASSWORD = userData.getPassword();
    }
}
