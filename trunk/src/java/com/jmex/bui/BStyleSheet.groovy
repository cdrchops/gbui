//
// $Id: BStyleSheet.java,v 1.2 2007/04/27 19:46:29 vivaldi Exp $
//
// BUI - a user interface library for the JME 3D engine
// Copyright (C) 2005-2006, Michael Bayne, All Rights Reserved
//
// This library is free software; you can redistribute it and/or modify it
// under the terms of the GNU Lesser General Public License as published
// by the Free Software Foundation; either version 2.1 of the License, or
// (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
/**
 * $ID:$
 * $COPYRIGHT:$
 */
package com.jmex.bui

import com.jme.renderer.ColorRGBA
import com.jmex.bui.Rule
import com.jmex.bui.background.BBackground
import com.jmex.bui.background.ImageBackground
import com.jmex.bui.border.BBorder
import com.jmex.bui.border.CompoundBorder
import com.jmex.bui.border.EmptyBorder
import com.jmex.bui.border.LineBorder
import com.jmex.bui.icon.BIcon
import com.jmex.bui.property.BackgroundProperty
import com.jmex.bui.property.CursorProperty
import com.jmex.bui.property.FontProperty
import com.jmex.bui.property.IconProperty
import com.jmex.bui.property.Property
import com.jmex.bui.provider.ResourceProvider
import com.jmex.bui.text.BKeyMap
import com.jmex.bui.text.BTextFactory
import com.jmex.bui.text.DefaultKeyMap
import com.jmex.bui.util.Dimension
import com.jmex.bui.util.Insets
import com.jmex.bui.util.TokReader;

/**
 * Defines a stylesheet which is used to configure the style (font family, font size, foreground
 * and background color, etc.) of components in the BUI library. The BUI stylesheets syntax is a
 * subset of the Cascading Style Sheet sytnax and follows its semantic conventions as well where
 * possible.
 * <p/>
 * <p> A basic stylesheet enumerating most usable values is as follows:
 * <pre>
 * style_class {*   // foreground and background properties
 *   color: 0 0 0;
 *   background: solid #00000088; // note the 50% alpha
 *   background: image monkey.png XX; // XX = centerx|centery|centerxy|
 *                                    //      scalex|scaley|scalexy|
 *                                    //      tilex|tiley|tilexy|
 *                                    //      framex|framey|framexy
 *   background: image monkey.png framexy top right bottom left;
 *   cursor: name;
 * <p/>
 *   // text properties
 *   font: Helvetica XX 12; // XX = normal|bold|italic|bolditalic
 *   text-align: XX; // XX = left|center|right
 *   vertical-align: XX; // XX = top|center|bottom
 *   text-effect: XX; // XX = none|outline|shadow|glow
 *   line-spacing: -2; // XX = amount of space to add/remove between lines
 * <p/>
 *   // box properties
 *   padding: top; // right=top, bottom=top, left=top
 *   padding: top, right; // bottom=top, left=right
 *   padding: top, right, bottom, left;
 *   border: 1 solid #FFCC99;
 *   border: 1 blank;
 *   size: 250 100; // overrrides component preferred size
 * <p/>
 *   // explicit inheritance
 *   parent: other_class; // other_class must be defined *before* this one
 * <p/>
 *   tooltip: other_class; // used to define the style class for the tool_tip
 *}* </pre>
 * <p/>
 * Each component is identified by its default stylesheet class, which are derived from the
 * component's Java class name: <code>window, label, textfield, component, popupmenu, etc.</code>
 * The component's stylesheet class can be overridden with a call to      {@link
 * BComponent # setStyleClass}     .
 * <p/>
 * <p> A component's style is resolved in the following manner:
 * <ul>
 * <li> First by looking up the property using the component's stylesheet class.
 * <p/>
 * <li> <em>For certain properties</em>, the interface hierarchy is then climbed and each parents'
 * stylesheet class is checked for the property in question. The properties for which that applies
 * are: <code>color, font, text-align, vertical-align</code>.
 * <p/>
 * <li> Lastly the <code>root</code> stylesheet class is checked (for all properties, not just
 * those for which we climb the interface hierarchy).
 * </ul>
 * <p/>
 * <p> This resolution process is followed at the time the component is added to the interface
 * hierarchy and the result is used to configure the component. We tradeoff the relative expense of
 * doing the lookup every time the component is rendered (every frame) with the memory expense of
 * storing the style of every component in memory.
 */
public class BStyleSheet {
    /**
     * A font style constant.
     */
    public static final String PLAIN = "plain";

    /**
     * A font style constant.
     */
    public static final String BOLD = "bold";

    /**
     * A font style constant.
     */
    public static final String ITALIC = "italic";

    /**
     * A font style constant.
     */
    public static final String BOLD_ITALIC = "bolditalic";

    /**
     * Creates a stylesheet from the specified textual source.
     *
     * @param reader Reader
     * @param rsrcprov ResourceProvider
     * @throws IOException io
     */
    public BStyleSheet(final Reader reader,
                       final ResourceProvider rsrcprov) {
        _rsrcprov = rsrcprov;

        try {
            parse(TokReader.tokenize(reader));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ColorRGBA getColor(BComponent component,
                              String pseudoClass) {
        return (ColorRGBA) findProperty(component, pseudoClass, "color", true);
    }

    public BBackground getBackground(BComponent component,
                                     String pseudoClass) {
        return (BBackground) findProperty(component, pseudoClass, "background", false);
    }

    public BIcon getIcon(BComponent component,
                         String pseudoClass) {
        return (BIcon) findProperty(component, pseudoClass, "icon", false);
    }

    public BCursor getCursor(BComponent component,
                             String pseudoClass) {
        return (BCursor) findProperty(component, pseudoClass, "cursor", true);
    }

    public BTextFactory getTextFactory(BComponent component,
                                       String pseudoClass) {
        return (BTextFactory) findProperty(component, pseudoClass, "font", true);
    }

    public int getTextAlignment(BComponent component,
                                String pseudoClass) {
        Integer value = (Integer) findProperty(component, pseudoClass, "text-align", true);
        return (value == null) ? BConstants.LEFT : value;
    }

    public int getVerticalAlignment(BComponent component,
                                    String pseudoClass) {
        Integer value = (Integer) findProperty(component, pseudoClass, "vertical-align", true);
        return (value == null) ? BConstants.CENTER : value;
    }

    public int getTextEffect(BComponent component,
                             String pseudoClass) {
        Integer value = (Integer) findProperty(component, pseudoClass, "text-effect", true);
        return (value == null) ? BConstants.NORMAL : value;
    }

    public int getLineSpacing(BComponent component, String pseudoClass) {
        Integer value = (Integer) findProperty(component, pseudoClass, "line-spacing", true);
        return (value == null) ? BConstants.DEFAULT_SPACING : value;
    }

    public int getEffectSize(BComponent component,
                             String pseudoClass) {
        Integer value = (Integer) findProperty(component, pseudoClass, "effect-size", true);
        return (value == null) ? BConstants.DEFAULT_SIZE : value;
    }

    public ColorRGBA getEffectColor(BComponent component,
                                    String pseudoClass) {
        return (ColorRGBA) findProperty(component, pseudoClass, "effect-color", true);
    }

    public Insets getInsets(BComponent component,
                            String pseudoClass) {
        Insets insets = (Insets) findProperty(component, pseudoClass, "padding", false);
        return (insets == null) ? Insets.ZERO_INSETS : insets;
    }

    public BBorder getBorder(BComponent component,
                             String pseudoClass) {
        // If there is a main border, use it.
        BBorder mainBorder = (BBorder) findProperty(component, pseudoClass, "border", false);
        if (mainBorder != null)
            return mainBorder;

        // Otherwise add up all of the other borders.
        CompoundBorder cBorder = new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new EmptyBorder(0, 0, 0, 0));
        BBorder leftBorder = (BBorder) findProperty(component, pseudoClass, "border-left", false);
        if (leftBorder != null) {
            CompoundBorder tempBorder = cBorder;
            cBorder = new CompoundBorder(leftBorder, tempBorder);
        }
        BBorder rightBorder = (BBorder) findProperty(component, pseudoClass, "border-right", false);
        if (rightBorder != null) {
            CompoundBorder tempBorder = cBorder;
            cBorder = new CompoundBorder(rightBorder, tempBorder);
        }
        BBorder topBorder = (BBorder) findProperty(component, pseudoClass, "border-top", false);
        if (topBorder != null) {
            CompoundBorder tempBorder = cBorder;
            cBorder = new CompoundBorder(topBorder, tempBorder);
        }
        BBorder bottomBorder = (BBorder) findProperty(component, pseudoClass, "border-bottom", false);
        if (bottomBorder != null) {
            CompoundBorder tempBorder = cBorder;
            cBorder = new CompoundBorder(bottomBorder, tempBorder);
        }
        return cBorder;

    }

    public Dimension getSize(BComponent component,
                             String pseudoClass) {
        return (Dimension) findProperty(component, pseudoClass, "size", false);
    }

    public String getTooltipStyle(BComponent component, String pseudoClass) {
        return (String) findProperty(component, pseudoClass, "tooltip", true);
    }

    public BKeyMap getKeyMap(BComponent component,
                             String pseudoClass) {
        return new DefaultKeyMap();
    }

    protected Object findProperty(BComponent component,
                                  String pseudoClass,
                                  String property,
                                  boolean climb) {
        Object value;

        // first try this component's configured style class
        String styleClass = component.getStyleClass();
        String fqClass = makeFQClass(styleClass, pseudoClass);
        if ((value = getProperty(fqClass, property)) != null) {
            return value;
        }

        // next fall back to its un-qualified configured style
        if (pseudoClass != null) {
            if ((value = getProperty(styleClass, property)) != null) {
                return value;
            }
        }

        // if applicable climb up the hierarch to its parent and try there
        if (climb) {
            BComponent parent = component.getParent();
            if (parent != null) {
                return findProperty(parent, pseudoClass, property, climb);
            }
        }

        // finally check the "root" class
        fqClass = makeFQClass("root", pseudoClass);
        if ((value = getProperty(fqClass, property)) != null) {
            return value;
        }
        if (pseudoClass != null) {
            return getProperty("root", property);
        }

        return null;
    }

    protected Object getProperty(String fqClass,
                                 String property) {
        Rule rule = _rules.get(fqClass);
        if (rule == null) {
            return null;
        }

        // we need to lazily resolve certain properties at this time
        Object prop = rule.get(_rules, property);
        if (prop instanceof Property) {
            prop = ((Property) prop).resolve(_rsrcprov);
            rule.properties.put(property, prop);
        }
        return prop;
    }

    protected void parse(StreamTokenizer tok) throws IOException {
        while (tok.nextToken() != StreamTokenizer.TT_EOF) {
            Rule rule = startRule(tok);
            while (parseProperty(tok, rule)) {
            }
            _rules.put(makeFQClass(rule.styleClass, rule.pseudoClass), rule);
        }
    }

    protected Rule startRule(StreamTokenizer tok)
    throws IOException {
        if (tok.ttype != StreamTokenizer.TT_WORD) {
            fail(tok, "style-class");
        }

        Rule rule = new Rule();
        rule.styleClass = tok.sval;

        switch (tok.nextToken()) {
            case '{':
                return rule;

            case ':':
                if (tok.nextToken() != StreamTokenizer.TT_WORD) {
                    fail(tok, "pseudo-class");
                }
                rule.pseudoClass = tok.sval;
                if (tok.nextToken() != '{') {
                    fail(tok, "{");
                }
                return rule;

            default:
                fail(tok, "{ or :");
                return null; // not reachable
        }
    }

    protected boolean parseProperty(StreamTokenizer tok,
                                    Rule rule)
    throws IOException {
        if (tok.nextToken() == '}') {
            return false;
        } else if (tok.ttype != StreamTokenizer.TT_WORD) {
            fail(tok, "property-name");
        }

        int sline = tok.lineno();
        String name = tok.sval;

        if (tok.nextToken() != ':') {
            fail(tok, ":");
        }

        ArrayList<Comparable> args = new ArrayList<Comparable>();
        while (tok.nextToken() != ';' && tok.ttype != '}') {
            switch (tok.ttype) {
                case '\'':
                case '"':
                case StreamTokenizer.TT_WORD:
                    args.add(tok.sval);
                    break;
                case StreamTokenizer.TT_NUMBER:
                    args.add(tok.nval);
                    break;
                default:
                    System.err.println(
                            "Unexpected token: '" + (char) tok.ttype + "'. Line " + tok.lineno() + ".");
                    break;
            }
        }

        try {
            rule.properties.put(name, createProperty(name, args));
//             System.out.println("  " + name + " -> " + rule.get(name));
        } catch (Exception e) {
            System.err.println(
                    "Failure parsing property '" + name + "' line " + sline + ": " + e.getMessage());
            if (!(e instanceof IllegalArgumentException)) {
                e.printStackTrace(System.err);
            }
        }
        return true;
    }

    protected Object createProperty(String name,
                                    ArrayList args) {
        if (name.equals("color") || name.equals("effect-color")) {
            return parseColor((String) args.get(0));
        } else if (name.equals("background")) {
            BackgroundProperty bprop = new BackgroundProperty();
            bprop.type = (String) args.get(0);
            if (bprop.type.equals("solid")) {
                bprop.color = parseColor((String) args.get(1));
            } else if (bprop.type.equals("image")) {
                bprop.ipath = (String) args.get(1);
                if (args.size() > 2) {
                    String scale = (String) args.get(2);
                    Integer scval = _ibconsts.get(scale);
                    if (scval == null) {
                        throw new IllegalArgumentException(
                                "Unknown background scaling type: '" + scale + "'");
                    }
                    bprop.scale = scval;
                    if (bprop.scale == ImageBackground.FRAME_XY && args.size() > 3) {
                        bprop.frame = new Insets();
                        bprop.frame.top = parseInt(args.get(3));
                        bprop.frame.right = (args.size() > 4) ?
                                            parseInt(args.get(4)) : bprop.frame.top;
                        bprop.frame.bottom = (args.size() > 5) ?
                                             parseInt(args.get(5)) : bprop.frame.top;
                        bprop.frame.left = (args.size() > 6) ?
                                           parseInt(args.get(6)) : bprop.frame.right;
                    }
                }
            } else if (bprop.type.equals("blank")) {
                // nothing to do

            } else {
                throw new IllegalArgumentException(
                        "Unknown background type: '" + bprop.type + "'");
            }
            return bprop;
        } else if (name.equals("icon")) {
            IconProperty iprop = new IconProperty();
            iprop.type = (String) args.get(0);
            if (iprop.type.equals("image")) {
                iprop.ipath = (String) args.get(1);
            } else if (iprop.type.equals("blank")) {
                iprop.width = parseInt(args.get(1));
                iprop.height = parseInt(args.get(2));
            } else {
                throw new IllegalArgumentException("Unknown icon type: '" + iprop.type + "'");
            }
            return iprop;
        } else if (name.equals("cursor")) {
            CursorProperty cprop = new CursorProperty();
            cprop.name = (String) args.get(0);
            return cprop;
        } else if (name.equals("font")) {
            try {
                FontProperty fprop = new FontProperty();
                fprop.family = (String) args.get(0);
                fprop.style = (String) args.get(1);
                if (!fprop.style.equals(PLAIN) && !fprop.style.equals(BOLD) &&
                    !fprop.style.equals(ITALIC) && !fprop.style.equals(BOLD_ITALIC)) {
                    throw new IllegalArgumentException("Unknown font style: '" + fprop.style + "'");
                }
                fprop.size = parseInt(args.get(2));
                return fprop;
            } catch (Exception e) {
                e.printStackTrace(System.err);
                throw new IllegalArgumentException(
                        "Fonts must be specified as: " +
                        "\"Font name\" plain|bold|italic|bolditalic point-size");
            }
        } else if (name.equals("text-align")) {
            String type = (String) args.get(0);
            Object value = _taconsts.get(type);
            if (value == null) {
                throw new IllegalArgumentException("Unknown text-align type '" + type + "'");
            }
            return value;
        } else if (name.equals("vertical-align")) {
            String type = (String) args.get(0);
            Object value = _vaconsts.get(type);
            if (value == null) {
                throw new IllegalArgumentException("Unknown vertical-align type '" + type + "'");
            }
            return value;
        } else if (name.equals("text-effect")) {
            String type = (String) args.get(0);
            Object value = _teconsts.get(type);
            if (value == null) {
                throw new IllegalArgumentException("Unknown text-effect type '" + type + "'");
            }
            return value;
        } else if (name.equals("effect-size")) {
            return parseInt(args.get(0));
        } else if (name.equals("line-spacing")) {
            return parseInt(args.get(0));
        } else if (name.equals("padding")) {
            Insets insets = new Insets();
            insets.top = parseInt(args.get(0));
            insets.right = (args.size() > 1) ? parseInt(args.get(1)) : insets.top;
            insets.bottom = (args.size() > 2) ? parseInt(args.get(2)) : insets.top;
            insets.left = (args.size() > 3) ? parseInt(args.get(3)) : insets.right;
            return insets;
        } else if (name.equals("border")) {
            int thickness = parseInt(args.get(0));
            String type = (String) args.get(1);
            if (type.equals("blank")) {
                return new EmptyBorder(thickness, thickness, thickness, thickness);
            } else if (type.equals("solid")) {
                return new LineBorder(parseColor((String) args.get(2)), thickness);
            } else {
                throw new IllegalArgumentException("Unknown border type '" + type + "'");
            }
        }/* else if (name.equals("border-left")) {
            int thickness = parseInt(args.get(0));
            String type = (String) args.get(1);
            if (type.equals("blank")) {
                return new EmptyBorder(thickness, 0, 0, 0);
            } else if (type.equals("solid")) {
                return new LineBorder(parseColor((String) args.get(2)), thickness, 0, 0, 0);
            } else {
                throw new IllegalArgumentException("Unknown border type '" + type + "'");
            }
        } else if (name.equals("border-top")) {
            int thickness = parseInt(args.get(0));
            String type = (String) args.get(1);
            if (type.equals("blank")) {
                return new EmptyBorder(0, thickness, 0, 0);
            } else if (type.equals("solid")) {
                return new LineBorder(parseColor((String) args.get(2)), 0, thickness, 0, 0);
            } else {
                throw new IllegalArgumentException("Unknown border type '" + type + "'");
            }
        } else if (name.equals("border-right")) {
            int thickness = parseInt(args.get(0));
            String type = (String) args.get(1);
            if (type.equals("blank")) {
                return new EmptyBorder(0, 0, thickness, 0);
            } else if (type.equals("solid")) {
                return new LineBorder(parseColor((String) args.get(2)), 0, 0, thickness, 0);
            } else {
                throw new IllegalArgumentException("Unknown border type '" + type + "'");
            }
        } else if (name.equals("border-bottom")) {
            int thickness = parseInt(args.get(0));
            String type = (String) args.get(1);
            if (type.equals("blank")) {
                return new EmptyBorder(0, 0, 0, thickness);
            } else if (type.equals("solid")) {
                return new LineBorder(parseColor((String) args.get(2)), 0, 0, 0, thickness);
            } else {
                throw new IllegalArgumentException("Unknown border type '" + type + "'");
            }
        }*/ else if (name.equals("size")) {
            Dimension size = new Dimension();
            size.width = parseInt(args.get(0));
            size.height = parseInt(args.get(1));
            return size;
        } else if (name.equals("parent")) {
            Rule parent = _rules.get(args.get(0));
            if (parent == null) {
                throw new IllegalArgumentException("Unknown parent class '" + args.get(0) + "'");
            }
            return parent;
        } else if (name.equals("tooltip")) {
            return (String) args.get(0);
        } else {
            throw new IllegalArgumentException("Unknown property '" + name + "'");
        }
    }

    protected void fail(StreamTokenizer tok,
                        String expected)
    throws IOException {
        String err = "Parse failure line: " + tok.lineno() +
                     " expected: '" + expected + "' found: '";
        switch (tok.ttype) {
            case StreamTokenizer.TT_WORD:
                err += tok.sval;
                break;
            case StreamTokenizer.TT_NUMBER:
                err += tok.nval;
                break;
            case StreamTokenizer.TT_EOF:
                err += "EOF";
                break;
            default:
                err += (char) tok.ttype;
        }
        throw new IOException(err + "'");
    }

    protected ColorRGBA parseColor(String hex) {
        if (!hex.startsWith("#") || (hex.length() != 7 && hex.length() != 9)) {
            String errmsg = "Color must be #RRGGBB or #RRGGBBAA: " + hex;
            throw new IllegalArgumentException(errmsg);
        }
        float r = Integer.parseInt(hex.substring(1, 3), 16) / 255f;
        float g = Integer.parseInt(hex.substring(3, 5), 16) / 255f;
        float b = Integer.parseInt(hex.substring(5, 7), 16) / 255f;
        float a = 1f;
        if (hex.length() == 9) {
            a = Integer.parseInt(hex.substring(7, 9), 16) / 255f;
        }
        return new ColorRGBA(r, g, b, a);
    }

    protected int parseInt(Object arg) {
        return (int) ((Double) arg).doubleValue();
    }

    protected static String makeFQClass(String styleClass,
                                        String pseudoClass) {
        return (pseudoClass == null) ? styleClass : (styleClass + ":" + pseudoClass);
    }


    protected ResourceProvider _rsrcprov;
    protected HashMap<String, Rule> _rules = new HashMap<String, Rule>();

    protected static HashMap<String, Integer> _taconsts = new HashMap<String, Integer>();
    protected static HashMap<String, Integer> _vaconsts = new HashMap<String, Integer>();
    protected static HashMap<String, Integer> _teconsts = new HashMap<String, Integer>();
    protected static HashMap<String, Integer> _ibconsts = new HashMap<String, Integer>();

    static {
        // alignment constants
        _taconsts.put("left", BConstants.LEFT);
        _taconsts.put("right", BConstants.RIGHT);
        _taconsts.put("center", BConstants.CENTER);

        _vaconsts.put("center", BConstants.CENTER);
        _vaconsts.put("top", BConstants.TOP);
        _vaconsts.put("bottom", BConstants.BOTTOM);

        // effect constants
        _teconsts.put("none", BConstants.NORMAL);
        _teconsts.put("shadow", BConstants.SHADOW);
        _teconsts.put("outline", BConstants.OUTLINE);
        _teconsts.put("plain", BConstants.PLAIN);

        // background image constants
        _ibconsts.put("centerxy", ImageBackground.CENTER_XY);
        _ibconsts.put("centerx", ImageBackground.CENTER_X);
        _ibconsts.put("centery", ImageBackground.CENTER_Y);
        _ibconsts.put("scalexy", ImageBackground.SCALE_XY);
        _ibconsts.put("scalex", ImageBackground.SCALE_X);
        _ibconsts.put("scaley", ImageBackground.SCALE_Y);
        _ibconsts.put("tilexy", ImageBackground.TILE_XY);
        _ibconsts.put("tilex", ImageBackground.TILE_X);
        _ibconsts.put("tiley", ImageBackground.TILE_Y);
        _ibconsts.put("framexy", ImageBackground.FRAME_XY);
        _ibconsts.put("framex", ImageBackground.FRAME_X);
        _ibconsts.put("framey", ImageBackground.FRAME_Y);
    }
}