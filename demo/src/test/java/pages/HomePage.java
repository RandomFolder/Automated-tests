package pages;

import org.openqa.selenium.By;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import constants.SharedConstants;
import exam.utils.WebElementsHandler;

public class HomePage extends Form {
    private final ILabel versionLabel = getElementFactory().getLabel(By.xpath("//span"), "Version label");
    private final IButton addProjectButton = getElementFactory().getButton(By.xpath("//div[@class='panel-heading']/*"), "+Add");
    private final String projectXPath = "//a[@class='list-group-item' and text()='%s']";

    public HomePage() {
        super(By.xpath("//div[@class='list-group']"), "Home page");
    }

    public int getVersion() {
        String versionAsText = this.versionLabel.getText().replaceAll(SharedConstants.DIGITS_REGEX, SharedConstants.EMPTY_STRING);
        return Integer.parseInt(versionAsText);
    }

    private IButton getProject(String projectName) {
        return getElementFactory().getButton(By.xpath(String.format(this.projectXPath, projectName)), projectName);
    }

    public int getProjectId(String projectName) {
        IButton projectButton = this.getProject(projectName);

        return Integer.parseInt(WebElementsHandler.divideAttributeIntoSubstringsAndGetThaLastOne(projectButton, SharedConstants.REFERENCE_PARAMETER, SharedConstants.EQUALS_SIGN));
    }

    public void goToProject(String projectName) {
        IButton projectButton = this.getProject(projectName);
        projectButton.click();
    }

    public boolean projectExists(String projectName) {
        IButton projectButton = this.getProject(projectName);

        return projectButton.state().waitForDisplayed();
    }

    public void clickAddButton() {
        this.addProjectButton.click();
    }
}
