package my_tests.pages.forms;

import org.openqa.selenium.By;
import aquality.selenium.forms.Form;

public class PersonalDetailsForm extends Form
{
    public PersonalDetailsForm()
    {
        super(By.xpath("//div[@class='slider__handle']"), "Personal details form");
    }    
}
