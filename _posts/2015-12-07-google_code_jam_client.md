---
layout: post
title: "Google Code Jam API"
date: 2015-12-07 12:28:00
categories: java maven google
download-section: true
repository-link: https://github.com/Faylixe/googlecodejam-client
---

<br>

Since May 2015, Google authentification protocol [ClientLogin](https://developers.google.com/identity/protocols/AuthForInstalledApps) is officially not supported anymore, which implies that the only command line tools for [Google Code Jam](https://code.google.com/codejam) contest is not working and deprecated. The Google Code Jam API aims to be a valid alternative to this obsolete client. Implemented in Java, it could be use by any application, or as a single command line application.

<br>

### Maven dependency

<br>

Following dependency could be added to your *POM.xml* if you want to use the client API into your project.

<br>

<script src="https://gist.github.com/Faylixe/d2f3e11197b8bdd94fcd.js"></script>

<br>

The API entry point is the [CodeJamSession](http://faylixe.fr/googlecodejam-client/apidocs/fr/faylixe/googlecodejam/client/CodeJamSession.html) class, which could be instantiated as following :

<br>

<script src="https://gist.github.com/Faylixe/d662a59ff7e81e19fddc.js"></script>

<br>

The session could be then used to retrieve contest information, problem description and analysis, download input file
as submit solution as well. You can check the [javadoc](http://faylixe.fr/googlecodejam-client/apidocs) to get more informations.

<br>

### Command line application

<br>
