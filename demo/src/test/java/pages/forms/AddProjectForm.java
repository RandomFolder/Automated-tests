package pages.forms;

import org.openqa.selenium.By;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class AddProjectForm extends Form {
    private final IButton saveButton = getElementFactory().getButton(By.xpath("//button[@type='submit']"), "Save project");
    private final ILabel successLabel = getElementFactory().getLabel(By.xpath("//div[@class='alert alert-success']"), "Success label");
    private final ITextBox textBox = getElementFactory().getTextBox(By.xpath("//input[@id='projectName']"), "Project name field");

    public AddProjectForm() {
        super(By.xpath("//div[@class='modal-backdrop fade in']"), "Add project form");
    }

    public void enterProjectName(String name) {
        this.textBox.clearAndType(name);
    }

    public void clickSaveButton() {
        this.saveButton.click();
    }

    public boolean projectGotCreated() {
        return this.successLabel.state().waitForDisplayed();
    }
}
