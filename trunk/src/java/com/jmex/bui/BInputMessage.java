//
// $Id: BInputMessage.java,v 1.4 2007/05/02 21:34:01 vivaldi Exp $
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
import com.jmex.bui.enumeratedConstants.IconOptions;
import com.jmex.bui.event.ComponentListener;
import com.jmex.bui.icon.BIcon;
import com.jmex.bui.icon.IconUtil;
import com.jmex.bui.layout.BorderLayout;
import com.jmex.bui.layout.GroupLayout;

/**
 * @author timo
 * @since 27Apr07
 */
public class BInputMessage extends BContainer {
    private static final BLabel infoIconLabel = new BLabel("");
    private static final BIcon INFO_ICON =
            IconUtil.getIcon(BDialogMessage.class.getResource("/rsrc/dialogLogos/metal-info.png"));

    private static final BLabel errorIconLabel = new BLabel("");
    private static final BIcon ERROR_ICON =
            IconUtil.getIcon(BDialogMessage.class.getResource("/rsrc/dialogLogos/metal-error.png"));

    private static final BLabel warnIconLabel = new BLabel("");
    private static final BIcon WARN_ICON =
            IconUtil.getIcon(BDialogMessage.class.getResource("/rsrc/dialogLogos/metal-warning.png"));

    private static final BLabel questionIconLabel = new BLabel("");
    private static final BIcon QUESTION_ICON =
            IconUtil.getIcon(BDialogMessage.class.getResource("/rsrc/dialogLogos/metal-question.png"));

    static {
        infoIconLabel.setIcon(INFO_ICON);
        errorIconLabel.setIcon(ERROR_ICON);
        warnIconLabel.setIcon(WARN_ICON);
        questionIconLabel.setIcon(QUESTION_ICON);
    }

    public BInputMessage(final String _name,
                         final String message) {
        this(_name, new BLabel(message), DialogOptions.OK, IconOptions.INFO);
    }

    public BInputMessage(final String _name,
                         final String message,
                         final DialogOptions options) {
        this(_name, new BLabel(message), options, IconOptions.INFO);
    }

    public BInputMessage(final String _name,
                         final String message,
                         final IconOptions option) {
        this(_name, new BLabel(message), DialogOptions.OK, option);
    }

    public BInputMessage(final String _name,
                         final String message,
                         final DialogOptions options,
                         final IconOptions option) {
        this(_name, new BLabel(message), options, option);
    }

    public BInputMessage(final String _name,
                         final String message,
                         final String messageStyle,
                         final DialogOptions options,
                         final IconOptions option) {
        this(_name, new BLabel(message, messageStyle), options, option);
    }

    public BInputMessage(final String _name,
                         final String message,
                         final String messageStyle,
                         final String backgroundStyle,
                         final DialogOptions options,
                         final IconOptions option) {
        this(_name, new BLabel(message, messageStyle), backgroundStyle, options, option);
    }

    public BInputMessage(final String _name,
                         final BLabel message,
                         final DialogOptions options,
                         final IconOptions option) {
        this(_name, message, "greymessagebg", options, option);
    }

    public BInputMessage(final String _name,
                         final BLabel message,
                         final String backgroundStyle,
                         final DialogOptions options,
                         final IconOptions option) {
        super(_name, new BorderLayout());

        createMessage(_name, message, backgroundStyle, options, option);
    }

    private static BTextField bf = new BTextField("");
    private static BButtonBar buttonBar;

    public String getInputText() {
        return bf.getText();
    }

    private void createMessage(final String _name,
                               final BLabel message,
                               final String backgroundStyle,
                               final DialogOptions options,
                               final IconOptions option) {
        if (option == null) {
            add(infoIconLabel, BorderLayout.WEST);
        } else {
            switch (option) {
                case INFO:
                    add(infoIconLabel, BorderLayout.WEST);
                    break;
                case WARNING:
                    add(warnIconLabel, BorderLayout.WEST);
                    break;
                case ERROR:
                    add(errorIconLabel, BorderLayout.WEST);
                    break;
                case QUESTION:
                    add(questionIconLabel, BorderLayout.WEST);
                    break;
            }
        }

        BContainer bc = new BContainer(_name, GroupLayout.makeVStretch());

        bc.add(message);

        bc.add(bf);

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