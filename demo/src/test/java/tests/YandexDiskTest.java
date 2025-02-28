package tests;

import org.testng.annotations.Test;
import constants.UploadFileStepsConstants;
import constants.AuthorizationStepsConstants;
import constants.DeleteFileStepsConstants;
import constants.RenameFileStepsConstants;
import constants.SharedConstants;
import io.restassured.response.Response;
import models.FileInfo;
import steps.AuthorizationSteps;
import steps.DeleteFileSteps;
import steps.RenameFileSteps;
import steps.UploadFileSteps;
import task4.utils.FileHandler;
import task4.utils.RandomDataGenerator;
import java.io.File;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.Assert;

public class YandexDiskTest extends BaseTest {
    @Test
    public void runYandexDiskTest() {
        AuthorizationSteps.authorize(AuthorizationStepsConstants.EMAIL, AuthorizationStepsConstants.PASSWORD);
        AuthorizationSteps.waitForDiskPageToLoad();

        int prevFilesCount = UploadFileSteps.getCurrentFilesCount();
        Response textFileUploadResponse = UploadFileSteps.uploadTextFile(UploadFileStepsConstants.TEXT_FILE_PATH,
                                                                        RandomDataGenerator.generateRandomStringFromAlphabet(SharedConstants.TEXT_LENGTH, SharedConstants.ALPHABET),
                                                                        UploadFileStepsConstants.UPLOAD_TEXT_PARAMETERS);

        Assert.assertEquals(textFileUploadResponse.getStatusCode(), HttpStatus.SC_CREATED, "Received status code is not " + HttpStatus.SC_ACCEPTED);
        Assert.assertTrue(UploadFileSteps.fileExists(SharedConstants.TEXT_FILE_NAME), "File with name " + SharedConstants.TEXT_FILE_NAME + " wasn't added");

        int newFilesCount = UploadFileSteps.getCurrentFilesCount();
        Assert.assertNotEquals(prevFilesCount, newFilesCount, "Files count didn't change");
        
        String diskFileText = UploadFileSteps.getTextFileContent(SharedConstants.TEXT_FILE_NAME);
        File localFile = new File(UploadFileStepsConstants.TEXT_FILE_PATH);
        String localFileText = FileHandler.readTextFile(localFile.getAbsolutePath());

        Assert.assertEquals(diskFileText, localFileText, "Texts in remote and local files are different");

        Response renameResponse = RenameFileSteps.renameFile(RenameFileStepsConstants.RENAME_PARAMETERS);
        Assert.assertEquals(renameResponse.getStatusCode(), HttpStatus.SC_CREATED, "Received status code is not " + HttpStatus.SC_CREATED);
        Assert.assertTrue(UploadFileSteps.fileExists(SharedConstants.IMAGE_FILE_NAME), "File's extension didn't get changed");
        Assert.assertTrue(UploadFileSteps.fileDoesNotExist(SharedConstants.TEXT_FILE_NAME), "Previous file still exists");

        Response uploadImageResponse = UploadFileSteps.uploadImageFile(UploadFileStepsConstants.IMAGE_PATH, UploadFileStepsConstants.UPLOAD_IMAGE_PARAMETERS);
        Assert.assertEquals(uploadImageResponse.getStatusCode(), HttpStatus.SC_CREATED, "Received status code is not " + HttpStatus.SC_CREATED);

        File downloadedImage = UploadFileSteps.openUploadedImageFile(SharedConstants.IMAGE_FILE_NAME);
        Assert.assertTrue(FileHandler.imagesAreEqual(downloadedImage, new File(UploadFileStepsConstants.IMAGE_PATH)), "images are not the same");
        UploadFileSteps.closeOpenedImage();

        FileInfo deletedFileInfo = DeleteFileSteps.getFileMetaData(DeleteFileStepsConstants.IMAGE_FILE_INFO_PARAMETERS);
        DeleteFileSteps.moveFileToRecycleBin(SharedConstants.IMAGE_FILE_NAME);

        List<FileInfo> files = DeleteFileSteps.getFilesInRecycleBin(DeleteFileStepsConstants.RECYCLE_BIN_INFO_PARAMETERS);
        FileInfo fileInRecycleBin = files.stream()
                    .filter(f -> (f.getName().equals(deletedFileInfo.getName()) && f.getCreated().equals(deletedFileInfo.getCreated())))
                    .findFirst()
                    .orElse(null);
        Assert.assertNotNull(fileInRecycleBin, "file wasn't found in recycle bin");

        DeleteFileSteps.restoreFile(fileInRecycleBin.getPath());
        Assert.assertTrue(UploadFileSteps.fileExists(SharedConstants.IMAGE_FILE_NAME));

        Response deleteResponse = DeleteFileSteps.deleteFile(DeleteFileStepsConstants.REMOVE_FILE_PARAMETERS);
        Assert.assertEquals(deleteResponse.getStatusCode(), HttpStatus.SC_NO_CONTENT, "Received status code is not " + HttpStatus.SC_NO_CONTENT);
        Assert.assertTrue(DeleteFileSteps.fileIsGoneAfterDeletion(SharedConstants.IMAGE_FILE_NAME), "file still exists");
    }
}
