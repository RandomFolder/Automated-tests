package task4.utils;

import java.time.Duration;
import java.util.concurrent.TimeoutException;
import java.util.function.BooleanSupplier;
import aquality.selenium.browser.AqualityServices;

public class WaitHandler {
    public static boolean waitForTrue(BooleanSupplier condition, int timeout, int pollingInterval) {
        try {
            AqualityServices.getConditionalWait().waitForTrue(condition,
                Duration.ofSeconds(timeout), Duration.ofMillis(pollingInterval));
            
            return true;
        }
        catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
    }    
}
