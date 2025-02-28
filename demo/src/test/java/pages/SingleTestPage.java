package pages;

import java.util.List;
import org.openqa.selenium.By;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import constants.SharedConstants;

public class SingleTestPage extends Form {
    private final String parameterXPath = "//div[./h4[text()='%s']]/p";
    private final List<ILabel> logLines = getElementFactory().findElements(By.xpath("//div[./div[text()='Logs']]//tr"), ElementType.LABEL);
    private ILabel image = getElementFactory().getLabel(By.xpath("//a[@target='_blank']"), "Image");

    public SingleTestPage() {
        super(By.xpath("//div[@class='chosen-container chosen-container-single']"), "Test page");
    }

    private ILabel getLabel(String parameterName) {
        return getElementFactory().getLabel(By.xpath(String.format(this.parameterXPath, parameterName)), parameterName);
    }

    public String getParameterValue(String parameterName) {
        ILabel label = this.getLabel(parameterName);
        return label.getText();
    }

    public int getLogLinesCount() {
        return this.logLines.size();
    }

    public String getLogLine(int index) {
        return this.logLines.get(index).getText();
    }

    public String getImageAsBase64String() {
        return (this.image.state().waitForExist()) ? 
            this.image.getAttribute(SharedConstants.REFERENCE_PARAMETER)
            : null;
    }
}
