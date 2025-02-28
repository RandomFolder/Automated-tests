package session;

import java.sql.Timestamp;

public class SessionManager {
    private static SessionManager sessionManager = null;
    private String sessionId = null;

    private SessionManager() {

    }

    private static void createSession() {
        sessionManager = new SessionManager();
        sessionManager.sessionId = new Timestamp(System.currentTimeMillis()).toString();
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

    public String getSessionId() {
        return (sessionManager != null) ? sessionManager.sessionId : null;
    }
}
