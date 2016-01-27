
![1](https://github.com/Kjelli/EatFishAndAI/blob/master/Art/logo_splash.png)

# Mind Your Fish (EatFishAndAI)
The programming contest at Techdays 2016 is all about Artificial Intelligence (AI).
The participants must design their own artificial intelligence to outsmart the other contestants, 
using Java as a programming language and implementing a skeleton code given at the start of the contest.

The winner will receive an amazing prize!

## Overview
If you have ever played Feeding Frenzy or Agar.io or any of the sort, you are familiar with the concept of this contest. In all its simplicity, your goal is to make your fish eat all the other players' fish. This can be done by programming the *mind* of the fish. You will have direct control on your own fish, and you are also provided with the whereabouts of all the other fish on screen. Using your wits and your programming knowledge to create a smart AI is key to winning the contest!

### Introduction
The first screen you are introduced to is the menu screen. Here you can quickstart the game or set up a custom game. More details on this in *How to start the simulator*

![2](https://github.com/Kjelli/EatFishAndAI/blob/master/Art/overview.png)

### Gameplay
This is the main screen where all the action takes place.
![3](https://github.com/Kjelli/EatFishAndAI/blob/master/Art/gameplay.png)

There are two different types of fish: The playerfish and the dummyfish. The contestants will provide their AI to each their fish, and then they will eat the dummyfish and eachother to be the last playerfish swimming.

#### A playerfish using the AI *BasicAI*
![4](https://github.com/Kjelli/EatFishAndAI/blob/master/Art/theplayer.png)

This is your fish. You will program an AI to control this fish, and make it do everything you tell it to!

#### The dummyfish
![5](https://github.com/Kjelli/EatFishAndAI/blob/master/Art/dummies.png)

This is the dummyfish, they are ridiculously basic and will only swim in a single direction. Free food!

## Instructions

### Prerequisites

* Installation of Java (Preferably JDK) 8+
* Any preferred IDE (Eclipse, IntelliJ, netbeans etc.)
* Basic to intermediate level of the Java programming language

### How to start the simulator

1. Clone this repository and import the ./Code/EatFishAndAI folder as a project into your IDE (i.e. Eclipse, IntelliJ, netbeans)
2. Run the application from your IDE, using launcher.Launcher as the main class.
3. If all is correct, you should see a brief loading screen followed up by the menu screen. You now have two options:
  * **Quickstart**: This will launch the game with four instances of a very simple AI, skipping configuration.
  * **Start**: This will take you to the configuration screen where you can set up the ais and the number of wins needed to be declared the final winner.
4. Choose the start option
5. Choose the "+ Add player" option
6. Navigate to where your java sourcecode containing the AI code is, and select the file. It will now be scanned* and compiled**.
7. Press the button with the name of the successfully uploaded AI to duplicate it.
8. Press play!

* * - **Note!** As somewhat of a safety mechanism, the simulator scans through the source code and looks for banned keywords that are considered (potentially) malicious to the host running the simulator.
* ** - **Note!** The program requires you to have a working java compiler that is not provided with the JRE, but usually with the JDK!

### How to code your AI

The skeleton code given for you to implement is the following:

```java
import java.util.List;

import fishhandles.OtherFish;
import fishhandles.YourFish;

public class TemplateAI extends AbstractAI {
	/*
	 * 
	 * Some common keywords have been blacklisted as they can be used in a
	 * malicious way. If any of these keywords are found in any line (except
	 * one-line comments) will lead to the java file not being accepted into the
	 * program, but if you want to use a set of whitelisted methods, checkout
	 * the utils.Log (for printing and debugging) and the utils.Time package
	 * (for getting the machine time in nanos and millis)
	 * 
	 * 
	 * Modify the code from here
	 */

	YourFish fish;

	@Override
	public void init(YourFish fish) {
		this.fish = fish;
	}

	@Override
	public void update(List<OtherFish> otherFish) {

	}

	@Override
	public void ateFish(OtherFish handle) {

	}

}
```

#### Details
The TemplateAI shown above is for you to modify. However the class you implement must extend the AbstractAI class, and the signatures of the methods given (init, update and atefish) must remain unaltered. The implementation of the said methods are for you to design however you want.

Also, **the class name is effectively the displayname of your fish, change it to something personal!** :smile:

Note that package declarations are stripped when the AI is compiled by the simulator. You might require a package declaration in your code when running and testing it, this has no effect on the simulator's end.

#### Implementing the AI

When implementing the AI, you will not work directly upon the fish, but through a handle, i.e. a YourFish object. The YourFish class exposes a set of methods available for you to manipulate the fish (i.e. change velocity, move towards fish/point, move from fish/point) and more. The same goes for the other surrounding fish, you will work with OtherFish objects. These ones are similar to the YourFish class with one major difference - it is considered read-only whereas the YourFish has writable and readable methods.

The source code is documented for your convenience. Here is a snippet of the documentation:

* **init(YourFish fish)** - This method is called at the initialization of the AI's behaviour, before
looping the update method. The method provides an argument being a handle to
your fish, i.e. the only fish you have both read and write access to.
Store this reference and invoke methods on it in the update method.
* **update(List<OtherFish> otherFish)** - This method is run continuously as the AI you implement is running its own thread. It runs an infinite loop, calling update and then sleeping for some time, leaving you to implement the behaviour of the AI iteratively. Provided in the update method is a list of read-only handles to the other fish visible on screen. This list is guaranteed not to contain the handle to your fish, and also contain the newest information about the fish's attributes. Note: As every fish runs as a separate thread, some race conditions may apply - but rarely becomes an issue because of the rapid calling of the update method.
* **ateFish(OtherFish fish)** - Action that will happen whenever you eat another fish. Provided is the referenced handle towards the fish you ate. Warning: This fish is not alive, so some methods are rendered useless. (i.e moveToward, distanceTo)

## Rules and restrictions

The goal is simple; make the best AI to win the prize. The contest is best suited prize-wise for individual participation, but you can also work in teams.

You are allowed to use any existing code you would like, as long as you can fit everything you need in **one .java file**. Each AI submitted can consist of more than one file.

**Important:** as this is a good faith contest and running unknown code from external sources is the single-most dangerous thing to do on a computer, submissions that contain malicious code which bypasses the security scan and (in any way) cause any damage or alter the state of the game in any way not intended are subject to immediate disqualification.

## Submission and Prize(s)

**How to submit:**
Send in your sourcecode to *post@techdays.no* with the topic including the keyword "TDAI", followed by your real name. The submitted file(s) must be in the form of *{yourclassname}*.java where *{yourclassname}* is whatever you want to be, but it must be consistent with the class declaration in the file. No *.class files will be accepted. Zipped files are ok.

The prize and winner will both be announced on Techdays.

There might also be a bonus prize for the hackers if anyone can alter the gameplay (in a way not intended in development) from within the AI code! :bomb:

## Troubleshooting and Q&A
If any errors should arise following these instructions, please contact one of the members of the contest comittee and we will do our best to help you.
Otherwise any questions or remarks about the competition are welcome!  :smile:

### Known issues

* Some clients have received a nullpointer when trying to load AIs into the simulator. This is because the simulator cannot find the system's JavaCompiler. This can be fixed by running with JDK instead of JRE. Also, Java 8 is required.
* Some clients using linux have reported they cannot run the program. This might be because of the missing native files to the dependency LibGDX. These can be found and downloaded [here](https://libgdx.badlogicgames.com/nightlies/).

Good luck!
