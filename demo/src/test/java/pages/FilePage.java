package pages;

import java.util.List;
import org.openqa.selenium.By;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;

public class FilePage extends Form {
    private final String textContainerXPath = "//div[@class='__page-1']";
    private final String textLinesCssSelector = "p[class='mg1']";
    private final IButton downloadButton = getElementFactory().getButton(By.xpath("//button[contains(@class, 'download-button')]"), "Download");

    public FilePage() {
        super(By.xpath("//a[@class='logo__link logo__link_yandex']"), "File page");
    }

    public List<IElement> getTextLines() {
        ILabel parentOfShadowRoot = getElementFactory().getLabel(By.xpath(this.textContainerXPath), "Text container");
        return parentOfShadowRoot.getShadowRootElementFactory().findElements(By.cssSelector(this.textLinesCssSelector), ElementType.LABEL);
    }

    public void downloadFile() {
        this.downloadButton.click();
    }
}
