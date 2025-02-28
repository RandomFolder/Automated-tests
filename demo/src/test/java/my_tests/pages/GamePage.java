package my_tests.pages;

import org.openqa.selenium.By;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import my_tests.pages.forms.AcceptCookiesForm;
import my_tests.pages.forms.HelpForm;
import task1.utils.WaitHandler;

public class GamePage extends Form
{
    private final ILabel timer = getElementFactory().getLabel(By.xpath("//div[contains(@class, 'timer')]"), "Timer");
    private final AcceptCookiesForm acceptCookiesForm = new AcceptCookiesForm();
    private final HelpForm helpForm = new HelpForm();
    
    
    public GamePage()
    {
        super(By.xpath("//div[contains(@class, 'timer')]"), "Game page");
    }


    public String getTimerCurrentTime()
    {
        return this.timer.getText();
    }


    public void waitForAcceptCookiesForm(int timeout, int pollingInterval)
    {
        WaitHandler.waitForTrue(() -> this.acceptCookiesForm.state().waitForDisplayed(), timeout, pollingInterval, "Cookies have to be accepted");
    }


    public AcceptCookiesForm getAcceptCookiesForm()
    {
        return this.acceptCookiesForm;
    }

    public HelpForm getHelpForm()
    {
        return this.helpForm;
    }
}
