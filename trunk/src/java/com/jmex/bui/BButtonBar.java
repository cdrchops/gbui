package com.jmex.bui;

import java.util.LinkedHashSet;
import java.util.Set;

import com.jmex.bui.enumeratedConstants.DialogOptions;
import com.jmex.bui.enumeratedConstants.TitleOptions;
import com.jmex.bui.event.ComponentListener;
import com.jmex.bui.layout.GroupLayout;
import com.jmex.bui.layout.HGroupLayout;

public class BButtonBar extends BContainer {
    private static BButton YES_BUTTON;
    private static BButton NO_BUTTON;
    private static BButton CANCEL_BUTTON;
    private static BButton OK_BUTTON;
    private static BButton MINIMIZE_BUTTON;
    private static BButton MAXIMIZE_BUTTON;
    private static BButton CLOSE_BUTTON;

    private Set<BButton> buttons = new LinkedHashSet<BButton>();
    private DialogOptions dialogOptions;
    private TitleOptions titleOptions;

    public BButtonBar(final String _name,
                      final DialogOptions __dialogOptions,
                      final TitleOptions __titleOptions) {
        super(_name, new HGroupLayout(GroupLayout.STRETCH));

        dialogOptions = __dialogOptions;
        titleOptions = __titleOptions;

        initButtons();
    }

    private void initButtons() {
        YES_BUTTON = new BButton("Yes", getName() + "yes");
        NO_BUTTON = new BButton("No", getName() + "no");
        CANCEL_BUTTON = new BButton("Cancel", getName() + "cancel");
        OK_BUTTON = new BButton("Ok", getName() + "ok");
        MINIMIZE_BUTTON = new BButton("Min", getName() + "minimize");
        MAXIMIZE_BUTTON = new BButton("Max", getName() + "maximize");
        CLOSE_BUTTON = new BButton("Close", getName() + "close");

        YES_BUTTON.setSize(12, 12);
        NO_BUTTON.setSize(12, 12);
        OK_BUTTON.setSize(12, 12);
        CANCEL_BUTTON.setSize(50, 12);

        MAXIMIZE_BUTTON.setSize(12, 12);
        MINIMIZE_BUTTON.setSize(12, 12);
        CLOSE_BUTTON.setSize(12, 12);

        YES_BUTTON.setStyleClass("dialogbutton");
        NO_BUTTON.setStyleClass("dialogbutton");
        CANCEL_BUTTON.setStyleClass("dialogbutton");
        OK_BUTTON.setStyleClass("dialogbutton");

        MAXIMIZE_BUTTON.setStyleClass("titlebutton");
        MINIMIZE_BUTTON.setStyleClass("titlebutton");
        CLOSE_BUTTON.setStyleClass("titlebutton");
    }

    private void configureDialog() {
        if (dialogOptions != null) {
            switch (dialogOptions) {
                case YES:
                    buttons.add(YES_BUTTON);
                    break;
                case NO:
                    buttons.add(NO_BUTTON);
                    break;
                case YES_NO:
                    buttons.add(YES_BUTTON);
                    buttons.add(NO_BUTTON);
                    break;
                case YES_NO_CANCEL:
                    buttons.add(YES_BUTTON);
                    buttons.add(NO_BUTTON);
                    buttons.add(CANCEL_BUTTON);
                    break;
                case NO_CANCEL:
                    buttons.add(NO_BUTTON);
                    buttons.add(CANCEL_BUTTON);
                    break;
                case YES_CANCEL:
                    buttons.add(YES_BUTTON);
                    buttons.add(CANCEL_BUTTON);
                    break;
                case CANCEL:
                    buttons.add(CANCEL_BUTTON);
                    break;
                case OK:
                    buttons.add(OK_BUTTON);
                    break;
            }
        }

        if (titleOptions != null) {
            switch (titleOptions) {
                case NONE:
                    return;
                case MAX:
                    buttons.add(MAXIMIZE_BUTTON);
                    break;
                case MIN:
                    buttons.add(MINIMIZE_BUTTON);
                    break;
                case CLOSE:
                    buttons.add(CLOSE_BUTTON);
                    break;
                case MAX_MIN:
                    buttons.add(MINIMIZE_BUTTON);
                    buttons.add(MAXIMIZE_BUTTON);
                    break;
                case MAX_CLOSE:
                    buttons.add(MAXIMIZE_BUTTON);
                    buttons.add(CLOSE_BUTTON);
                    break;
                case MIN_CLOSE:
                    buttons.add(MINIMIZE_BUTTON);
                    buttons.add(CLOSE_BUTTON);
                    break;
                case MIN_MAX_CLOSE:
                    buttons.add(MINIMIZE_BUTTON);
                    buttons.add(MAXIMIZE_BUTTON);
                    buttons.add(CLOSE_BUTTON);
                    break;
            }
        }
    }

    public void addComponents() {
        configureDialog();

        for (BButton button : buttons) {
            if (_listeners != null) {
                button.addListeners(_listeners);
            }
            add(button);
        }
    }

    public void setDialogButtonInactive(DialogOptions option) {}

    public void setTitleButtonInactive(TitleOptions option) {
        switch (titleOptions) {
            case NONE:
                return;
            case MAX:
                buttons.add(MAXIMIZE_BUTTON);
                break;
            case MIN:
                buttons.add(MINIMIZE_BUTTON);
                break;
            case CLOSE:
                buttons.add(CLOSE_BUTTON);
                break;
            case MAX_MIN:
                buttons.add(MINIMIZE_BUTTON);
                buttons.add(MAXIMIZE_BUTTON);
                break;
            case MAX_CLOSE:
                buttons.add(MAXIMIZE_BUTTON);
                buttons.add(CLOSE_BUTTON);
                break;
            case MIN_CLOSE:
                buttons.add(MINIMIZE_BUTTON);
                buttons.add(CLOSE_BUTTON);
                break;
            case MIN_MAX_CLOSE:
                buttons.add(MINIMIZE_BUTTON);
                buttons.add(MAXIMIZE_BUTTON);
                buttons.add(CLOSE_BUTTON);
                break;
        }
    }

    public DialogOptions getDialogOptions() {
        return dialogOptions;
    }

    public TitleOptions getTitleOptions() {
        return titleOptions;
    }

    public void addListener(ComponentListener listener) {
        super.addListener(listener);
        for (BButton button : buttons) {
            button.addListener(listener);
        }
    }
}
