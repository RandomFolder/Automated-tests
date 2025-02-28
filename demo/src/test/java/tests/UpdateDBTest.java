package tests;

import java.sql.Timestamp;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import constants.DatabaseFieldNames;
import constants.SharedConstants;
import constants.UpdateDBConstants;
import databases.AuthorDB;
import databases.ProjectDB;
import databases.TestDB;
import models.ProjectRecord;
import models.TestRecord;
import session.SessionManager;
import task5.utils.RandomDataGenerator;
import task5.utils.SystemInfo;
import task5.utils.TimeHandler;

public class UpdateDBTest extends BaseTest {
    private List<Long> idValues;

    @BeforeClass
    public void copyTests() {
        ProjectDB projectDB = new ProjectDB();
        AuthorDB authorDB = new AuthorDB();

        int randomDigit = RandomDataGenerator.generateRandomNumber(UpdateDBConstants.MAX_DIGIT);
        TestDB testDB = new TestDB();

        ProjectRecord project = new ProjectRecord(SharedConstants.PROJECT_NAME);
        Long currentProjectId = projectDB.selectOrAddIfNotPresent(DatabaseFieldNames.NAME, SharedConstants.PROJECT_NAME, SharedConstants.PROJECT_DB_INSERT_REQUEST,
                                                                    project.name());

        Long currentAuthorId = authorDB.selectOrAddIfNotPresent(DatabaseFieldNames.LOGIN, SharedConstants.AUTHOR.login(), SharedConstants.AUTHOR_DB_INSERT_REQUEST,
                                                                    SharedConstants.AUTHOR.name(),
                                                                    SharedConstants.AUTHOR.login(),
                                                                    SharedConstants.AUTHOR.email());

        this.idValues = testDB.selectIdValuesThatIncludeDigitMultipleTimes(randomDigit);
        for (int i = 0; i < idValues.size(); i++) {
            TestRecord record = testDB.selectById(idValues.get(i));

            TestRecord newRecord = new TestRecord(
                record.name(),
                record.status(),
                record.method(),
                currentProjectId,
                record.sessionID(),
                record.startTime(),
                record.endTime(),
                record.env(),
                record.browser(),
                currentAuthorId
            );
            idValues.set(i, testDB.execute(SharedConstants.TEST_DB_INSERT_REQUEST,
                                                                newRecord.name(),
                                                                newRecord.status(),
                                                                newRecord.method(),
                                                                newRecord.projectID(),
                                                                newRecord.sessionID(),
                                                                newRecord.startTime(),
                                                                newRecord.endTime(),
                                                                newRecord.env(),
                                                                newRecord.browser(),
                                                                newRecord.authorID()));
        }
    }

    @DataProvider
    public Object[][] prepareIdValues() {
        Long[][] result = new Long[this.idValues.size()][1];

        for (int i = 0; i < this.idValues.size(); i++)
        {
            result[i][0] = this.idValues.get(i);
        }

        return result;
    }

    @Test(dataProvider = "prepareIdValues")
    public void runUpdateTest(Long id) {
        TestDB testDB = new TestDB();
        int status;
        Timestamp startTime = new Timestamp(System.currentTimeMillis());

        Boolean result = RandomDataGenerator.generateRandomBoolean();
        TimeHandler.sleep(RandomDataGenerator.generateRandomNumber(SharedConstants.MAX_EXECUTION_TIME));
        Assert.assertNotNull(result, "Test failed");

        status = (result) ? UpdateDBConstants.TEST_SUCCESS : UpdateDBConstants.TEST_FAILURE;

        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        Long sessionId = SessionManager.getSessionManager().getSessionId();
        String env = SystemInfo.getName();

        TestRecord oldValue = testDB.selectById(id);
        TestRecord newValue = new TestRecord(oldValue.name(), status, oldValue.method(), oldValue.projectID(), sessionId, startTime, endTime, env, SharedConstants.BROWSER, oldValue.authorID());
        testDB.execute(UpdateDBConstants.TEST_DB_UPDATE_REQUEST,
                                                    newValue.status(),
                                                    newValue.sessionID(),
                                                    newValue.startTime(),
                                                    newValue.endTime(),
                                                    newValue.env(),
                                                    newValue.browser(),
                                                    id);

        TestRecord receivedValue = testDB.selectById(id);
        if (newValue.startTime().getNanos() >= UpdateDBConstants.NANOS_ROUND_THRESHOLD) {
            Timestamp newTime = TimeHandler.addTime(newValue.startTime(), UpdateDBConstants.ADDITIONAL_SECONDS);
            newTime.setNanos(UpdateDBConstants.TIMESTAMP_NANOS_VALUE);
            newValue = newValue.withStartTime(newTime);
        }
        else {
            newValue.startTime().setNanos(UpdateDBConstants.TIMESTAMP_NANOS_VALUE);
        }

        if (newValue.endTime().getNanos() >= UpdateDBConstants.NANOS_ROUND_THRESHOLD) {
            Timestamp newTime = TimeHandler.addTime(newValue.endTime(), UpdateDBConstants.ADDITIONAL_SECONDS);
            newTime.setNanos(UpdateDBConstants.TIMESTAMP_NANOS_VALUE);
            newValue = newValue.withEndTime(newTime);
        }
        else {
            newValue.endTime().setNanos(UpdateDBConstants.TIMESTAMP_NANOS_VALUE);
        }

        Assert.assertEquals(newValue, receivedValue, "Value didn't get updated the expected way");
    }

    @AfterClass
    public void deleteCopies() {
        TestDB testDB = new TestDB();

        for (int i = 0; i < this.idValues.size(); i++) {
            testDB.execute(UpdateDBConstants.TEST_DB_DELETE_REQUEST, this.idValues.get(i));
        }
    }
}
