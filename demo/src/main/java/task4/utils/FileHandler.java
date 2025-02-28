package task4.utils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;

public class FileHandler {
    public static File createTextFile(String path, String text, String pathSplit) {
        try {
            String[] pathNodes = path.split(pathSplit);
            String currentPath = pathNodes[0];
            File file = new File(currentPath);

            for (int i = 0; i < pathNodes.length - 1; i++) {
                if (!file.exists()) {
                    file.mkdir();
                }
                currentPath += pathSplit + pathNodes[i + 1];
                file = new File(currentPath);
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.append(text);
            
            writer.close();

            return file;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String readTextFile(String path) {
        try {
            String result = "";

            BufferedReader reader = new BufferedReader(new FileReader(path));
            String currentLine = reader.readLine();

            while (currentLine != null) {
                result += currentLine;
                currentLine = reader.readLine();
            }
            reader.close();

            return result;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteDirectory(String pathToDirectory) {
        try {
            FileUtils.deleteDirectory(new File(pathToDirectory));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean imagesAreEqual(File fileA, File fileB) {        
        try {
            BufferedImage biA = ImageIO.read(fileA);
            DataBuffer dbA = biA.getData().getDataBuffer();
            int sizeA = dbA.getSize();
                                  
            BufferedImage biB = ImageIO.read(fileB);
            DataBuffer dbB = biB.getData().getDataBuffer();
            int sizeB = dbB.getSize();
            
            if(sizeA == sizeB) {
                for(int i=0; i<sizeA; i++) { 
                    if(dbA.getElem(i) != dbB.getElem(i)) {
                        return false;
                    }
                }
                return true;
            }
            else {
                return false;
            }
        } 
        catch (Exception e) { 
            e.printStackTrace();
            return  false;
        }
    }
}
