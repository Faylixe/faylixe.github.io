---
layout: post
title:  "Menu creation using Swing"
date:   2015-08-08 14:40:28
categories: Java Swing Review
---

This review aims to introduce functional interface applied to a concrete application : creating menu with graphical API [Swing](https://docs.oracle.com/javase/tutorial/uiswing/).

<br></br>

# Application controller


For this review purpose, we will consider a simple application that offers a traditional ``File`` menu, with three actions :
``Open``, ``Save`` and ``Exit``.


With [Swing](https://docs.oracle.com/javase/tutorial/uiswing/), each menu item should be connected to the functional interface [ActionListener](http://docs.oracle.com/javase/8/docs/api/java/awt/event/ActionListener.html), in order to perform an associated action. Since method reference could be now interpreted as functional interface, we will consider the ``Controller`` interface which provides a functional method for each action it could handle.


<script src="https://gist.github.com/Faylixe/0e8a9f92d0791c3a7777.js"></script>


# Menu builder


To build our menu, we will first setup a dedicated class for building a [JMenu](http://docs.oracle.com/javase/8/docs/api/javax/swing/JMenu.html) entry, named ``MenuBuilder``.


The following snippet shows our class header. We also made our class implements [Supplier](https://docs.oracle.com/javase/8/docs/api/java/util/function/Supplier.html) interface as it aims to provide a  [JMenu](http://docs.oracle.com/javase/8/docs/api/javax/swing/JMenu.html).

<a href="/images/review/menu-creation-using-swing/menubuilder.png">
<img src="/images/review/menu-creation-using-swing/menubuilder.png" class="img-responsive img-thumbnail" style="max-width: 300px;" /></a>

<script src="https://gist.github.com/Faylixe/b4bc013cb81cefeec83e.js"></script>

Now in order to fill our menu, we will add a method for creating a menu item instance using a given ``label``, and [ActionListener](http://docs.oracle.com/javase/8/docs/api/java/awt/event/ActionListener.html), which will be added to the target [JMenu](http://docs.oracle.com/javase/8/docs/api/javax/swing/JMenu.html) :

<script src="https://gist.github.com/Faylixe/4ed4f4f776afb9734066.js"></script>

# Menu bar builder

Finally, we need to setup a component which will be in charge of creating a [JMenuBar](http://docs.oracle.com/javase/8/docs/api/javax/swing/JMenuBar.html), and will only contains the two following atttributes :

* Reference to our application controller (as we will use it to connect item to action).
* Target [JMenuBar](http://docs.oracle.com/javase/8/docs/api/javax/swing/JMenuBar.html) instance that this builder will fill up.

<div class="alert alert-info">You can notice that the constructor is set as <b>private</b>, only a static factory method would be provided later.</div>
</div>

<a href="/images/review/menu-creation-using-swing/menubarbuilder.png">
<img src="/images/review/menu-creation-using-swing/menubarbuilder.png" class="img-responsive img-thumbnail" style="max-width: 300px;" /></a>

Then creating a menu will be as simple as following, we only have to use the previously defined ``MenuBuilder`` class, with method reference from our ``Controller`` instance as [ActionListener](http://docs.oracle.com/javase/8/docs/api/java/awt/event/ActionListener.html) for creating menu entry :

<script src="https://gist.github.com/Faylixe/9f70951e3501b3d4d960.js"></script>

Last step, we have to define a static factory method for using our class which will prevent from misusage. Here we only have one menu, but you could add method as much as menu you have.

<script src="https://gist.github.com/Faylixe/88ca1f0082ef24f29a13.js"></script>

# Bonus : Item delayed activation

Our menu is working, but let's improve it. Imagine you want to set the ``Save`` item disabled until a file has been opened. To do so, we will use the method you probably have noticed while looking the UML class diagram for the ``MenuBuilder`` : ``createActivable([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html), [ActionListener](http://docs.oracle.com/javase/8/docs/api/java/awt/event/ActionListener.html))``.

This method creates a [JMenuItem](http://docs.oracle.com/javase/8/docs/api/javax/swing/JMenuItem.html) by calling the ``createItem([String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html), [ActionListener](http://docs.oracle.com/javase/8/docs/api/java/awt/event/ActionListener.html))`` method, and then returns a [Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html) object :

<script src="https://gist.github.com/Faylixe/4f823121e559bbb2e12c.js"></script>

As you can see, the returned [Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html) instance is only enabling the associated menu item, and could then be used in order to activate the entry. To integrate this behavior for the ``Save`` item, we will update our ``Controller`` interface, and make it extends [Consumer](https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html) for [Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html) type :

<script src="https://gist.github.com/Faylixe/adb10f84b0b5dd85bbd2.js"></script>

And use the associated ``[accept(Runnable)](https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html#accept-T-)`` method into the file menu creation :

<script src="https://gist.github.com/Faylixe/1903c2249384d048bfbf.js"></script>

The ``Controller`` implementation will just have to store the given [Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html) and call it during the ``open([ActionEvent](https://docs.oracle.com/javase/8/docs/api/java/awt/event/ActionEvent.html))`` method.

# Download

<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> [Controller.java](/download/snippet/menu-creation-with-swing/Controller.java)

<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>[MenuBuilder.java](/download/snippet/menu-creation-with-swing/MenuBuilder.java)

<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> [MenuBarBuilder.java](/download/snippet/menu-creation-with-swing/MenuBarBuilder.java)
