package my_tests.constants;

import aquality.selenium.browser.AqualityServices;

public class SharedConstants
{
    public static final String WEB_RESOURCE_PATH = "https://userinyerface.com/";
    public static final int DEFAULT_TIMEOUT = AqualityServices.getConfiguration().getTimeoutConfiguration().getCondition().toSecondsPart();
    public static final int POLLING_INTERVAL = AqualityServices.getConfiguration().getTimeoutConfiguration().getPollingInterval().toMillisPart();
}
