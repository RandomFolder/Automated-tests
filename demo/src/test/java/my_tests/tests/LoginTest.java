package my_tests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import my_tests.constants.LoginTestConstants;
import my_tests.constants.SharedConstants;
import my_tests.pages.MainPage;
import my_tests.pages.forms.PersonalDetailsForm;
import my_tests.pages.forms.ThisIsMeForm;
import my_tests.pages.forms.UserLoginDataForm;
import my_tests.steps.ThisIsMeFormSteps;
import my_tests.steps.UserLoginDataFormSteps;
import task1.utils.RandomDataGenerator;

public class LoginTest extends BaseTest
{
    @Test
    public void runLoginTest()
    {
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main page wasn't found");
        mainPage.goToNextPage();

        UserLoginDataForm userLoginDataForm = new UserLoginDataForm();
        Assert.assertTrue(userLoginDataForm.state().waitForDisplayed(), "User login data page didn't get opened");

        String emailUsername = RandomDataGenerator.generateRandomStringFromAlphabet(LoginTestConstants.USERNAME_LENGTH, LoginTestConstants.SMALL_ENGLISH_LETTERS);
        String emailDomain = RandomDataGenerator.generateRandomStringFromAlphabet(LoginTestConstants.DOMAIN_LENGTH, LoginTestConstants.SMALL_ENGLISH_LETTERS);
        String password = RandomDataGenerator.generateRandomStringFromAlphabets(LoginTestConstants.PASSWORD_LENGTH, LoginTestConstants.BIG_ENGLISH_LETTERS, LoginTestConstants.SMALL_ENGLISH_LETTERS, LoginTestConstants.NUMBERS, LoginTestConstants.CYRILLIC_LETTERS, emailUsername + emailDomain);

        UserLoginDataFormSteps userLoginDataFormSteps = new UserLoginDataFormSteps();
        userLoginDataFormSteps.login(emailUsername, emailDomain, password);
        userLoginDataForm.unmarkCheckbox();
        userLoginDataForm.pressNextButton();

        ThisIsMeForm thisIsMeForm = new ThisIsMeForm();
        Assert.assertTrue(thisIsMeForm.state().waitForDisplayed(), "This is me form didn't get opened");

        ThisIsMeFormSteps thisIsMeFormSteps = new ThisIsMeFormSteps();
        thisIsMeFormSteps.waitForFileDownload(LoginTestConstants.DOWNLOADED_FILE_NAME, SharedConstants.DEFAULT_TIMEOUT, SharedConstants.POLLING_INTERVAL);
        thisIsMeFormSteps.selectInterests(LoginTestConstants.INTERESTS_COUNT);
        thisIsMeFormSteps.uploadImageAndWait(LoginTestConstants.DOWNLOADED_FILE_NAME, SharedConstants.DEFAULT_TIMEOUT, SharedConstants.POLLING_INTERVAL);
        thisIsMeForm.goToNextPage();

        PersonalDetailsForm personalDetailsForm = new PersonalDetailsForm();
        Assert.assertTrue(personalDetailsForm.state().waitForDisplayed(), "Personal details form didn't get displayed");
    }
}
