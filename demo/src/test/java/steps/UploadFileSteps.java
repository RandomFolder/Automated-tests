package steps;

import java.io.File;
import java.util.Map;
import aquality.selenium.browser.AqualityServices;
import constants.RenameFileStepsConstants;
import constants.SharedConstants;
import io.restassured.response.Response;
import pages.DiskPage;
import pages.FilePage;
import pages.forms.ImageForm;
import requests.MoveRequest;
import requests.UploadRequest;
import task4.utils.FileHandler;
import task4.utils.WaitHandler;
import task4.utils.WebElementHandler;

public class UploadFileSteps {
    private static final DiskPage DISK_PAGE = new DiskPage();
    private static final ImageForm IMAGE_FORM = new ImageForm();

    public static int getCurrentFilesCount() {
        return DISK_PAGE.getFilesCount();
    }

    public static Response uploadTextFile(String filePath, String text, Map<String, String> uploadParameters) {
        File textFile = FileHandler.createTextFile(filePath, text, SharedConstants.PATH_SPLIT);
        Response response = UploadRequest.uploadFile(textFile, uploadParameters);

        return response;
    }

    public static Response uploadImageFile(String filePath, Map<String, String> uploadParameters) {
        File imageFile = new File(filePath);
        Response response = UploadRequest.uploadFile(imageFile, uploadParameters);
        MoveRequest.moveFile(RenameFileStepsConstants.UPDATE_PARAMETERS);
        DISK_PAGE.waitForFilePresent(RenameFileStepsConstants.TEMP_FILE_NAME);
        MoveRequest.moveFile(RenameFileStepsConstants.UPDATE_PARAMETERS_REVERSE);
        DISK_PAGE.waitForFilePresent(SharedConstants.IMAGE_FILE_NAME);

        return response;
    }

    public static File openUploadedImageFile(String fileName) {
        DISK_PAGE.clickOnFile(fileName);
        IMAGE_FORM.downloadImage();
        File file = new File(AqualityServices.getBrowser().getDownloadDirectory() + SharedConstants.PATH_SPLIT + fileName);
        WaitHandler.waitForTrue(() -> file.exists() && file.canRead(), SharedConstants.TIMEOUT, SharedConstants.POLLING_INTERVAL);

        return file;
    }

    public static void closeOpenedImage() {
        IMAGE_FORM.closeForm();
    }

    public static boolean fileExists(String name) {
        return DISK_PAGE.waitForFilePresent(name);
    }

    public static boolean fileDoesNotExist(String name) {
        return DISK_PAGE.waitForFileNotPresent(name);
    }

    public static String getTextFileContent(String fileName) {
        DISK_PAGE.clickOnFile(fileName);
        AqualityServices.getBrowser().tabs().switchToLastTab();

        String content = WebElementHandler.elementsTextToString((new FilePage()).getTextLines());
        AqualityServices.getBrowser().tabs().closeTab();
        AqualityServices.getBrowser().tabs().switchToLastTab();

        return content;
    }
}
