package org.eleventhlabs.ncomplo.business.util;

import java.util.Comparator;
import java.util.Locale;

import org.eleventhlabs.ncomplo.business.entities.I18nNamedEntity;




public final class I18nNamedEntityComparator implements Comparator<I18nNamedEntity>{
    
    
    private final Locale locale;
    
    public I18nNamedEntityComparator(final Locale locale) {
        super();
        this.locale = locale;
    }
    
    
    
    @Override
    public int compare(final I18nNamedEntity o1, final I18nNamedEntity o2) {
        final String o1Name = o1.getName(this.locale);
        final String o2Name = o2.getName(this.locale);
        if (o1Name == null) {
            return 1;
        }
        if (o2Name == null) {
            return -1;
        }
        return o1Name.compareTo(o2Name);
    }
    
    
}
