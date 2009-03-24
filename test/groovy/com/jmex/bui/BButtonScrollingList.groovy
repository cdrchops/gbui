/**
 * $ID$
 * $COPYRIGHT$
 */
package com.jmex.bui

import com.jmex.bui.BButton
import com.jmex.bui.BScrollingList

/**
 *
 * @author timo
 * @since Dec 13, 2008 12:19:51 PM
 */
class BButtonScrollingList extends BScrollingList {
  @Override
  public BButton createComponent(Object str) {
    return new BButton((String) str);
  }
}