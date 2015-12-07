---
layout: post
title: "Google Code Jam API"
date: 2015-12-07 12:28:00
categories: java maven google
download-section: true
repository-link: https://github.com/Faylixe/googlecodejam-client
---

Since May 2015, Google authentification protocol [ClientLogin](https://developers.google.com/identity/protocols/AuthForInstalledApps) is officially not supported anymore, which implies that the only command line tools for [Google Code Jam](https://code.google.com/codejam) contest is not working and deprecated. The Google Code Jam API aims to be a valid alternative to this obsolete client. Implemented in Java, it could be use by any application, or as a single command line application.

<br>

## Maven dependency

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

## Command line application

<br>

A command line application which consists in the client JAR and a running script is also available. Here is the usage description :

```bash
codejam action parameter
```

Where action belongs to the following option list :

* *--init*
* *--download*
* *--submit*

### Installation

You can install script and packaged version of client by running the following command :

```bash
wget -O - https://raw.githubusercontent.com/Faylixe/googlecodejam-client/master/scripts/install | bash
```

Once script has been executed, you can run the **codejam** command. Please note that installation script should be ran under root permission.

### Initialization action

This action does not take any parameters, and will open up a Firefox instance
in order to authenticate to Google services. Once Firefox is opened and the login page loaded,
please proceed to the authentication process, and when you will be logged and redirected
to the code jam home page, Firefox will be closed automatically.

```bash
codejam --init
```

Once logged you will be prompted to choose a contest and a round. Those will become contextual round and session
for the current directory you are running the script in, meaning that if you run another time the script with another
action, it will use the created contextual logged session and round.

### Download action

As it name suggests, the *download* action allows logged user to download an input file for a given problem.
If the contest is active, then it will trigger the submission timer depending of the input difficulty you have
choosen (usually 4 minutes for a *small* input, 8 for a *large* one).

The following exemple will download the *small* input file for the first problem.

```bash
codejam --download --problem A --inputtype small
```

If the download is successful, the name of the downloaded file will be printed, so it could be chained in a command workflow, for example :

```bash
cat < `codejam --download --problem A --inputtype small` | python A.py
```
### Submit action

Once input file is downloaded, and algorithm solved all test cases, *submit* action could be used in order
to send either the output file as the source file of your algorithm as well.

```bash
codejam --submit --problem A --inputtype small --output path/to/output --sourcefile path/to/sourcefile
```
