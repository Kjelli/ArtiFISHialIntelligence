package ai;

import java.util.List;

import fishhandles.OtherFish;
import fishhandles.YourFish;

public class Hal9000AI extends AbstractAI {
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

	YourFish myFish;
	OtherFish enemyFish;
	
	double currentDistance = 1000;

	@Override
	public void init(YourFish fish) {
		this.myFish = fish;
		this.currentDistance = 1000;
	}

	@Override
	public void update(List<OtherFish> otherFish) {
		/* Finn stim, fokus på fisk i steam. Ed nermaste */
		for(OtherFish someFish : otherFish){
			if(someFish.smallerThan(this.myFish) && someFish.isAlive() 
					&& towardsMe(someFish) 
					&& someFish.distanceTo(this.myFish) < this.currentDistance){
				this.enemyFish = someFish;
				this.myFish.moveTowards(this.enemyFish);
				eatFish(this.enemyFish);
			}else{
				if (this.enemyFish == null){
					continue;
				}else{
					eatFish(this.enemyFish);
				}
			}
		}
	}
	
	private void eatFish(OtherFish prey){
		if(!prey.isAlive()){
			stopHunt();
		}else if(!towardsMe(prey)){
			stopHunt();
		}else
			this.currentDistance = prey.distanceTo(this.myFish);
			this.myFish.moveTowards(prey);
	}
	
	private void stopHunt(){
		//this.myFish.setVelocityX(0);
		//this.myFish.setVelocityY(0);
		this.currentDistance = 1000;
	}
	
	private boolean towardsMe(OtherFish prey){
		if(prey.getVelocityX() > 0 && this.myFish.getX() > prey.getX()){
			return true;
		}else if(prey.getVelocityX() < 0 && this.myFish.getX() < prey.getX()){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public void ateFish(OtherFish handle) {
		this.currentDistance = 1000;
	}

}
