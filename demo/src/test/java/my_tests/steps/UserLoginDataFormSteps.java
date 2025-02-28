package my_tests.steps;

import my_tests.pages.forms.UserLoginDataForm;
import task1.utils.RandomDataGenerator;

public class UserLoginDataFormSteps
{
    private final UserLoginDataForm userLoginDataForm = new UserLoginDataForm();
    
    public void login(String emailUsername, String emailDomain, String password)
    {
        this.userLoginDataForm.enterUsername(emailUsername);
        this.userLoginDataForm.enterDomain(emailDomain);
        this.userLoginDataForm.clickDropdownOpenerAndMoveCursorToCombobox();
        this.userLoginDataForm.selectComboboxValue(RandomDataGenerator.generateRandomNumber(this.userLoginDataForm.getComboboxValuesCount()));
        this.userLoginDataForm.enterPassword(password);
    }
}
