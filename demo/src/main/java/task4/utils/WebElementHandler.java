package task4.utils;

import java.util.List;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IElement;

public class WebElementHandler {
    public static String elementsTextToString(List<IElement> elements) {
        String result = "";

        for (IElement el : elements)
        {
            result += el.getText();
        }

        return result;
    }
    
    public static void dragAndDrop(IElement src, IElement dest) {
        Actions builder = new Actions(AqualityServices.getBrowser().getDriver());
        Action dragAndDrop = builder.clickAndHold(src.getElement())
        .moveToElement(dest.getElement())
        .release(dest.getElement())
        .build();

        dragAndDrop.perform();
    }
}
