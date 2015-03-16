# 1.	Getting started with GBUI in Eclipse #
## 1.1 Caveat Emptor ##
In the words of “standtrooper”: “When BUI was written BSS was, as best I can tell a “streamlined” or thinner version of CSS. It never occurred to me, a web developer, that there would be Swing developers who don't know CSS (just as there are Swing developers who don't think that there are web developers that don't think like them  ;-).”

I don't consider myself to be either a web developer, or a Swing developer. I've used CSS and am generally familiar with the concept, if not the implementation. I've also used Swing, but not much in the last 9 years, so my recent work with JMEDesktop was a crash-course getting reacquainted with that. So if you don't know CSS and you don't know Swing, don't worry: that knowledge will probably help, but I think I've got it figured out so it can't be that hard. :-)

## 1.2 Tools ##
As a starting point, I'm going to assume the following:
  * you already have Eclipse installed and running (you can create, compile, and execute Java projects)
  * you have a project named “jME” in your Eclipse workspace. This project is a compiled and running version of the jME 2.0 source code. If you have any problems getting to this point, reference some of the other available “Setting up Eclipse to build jME 2” tutorials.
## 1.3 Getting the GBUI source ##
The [Download](http://code.google.com/p/gbui/downloads/list) tab of the GBUI site offers some pre-compiled jars you can download for GBUI. However, I want to work with the source code because:
  * it's the most up-to-date
  * it contains the source code for the examples
  * when debugging, I can step into the source to help determine the root cause of any errors I encounter

When working with SVN repositories, I prefer to use [TortoiseSVN](http://tortoisesvn.tigris.org/). I know I've heard people rave about Subclipse, but I never had much luck with it, and I prefer the control that TortoiseSVN gives me. So if you haven't already done so, download and install TortoiseSVN. (Go ahead; I'll wait :)

After TortoiseSVN has been installed, use Windows Explorer to view your Eclipse workspace directory. Mine is C:\eclipse\workspace. Create a new folder there called “gbui”. Right-click on that newly-created directory and select “SVN Checkout” (notice how TortoiseSVN nicely integrates itself?). In the “URL of repository” field of the Checkout dialog, enter “http://gbui.googlecode.com/svn/trunk” (without the quotes). The “Checkout Directory” should already be the directory you just created (“C:\eclipse\workspace\gbui” in my case), but correct it if it's not.

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.3.1.jpg](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.3.1.jpg)

Click the “OK” button, then take a break while it downloads the source. The checkout status window will update as each file is downloaded so you can verify that something is happening. Wait until the last message is “Completed” and the “OK” button is enabled.

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.3.2.jpg](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.3.2.jpg)

Click “OK” to close the dialog.

## 1.4 Installing the Groovy plug-in ##
Note: to save yourself some time, do this step while you’re waiting for the gbui source to download.

In Eclipse, navigate to Help | Software Updates.  Select the “Available Software” tab at the top, then the “Add Site” button.  Enter the following URL: http://dist.codehaus.org/groovy/distributions/update/ and the press “OK”.  Once the site has loaded, click on the plus sign to the left of the GroovySite to expand it, then expand the “Uncategorized” item.  Check the box to the left of “GroovyFeature”.

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.4.1.jpg](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.4.1.jpg)

The “Install…” button in the upper-right of the window should become enabled.  Press it and follow the instructions.  This may require rebooting your computer, or at least Eclipse, after the installation.

## 1.5 Compiling the project ##
If you haven't already done it, open Eclipse. Create a new Java project. The project name must be the same as the name of the folder that you just downloaded the source code to. Case matters! Click the “Create project from existing source” option, and press the “Browse” button to navigate to the directory you created. Don't go any lower in the hierarchy (to “bin”, “lib”, “src”, etc). Press the “OK” button.

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.5.1.jpg](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.5.1.jpg)

Press “Next”. Eclipse should then display all the sub-directories and files that are in the root directory. If you only see a single “src” folder, the name of the project you entered probably didn't match the directory the files are stored in, and Eclipse won't create the project properly.

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.5.2.jpg](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.5.2.jpg)

Once everything looks right, select the “Projects” tab. Add the “jME” project that you already have in your workspace (remember the assumptions up above in the “Tools” section?) Select the “Libraries” tab and remove the reference to all the “jme\*” files and the “lwgjl.jar”.  This is so we can keep this project in sync with the same files as our jME project.

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.5.3.jpg](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.5.3.jpg)

Press the “Add JARs” button and navigate to “jME/lib/lwjgl”. Select “lwjgl.jar” and press “OK”. Select the plus-sign to the left of the “lwjgl.jar”, then select “Native Library Location”. Select the “Edit” button, then the “Workspace” button. Select “jME/lib/lwjgl/native/win32”.

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.5.4.jpg](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.5.4.jpg)

Press “OK” twice. Finally, press the “Finish” button and give Eclipse some time to compile the project (assuming you have auto-compile enabled). Check the “Problems” tab for any errors or warnings. Ignore any warnings, but there shouldn't be any errors.

### 1.5.1 Troubleshooting ###
My experience was that Eclipse usually does a pretty good job of automatically detecting that the project uses Groovy, and automatically enables what is needed for a successful compile.  However, I did run into two issues that may help.  When the compile failed, it was because the BStyleSheet.groovy file was not being properly compiled, and all the classes that referenced BStyleSheet couldn’t find  the definition.  If that appears to be the error you’re seeing, try the following:
  * 1)	Right-click on the gbui project.  You should see an option for “Groovy” right above “Properties” at the bottom.  Select “Groovy”.  You should see an option for “Add Groovy Nature”.  This option should be disabled, indicating it has already been added.  If this option is enabled, select it.
![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.5.1.1.jpg](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.5.1.1.jpg)
  * 2)	Right-click on the gbui project and select “Properties”.  Select the “Groovy Project Options” line.  Ensure the build path is set to “bin-groovy”.
![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.5.1.2.jpg](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.5.1.2.jpg)

## 1.6 Running the tests/demos ##
I'm not a fan of full-screen applications (especially when trying to debug), so I wanted a chance to modify the default properties. In your “gbui” project, open the “src/java”, then the “com.jmex.bui.tests” package. Double-click on “AllDialogTests.java” to open it. Modify it's main method to the following:
```
  public static void main(String[] args) {
      Logger.getLogger("com.jmex.bui").setLevel(Level.WARNING);
      
      AllDialogsTest test = new AllDialogsTest();
      test.setConfigShowMode(ConfigShowMode.AlwaysShow);
      test.start();
  }
```
Save the changes.

This next step seems very kludgy, and I hope there's a more elegant solution that someone else can point me to, but for now I'm taking the path of pragmatism: it works for me, and I don't know a better way to do it.
  * right-click on the “rsrc” folder and “Copy”
  * right-click on the “src/java” folder and “Paste”
  * right-click on the “rsrc” folder and “Delete”. Yes, you're sure.
This is the only way I've found that will let you modify the GBUI styles inside Eclipse, and still use those changes files when running the tests.

Finally, right-click on the “AllDialogsTest.java” and run as a Java application. You'll be presented with the default jME properties configuration. Un-select the “Fullscreen?” checkbox and press “OK”. You should be presented with a dialog in the middle of the window. Move the dialog to see more underneath it.

Congratulations! You've just gotten GBUI to work, and can now run all the tests to see different examples of what it can do.

## 1.7 Building a jar file ##
As I mentioned at the beginning, I prefer to work with the latest version of the source, rather than a pre-built distribution.  However, the reality is that once I have the source downloaded and working, I usually ignore that code while I work on whatever application I’m writing that uses the library.  Everything works well until I run into a bug that’s fixed in a later version than I have, or a feature is added, or whatever.  So I update the source of the library, and then the compile fails.  Now my “real” application is dead in the water.  My solution to this is to download the source of the library, and if it compiles, build a jar that I use for developing against.  Then, when I update the source of the library, I can continue developing against the jar if the latest source doesn’t compile for some reason.  If the updated code does compile, then I change the reference in my project from the jar of the library to the actual project containing the source of the library.  If my “real” application still runs and behaves as expected, I build a new jar and develop against that.  If any type of error or unexpected behavior happens, I can either dig into the library to see what changed, or I can revert to the jar.

To create a jar of the GBUI library, just right-click on the build.xml file in the root directory of the source code.  “Run As…” “2 Ant Build…”.

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.7.1.jpg](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.7.1.jpg)

Select the “dist” option.

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.7.2.jpg](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure1.7.2.jpg)

Press the “Run” button and watch the console.  If the build is successful (which it should be), you will now have a jme-bui.jar file in your gbui\dist directory that you can reference from any other application without worrying that changes to the gbui source will break anything before you’ve had a chance to test it.