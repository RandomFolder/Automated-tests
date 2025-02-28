package session;

import java.sql.Timestamp;

import constants.SharedConstants;
import databases.SessionDB;
import models.SessionRecord;
import task5.utils.RandomDataGenerator;

public class SessionManager {
    private static SessionManager sessionManager = null;
    private Long sessionId = null;

    private SessionManager() {

    }

    private static void createSession() {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        String sessionKey = currentTime.toString();
        int buildNumber = RandomDataGenerator.generateRandomNumber(SharedConstants.MAX_BUILD_NUMBER);

        sessionManager = new SessionManager();
        SessionRecord session = new SessionRecord(sessionKey, currentTime, buildNumber);
        sessionManager.sessionId = (new SessionDB()).execute(SharedConstants.SESSION_DB_INSERT_REQUEST, session.sessionKey(), session.createdTime(), session.buildNumber());
    }

    public static SessionManager getSessionManager() {
        if (sessionManager == null) {
            createSession();
            return sessionManager;
        }
        return sessionManager;
    }

    public static void clearSession() {
        sessionManager = null;
    }

    public Long getSessionId() {
        return (sessionManager != null) ? sessionManager.sessionId : null;
    }
}
