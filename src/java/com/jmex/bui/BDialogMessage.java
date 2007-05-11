//
// $Id: BDialogMessage.java,v 1.6 2007/05/02 21:34:01 vivaldi Exp $
//
// BUI - a user interface library for the JME 3D engine
// Copyright (C) 2005, Michael Bayne, All Rights Reserved
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

package com.jmex.bui;

import com.jmex.bui.enumeratedConstants.DialogOptions;
import com.jmex.bui.enumeratedConstants.DisplayStyleOptions;
import com.jmex.bui.enumeratedConstants.IconOptions;
import com.jmex.bui.event.ComponentListener;
import com.jmex.bui.icon.BIcon;
import com.jmex.bui.icon.IconUtil;
import com.jmex.bui.layout.BorderLayout;
import com.jmex.bui.layout.GroupLayout;

//todo: change so that the style is set in the BuiSystem? and then can be propogated through
//      the subset of the window

//      otherwise parts might be one way, and other parts the other way
/**
 * @author timo
 * @since 27Apr07
 */
public class BDialogMessage extends BContainer {
    private static final BLabel infoIconLabel = new BLabel("");
    private static final BIcon INFO_ICON_MOTIF =
            IconUtil.getIcon(BDialogMessage.class.getResource("/rsrc/dialogLogos/metal-info.png"));
    private static final BIcon INFO_ICON_WINDOWS =
            IconUtil.getIcon(BDialogMessage.class.getResource("/rsrc/dialogLogos/metal-info.png"));

    private static final BLabel errorIconLabel = new BLabel("");
    private static final BIcon ERROR_ICON_MOTIF =
            IconUtil.getIcon(BDialogMessage.class.getResource("/rsrc/dialogLogos/metal-error.png"));
    private static final BIcon ERROR_ICON_WINDOWS =
            IconUtil.getIcon(BDialogMessage.class.getResource("/rsrc/dialogLogos/metal-error.png"));

    private static final BLabel warnIconLabel = new BLabel("");
    private static final BIcon WARN_ICON_MOTIF =
            IconUtil.getIcon(BDialogMessage.class.getResource("/rsrc/dialogLogos/metal-warning.png"));
    private static final BIcon WARN_ICON_WINDOWS =
            IconUtil.getIcon(BDialogMessage.class.getResource("/rsrc/dialogLogos/metal-warning.png"));

    private static final BLabel questionIconLabel = new BLabel("");
    private static final BIcon QUESTION_ICON_MOTIF =
            IconUtil.getIcon(BDialogMessage.class.getResource("/rsrc/dialogLogos/metal-question.png"));
    private static final BIcon QUESTION_ICON_WINDOWS =
            IconUtil.getIcon(BDialogMessage.class.getResource("/rsrc/dialogLogos/metal-question.png"));

    public BDialogMessage(final String _name,
                          final String message) {
        this(_name, new BLabel(message), DialogOptions.OK, IconOptions.INFO, DisplayStyleOptions.MOTIF);
    }

    public BDialogMessage(final String _name,
                          final String message,
                          final DialogOptions options,
                          final DisplayStyleOptions style) {
        this(_name, new BLabel(message), options, IconOptions.INFO, style);
    }

    public BDialogMessage(final String _name,
                          final String message,
                          final IconOptions option,
                          final DisplayStyleOptions style) {
        this(_name, new BLabel(message), DialogOptions.OK, option, style);
    }

    public BDialogMessage(final String _name,
                          final String message,
                          final DialogOptions options,
                          final IconOptions option,
                          final DisplayStyleOptions style) {
        this(_name, new BLabel(message), options, option, style);
    }

    public BDialogMessage(final String _name,
                          final String message,
                          final String messageStyle,
                          final DialogOptions options,
                          final IconOptions option,
                          final DisplayStyleOptions style) {
        this(_name, new BLabel(message, messageStyle), options, option, style);
    }

    public BDialogMessage(final String _name,
                          final String message,
                          final String messageStyle,
                          final String backgroundStyle,
                          final DialogOptions options,
                          final IconOptions option,
                          final DisplayStyleOptions style) {
        this(_name, new BLabel(message, messageStyle), backgroundStyle, options, option, style);
    }

    public BDialogMessage(final String _name,
                          final BLabel message,
                          final DialogOptions options,
                          final IconOptions option,
                          final DisplayStyleOptions style) {
        this(_name, message, "greymessagebg", options, option, style);
    }

    public BDialogMessage(final String _name,
                          final BLabel message,
                          final String backgroundStyle,
                          final DialogOptions options,
                          final IconOptions option,
                          final DisplayStyleOptions style) {
        super(_name, new BorderLayout());

        createMessage(_name, message, backgroundStyle, options, option, style);
    }

    private static BButtonBar buttonBar;

    private void createMessage(final String _name,
                               final BLabel message,
                               final String backgroundStyle,
                               final DialogOptions options,
                               final IconOptions option,
                               DisplayStyleOptions styleOption) {
        BContainer bc = GroupLayout.makeVBox(GroupLayout.CENTER);
        bc.setName(_name);

        bc.add(message);
        if (styleOption == null) {
            styleOption = DisplayStyleOptions.MOTIF;
        }

        if (option == null) {
            if (styleOption == DisplayStyleOptions.WINDOWS) {
                infoIconLabel.setIcon(INFO_ICON_WINDOWS);
            } else {
                infoIconLabel.setIcon(INFO_ICON_MOTIF);
            }

            add(infoIconLabel, BorderLayout.WEST);
        } else {
            switch (option) {
                case INFO:
                    if (styleOption == DisplayStyleOptions.WINDOWS) {
                        infoIconLabel.setIcon(INFO_ICON_WINDOWS);
                    } else {
                        infoIconLabel.setIcon(INFO_ICON_MOTIF);
                    }

                    add(infoIconLabel, BorderLayout.WEST);
                    break;
                case WARNING:
                    if (styleOption == DisplayStyleOptions.WINDOWS) {
                        warnIconLabel.setIcon(WARN_ICON_WINDOWS);
                    } else {
                        warnIconLabel.setIcon(WARN_ICON_MOTIF);
                    }

                    add(warnIconLabel, BorderLayout.WEST);
                    break;
                case ERROR:
                    if (styleOption == DisplayStyleOptions.WINDOWS) {
                        errorIconLabel.setIcon(ERROR_ICON_WINDOWS);
                    } else {
                        errorIconLabel.setIcon(ERROR_ICON_MOTIF);
                    }

                    add(errorIconLabel, BorderLayout.WEST);
                    break;
                case QUESTION:
                    if (styleOption == DisplayStyleOptions.WINDOWS) {
                        questionIconLabel.setIcon(QUESTION_ICON_WINDOWS);
                    } else {
                        questionIconLabel.setIcon(QUESTION_ICON_MOTIF);
                    }

                    add(questionIconLabel, BorderLayout.WEST);
                    break;
            }
        }


        buttonBar = new BButtonBar(_name, options, null);
        buttonBar.addComponents();

        bc.add(buttonBar);

        add(bc, BorderLayout.CENTER);
        setStyleClass(backgroundStyle);
    }

    public void addListener(final ComponentListener listener) {
        super.addListener(listener);
        buttonBar.addListener(listener);
    }
}