package pages.forms;

import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;

public class ResourcesActionsForm extends Form {
    private final IButton versionsButton = getElementFactory().getButton(By.xpath("//div[@value='versions']"), "Versions");

    public ResourcesActionsForm() {
        super(By.xpath("//div[@class='resources-actions-popup__wrapper']"), "Popup form");
    }

    public void clickVersionsButton() {
        this.versionsButton.click();
    }
}
