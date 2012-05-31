package org.eleventhlabs.ncomplo.business.util;

import java.util.ArrayList;
import java.util.List;



public final class IterableUtils {


    public static <T> List<T> toList(final Iterable<T> iter) {
        final List<T> result = new ArrayList<T>();
        for (final T value : iter) {
            result.add(value);
        }
        return result;
    }
    
    
    private IterableUtils() {
        super();
    }
    
    
}
