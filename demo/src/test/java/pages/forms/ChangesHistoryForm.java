package pages.forms;

import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;

public class ChangesHistoryForm extends Form {
    private final ILabel errorLabel = getElementFactory().getLabel(By.xpath("//div[@class='versions-list__error-desc']"), "No history found");
    private final IButton closeButton = getElementFactory().getButton(By.xpath("//button[contains(@class, 'versions-dialog__close')]"), "Close button");

    public ChangesHistoryForm() {
        super(By.xpath("//h2[@class='versions-dialog__title']"), "Changes history");
    }

    public boolean fileDoesNotExist() {
        return this.errorLabel.state().waitForDisplayed();
    }

    public void closeForm() {
        this.closeButton.click();
    }
}
