## 5.1 Required Reading ##
Many times, academic articles will be sprinkled with a long series of footnotes throughout the text, referring the user to other articles that pertain to what the author is saying.  In this article, I’m going to reverse the article by listing reference works first, before I say anything that’s at all original.  The reason for this is simple: the work I’ve done is built on the foundations laid by many other people, and I believe understanding where I got my ideas from will help better explain what I’ve done.

### 5.1.1 Model-View-Controller/Model-View-Presenter/State-View-Controller ###
This is a software-engineering design pattern that goes by a variety of names, but what they have in common is a formal separation of data (“model” or “state”), the way that data is presented to the user (“view”), and the way the user interacts with that data (“controller” or “presenter”).  It’s such a widely covered topic that any Google search will turn up enough references to keep you busy reading for the next year, so I’m not going to include links to any specific articles.  Besides, the next item takes this general pattern, and puts enough meat on the concept to make it very useable.

### 5.1.2 Presenter-First ###
This is a variation of the MVC/MVP/SVC pattern published by [Atomic Object](http://www.atomicobject.com/).  Their include a great number of articles and examples explaining it on their [site](http://www.atomicobject.com/pages/Presenter+First), and I definitely recommend you spend some time studying them.  Just in case you don’t, here’s my quick summary: in Presenter-First, the presenter knows about the model and the view only through interfaces.  The presenter is constructed with an instance of an object that implements the view interface, and an instance of an object that implements the model interface.  The presenter interacts with the view and model by calling methods of their interfaces.  Neither the view nor the model know anything about each other or the presenter; they can interact with the presenter only by firing events.  This architecture makes it very simple to test the presenter (assumed to contain the bulk of the program’s logic) by simply creating test mock objects that implement the appropriate interface.

### 5.1.3 Inversion-of-control/Direct-Injection ###
I’m not going to spend any time explaining either of these topics; I’m just going to direct you to what I consider to be **_the_** classic article on the topic: [Inversion of Control Containers and the Dependency Injection pattern](http://martinfowler.com/articles/injection.html).

Once again, don’t get too hung up on understanding everything in this article; if you do a quick skim to get the basic ideas, you’ll be good to go.

### 5.1.4 Game States ###
When I started working with jMonkeyEngine, this was my understanding of game states: [Managing Game States in C++](http://gamedevgeek.com/tutorials/managing-game-states-in-c/).  I was led to this article during my work with Ogre3D and this article: [Managing Game States with Ogre](http://www.ogre3d.org/wiki/index.php/Managing_Game_States_with_OGRE).  Yet again, don’t obsess with the details, just understand that these articles use a different understanding of game states than jME.  These articles use the more traditional software engineering definition of a states as being mutually exclusive of each other; only one state is active at a time.

### 5.1.5 StandardGame ###
Hopefully you’re already familiar with StandardGame and it’s definition of GameStates (that are different from the definition of GameState in the previous reference).  If not, there are a number of tutorials on the jME Wiki:
  * [StandardGame, GameStates, and Multithreading (A New Way of Thinking)](http://www.jmonkeyengine.com/wiki/doku.php?id=standardgame_gamestates_and_multithreading_a_new_way_of_thinking)
  * [SimpleGame to StandardGame - An effort to gain mindshare by darkfrog](http://www.jmonkeyengine.com/wiki/doku.php?id=simplegame_to_standardgame)

## 5.2 “A Technique” ##
Why do I call this “A Technique”?  Blame it on my military schooling and the instructors that say “Gentlemen, what I’m about to teach you is merely ‘A Technique’”.  What it means is that I’m going to show you one way of doing something.  I’m not going to claim that it’s the “One True Way”, or that it’s the best way.  I’m not even going to claim that it’s a correct way.  I will say that it “Works For Me”.  Try it for yourself and learn from it.  I can almost guarantee there will be parts of it that you don’t like.  I hope there are parts you do like but it won’t hurt my feelings if you don’t.  Take what you like, find your own way for the parts you don’t, and move forward.

## 5.3 What we’re going to create ##
Here’s the premise of this sample application: we’re going to create a 3D, online version of a tabletop [miniatures wargame](http://en.wikipedia.org/wiki/Miniatures_wargame).  One player will start the application and select a campaign.  A campaign is a series of related battles or scenarios, the results of each one influencing the others.  For this game, campaigns and their scenarios will either ship with the game, players can purchase them as expansion packs, or they can create their own (if an appropriate editor is provided).  The player can either start a new campaign from the beginning, or resume one that they have already progressed in.  After the campaign and scenario have been selected, the player will start a server connection.  They will specify what their username will be, what port the server will listen on for incoming connections, and which faction (defined in the scenario) they will play in the upcoming battle.  The game will then load and display the appropriate terrain and wait for incoming connections.  Another player will start the game, connect to an existing game, and enter the server address and port, and their username.  Once their client connects to the server, they will select which faction they will play.  Now that everyone has connected and sides chosen, the players will play their game.  At any point, they can save the game where they are, to resume at a later time.

## 5.4 How we’re going to do it ##
I’ve separated my code and the media resources so I can hopefully update the code at a later date and save you the bandwidth of downloading the media again.  I’m not going to go into a lot of detail on setting up the project here.  Refer to the chapter on setting up GBUI and understand this project is practically a parallel to that.  Once that’s been done successfully, your Eclipse project should resemble the following:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.1.jpg](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.1.jpg)

### 5.4.1 Main ###
```
package atechnique;

import com.jme.renderer.ColorRGBA;
import com.jmex.game.StandardGame;

import atechnique.classfactory.ClassFactory;
import atechnique.game.state.GameManager;

public class Main {
	private static StandardGame _game;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {		
		_game = new StandardGame("ATechnique", StandardGame.GameType.GRAPHICAL, ClassFactory.getGameSettings());
		_game.setBackgroundColor(ColorRGBA.darkGray);
		_game.start();
		
		ClassFactory.initialize(_game.getCamera());
		
		GameManager.getInstance().start(ClassFactory.getMainMenuPresenter());
	}

    public static void exit() {    	
        _game.shutdown();
    }

    public static void changeResolution() {
    	_game.recreateGraphicalContext();
    }
} 
```

Let’s look at what we have here: at first glance, this looks like a fairly standard (no pun intended) start of a StandardGame application.  We declare a class-level StandardGame instance, then create it, set a background color, and start the game.  But what’s with the references to ClassFactory and GameManager?  I’ll go into more detail later, but for now let me just say that ClassFactory is my implementation of a Direct Injection container.  It is a Singleton object that provides access to all of the shared objects that will be referenced throughout the application.  More than just providing access to these objects, the ClassFactory is responsible for creating them.  The GameManager is my implementation of my game state machine, as described in the above required reading.  The call to the start method is initiating the state machine.  The two public methods (`exit` and `changeResolution`)  are here because it’s the only way I could find to provide access to the instance of StandardGame.  I don’t really like it, it doesn’t feel right, but it is what it is.

### 5.4.2 ClassFactory ###
For reasons of space, I’m not going to reprint the entire class here.  Follow along in the source as I describe each item:

`package atechnique.classfactory;`

The package declaration.  If you found the source file, you already knew this.  If not, now you do! :-)
I don’t think there’s anything in the imports section or class declaration that needs to be explained.  Just after the declaration for the class are the declarations for all the class member variables.  You may notice that the vast majority are `private` and `static`.  There is one that is `public`, but I generally believe that `public`, and even `protected`, member variables are a bad idea.  When I violate this guideline, it’s generally a shortcut to what I consider to be a “proper” solution, and I usually regret it later.  This `public` variable is no exception: I haven’t yet regretted it, but it’s definitely a shortcut.

Before I go further, let me explain the principle behind this entire class: in the required reading section, I referred to Inversion-of-Control and Direct-Injection.  This class is my implantation of a Direct Injection container; it’s job is to construct objects and make them available to the application.  It also lets me use the concepts of global variables and singleton objects, while also avoiding implementing the objects I need as singletons, so I can actually use automated unit tests on them.  (In case you haven’t already encountered the issue, singleton objects are notoriously difficult to test with automated unit test suites).  I’ve used Spring.NET in a previous application, but ended up ripping it out and replacing it with a class similar to what I have here.  Using Spring just required too much overhead, and caused problems during debugging.  I prefer to have my mistakes pointed out to me by the compiler, rather than at run-time.  When other developers (not familiar with Spring, or how to configure it) tried to work on the application, they just got confused and stumped.  So maybe I’m just not smart enough to see the benefit of a container that puts together objects at run-time based on a configuration file, even though I know exactly how I want the objects constructed at compile time and I won’t ever change that,  but it wasn’t worth the hassle.  I’m still not really happy with the name of this class but I haven’t found a better one yet, so it stands.

Now to continue with the code: with this type of container, I have some control over exactly when and how I create all the objects I need, but eventually they do need to be created before they can be used.  One simple technique would be to create all the objects in the constructor of the class.  But this doesn’t work so well with a bunch of `static` members.  One way to work around this is to include an `Initialize` method that can be called to do all necessary creation.  This gives us explicit control over when we do that creation.  But here was the problem I ran into: I knew that the game settings was going to be an object that I wanted to have access to through this container.  I also knew I wanted to use the StrategicHandler in my main game state.  StrategicHandler requires an instance of the camera when it is created.  The StandardGame creates the camera, but requires a reference to the game settings when it is created.  So if the `initialize` method was going to create the StrategicHandler object, it would need a reference to the camera created by the StandardGame.  We should call `initialize` after we create the StandardGame.  But since StandardGame requires the game settings, now we need to call `initialize` before we create the StandardGame.  Since we can’t call `initialize` both before and after we create the StandardGame, we have a problem.  The solution I chose was to use a static initializor to create the game settings so we could use that when we create the StandardGame, and we then use the camera created to call `ClassFactory.initialize()`.  This explains the static initializor you see in the ClassFactory declaration, as well as most of the Main method.

In the `initialize` method, you can see a lot of reference to the mouse and cursors.  The reason for this is that I wanted to control the appearance of the mouse cursor consistently throughout the entire application so I don’t have to recreate the same logic all through the application.

Finally, we get to some real meat.  The rest of the `initialize` method creates the actual states (in the StandardGame sense) we will use in our application.  Note that for each triplet, the declarations of the view and model are for interfaces, but here we specify concrete objects that implement those interfaces.  We then pass those to the constructor of the presenter.  Finally, we add the new state to the GameStateManager, but this is typical for any StandardGame application.  Why do I specify the complete path for the view object when an `imports` statement could have done the same thing?  Because this is where I have changed the actual view class.  When learning jME GUIs, I started with FengGUI, moved to JMEDesktop, then to a custom hud implementation, and finally GBUI.  By creating different packages, I could (and did!) have a view class for each of the above libraries, all implementing the same interface.  As I moved from one library to another, I could do it one view at a time simply by modifying the class path to the view I wanted.

The rest of the class is fairly straight-forward: public accessors to the private member variables.  The notable exception is at the end of the class:
```
	public static InGamePausePresenter getInGamePausePresenter() {
		return _inGamePausePresenter;
	}
	
	public static InGameState getInGameState() {
		if (_inGameState == null) {
	        _inGameState = new InGameState(_camera);
	        GameStateManager.getInstance().attachChild(_inGameState);
		}
		
		return _inGameState;
	}
```

You’ll notice that `getInGamePausePresenter` merely returns the class that we created in the `initialize` method.  On the other hand, the `_inGameState` object is not created in the initialize method: rather, the `getInGameState` method checks if the `_inGameState` method has been created yet, and creates it if it has not.  I think of this as “lazy” instantiation, deferring the creation of the object until it is first needed.  The trade-off of lazy instantiation versus the `initialize` method is merely a matter of when we will make the user wait for the object(s) to be created: at the game’s startup, or during play of the game.  I started with lazy instantiation for all objects, but didn’t like the delay during menu navigation, so I create the GUI states when the game starts up.  Since I expect the `_inGameState` to have to load data specific to the selected scenario, which won’t be known until the user selects it, I use lazy instantiation for that object.

### 5.4.3 GameManager ###
This class is the implementation of the state machine, using the concept of mutually-exclusive states.  I know the multiple definitions of “Game State” is confusing, and I apologize for it, but I’ll try to make it clear which type I mean when I can.  This class is meant to be a singleton object and is implemented as such; hence the `getInstance` method.  I’m sure that simple static methods would work just as well, but this is a remnant of this class’s original implementation in C++.  Also, if you’re wondering why this class is implemented as a singleton, rather than being created by the ClassFactory, my stock answer is going to be that it serves as an example of the two different techniques.  Yeah, yeah, that’s the ticket…

Anyway, we have created an interface called `ATechniqueGameState` as follows:
```
public interface ATechniqueGameState {
	void enter();
	void exit();
	void pause();
	void resume();
}
```
If you need any more explanation for this interface, refer back to the “Managing Game States using C++” article.

The GameManager class declares a stack of objects that implement the ATechniqueGameState interface.  The object at the top of the stack represents the current state (in the State Machine sense) of the application.  This class lets us specify a new state for the application, and it will automatically call the appropriate methods of the new and old states to ensure all transitions are done properly.  pushState lets us change to a new state, and then we can call popState to return to the original state.  We can (and will) use this feature when we have a state that we will change to from more than one other state.  This is probably a good time to describe the different game states we’ll be using during this game:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.3.1.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.3.1.gif)

Note that if the current state is MainMenu, and we `pushState` to the EditSettings state, `popState` will return us to MainMenu.  If the current state is InGamePause and we `pushState` to the EditSettings state, `popState` will return us to InGamePause.

### 5.4.4 Translator ###
Maybe I’m getting ahead of myself, but I’d like to think that my game will be so popular that it will be played be people all over the world, including those that don’t speak English.  Since I know that localization of strings can be a significant effort when done as an afterthought, I want to include the capability from the beginning.  Accordingly, I’m going to create an object that will be responsible for providing me with the correct text, without me needing to know what the current language is.

```
package atechnique.views.interfaces;

import java.util.ArrayList;

public interface ITranslator {
	ArrayList<String> getTranslatedPhrases(ArrayList<String> translationTags);
}
```

This interface defines a single method.  This method expect to receive an array of strings that represent the tags to be translated.  This method will return an array of phrases in the appropriate language.  My current implementation is as follows:

```
package atechnique.classfactory;

import java.util.ArrayList;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import atechnique.views.interfaces.ITranslator;

public class Translator implements ITranslator {
	private Locale _currentLocale;
      private ResourceBundle _messages;

	public Translator(String language, String country) {
        _currentLocale = new Locale(language, country);
        _messages = ResourceBundle.getBundle("atechnique/data/languages/MessagesBundle", _currentLocale);
	}

	@Override
	public ArrayList<String> getTranslatedPhrases(ArrayList<String> translationTags) {
		ArrayList<String> translatedPhrases = new ArrayList<String>();

		if (translationTags != null) {
			for (int idx = 0; idx < translationTags.size(); idx++) {
				try {
					translatedPhrases.add(_messages.getString(translationTags.get(idx)));
				} catch (MissingResourceException e) {
					// Just add the tag surrounded by <>
					translatedPhrases.add("<" + translationTags.get(idx) + ">");				
				}
			}
		}
		
		return translatedPhrases;
	}
}
```

This uses the ResourceBundle feature of Java.  Another implementation might use a database, or even a web service.  The implementation really isn’t important.  When testing this, I found that if I tried to fetch a phrase that didn’t exist in my current bundle, and exception would be thrown.  I didn’t like that, so I modified it to return the tag surrounded by less-than/greater-than symbols so I could know I needed to add the tag and phrase to my bundle without needing to do so immediately.

### 5.4.5 Main Menu ###
When the game is started, the user will be presented with the following screen:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.5.1.jpg](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.5.1.jpg)

Unfortunately I can’t get the mouse cursor to appear in this screen-shot, but it is hovering over the “Exit” button, causing the text to be outlined, and a tooltip to be displayed.  Note that the “Connect to a Game” button is disabled (because I haven’t implemented that yet).

In order to explain how this is created, let me display the following class diagram:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.5.2.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.5.2.gif)

Note the following items:
  * GameStatePresenter provides a default implementation of the ATechniqueGameState interface
  * MainMenuPresenter inherits from GameStatePresenter, making it a GameState (in the State Machine sense) that can be transitioned by the GameManager
  * GameStatePresenter has a reference to IGameStateView, and MainMenuPresenter has a reference to IMainMenuView.  When the MainMenuPresenter is created, it is given a reference to a MainMenuView object
  * Since GbuiGameState inherits from BasicGameState, MainMenuView is a GameState (in the StandardGame sense) that can be activated and deactivated by the StandardGame GameStateManager
  * In the required reading, I mentioned that a presenter will call methods on the view’s interface, and the view will fire events that will be caught by the presenter.  The IMainMenuListener represents “events” that are “fired” by the view, since I don’t know how to do a true event/handler implementation in Java as I do in C#.  The presenter implements the IMainMenuListener interfaces and calls addMainMenuListener on the view to hand a reference to itself

When we change to a new GameState (in the State Machine sense), the `enter` method (defined by the `ATechniqueGameState` interface) of that state is called.  The default implementation by `GameStatePresenter` is to call the `activate` method of the IGameStateView interface.  GbuiGameState provides a default implementation of `activate` by calling `setActive(true)`, a GameState (in the StandardGame sense) method.  By putting this function in GbuiGameState, we can create each view without worrying about these details.

If you look at the code in the constructor of `MainMenuView`, you’ll see it creates a GBUI window and controls, and displays them.  It responds to input events by calling methods of the `IMainMenuListener` interface, thus keeping all game and control logic in the presenter and out of the view.  When this view implements the `getTranslationTags` and `setTranslationPhrases` methods of the `IGameStateView` interface, the code merely needs to return a list of tags to be translated, and then display the returned text on the appropriate control, respectively.  I believe the code is fairly self-explanatory, but then me know if you have any questions about it.

A note about the background image: I know I could have defined it in the style class definition.  However, I didn’t want the image in every window I created, and I was too lazy to define a custom style class.  Since that is probably a “better” solution, it will probably end up there, but it’s not there now.

You may be wondering where the Model portion of the MVP triad is: simply put, it ended up being an empty interface and I didn’t anticipate it ever having anything to do.  Rather than dogmatically adhering to the architecture, I decided I would be better off just removing the interface and it’s implementation.

### 5.4.6 Select Campaign ###
![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.6.1.jpg](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.6.1.jpg)

I know what you’re thinking: it looks a little incomplete.  That’s because the implementation is very specific to my game, but it’s not critical for the architectural concepts I’m trying to explain but it might have distracted and confused you.  So just understand that on this screen, I’m going to present a scrolling list of all the campaigns in the appropriate directory.  When the user clicks on a campaign, I’ll present a narrative description, and display the “progression tree” of all the scenarios in the campaign, and how they are connected.  Each campaign will have a flag stored indicating if they’re new, or in-progress.  If new, the “Start New Game” button will be enabled.  Oterwise, the “Continue Game” button will be enabled.  For the purposes of this tutorial, this is just another state (in the State Machine sense) that the user can view and interact with.

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.6.2.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.6.2.gif)

Here’s how all these classes and interfaces work together:
  * ISelectCampaignListener again represents the events that the view would fire and the presenter would handle.  Instead, these are methods of the presenter that the view will call
  * When the SelectCampaignModel is created, it will automatically compile a collection of the available campaigns, and all their details
  * When the Presenter is created, it will get the collection of campaign from the model, and push them to the view for display
  * When the user selects a campaign in the list, the view will call the `campaignSelected` method of the presenter
  * The presenter will get then push the description to the view for display

I’m expecting that when you first saw the class model for the Main Menu, the number of interfaces and states seemed somewhat daunting.  Hopefully now you can see that many of them can essentially be ignored once you understand what they are doing, and you can concentrate on the specific implementation of your game.

### 5.4.7 Start Server ###
![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.7.1.jpg](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.7.1.jpg)

### 5.4.8 InGame ###
![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.8.1.jpg](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.8.1.jpg)

This state is essentially the StrategicHandler demo, converted to a StandardGame GameState.  I don’t have the mouse cursor working perfectly, but most of the functionality is here, or at least enough to showcase jME.

### 5.4.9 InGamePause ###
![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.9.1.jpg](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.9.1.jpg)

When the player hits the Escape key, I display this menu.  I wanted to somehow make it obvious to the user that the game is paused and disabled, and I did this by setting the GBUI window to the size of the entire display and then applying an alpha state so it looks faded.

### 5.4.10 Edit Settings ###
Called from Main Menu:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.10.1.jpg](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.10.1.jpg)

Called from InGame:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.10.2.jpg](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure5.4.10.2.jpg)

The significance of this state (in the State Machine sense) is that it gets called from two other states, and returns to the state it was called from when closed.  Because the background is transparent, we get a different appearance depending on where it is called from.  Personally, I don’t think I like that, but you might.  Also, notice that when called from the InGame state, we haven’t set the alpha blending on the rest of the display.  Now we’ve confused the player.  Oh well, we’ll just put it on the “issues” list and move on. :-)

## 5.5 What’s Next? ##
Here’s what’s on my list of next steps, for this tutorial and for my real project:
  * Networking: you may have notices that even my limited example only covered the side of the player creating the server, but not the player connecting to a server.  I want to introduce real networking and a “chat” application.  This will also introduce the use of GBUI components at the same time as “regular” jME objects.  I believe this is going to required at least some slight modification of my architecture, but time will tell.
  * Try this: notice that the windows are centered on the display.  Now use the Edit Settings screen to change the resolution.  Notice the windows aren’t centered any more.  This is because when the screens/views are created, they use the display setting current at that time.  Many views are sized to be proportional to the display resolution.  Currently we have no mechanism to rebuild them when the resolution changes.  I think the application would be a little more “well behaved” if it did this, but this is a relatively low priority for me since it is non-critical eye-candy and there is a workaround: restart the app.
  * Fix the mouse cursor problem in the InGame state
  * Put real meat on the application: load real campaigns with real scenarios and let players play real games

Good luck!