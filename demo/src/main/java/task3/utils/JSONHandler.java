package task3.utils;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONHandler
{
    public static <T> T readFile(String filepath, Class<T> type)
    {
        try
        {
            File f = new File(filepath);
            ObjectMapper objMapper = new ObjectMapper();
            objMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            T res = objMapper.readValue(f, type);

            return res;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    public static <T> T jsonStringToObject(String jsonString, Class<T> objectType)
    {
        ObjectMapper objMapper = new ObjectMapper();

        try
        {
            return objMapper.readValue(jsonString, objectType);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    public static String objectToJSONString(Object object)
    {
        ObjectMapper objMapper = new ObjectMapper();

        try
        {
            return objMapper.writeValueAsString(object);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
