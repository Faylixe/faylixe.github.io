---
layout: post
title:  "Menu creation using Swing"
date:   2015-08-08 14:40:28
categories: Java Swing Review
---

This review aims to introduce functional interface applied to a concrete application : creating menu with graphical API [Swing](https://docs.oracle.com/javase/tutorial/uiswing/).

<br>

# Application controller

<br>

For this review purpose, we will consider a simple application that offers a traditional ``File`` menu, with three actions :
``Open``, ``Save`` and ``Exit``.


With [Swing](https://docs.oracle.com/javase/tutorial/uiswing/), each menu item should be connected to the functional interface [ActionListener](http://docs.oracle.com/javase/8/docs/api/java/awt/event/ActionListener.html), in order to perform an associated action. Since method reference could be now interpreted as functional interface, we will consider the ``Controller`` interface which provides a functional method for each action it could handle.


<br>

<script src="https://gist.github.com/Faylixe/0e8a9f92d0791c3a7777.js"></script>

<br>

# Menu builder

<br>

<p align="center">
  <img src="/images/review/menu-creation-using-swing/menubuilder.png" />
</p>

<br>

To build our menu, we will first setup a dedicated class for building a [JMenu](http://docs.oracle.com/javase/8/docs/api/javax/swing/JMenu.html) entry, named ``MenuBuilder``. The following snippet shows our class header. We also made our class implements [Supplier](https://docs.oracle.com/javase/8/docs/api/java/util/function/Supplier.html) interface as it aims to provide a  [JMenu](http://docs.oracle.com/javase/8/docs/api/javax/swing/JMenu.html).

<br>

<script src="https://gist.github.com/Faylixe/b4bc013cb81cefeec83e.js"></script>

<br>

Now in order to fill our menu, we will add a method for creating a menu item instance using a given ``label``, and [ActionListener](http://docs.oracle.com/javase/8/docs/api/java/awt/event/ActionListener.html), which will be added to the target [JMenu](http://docs.oracle.com/javase/8/docs/api/javax/swing/JMenu.html) :

<br>

<script src="https://gist.github.com/Faylixe/4ed4f4f776afb9734066.js"></script>

<br>

# Menu bar builder

<br>

Finally, we need to setup a component which will be in charge of creating a [JMenuBar](http://docs.oracle.com/javase/8/docs/api/javax/swing/JMenuBar.html), and will only contains the two following atttributes :

<br>

* Reference to our application controller (as we will use it to connect item to action).
* Target [JMenuBar](http://docs.oracle.com/javase/8/docs/api/javax/swing/JMenuBar.html) instance that this builder will fill up.

<br>

<div class="alert alert-info">You can notice that the constructor is set as <b>private</b>, only a static factory method would be provided later.</div>

<p align="center">
  <img src="/images/review/menu-creation-using-swing/menubarbuilder.png" class="img-responsive" /></a>
</p>

<br>

Then creating a menu will be as simple as following, we only have to use the previously defined ``MenuBuilder`` class, with method reference from our ``Controller`` instance as [ActionListener](http://docs.oracle.com/javase/8/docs/api/java/awt/event/ActionListener.html) for creating menu entry :

<br>

<script src="https://gist.github.com/Faylixe/9f70951e3501b3d4d960.js"></script>

<br>

Last step, we have to define a static factory method for using our class which will prevent from misusage. Here we only have one menu, but you could add method as much as menu you have.

<br>

<script src="https://gist.github.com/Faylixe/88ca1f0082ef24f29a13.js"></script>

<br>

# Bonus : Item delayed activation

<br>

Our menu is working, but let's improve it. Imagine you want to set the ``Save`` item disabled until a file has been opened. To do so, we will use the method you probably have noticed while looking the UML class diagram for the ``MenuBuilder`` : ``createActivable(String, ActionListener)``.

This method creates a [JMenuItem](http://docs.oracle.com/javase/8/docs/api/javax/swing/JMenuItem.html) by calling the ``createItem(String, ActionListener)`` method, and then returns a [Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html) object :

<br>

<script src="https://gist.github.com/Faylixe/4f823121e559bbb2e12c.js"></script>

<br>

As you can see, the returned [Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html) instance is only enabling the associated menu item, and could then be used in order to activate the entry. To integrate this behavior for the ``Save`` item, we will update our ``Controller`` interface, and make it extends [Consumer](https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html) for [Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html) type :

<br>

<script src="https://gist.github.com/Faylixe/adb10f84b0b5dd85bbd2.js"></script>

<br>

And use the associated ``accept(Runnable)`` method into the file menu creation :

<br>

<script src="https://gist.github.com/Faylixe/1903c2249384d048bfbf.js"></script>

<br>

The ``Controller`` implementation will just have to store the given [Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html) and call it during the ``open(ActionEvent)`` method.

<br>

# Download

<br>

<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> [Controller.java](/download/snippet/menu-creation-with-swing/Controller.java) <br>
<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> [MenuBuilder.java](/download/snippet/menu-creation-with-swing/MenuBuilder.java) <br>
<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> [MenuBarBuilder.java](/download/snippet/menu-creation-with-swing/MenuBarBuilder.java) <br>
