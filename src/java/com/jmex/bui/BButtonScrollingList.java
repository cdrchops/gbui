/**
 * $ID:$
 * $COPYRIGHT:$
 */
package com.jmex.bui;

/**
 * @author torr
 * @since Mar 24, 2009 - 10:37:43 AM
 */
public class BButtonScrollingList extends BScrollingList {
    @Override
    public BButton createComponent(Object str) {
        return new BButton((String) str);
    }
}