package models;

import java.util.List;

public record NewTestInfo(String projectName, String testName, String testMethodName, String env, List<String> logs, String imageAsBase64) {
    
}
