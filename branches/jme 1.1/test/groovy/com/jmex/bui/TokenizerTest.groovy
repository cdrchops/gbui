/**
 * $ID:$
 * $COPYRIGHT:$
 */
package com.jmex.bui

/**
 *
 *
 * @author torr
 * @since Oct 10, 2008 - 1:28:25 PM
 *
 */

class TokenizerTest extends GroovyTestCase {
    void testIntValues() {
        assert BStyleSheet.@END_BRACE == 125
        assert BStyleSheet.@START_BRACE == 123
        assert BStyleSheet.@SEMI_COLON == 59
        assert BStyleSheet.@SINGLE_QUOTE == 39
        assert BStyleSheet.@DOUBLE_QUOTE == 34
        assert BStyleSheet.@COLON == 58
    }
}

