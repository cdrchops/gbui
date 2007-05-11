//
// $Id: BAbstractMessageWindow.java,v 1.5 2007/05/08 22:13:49 vivaldi Exp $
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

package com.jmex.bui.headlessWindows;

import com.jmex.bui.BStyleSheet;
import com.jmex.bui.layout.BLayoutManager;

/**
 * @author timo
 * @since 27Apr07
 */
public abstract class BAbstractMessageWindow extends BDraggableWindow {
    private String minimizedStyleClass = "window";
    private String maximizedStyleClass = "window";

    private int maximizedWidth;
    private int maximizedHeight;

    private int minimizedWidth;
    private int minimizedHeight;

    public BAbstractMessageWindow(final String name,
                                  final BStyleSheet style,
                                  final BLayoutManager layout) {
        super(name, style, layout);
    }

    public BAbstractMessageWindow(final BStyleSheet style,
                                  final BLayoutManager layout) {
        super(style, layout);
    }

    public String getMaximizedStyleClass() {
        return maximizedStyleClass;
    }

    public void setMaximizedStyleClass(final String _maximizedStyleClass) {
        maximizedStyleClass = _maximizedStyleClass;
    }

    public String getMinimizedStyleClass() {
        return minimizedStyleClass;
    }

    public void setMinimizedStyleClass(final String _minimizedStyleClass) {
        minimizedStyleClass = _minimizedStyleClass;
    }

    public int getMaximizedHeight() {
        return maximizedHeight;
    }

    public void setMaximizedHeight(final int _maximizedHeight) {
        maximizedHeight = _maximizedHeight;
    }

    public int getMaximizedWidth() {
        return maximizedWidth;
    }

    public void setMaximizedWidth(final int _maximizedWidth) {
        maximizedWidth = _maximizedWidth;
    }

    public int getMinimizedHeight() {
        return minimizedHeight;
    }

    public void setMinimizedHeight(final int _minimizedHeight) {
        minimizedHeight = _minimizedHeight;
    }

    public int getMinimizedWidth() {
        return minimizedWidth;
    }

    public void setMinimizedWidth(final int _minimizedWidth) {
        minimizedWidth = _minimizedWidth;
    }

    public void setSize(final int width,
                        final int height,
                        final int minW,
                        final int minH) {
        super.setSize(width, height);
        maximizedWidth = width;
        maximizedHeight = height;
        minimizedWidth = minW;
        minimizedHeight = minH;
    }

    public void setStyleClass(final String styleClass) {
        super.setStyleClass(styleClass);
        maximizedStyleClass = styleClass;
    }

    public abstract void addTitleBar();

    public abstract void addComponents();
}
