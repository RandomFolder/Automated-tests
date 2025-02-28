package pages.forms;

import org.openqa.selenium.By;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class PasswordForm extends Form {
    private ITextBox passwordTextBox = getElementFactory().getTextBox(By.xpath("//input[@id='passp-field-passwd']"), "Enter password");
    private IButton continueButton = getElementFactory().getButton(By.xpath("//button[@id='passp:sign-in']"), "Continue");

    public PasswordForm() {
        super(By.xpath("//input[@id='passp-field-passwd']"), "Password form");
    }

    public void enterPassword(String password) {
        this.passwordTextBox.clearAndType(password);
    }

    public void signIn() {
        this.continueButton.click();
    }
}
