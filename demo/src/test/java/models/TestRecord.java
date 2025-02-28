package models;

import java.sql.Timestamp;

public record TestRecord(String name, Integer status, String method, Long projectID, Long sessionID, Timestamp startTime, Timestamp endTime, String env, String browser, Long authorID) {
    public TestRecord withStartTime(Timestamp newStartTime) {
        return new TestRecord(name, status, method, projectID, sessionID, newStartTime, endTime, env, browser, authorID);
    }

    public TestRecord withEndTime(Timestamp newEndTime) {
        return new TestRecord(name, status, method, projectID, sessionID, startTime, newEndTime, env, browser, authorID);
    }
}
