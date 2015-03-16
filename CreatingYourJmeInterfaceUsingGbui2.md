## 2.1 Orientation ##
## 2.2 Component Hierarchy (or, "Why Understanding Swing Will Be Helpful") ##
As a developer, when I started working with GBUI, my primary interest was in what the library can offer me; what widgets are available? What events can they respond to? How easy is it to use? From that perspective, I can say that the library offers an excellent selection of widgets, and the implementation reminds me of Swing. At the top is BComponent (think “JComponent”), from which everything derives.

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.2.1.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.2.1.gif)

Figure 2.2.1: This shows BComponent down to some of the more common widgets such as text components, labels, buttons, menu items, combo box, etc.

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.2.2.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.2.2.gif)

Figure 2.2.2: This shows BContainer deriving from BComponent, and then some of the more common container-type widgets. Again, think of parallels to Swing components with similar names.

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.2.3.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.2.3.gif)

Figure 2.2.3:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.2.4.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.2.4.gif)

Figure 2.2.4: This shows the variety of Window and Dialog components that derive from BContainer.

Note: a BWindow may only be added to BRootNode; it may not be the child of any other component.

## 2.3 Style Hierarchy (or, "Why Understanding CSS Will Be Helpful") ##
If you've looked at the GBUI source at all, I'm sure you've seen the `*`.bss files that define how your GUI will look. You can see widget types, and some obvious properties that relate to appearance: font size, background image, etc. When GBUI renders each component on the screen, it decides how each widget should look based upon it's component type, and it's state.

### 2.3.1 States ###
“State” is an easy-to-understand concept that is used to describe what the component is doing: BComponent defines three states common to all widgets:
  * Disabled: most components that are disabled have a darker than normal appearance used to indicate that the user can not interact with those components
  * Hover: a component is in this state when the mouse cursor is within the bounds of the component. Often this is used to give a visual cue to the user that pressing the mouse button will do something
  * Default: this state applies to any component that is not disabled, and the mouse is not hovering over it.

### 2.3.2 Styles ###
A component's “Style” is determined by the values of the properties for that component. The properties that can be set, their format and allowed values, and the defaults if that property is not explicitly set are:

| Name: | **background** |
|:------|:---------------|
| Description: | This property defines what the main body of the component looks like. This may be a solid color, an image, or a blank (which basically means the component is invisible). |
| Format: | type path/color `[scale]` `[insets]` |
| Valid values: | type: image/solid/blank |
|               | path/color:             |
|               | scale: centerxy, centerx, centery, scalexy, scalex, scaley, tilexy, tilex, tiley, framexy, framex, framey  |
| Note: | the second parameter is ignored if the type is “blank” |
|       | the second parameter is a color only if the type is “solid” |
|       | the second parameter is a path only if the type is “path” |
|       | the third parameter is used only if the type is “path”, and is optional.  The default value is scalexy. |
|       | the insets are used only if the third parameter is framexy.  See padding for a description of the expected format. |
| _Examples:_ | **background: image “rsrc/textures/button\_up.png” tilexy;** |
|             | **background: image “rsrc/textures/button\_up.png” framexy 1 2 3 4;** |
|             | **background: solid #00000088;** |
| _Default:_ | n/a? |

| Name: | **border, border-left, border-top, border-right, border-bottom** |
|:------|:-----------------------------------------------------------------|
| Description: |  |
| Format: | thickness type `[color]` |
| Valid values: | thickness: (integer value) |
|               | type: solid/blank |
|               | color: #RRGGBB or #RRGGBBAA |
| Note: | if a border class is defined, it will be used.  If one is not defined, the border will be constructed by compiling the definitions of the individual sides. |
|       | the color parameter will only be read if the type is “solid”. |
| _Examples:_ | **border: 1 solid #FFFFFF;** |
| _Default:_ |  |

| Name: | **color** |
|:------|:----------|
| Description: |  |
| Format: | color |
| Valid values: | color: #RRGGBB or #RRGGBBAA |
| _Examples:_ | **color #BBBBBB;** |
| _Default:_ | n/a? |

| Name: | **cursor** |
|:------|:-----------|
| Description: |  |
| Format: | name |
| Valid values: |  |
| _Examples:_ |  |
| _Default:_ | n/a? |

| Name: | **effect-color** |
|:------|:-----------------|
| Description: |  |
| Format: |  |
| Valid values: |  |
| _Examples:_ | **effect-color: #442288;** |
| _Default:_ | n/a? |

| Name: | **effect-size** |
|:------|:----------------|
| Description: |  |
| Format: | size |
| Valid values: | size: (integer value) |
| _Examples:_ |  |
| _Default:_ | **effect-size: 1;** |

| Name: | **font** |
|:------|:---------|
| Description: |  |
| Format: | family style size |
| Valid values: | family: “Dialog”, “Helvetica” |
|               | style: plain/bold/italic/bolditalic |
|               | size: (integer value) |
| _Examples:_ | **font: “Dialog” plain 16;** |
| _Default:_ | n/a? |

| Name: | **icon** |
|:------|:---------|
| Description: |  |
| Format: | type path/width `[height]` |
| Valid values: | type: blank/image |
| Note: | if the type is “blank”, the second parameter is the width and the third parameter is the height.|
|       | if the type is “image”, the second parameter specifies the path to the image file.  If any error is encountered loading the specified file, the icon will default to blank 10 10. |
| _Examples:_ | **icon: blank 7 7;** |
|             | **icon: image “rsrc/textures/checkbox.png”;** |
| _Default:_ | **icon: blank 10 10;** |

| Name: | **line-spacing** |
|:------|:-----------------|
| Description: | Amount of space to add or remove between lines. |
| Format: | spacing |
| Valid values: | spacing: (integer value) |
| _Examples:_ | **line-spacing: -2;** |
| _Default:_ | **line-spacing: 0;** |

| Name: | **padding** |
|:------|:------------|
| Description: |  |
| Format: | top `[right]` `[bottom]` `[left]` |
| Valid values: |  |
| _Examples:_ | **padding: 5;		// top = 5; right = top; bottom = top; left = top** |
|             | **padding: 3 5;		// top = 3; right = 5; bottom = top; left = right** |
|             | **padding 4 6 2 4;** |
| _Default:_ | **padding 0;** |

| Name: | **parent** |
|:------|:-----------|
| Description: | Establishes an explicit inheritance.  The class being extended must be defined before this one. |
| Format: |
| Valid values: |  |
| _Examples:_ | **parent: titlebutton;** |
| _Default:_ |  |

| Name: | **size** |
|:------|:---------|
| Description: | Overrides the component’s preferred size. |
| Format: | width height |
| Valid values: |  |
| _Examples:_ |  |
| _Default:_ |  |

| Name: | **text-align** |
|:------|:---------------|
| Description: | Specifies the horizontal alignment of text on the component. |
| Format: | alignment |
| Valid values: | alignment: left/right/center |
| _Examples:_ | **text-align: center;** |
| _Default:_ | **text-align: left;** |

| Name: | **text-effect** |
|:------|:----------------|
| Description: |  |
| Format: | effect |
| Valid values: | effect: none/shadow/outline/plain |
| _Examples:_ | **text-effect: outline;** |
| _Default:_ | **text-effect: none;** |

| Name: | **tooltip** |
|:------|:------------|
| Description: | Used to define the style class for the tooltip. |
| Format: | other\_class |
| Valid values: |  |
| Examples: |  |
| Default: |  |

| Name: | **vertical-align** |
|:------|:-------------------|
| Description: | Specifies the vertical alignment of text on the component. |
| Format: | alignment |
| Valid values: | alignment: center/top/bottom |
| Examples: | **vertical-align: top;** |
| Default: | **vertical-align: center;** |

### 2.3.3 Determining a component’s style ###
In “typical” (ie. VB-style) GUI programming, a widget will have a standard look, and then you can modify the appearance of each instance by modifying the text displayed, the back color, etc. The problem with this is that each application made with the same widgets tends to look very similar, since implementing a common look-and-feel across an entire application is no small task. With GBUI, just as with CSS, each widget class (button, window, etc) can be given the same look-and-feel with relatively little work, making it easy to implement an application-specific look-and-feel that is different from another application built with the same widgets. As an added bonus, the “cascading” feature means that if a particular component type does not define a value for a property, GBUI will traverse up the style hierachy looking for the next-lowest component that does define a value. By default, the style hierarchy is the same as the class hierarchy, but that can be modified by explicitly defining the style class a component should use, and/or by using the “parent” property. On top of everything, a “root” style class exists that will be checked after all other components in the hierarchy.

A component’s style is resolved in the following manner:
  * First by looking up the property using the component’s stylesheet class (either the default, or the one explicitly set) and state
  * For certain properties, the interface hierarchy is the climbed and each parent’s stylesheet class is checked for the property in question.  The properties for which this applies are: color, font, text-align, vertical-align
  * Finally, the root stylesheet class is checked (for all properties, not just those for which we climb the interface hierarchy)

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.3.1.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.3.1.gif)

Figure 2.3.3.1:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.3.2.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.3.2.gif)

Figure 2.3.3.2:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.3.3.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.3.3.gif)

Figure 2.3.3.3:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.3.4.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.3.4.gif)

Figure 2.3.3.4:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.3.5.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.3.5.gif)

Figure 2.3.3.5:

### 2.3.4 Styles without classes ###

### 2.3.5 Overriding the default class style ###

### 2.3.6 Windows and their buttons ###

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.6.1.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.6.1.gif)

Figure 2.3.6.1:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.6.2.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.6.2.gif)

Figure 2.3.6.2:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.6.3.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.6.3.gif)

Figure 2.3.6.3:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.6.4.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.6.4.gif)

Figure 2.3.6.4:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.6.5.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.6.5.gif)

Figure 2.3.6.5:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.6.6.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.6.6.gif)

Figure 2.3.6.6:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.6.7.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.6.7.gif)

Figure 2.3.6.7:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.6.8.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.6.8.gif)

Figure 2.3.6.8:

### 2.3.7 Custom style classes ###

### 2.3.8 Scrollbars ###

Figure 2.3.8.1:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.8.1.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.8.1.gif)

Figure 2.3.8.2:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.8.2.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.8.2.gif)

Figure 2.3.8.3:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.8.3.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.8.3.gif)

Figure 2.3.8.4:

![http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.8.4.gif](http://gbui.googlecode.com/svn/wiki/ATechnique/images/Figure2.3.8.4.gif)