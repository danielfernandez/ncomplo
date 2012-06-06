package org.eleventhlabs.ncomplo.business.entities;

import java.util.Date;
import java.util.Locale;
import java.util.Map;


public interface DatedAndNamedEntity {


    public Date getDate();
    public String getName(final Locale locale);
    public String getName();
    public Map<String, String> getNamesByLang();
    
}
