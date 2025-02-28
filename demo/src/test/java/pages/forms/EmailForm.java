package pages.forms;

import org.openqa.selenium.By;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;

public class EmailForm extends Form {
    private ITextBox emailTextBox = getElementFactory().getTextBox(By.xpath("//input[@id='passp-field-login']"), "Enter email");
    private IButton signInButton = getElementFactory().getButton(By.xpath("//button[@id='passp:sign-in']"), "Sign in");

    public EmailForm() {
        super(By.xpath("//input[@id='passp-field-login']"), "Email form");
    }

    public void enterEmail(String email) {
        this.emailTextBox.clearAndType(email);
    }

    public void signIn() {
        this.signInButton.click();
    }
}
