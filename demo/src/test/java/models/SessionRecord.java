package models;

import java.sql.Timestamp;

public record SessionRecord(String sessionKey, Timestamp createdTime, int buildNumber) {
    
}
