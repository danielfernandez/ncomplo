package org.eleventhlabs.ncomplo.business.util;

import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import org.eleventhlabs.ncomplo.business.entities.DatedAndNamedEntity;




public final class DatedAndNamedEntityComparator implements Comparator<DatedAndNamedEntity>{
    
    
    private final Locale locale;
    
    public DatedAndNamedEntityComparator(final Locale locale) {
        super();
        this.locale = locale;
    }
    
    
    
    @Override
    public int compare(final DatedAndNamedEntity o1, final DatedAndNamedEntity o2) {
        
        final Date o1Date = o1.getDate();
        final Date o2Date = o2.getDate();
        
        if (o1Date != null) {
            if (o2Date == null) {
                return -1;
            }
            final int dateComp = o1Date.compareTo(o2Date);
            if (dateComp != 0) {
                return dateComp;
            }
        } else if (o2Date != null) {
            return 1;
        }
        
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
