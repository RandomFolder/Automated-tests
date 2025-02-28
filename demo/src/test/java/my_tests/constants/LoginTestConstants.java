package my_tests.constants;

import aquality.selenium.browser.AqualityServices;

public class LoginTestConstants
{
    public static final String BIG_ENGLISH_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String SMALL_ENGLISH_LETTERS = "abcdefghijklmnopqrstuwvxyz";
    public static final String NUMBERS = "0123456789";
    public static final String CYRILLIC_LETTERS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    public static final String DOWNLOADED_FILE_NAME = AqualityServices.getBrowser().getDownloadDirectory() + "/avatar.png";
    public static final int USERNAME_LENGTH = 10;
    public static final int DOMAIN_LENGTH = 8;
    public static final int PASSWORD_LENGTH = 10;
    public static final int INTERESTS_COUNT = 3;
}
