/**
 * $ID:$
 * $COPYRIGHT:$
 */
package com.jmex.bui

import com.steadystate.css.parser.SACParserCSS2
import com.steadystate.css.parser.SACParserCSS2TokenManager
import com.steadystate.css.parser.CharStream

/**
 *
 *
 * @author torr
 * @since Oct 8, 2008 - 4:11:03 PM
 *
 */
class BuiStyleSheetTest extends GroovyTestCase {
    void test() {
        def s = new SACParserCSS2()
        s.parseStyleSheet("file://../gbui/trunk/test/groovy/com/jmex/bui/colors.css")

//        def f = new File("../gbui/trunk/build/rsrc/style2.bss")
//        println ClassPath.getResourceAsStream("/rsrc/style2.bss")
//        println f.absolutePath

//        def bss = new BStyleSheet(new FileReader(f), new BStyleSheet.DefaultResourceProvider())


    }
}