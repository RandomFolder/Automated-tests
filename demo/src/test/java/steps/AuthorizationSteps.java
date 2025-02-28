package steps;

import pages.DiskPage;
import pages.HomePage;
import pages.forms.EmailForm;
import pages.forms.PasswordForm;

public class AuthorizationSteps {
    private static final HomePage HOME_PAGE = new HomePage();
    private static final EmailForm EMAIL_FORM = new EmailForm();
    private static final PasswordForm PASSWORD_FORM = new PasswordForm();
    private static final DiskPage DISK_PAGE = new DiskPage();

    public static void authorize(String email, String password) {
        HOME_PAGE.startLogin();

        EMAIL_FORM.enterEmail(email);
        EMAIL_FORM.signIn();

        PASSWORD_FORM.enterPassword(password);
        PASSWORD_FORM.signIn();
    }

    public static void waitForDiskPageToLoad() {
        DISK_PAGE.waitForItemsListPresent();
    }
}
