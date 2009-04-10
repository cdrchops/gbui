package atechnique.classfactory;

import atechnique.game.state.ATechniqueGameState;
import atechnique.views.interfaces.IGameStateView;

public class GameStatePresenter implements ATechniqueGameState {
    protected IGameStateView _view;

    protected GameStatePresenter(IGameStateView view) {
        _view = view;
        _view.setTranslationPhrases(ClassFactory.getTranslator().getTranslatedPhrases(_view.getTranslationTags()));
    }

    @Override
    public void enter() {
        _view.activate();
    }

    @Override
    public void exit() {
        _view.deactivate();
    }

    @Override
    public void pause() {
        _view.deactivate();
    }

    @Override
    public void resume() {
        _view.activate();
    }
}
