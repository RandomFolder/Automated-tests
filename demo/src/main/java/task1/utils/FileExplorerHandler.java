package task1.utils;

import org.apache.commons.io.FileUtils;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class FileExplorerHandler
{
    public static void selectFile(String path)
    {
        try
        {
            Robot robot = new Robot();

            StringSelection str = new StringSelection(path);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
            robot.delay(500);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
        
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.delay(500);
            
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            robot.waitForIdle();
        }
        catch (AWTException e)
        {
            e.printStackTrace();
        }
    }


    public static void deleteDirectory(String pathToDirectory)
    {
        try
        {
            FileUtils.deleteDirectory(new File(pathToDirectory));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
