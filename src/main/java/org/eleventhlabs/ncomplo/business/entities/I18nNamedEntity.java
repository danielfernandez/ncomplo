package org.eleventhlabs.ncomplo.business.entities;

import java.util.Locale;
import java.util.Map;


public interface I18nNamedEntity {


    public String getName(final Locale locale);
    public String getName();
    public Map<String, String> getNamesByLang();
    
}
