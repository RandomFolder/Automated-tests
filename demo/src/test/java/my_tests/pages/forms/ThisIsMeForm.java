package my_tests.pages.forms;

import java.util.List;
import org.openqa.selenium.By;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import task1.utils.WaitHandler;


public class ThisIsMeForm extends Form
{
    private final List<ICheckBox> interests = getElementFactory().findElements(By.xpath("//span[./label[not(contains(@for, 'selectall'))]]"), ElementType.CHECKBOX);
    private final IButton downloadImage = getElementFactory().getButton(By.xpath("//button[contains(@class, 'upload')]"), "Download image");
    private final ICheckBox unselectAll = getElementFactory().getCheckBox(By.xpath("//span[./label[contains(@for, 'unselect')]]"), "Unselect all");
    private final ILink uploadLink = getElementFactory().getLink(By.xpath("//a[contains(@class, 'upload')]"), "Upload");
    private final IButton nextButton = getElementFactory().getButton(By.xpath("//button[contains(@class, 'stroked')]"), "Next");
    private final ILabel avatarImage = getElementFactory().getLabel(By.xpath("//div[@class='avatar-and-interests__avatar-image']"), "Avatar image");


    public ThisIsMeForm()
    {
        super(By.xpath("//*[@class='avatar-and-interests__text']"), "This is me form");
    }
    
    
    public void clickDownloadButton()
    {
        this.downloadImage.click();
    }


    public void unselectAll()
    {
        this.unselectAll.click();
    }


    public void selectInterest(int index)
    {
        this.interests.get(index).check();
    }


    public int getInterestsCount()
    {
        return this.interests.size();
    }


    public void clickUploadButton()
    {
        this.uploadLink.click();
    }


    public void waitForImageToAppear(int timeout, int pollingInterval)
    {
        WaitHandler.waitForTrue(() -> this.avatarImage.state().isDisplayed(), timeout, pollingInterval, "Image must be present");
    }


    public void goToNextPage()
    {
        this.nextButton.click();
    }
}
