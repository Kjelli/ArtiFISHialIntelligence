package ai;

import java.util.ArrayList;
import java.util.List;

import fishhandles.OtherFish;
import fishhandles.YourFish;
import game.EatFishAndAI;

public class Fred extends AbstractAI {
	/*
	 * 
	 * Some common keywords have been blacklisted as they can be used in a
	 * malicious way. If any of these keywords are found in any line (except
	 * one-line comments) will lead to the java fiile not being accepted into the
	 * program, but if you want to use a set of whitelisted methods, checkout
	 * the utils.Log (for printing and debugging) and the utils.Time package
	 * (for getting the machine time in nanos and millis)
	 * 
	 * 
	 * Modify the code from here
	 */

	YourFish fish;
	private List<OtherFish> bigger_Fish;
	private OtherFish closest_Fish_small;
	private OtherFish closest_Fish_big;

	@Override
	public void init(YourFish fish) {
		this.fish = fish;
	}

	@Override
	public void update(List<OtherFish> otherFish) {
		if(fish.isAlive()){
			bigger_Fish = new ArrayList<OtherFish>();
			
			for(int i = 0;i < otherFish.size();i++){
				if(closest_Fish_small == null && fish.greaterThan(otherFish.get(i))
						|| closest_Fish_small != null && !closest_Fish_small.isAlive()){
					closest_Fish_small = otherFish.get(i);
				}
				if(closest_Fish_big == null && fish.greaterThan(otherFish.get(i))
						|| closest_Fish_big != null && !closest_Fish_big.isAlive()){
					closest_Fish_big = otherFish.get(i);
				}
				if(fish.smallerThan(otherFish.get(i))){
					bigger_Fish.add(otherFish.get(i));
				}
				if(closest_Fish_small != null && fish.distanceTo(otherFish.get(i)) < closest_Fish_small.distanceTo(fish)
						&& fish.greaterThan(otherFish.get(i))){
					closest_Fish_small = otherFish.get(i);
				}
				if(closest_Fish_big != null && fish.distanceTo(otherFish.get(i)) < closest_Fish_big.distanceTo(fish)
						&& fish.smallerThan(otherFish.get(i))){
					closest_Fish_big = otherFish.get(i);
				}
			}
			if(bigger_Fish.size() != 0){
				if(fish.getCenterX() < 50 || fish.getCenterX() > (EatFishAndAI.WIDTH-50) ||
					fish.getCenterY() < 50 || fish.getCenterY() > (EatFishAndAI.HEIGHT-50)){
					fish.moveTowards(EatFishAndAI.WIDTH/4, EatFishAndAI.HEIGHT/4);
					if(closest_Fish_big != null){
						fish.moveFrom(closest_Fish_big);
					}
					
				}else{
					if(closest_Fish_big != null && fish.distanceTo(closest_Fish_big) < fish.distanceTo(closest_Fish_small)){
						fish.moveFrom(closest_Fish_big);
					}else if(closest_Fish_big != null){
						fish.moveFrom(closest_Fish_big);
						if(closest_Fish_small != null){
							fish.moveTowards(closest_Fish_small);
						}
					}else if(closest_Fish_small != null){
						fish.moveTowards(closest_Fish_small);
					}
				}
			}else{
				if(closest_Fish_small != null){
					fish.moveTowards(closest_Fish_small);
				}
			}
		}
	}

	@Override
	public void ateFish(OtherFish handle) {

	}

}
