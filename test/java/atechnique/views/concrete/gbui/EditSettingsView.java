package atechnique.views.concrete.gbui;

import atechnique.views.interfaces.IEditSettingsListener;
import atechnique.views.interfaces.IEditSettingsView;
import com.jmex.bui.BButton;
import com.jmex.bui.BCheckBox;
import com.jmex.bui.BComboBox;
import com.jmex.bui.BLabel;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.headlessWindows.DialogBoxUtil;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.util.Point;

import java.util.ArrayList;
import java.util.List;

public class EditSettingsView extends GbuiGameState implements IEditSettingsView {
    private BLabel _lblResolution;
    private BComboBox _cboResolution;
    private BLabel _lblDepth;
    private BComboBox _cboDepth;
    private BLabel _lblFrequency;
    private BComboBox _cboFrequency;
    private BLabel _lblFullScreen;
    private BCheckBox _chkIsFullScreen;
    private BButton _btnCancel;
    private BButton _btnOk;

    private IEditSettingsListener _editSettingsListener;

    public EditSettingsView() {
        super("EditSettingsView");

        _window = new BWindow(BuiSystem.getStyle(), new AbsoluteLayout());
        _window.setSize((int) (0.8f * 1.2f * display.getWidth() / 2),
                        (int) (1.4f * (display.getHeight() / 2)));

        int labelWidth = 100;
        int labelHeight = 32;

        _lblResolution = new BLabel("");
        _lblResolution.setPreferredSize(labelWidth, labelHeight);
        _window.add(_lblResolution, new Point(50, _window.getHeight() - 50));

        _cboResolution = new BComboBox();
        _cboResolution.setPreferredSize(labelWidth, labelHeight);
        _window.add(_cboResolution, new Point(200, _window.getHeight() - 50));

        _lblDepth = new BLabel("");
        _lblDepth.setPreferredSize(labelWidth, labelHeight);
        _window.add(_lblDepth, new Point(50, _window.getHeight() - 100));

        _cboDepth = new BComboBox();
        _cboDepth.setPreferredSize(labelWidth, labelHeight);
        _window.add(_cboDepth, new Point(200, _window.getHeight() - 100));

        _lblFrequency = new BLabel("");
        _lblFrequency.setPreferredSize(labelWidth, labelHeight);
        _window.add(_lblFrequency, new Point(50, _window.getHeight() - 150));

        _cboFrequency = new BComboBox();
        _cboFrequency.setPreferredSize(labelWidth, labelHeight);
        _window.add(_cboFrequency, new Point(200, _window.getHeight() - 150));

        _lblFullScreen = new BLabel("");
        _lblFullScreen.setPreferredSize(labelWidth, labelHeight);
        _window.add(_lblFullScreen, new Point(50, _window.getHeight() - 200));

        _chkIsFullScreen = new BCheckBox("");
        _chkIsFullScreen.setPreferredSize(labelHeight, labelHeight);
        _window.add(_chkIsFullScreen, new Point(200, _window.getHeight() - 200));

        int buttonWidth = _window.getWidth() / 4;
        int buttonHeight = 32;

        // Cancel button
        _btnCancel = new BButton("");
        _btnCancel.setPreferredSize(buttonWidth, buttonHeight);
        _btnCancel.addListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (_editSettingsListener != null) {
                    _editSettingsListener.CancelPressed();
                }
            }
        });
        _window.add(_btnCancel, new Point(10, 20));

        // OK button
        _btnOk = new BButton("");
        _btnOk.setPreferredSize(buttonWidth, buttonHeight);
        _btnOk.addListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (_editSettingsListener != null) {
                    _editSettingsListener.SaveSettings();
                }
            }
        });
        _window.add(_btnOk, new Point(_window.getWidth() - 10 - buttonWidth, 20));

        _window.center();
    }

    @Override
    public ArrayList<String> getTranslationTags() {
        ArrayList<String> translationTags = new ArrayList<String>();
        translationTags.add("EditSettings.Resolution"); // Resolution:
        translationTags.add("EditSettings.Depth");     // Depth:
        translationTags.add("EditSettings.Frequency");    // Frequency:
        translationTags.add("EditSettings.FullScreen");    // Full-Screen:
        translationTags.add("EditSettings.Cancel");    // Cancel
        translationTags.add("EditSettings.OK");    // OK
        return translationTags;
    }

    @Override
    public void setTranslationPhrases(ArrayList<String> translationPhrases) {
        _lblResolution.setText(translationPhrases.get(0));
        _lblDepth.setText(translationPhrases.get(1));
        _lblFrequency.setText(translationPhrases.get(2));
        _lblFullScreen.setText(translationPhrases.get(3));
        _btnCancel.setText(translationPhrases.get(4));
        _btnOk.setText(translationPhrases.get(5));
    }

    @Override
    public void addEditSettingsListener(IEditSettingsListener editSettingsListener) {
        _editSettingsListener = editSettingsListener;
    }

    @Override
    public void setResolutionOptions(List<String> resolutions) {
        _cboResolution.clearItems();
        for (String resolution : resolutions) {
            _cboResolution.addItem(resolution);
        }
    }

    @Override
    public void setDepthOptions(List<String> depthValues) {
        _cboDepth.clearItems();
        for (String bit : depthValues) {
            _cboDepth.addItem(bit);
        }
    }

    @Override
    public void setFrequencyOptions(List<String> frequencyOptions) {
        _cboFrequency.clearItems();
        for (String frequency : frequencyOptions) {
            _cboFrequency.addItem(frequency);
        }
    }

    @Override
    public void setResolution(String resolution) {
        _cboResolution.selectItem(resolution);
    }

    @Override
    public void setDepth(String depth) {
        _cboDepth.selectItem(depth);
    }

    @Override
    public void setFrequency(String frequency) {
        _cboFrequency.selectItem(frequency);
    }

    @Override
    public void setIsFullScreen(boolean isFullScreen) {
        _chkIsFullScreen.setSelected(isFullScreen);
    }

    @Override
    public int getWidth() {
        int width = 0;

        String resolution = (String) _cboResolution.getSelectedItem();
        int xPos = resolution.indexOf('x');
        if (xPos > 0) {
            width = Integer.parseInt(resolution.substring(0, xPos));
        }

        return width;
    }

    @Override
    public int getHeight() {
        int height = 0;

        String resolution = (String) _cboResolution.getSelectedItem();
        int xPos = resolution.indexOf('x');
        if (xPos > 0) {
            height = Integer.parseInt(resolution.substring(xPos + 1));
        }

        return height;
    }

    @Override
    public int getDepth() {
        return Integer.parseInt((String) _cboDepth.getSelectedItem());
    }

    @Override
    public int getFrequency() {
        return Integer.parseInt((String) _cboFrequency.getSelectedItem());
    }

    @Override
    public boolean isFullScreen() {
        return _chkIsFullScreen.isSelected();
    }

    @Override
    public void showErrorDialog(String title, String message) {
        DialogBoxUtil.createErrorDialogBox("dialog1", message);
    }
}
