package pages.forms;

import org.openqa.selenium.By;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;

public class ImageForm extends Form {
    private final IButton downloadButton = getElementFactory().getButton(By.xpath("//button[contains(@class, 'button_name_download')]"), "Download");
    private final IButton closeButton = getElementFactory().getButton(By.xpath("//button[contains(@class, '__button_close')]"), "Close");

    public ImageForm() {
        super(By.xpath("//img[@class='scalable-preview__image']"), "Image form");
    }

    public void downloadImage() {
        this.downloadButton.click();
    }

    public void closeForm() {
        this.closeButton.click();
    }
}
