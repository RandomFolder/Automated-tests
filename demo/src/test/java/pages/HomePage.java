package pages;

import org.openqa.selenium.By;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;

public class HomePage extends Form {
    private IButton loginButton = getElementFactory().getButton(By.xpath("//a[@id='header-login-button']"), "Start using");

    public HomePage() {
        super(By.xpath("//a[@id='header-login-button']"), "Yandex disk home page");
    }

    public void startLogin() {
        this.loginButton.click();
    }
}
