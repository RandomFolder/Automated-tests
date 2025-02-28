package steps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import constants.DeleteFileStepsConstants;
import constants.SharedConstants;
import io.restassured.response.Response;
import models.FileInfo;
import pages.DiskPage;
import pages.forms.ChangesHistoryForm;
import requests.DiskContentAndDeleteRequest;
import requests.RestoreRequest;
import requests.TrashContentRequest;
import task4.utils.HttpRequests;
import task4.utils.JSONHandler;

public class DeleteFileSteps {
    private static final DiskPage DISK_PAGE = new DiskPage();
    private static final ChangesHistoryForm HISTORY_FORM = new ChangesHistoryForm();

    public static FileInfo getFileMetaData(Map<String, String> parameters) {
        Response response = DiskContentAndDeleteRequest.getMetadata(parameters);
        FileInfo info = HttpRequests.convertResponseToModel(response, FileInfo.class);
        
        return info;
    }

    public static List<FileInfo> getFilesInRecycleBin(Map<String, String> parameters) {
        Response response = TrashContentRequest.getMetadata(parameters);
        FileInfo[] info = JSONHandler.jsonStringToObject(response.asString(), DeleteFileStepsConstants.RECYCLE_BIN_ITEMS_PATH, SharedConstants.PATH_SPLIT, FileInfo[].class);
        List<FileInfo> files = Arrays.asList(info);
        
        return files;
    }

    public static void moveFileToRecycleBin(String fileName) {
        DISK_PAGE.moveFileToTrash(fileName);
        DISK_PAGE.waitForNotification();
    }

    public static void restoreFile(String pathToFile) {
        RestoreRequest.recoverFile(pathToFile);
    }

    public static Response deleteFile(Map<String, String> deleteFileParameters) {
        Response currentResponse = DiskContentAndDeleteRequest.deleteFile(deleteFileParameters);
        
        return currentResponse;
    }

    public static boolean fileIsGoneAfterDeletion(String name) {
        DISK_PAGE.openChangesHistory(name);

        boolean result = HISTORY_FORM.fileDoesNotExist();
        HISTORY_FORM.closeForm();

        DISK_PAGE.goToTrashCan();
        result = !DISK_PAGE.waitForFilePresent(name);

        return result;
    }
}
