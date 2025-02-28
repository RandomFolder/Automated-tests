package my_tests.pages.forms;

import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;

public class AcceptCookiesForm extends Form
{
    private final IButton acceptButton = getElementFactory().getButton(By.xpath("//button[contains(@class, 'transparent')]"), "Not really, no");
    
    public AcceptCookiesForm()
    {
        super(By.xpath("//div[@class='cookies']"), "Accept cookies form");
    }


    public void acceptCookies()
    {
        this.acceptButton.click();
    }
}
