package tests;

import java.sql.Timestamp;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import constants.AddDataToDBConstants;
import constants.DatabaseFieldNames;
import constants.SharedConstants;
import databases.AuthorDB;
import databases.ProjectDB;
import databases.TestDB;
import models.ProjectRecord;
import models.TestRecord;
import session.SessionManager;
import task5.utils.RandomDataGenerator;
import task5.utils.SystemInfo;
import task5.utils.TimeHandler;

public class AddToDBTest extends BaseTest {
    @Test(description = "Do random test")
    public void runAddToDBTest() {
        Boolean result = RandomDataGenerator.generateRandomBoolean();
        TimeHandler.sleep(RandomDataGenerator.generateRandomNumber(SharedConstants.MAX_EXECUTION_TIME));
        Assert.assertNotNull(result, "Test failed");
    }

    @Test(description = "Do random test 2")
    public void runAddToDBSecondTest() {
        Boolean result = RandomDataGenerator.generateRandomBoolean();
        TimeHandler.sleep(RandomDataGenerator.generateRandomNumber(SharedConstants.MAX_EXECUTION_TIME));
        Assert.assertNotNull(result, "Test failed");
    }

    @AfterMethod
    public void addDataToDB(ITestResult result) {
        TestDB testDB = new TestDB();
        AuthorDB authorDB = new AuthorDB();
        ProjectDB projectDB = new ProjectDB();

        Long authorID = authorDB.selectFirstIdByField(DatabaseFieldNames.LOGIN, SharedConstants.AUTHOR.login());
        if (authorID == null) {
            authorID = authorDB.execute(SharedConstants.AUTHOR_DB_INSERT_REQUEST, SharedConstants.AUTHOR.name(), SharedConstants.AUTHOR.login(), SharedConstants.AUTHOR.email());
        }
        
        Long projectID = projectDB.selectFirstIdByField(DatabaseFieldNames.NAME, SharedConstants.PROJECT_NAME);
        if (projectID == null) {
            ProjectRecord project = new ProjectRecord(SharedConstants.PROJECT_NAME);
            projectID = projectDB.execute(SharedConstants.PROJECT_DB_INSERT_REQUEST, project.name());
        }

        String testName = result.getMethod().getDescription();
        int status = result.getStatus();
        Timestamp start = new Timestamp(result.getStartMillis());
        Timestamp end = new Timestamp(result.getEndMillis());
        String className = result.getMethod().getInstance().toString().split(AddDataToDBConstants.CLASS_NAME_SEPARATOR)[0];
        String methodName = result.getName();
        String fullMethodName = className + AddDataToDBConstants.METHOD_NAME_PATH_SEPARATOR + methodName;
        String env = SystemInfo.getName();

        Long sessionID = SessionManager.getSessionManager().getSessionId();

        TestRecord record = new TestRecord(testName, status, fullMethodName, projectID, sessionID, start, end, env, SharedConstants.BROWSER, authorID);
        long newTestId = testDB.execute(SharedConstants.TEST_DB_INSERT_REQUEST,
                                                                record.name(),
                                                                record.status(),
                                                                record.method(),
                                                                record.projectID(),
                                                                record.sessionID(),
                                                                record.startTime(),
                                                                record.endTime(),
                                                                record.env(),
                                                                record.browser(),
                                                                record.authorID());

        Assert.assertNotNull(testDB.selectById(newTestId), "new test wasn't added");
    }
}
