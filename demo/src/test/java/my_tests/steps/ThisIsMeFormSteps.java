package my_tests.steps;

import java.io.File;
import my_tests.pages.forms.ThisIsMeForm;
import task1.utils.FileExplorerHandler;
import task1.utils.RandomDataGenerator;
import task1.utils.WaitHandler;

public class ThisIsMeFormSteps
{
    private final ThisIsMeForm thisIsMeForm = new ThisIsMeForm();
    
    public void waitForFileDownload(String pathToFile, int timeout, int pollingInterval)
    {
        this.thisIsMeForm.clickDownloadButton();
        File fileDownloaded = new File(pathToFile);
        WaitHandler.waitForTrue(() -> fileDownloaded.exists() && fileDownloaded.canRead(), timeout, pollingInterval, "file should exist");
    }


    public void selectInterests(int count)
    {
        this.thisIsMeForm.unselectAll();

        int[] indecies = RandomDataGenerator.generateRandomSequence(count, this.thisIsMeForm.getInterestsCount());
        for (int index : indecies)
        {
            this.thisIsMeForm.selectInterest(index);
        }
    }


    public void uploadImageAndWait(String pathToFile, int timeout, int pollingInterval)
    {
        this.thisIsMeForm.clickUploadButton();
        File uploadFile = new File(pathToFile);
        FileExplorerHandler.selectFile(uploadFile.getAbsolutePath());
        this.thisIsMeForm.waitForImageToAppear(timeout, pollingInterval);
    }
}
