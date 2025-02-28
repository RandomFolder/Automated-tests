package pages;

import java.util.List;
import org.openqa.selenium.By;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;

public class TestsPage extends Form {
    private final String tableCellXPath = "/td[%d]";
    private final String testLinkXPath = "//a[@href='testInfo?testId=%d']";
    private final ILabel loadingLabel = getElementFactory().getLabel(By.xpath("//div[@class='messi-content']"), "Loading label");
    private final ILabel glyphIcon = getElementFactory().getLabel(By.xpath("//span[contains(@class, 'glyphicon')]"), "Glyph icon");
    private final List<ILabel> tableHeaders = getElementFactory().findElements(By.xpath("//table[@class='table']//th[text()!='History']"), ElementType.LABEL);
    private final List<ILabel> tests = loadAllTests();

    private List<ILabel> loadAllTests() {
        this.loadingLabel.state().waitForNotDisplayed();

        return getElementFactory().findElements(By.xpath("//table[@class='table']/tbody/tr[not(th)]"), ElementType.LABEL);
    }

    public TestsPage() {
        super(By.xpath("//div[@id='pie']"), "Tests page");
    }

    public int getTestsCountOnCurrentPage() {
        return this.tests.size();
    }

    public String getValueByHeader(ILabel tableRow, int columnIndex) {
        String cellXPath = String.format(tableCellXPath, columnIndex);
        ILabel cell = tableRow.findChildElement(By.xpath(cellXPath), ElementType.LABEL);

        return cell.getText();
    }

    public List<ILabel> getTableHeaders() {
        return this.tableHeaders;
    }

    public List<ILabel> getTests() {
        return this.tests;
    }

    public void waitUntilGlyphIsPresent() {
        this.glyphIcon.state().waitForDisplayed();
    }

    private ILink getTest(int testId) {
        return getElementFactory().getLink(By.xpath(String.format(this.testLinkXPath, testId)), "Test " + testId);
    }

    public boolean testExists(int testId) {
        ILink testLink = this.getTest(testId);
        return testLink.state().waitForDisplayed();
    }

    public void goToTest(int testId) {
        ILink testLink = this.getTest(testId);
        testLink.click();
    }
}
