/**
 * $ID:$
 * $COPYRIGHT:$
 */
package com.jmex.bui;

/**
 * @author torr
 * @since Mar 24, 2009 - 10:37:43 AM
 */
public class BButtonScrollingList<V, C extends BComponent> extends BScrollingList<V, C> {
    @Override
    public BComponent createComponent(final Object value) {
        return new BButton((String) value);
    }
}