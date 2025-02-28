package my_tests.pages.forms;

import org.openqa.selenium.By;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import task1.utils.WaitHandler;

public class HelpForm extends Form
{
    private final ILabel formTitle = getElementFactory().getLabel(By.xpath("//h2[@class='help-form__title']"), "Form title");
    private final IButton hideButton = getElementFactory().getButton(By.xpath("//button[./span[@class='highlight']]"), "Hide button");


    public HelpForm()
    {
        super(By.xpath("//div[@class='help-form__container']"), "Help form");
    }
    
    
    public void pressHideButton()
    {
        this.hideButton.click();
    }


    public void waitUntilFormIsHidden(int timeout, int pollingInterval)
    {
        WaitHandler.waitForTrue(() -> this.formTitle.state().isDisplayed() == false, timeout, pollingInterval, "Form should have disappeared");
    }


    public boolean isTitleVisible()
    {
        return this.formTitle.state().isDisplayed();
    }
}
