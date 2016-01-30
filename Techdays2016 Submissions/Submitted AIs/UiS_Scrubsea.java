package ai;

import java.util.List;

import fishhandles.OtherFish;
import fishhandles.YourFish;
import game.EatFishAndAI;
import utils.Log;

public class UiS_Scrubsea extends AbstractAI {
	/*
	 * 
	 * Some common keywords have been blacklisted as they can be used in a
	 * malicious way. If any of these keywords are found in any line (except
	 * one-line comments) will lead to the java fi5le not being accepted into the
	 * program, but if you want to use a set of whitelisted methods, checkout
	 * the utils.Log (for printing and debugging) and the utils.Time package
	 * (for getting the machine time in nanos and millis)
	 * 
	 * 
	 * Modify the code from here
	 */

	YourFish fish;
	boolean isTarget = false;
	boolean targetEscaped = false;
	int targetFishIndex = -1;
	int closestFoodIndex = -1;
	OtherFish targetFish;
	float targetDistance = -1;
	float targetDistance_old = 1000;

	@Override
	public void init(YourFish fish) {
		/* Initialize local vars */

		this.fish = fish;
		fish.setVelocityX(1);
		fish.setVelocityY(1);
	}

	@Override
	public void update(List<OtherFish> otherFish) {

		/* Handle collisions with end of frame */
		if (fish.getX() <= 0){
			fish.setVelocityX(1);
			return;
		} else if ((fish.getX() + fish.getWidth()) >= EatFishAndAI.WIDTH){
			fish.setVelocityX(-1);
			return;
		} else if (fish.getY() <= 0){
			fish.setVelocityY(1);
			return;
		}
		if ((fish.getY() + fish.getHeight()) >= EatFishAndAI.HEIGHT){
			fish.setVelocityY(-1);
			return;
		}

		if(targetFishIndex != -1){
			
			if(!otherFish.contains(targetFish)){
				targetFishIndex = -1;
				targetDistance = -1;
				isTarget = false;
				return;
			}
			
			targetEscaped |= targetFish.getCenterX() < 0;
			targetEscaped |= targetFish.getCenterX() > EatFishAndAI.WIDTH;
			targetEscaped |= targetFish.getCenterY() < 0;
			targetEscaped |= targetFish.getCenterY() > EatFishAndAI.HEIGHT;
			targetEscaped &= targetFish.isAlive();

			if(targetEscaped){
				targetFishIndex = -1;
				targetDistance = -1;
				isTarget = false;
			}
		}

		/* Fish search */
		float minFoodDistance = 1000;
		float minEnemyDistance = 1000;
		float curDistance = 1000;


		int closestEnemyIndex = -1;
		float biggestFood = -1;
		boolean checker = false;
		boolean dirCheck = false;
		boolean canReach = false;
		boolean validNavigation = false;

		float fishX,fishY,fishW,fishCX,fishCY,fishH,fX,fY,fW,fCX,fCY,fVeloX,fH,targetX,targetY;
		boolean fishGreat;

		fishX = fish.getX();
		fishY = fish.getY();
		fishW = fish.getWidth();
		fishCX = fish.getCenterX();
		fishCY = fish.getCenterY();
		fishH = fish.getHeight();

		if (!isTarget){
			for (OtherFish f : otherFish){
				if (!f.isAlive()){
					continue; //don't care about dead fish
				}

				curDistance = fish.distanceTo(f);

				fishGreat = fish.greaterThan(f);

				fX = f.getX();
				fY = f.getY();
				fW = f.getWidth();
				fCX = f.getCenterX();
				fCY = f.getCenterY();
				fVeloX = f.getVelocityX();
				fH = fish.getHeight();

				/* Find food */
				checker = curDistance < minFoodDistance;
				checker &= fishGreat;

				dirCheck |= ( (fVeloX > 0) && (fishCX > fCX) );
				dirCheck |= ( (fVeloX < 0) && (fishCX < fCX) );

				checker &= dirCheck;

				if(checker){
					closestFoodIndex = otherFish.indexOf(f);
					minFoodDistance = curDistance;
					validNavigation = true;
					isTarget = true;
					targetFishIndex = closestFoodIndex;
					targetFish = f;
					targetDistance = fish.distanceTo(f);
				}

				/* Check for big fish (usually playerfish) 
				 * Chase if no enemies close*/
				if( (10 < fW)  && (fW<(fishW - 15) && (biggestFood < f.getScale()))){
					closestFoodIndex = otherFish.indexOf(f);
					minFoodDistance = -1;
					validNavigation = true;
					biggestFood = f.getScale();
					targetFishIndex = closestFoodIndex;
					isTarget = true;
					targetFish = f;
					targetDistance = fish.distanceTo(f);
					//break;
				}
				if (fishGreat) continue; // continue if food

				/* Find enemies */
				if (curDistance < minEnemyDistance){
					minEnemyDistance = curDistance;
					closestEnemyIndex = otherFish.indexOf(f);
					validNavigation  = true;
				}
			}
		} // end find target

		/* Check for enemies if isTarget */
		if(isTarget){
			for(OtherFish f: otherFish){
				if(!f.isAlive()) continue;
				if(f.smallerThan(fish)) continue;
				curDistance = fish.distanceTo(f);
				if(curDistance < minEnemyDistance){
					minEnemyDistance = curDistance;
					closestEnemyIndex = otherFish.indexOf(f);
					validNavigation = true;
				}

			}
		}

		/* if no valid navigation, move towards center */
		if (!isTarget){
			if(fishCX < (EatFishAndAI.WIDTH/2)) fish.setVelocityX(1);
			if(fishCX > (EatFishAndAI.WIDTH/2)) fish.setVelocityX(-1);
			if(fishCY < (EatFishAndAI.HEIGHT/2)) fish.setVelocityY(1);
			if(fishCY > (EatFishAndAI.HEIGHT/2)) fish.setVelocityY(-1);
			Log.println("Returning to center");
			return;
		}


		/* Escape from enemy */
		if ((minEnemyDistance < fish.getWidth() + 100) && (closestEnemyIndex != -1)){
			if (fish.getX()<otherFish.get(closestEnemyIndex).getX()){
				fish.setVelocityX(-1);
			} else{
				fish.setVelocityX(1);
			}

			if (fish.getY()<otherFish.get(closestEnemyIndex).getY()){
				fish.setVelocityY(-1);
			} else{
				fish.setVelocityY(1);
			}
			//Log.println("Running from : " + closestEnemyIndex + " at a distance of: " + minEnemyDistance);
		}
		/* Navigate to closest food if no close enemies. */
		else if (targetFishIndex != -1 && isTarget){
			targetX = targetFish.getX();
			targetY = targetFish.getY();

			fish.setVelocityX(Math.signum(targetX-fishX));
			fish.setVelocityY(Math.signum(targetY-fishY));
			
			targetDistance = fish.distanceTo(targetFish);
			
			if (targetDistance-2<targetDistance_old){
				isTarget = false;
				targetFishIndex = -1;
				targetDistance = -1;
			}
			
			targetDistance_old = targetDistance;
			//Log.println("Closest food is: " + closestFoodIndex + " at a distance of: " + minFoodDistance);
		}
	}


	@Override
	public void ateFish(OtherFish handle) {
		isTarget = false;
		targetFishIndex = -1;
	}

}
