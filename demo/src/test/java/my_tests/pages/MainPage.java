package my_tests.pages;

import org.openqa.selenium.By;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;


public class MainPage extends Form
{
    private final ILink link = getElementFactory().getLink(By.xpath("//a[@class='start__link']"), "click HERE to GO to the next page");


    public MainPage()
    {
        super(By.xpath("//button[@class='start__button']"), "Main page");
    }
    
    
    public void goToNextPage()
    {
        this.link.click();
    }
}
