package org.eleventhlabs.ncomplo.business.util;

import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;



public final class I18nUtils {

    public static final String DEFAULT_LANG = "*";
    

    public static String getTextForLocale(final Map<String,String> texts, final Locale locale) {
        
        Validate.notNull(texts, "Map of texts cannot be null");
        Validate.notNull(locale, "Locale cannot be null");

        String selector = locale.toString();
        String text = texts.get(selector);
        if (!StringUtils.isEmpty(text)) {
            return text;
        }
        if (locale.getCountry() != null) {
            selector = locale.getLanguage() + "_" + locale.getCountry();
            text = texts.get(selector);
            if (!StringUtils.isEmpty(text)) {
                return text;
            }
        }
        selector = locale.getLanguage();
        text = texts.get(selector);
        if (!StringUtils.isEmpty(text)) {
            return text;
        }

        return texts.get(DEFAULT_LANG);
        
    }
    
    
    
    private I18nUtils() {
        super();
    }
    
    
}
