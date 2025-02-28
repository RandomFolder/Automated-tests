package exam.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SystemInfo {
    public static String getName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }
}
