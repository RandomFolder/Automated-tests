package exam.utils;

import aquality.selenium.core.elements.interfaces.IElement;

public class WebElementsHandler {
    public static String divideAttributeIntoSubstringsAndGetThaLastOne(IElement element, String attr, String separator) {
        String attrValue = element.getAttribute(attr);
        String[] substrings = attrValue.split(separator);
        String result = substrings[substrings.length - 1];

        return result;
    }
}
