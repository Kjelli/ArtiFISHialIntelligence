
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

## Rules and Instructions

### Prerequisites

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
7. Press the button with the name of the successfully uploaded AI** to duplicate it.
8. Press play!

* - **Note!** As somewhat of a safety mechanism, the simulator scans through the source code and looks for banned keywords that are considered (potentially) malicious to the host running the simulator.
** - **Note!** The program requires you to have a working java compiler that is not provided with the JRE, but usually with the JDK!

### How to run your AI

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

### Rules and restrictions

### Troubleshooting and Q&A
If any errors should arise following these instructions, please contact one of the members of the contest comittee and we will do our best to help you.
Otherwise any questions or remarks about the competition are welcome!  :smile:
