/**
 * $ID:$
 * $COPYRIGHT:$
 */
package com.jmex.bui.util;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StreamTokenizer;

/**
 * @author torr
 * @since Oct 9, 2008 - 11:48:34 AM
 */
public class TokReader {
    public static StreamTokenizer tokenize(final Reader reader) {
        final StreamTokenizer tok = new StreamTokenizer(new BufferedReader(reader));

        tok.lowerCaseMode(true);
        tok.slashSlashComments(true);
        tok.slashStarComments(true);

        tok.eolIsSignificant(false);

        tok.wordChars('#', '#');
        tok.wordChars('_', '_');

        return tok;
    }
}
