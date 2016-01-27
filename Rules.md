
![1](https://github.com/Kjelli/EatFishAndAI/blob/master/Art/logo_splash.png)

# Mind Your Fish (EatFishAndAI)
The programming contest at Techdays 2016 is all about Artificial Intelligence (AI).
The participants must design their own artificial intelligence to outsmart the other contestants, 
using Java as a programming language and implementing a skeleton code given at the start of the contest.

The winner will receive an amazing prize!



## Overview



### Introduction
The first screen
![2](https://github.com/Kjelli/EatFishAndAI/blob/master/Art/overview.png)

### Gameplay
![3](https://github.com/Kjelli/EatFishAndAI/blob/master/Art/gameplay.png)

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


### How to run your AI

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

### Rules and restrictions

### Troubleshooting and Q&A
If any errors should arise following these instructions, please contact one of the members of the contest comittee and we will do our best to help you.
Otherwise any questions or remarks about the competition are welcome!  :smile:
