package ai;

import java.util.List;

import fishhandles.OtherFish;
import fishhandles.YourFish;

public class TemplateAI extends AbstractAI {
	/*
	 * Modify the code from here
	 * 
	 * Some keywords have been blacklisted and if found in any line (except
	 * one-line comments) will lead to the java file not being accepted into the
	 * program, but if you want to use a set of whitelisted methods, checkout
	 * the utils.Log and the utils.Time package
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
