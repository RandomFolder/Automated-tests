package my_tests.pages.forms;

import java.util.List;
import org.openqa.selenium.By;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class UserLoginDataForm extends Form
{
    private final ITextBox passwordTextBox = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Choose Password']"), "Password");
    private final ITextBox usernameTextBox = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Your email']"), "Username");
    private final ITextBox domainTextBox = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Domain']"), "Domain");
    private final IButton dropdownOpener = getElementFactory().getButton(By.xpath("//span[@class='icon icon-chevron-down']"), "Dropdown opener");
    private final IComboBox comboBox = getElementFactory().getComboBox(By.xpath("//div[@class='dropdown__list']"), "Combo box");
    private final IButton nextButton = getElementFactory().getButton(By.xpath("//a[@class='button--secondary']"), "Next");
    private final ICheckBox checkBox = getElementFactory().getCheckBox(By.xpath("//span[@class='checkbox']"), "Check box");
    private final String dropDownListOptionsXPath = "//div[contains(@class, 'dropdown__list-item') and not(contains(@class, 'selected'))]";


    public UserLoginDataForm()
    {
        super(By.xpath("//input[@placeholder='Choose Password']"), "User login data form");
    }


    public void enterUsername(String username)
    {
        this.usernameTextBox.clearAndType(username);
    }


    public void enterDomain(String domain)
    {
        this.domainTextBox.clearAndType(domain);
    }


    public void clickDropdownOpenerAndMoveCursorToCombobox()
    {
        this.dropdownOpener.click();
        this.comboBox.getMouseActions().moveMouseToElement();
    }


    public void selectComboboxValue(int valueIndex)
    {
        List<IElement> options = getElementFactory().findElements(By.xpath(this.dropDownListOptionsXPath), ElementType.BUTTON);
        options.get(valueIndex).click();
    }


    public int getComboboxValuesCount()
    {
        return getElementFactory().findElements(By.xpath(this.dropDownListOptionsXPath), ElementType.BUTTON).size();
    }


    public void enterPassword(String password)
    {
        this.passwordTextBox.clearAndType(password);
    }


    public void unmarkCheckbox()
    {
        this.checkBox.click();
    }


    public void pressNextButton()
    {
        this.nextButton.click();
    }
}
