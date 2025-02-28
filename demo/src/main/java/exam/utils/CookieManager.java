package exam.utils;

import org.openqa.selenium.Cookie;
import aquality.selenium.browser.AqualityServices;

public class CookieManager {
    public static void addCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        AqualityServices.getBrowser().getDriver().manage().addCookie(cookie);
    }
}
