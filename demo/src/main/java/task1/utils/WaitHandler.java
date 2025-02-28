package task1.utils;

import java.time.Duration;
import java.util.concurrent.TimeoutException;
import java.util.function.BooleanSupplier;
import aquality.selenium.browser.AqualityServices;

public class WaitHandler
{
    public static void waitForTrue(BooleanSupplier condition, int timeout, int pollingInterval, String message)
    {
        try
        {
            AqualityServices.getConditionalWait().waitForTrue(condition,
                Duration.ofSeconds(timeout), Duration.ofMillis(pollingInterval), message);
        }
        catch (TimeoutException e)
        {
            e.printStackTrace();
        }
    }    
}
