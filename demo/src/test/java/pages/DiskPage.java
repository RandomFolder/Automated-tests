package pages;

import org.openqa.selenium.By;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import pages.forms.ResourcesActionsForm;
import task4.utils.WebElementHandler;

public class DiskPage extends Form {
    private final String fileXPath = "//div[@aria-label='%s']";
    private final String iconXPath = "//div[.//div[@aria-label='%s'] and @role='row']//div[@class='listing-item__fields']";
    private final String allFilesXPath = "//div[contains(@class, 'listing-item listing-item_theme_tile listing-item_size_m')]";
    private final IButton itemsContainer = getElementFactory().getButton(By.xpath("//div[@class='listing__items']"), "Items list");
    private final ILabel notification = getElementFactory().getLabel(By.xpath("//div[@data-test-id='notification']"), "Notification");
    private final IButton recycleBin = getElementFactory().getButton(By.xpath("//div[contains(@class, 'prevent-drag')]"), "Recycle bin");

    public DiskPage() {
        super(By.xpath("//span[@class='create-resource-popup-with-anchor']"), "Disk page");
    }

    public boolean waitForFilePresent(String fileName) {
        IButton file = getElementFactory().getButton(By.xpath(String.format(this.fileXPath, fileName)), fileName);
        return file.state().waitForDisplayed();
    }

    public boolean waitForFileNotPresent(String fileName) {
        IButton file = getElementFactory().getButton(By.xpath(String.format(this.fileXPath, fileName)), fileName);
        return file.state().waitForNotDisplayed();
    }

    public boolean waitForItemsListPresent() {
        return this.itemsContainer.state().waitForDisplayed();
    }

    public void waitForNotification() {
        this.notification.state().waitForDisplayed();
    }

    public void openChangesHistory(String fileName) {
        IButton file = getElementFactory().getButton(By.xpath(String.format(this.fileXPath, fileName)), fileName);
        file.getMouseActions().rightClick();
        (new ResourcesActionsForm()).clickVersionsButton();
    }

    public void goToTrashCan() {
        this.recycleBin.getMouseActions().doubleClick();
    }

    public void clickOnFile(String fileName) {
        IButton file = getElementFactory().getButton(By.xpath(String.format(this.fileXPath, fileName)), fileName);
        file.getMouseActions().doubleClick();
    }

    public int getFilesCount() {
        return getElementFactory().findElements(By.xpath(this.allFilesXPath), ElementType.BUTTON).size();
    }

    public void moveFileToTrash(String fileName) {
        IButton file = getElementFactory().getButton(By.xpath(String.format(this.iconXPath, fileName)), fileName);
        WebElementHandler.dragAndDrop(file, this.recycleBin);
    }
}
