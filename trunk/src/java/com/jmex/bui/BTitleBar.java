//
// $Id: BTitleBar.java,v 1.3 2007/05/02 21:34:01 vivaldi Exp $
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

import com.jmex.bui.enumeratedConstants.TitleOptions;
import com.jmex.bui.event.ComponentListener;
import com.jmex.bui.layout.BorderLayout;

/**
 * @author timo
 * @since 27Apr07
 */
public class BTitleBar extends BContainer {
    private BLabel title;
    private BButtonBar buttonBar;

    public BTitleBar(final String _name,
                     final BLabel _title,
                     final TitleOptions options) {
        super(_name, new BorderLayout());

        title = _title;
        createButtonBar(options, null);
        setStyleClass("titlebar");
        addComponents();
    }

    public BTitleBar(final String _name,
                     final String _title,
                     final TitleOptions options) {
        this(_name, new BLabel(_title), options);
    }

    public BTitleBar(final String _name,
                     final BLabel _title,
                     final BButtonBar _buttonBar) {
        super(_name, new BorderLayout());
        title = _title;
        buttonBar = _buttonBar;
        addComponents();
    }

    public BTitleBar(final String _name,
                     final String _title,
                     final BButtonBar _buttonBar) {
        this(_name, new BLabel(_title), _buttonBar);
    }

    public BTitleBar(final String _name,
                     final String _title,
                     final String titleStyle,
                     final TitleOptions options,
                     final String buttonBarStyle) {
        super(_name, new BorderLayout());
        title = new BLabel(_title, titleStyle);
        createButtonBar(options, buttonBarStyle);

        addComponents();
    }

    public BTitleBar(final String _name,
                     final String _title,
                     final String titleStyle,
                     final TitleOptions options,
                     final String buttonBarStyle,
                     final String titleBarStyle) {
        super(_name, new BorderLayout());
        title = new BLabel(_title, titleStyle);
        createButtonBar(options, buttonBarStyle);

        setStyleClass(titleBarStyle);
        addComponents();
    }

    public BTitleBar(final String _name,
                     final String _title,
                     final TitleOptions options,
                     final String titleBarStyle) {
        this(_name, _title, options);
        setStyleClass(titleBarStyle);
    }

    public BTitleBar(final String _name,
                     final BLabel _title,
                     final BButtonBar _buttonBar,
                     final String titleBarStyle) {
        this(_name, _title, _buttonBar);
        setStyleClass(titleBarStyle);
    }

    private void createButtonBar(final TitleOptions options,
                                 final String styleClass) {
        buttonBar = new BButtonBar(getName(), null, options);
        buttonBar.addComponents();
        if (styleClass != null) {
            buttonBar.setStyleClass(styleClass);
        }
    }

    private void addComponents() {
        if (getLayoutManager() instanceof BorderLayout) {
            add(title, BorderLayout.WEST);

            if (buttonBar.getTitleOptions() != TitleOptions.NONE) {
                add(buttonBar, BorderLayout.EAST);
            }
        } else {
            add(title);

            if (buttonBar.getTitleOptions() != TitleOptions.NONE) {
                add(buttonBar);
            }
        }
    }

    public void addListener(ComponentListener listener) {
        super.addListener(listener);
        title.addListener(listener);
        buttonBar.addListener(listener);
    }
}
