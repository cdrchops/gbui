package atechnique.views.interfaces;

import java.util.ArrayList;

public interface IGameStateView {
    ArrayList<String> getTranslationTags();

    void setTranslationPhrases(ArrayList<String> translationPhrases);

    void activate();

    void deactivate();
}
