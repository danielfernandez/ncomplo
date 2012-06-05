package org.eleventhlabs.ncomplo.business.util;

import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;



public final class I18nUtils {
    

    public static String getTextForLocale(
            final Locale locale,
            final Map<String,String> i18nTexts,
            final String defaultText) {
        
        Validate.notNull(i18nTexts, "The map of i18n-ized texts cannot be null");
        Validate.notNull(defaultText, "Default text cannot be null");
        Validate.notNull(locale, "Locale cannot be null");

        String selector = locale.toString();
        String text = i18nTexts.get(selector);
        if (!StringUtils.isEmpty(text)) {
            return text;
        }
        if (locale.getCountry() != null) {
            selector = locale.getLanguage() + "_" + locale.getCountry();
            text = i18nTexts.get(selector);
            if (!StringUtils.isEmpty(text)) {
                return text;
            }
        }
        selector = locale.getLanguage();
        text = i18nTexts.get(selector);
        if (!StringUtils.isEmpty(text)) {
            return text;
        }

        return defaultText;
        
    }
    
    
    
    private I18nUtils() {
        super();
    }
    
    
}
