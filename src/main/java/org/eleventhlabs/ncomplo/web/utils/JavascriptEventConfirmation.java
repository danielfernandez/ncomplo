package org.eleventhlabs.ncomplo.web.utils;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.model.Model;


public class JavascriptEventConfirmation extends AttributeModifier {
    
    private static final long serialVersionUID = 418125140025361710L;

    public JavascriptEventConfirmation(String event, String msg) {
        super(event, true, new Model<String>(msg));
    }

    @Override
    protected String newValue(final String currentValue, final String replacementValue) {
        String prefix = "var conf = confirm('" + replacementValue + "'); " +
            "if (!conf) return false; ";
        String result = prefix;
        if (currentValue != null) {
            result = prefix + currentValue;
        }
        return result;
    }
    
}
