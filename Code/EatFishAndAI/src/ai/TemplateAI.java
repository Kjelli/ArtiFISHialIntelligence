package ai;

import java.util.List;

import fishhandles.OtherFish;
import fishhandles.YourFish;

public class TemplateAI extends AbstractAI {
	// Modify the code from here

	YourFish fish;
	
	@Override
	public void init(YourFish fish) {
		this.fish = fish;
	}

	@Override
	public void act(List<OtherFish> otherFish) {
		
	}

}
