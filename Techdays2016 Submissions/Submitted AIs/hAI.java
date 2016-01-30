package ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fishhandles.OtherFish;
import fishhandles.YourFish;
import game.EatFishAndAI;
import utils.Log;

public class hAI extends AbstractAI {
	/*
	 * 
	 */

	YourFish fish;
	OtherFish closestFish;
	OtherFish closestDangerousFish;
	OtherFish largestSafeFish;
	OtherFish targetFish;
	private static final float DANGERZONE_DEG = (float)(30 * (Math.PI / 180));
	private static final float DANGERZONE_DIST = 2/3;
	private int i = 0;
	
	@Override
	public void init(YourFish fish) {
		this.fish = fish;
		
	}

	@Override
	public void update(List<OtherFish> otherFish) {
		i++;
			
		if (i % 5 != 0)
			return;
		
		ArrayList<FishNode> fishes = GetAllFishNodes(otherFish);
		//for (int i = 0; i < fishes.size(); i++)
		{
			//Log.println("" fishes.get(i).ToString());
		}
		
		// Search through fishes for dangerous = true, and remove safe fishes from area without 75% of dist
		FishNode n = null;
		for(int i = 0; i < fishes.size(); i++)
		{
			n = fishes.get(i);
			if (n.dangerous)
			{
				float maxDist = n.dist * DANGERZONE_DIST * n.fish.getScale()*2;
				//maxDist = DANGERZONE_DIST;
				float maxAngle = (float)Math.min(DANGERZONE_DEG * n.fish.getScale() * 10/ n.dist, Math.PI); //Math.min(Math.max(n.fish.getScale()/10, 2), 1);
				//maxAngle = DANGERZONE_DEG;
				int j = i-1;
				
				//remove fishes right of dangerous fish
				boolean changed = false;
				while(true)
				{
					if (j < 0)
					{
						changed = true;
						j = fishes.size() - 1;
					}
						
					if (j == i)
						break;
					
					if (angleBetween(n.angle, fishes.get(j).angle) > maxAngle)
						break;
					
					if (fishes.get(j).dist > maxDist)
					{
						fishes.remove(j);
						if (!changed)
							i--;
					}
					
					j--;
				}
				
				//remove fishes left of the dangerous fish
				changed = false;
				j = i+1;
				while(true)
				{
					if (j >= fishes.size())
					{
						changed = true;
						j = 0;
					}
					
					if (j == i)
						break;
					
					if (angleBetween(n.angle, fishes.get(j).angle) > maxAngle)
						break;
					
					if (fishes.get(j).dist > maxDist)
					{
						fishes.remove(j);
						j--;
						if (changed)
							i--;
					}
					
					j++;
				}
				
				fishes.remove(i);
				i--;
			}
		}
		
		//target closest fish
		if (fishes.size() == 0){
			//fish.setVelocityX(0);
			//fish.setVelocityY(0);
			return;
		}
		
		
		float closestFishDist = fishes.get(0).dist;
		closestFish = fishes.get(0).fish;
		largestSafeFish = fishes.get(0).fish;
		
		for(FishNode fn : fishes)
		{
			if (fn.dist < closestFishDist && (fishMovingTowardsYouInX(fn.fish) && closestFishDist > 30)) //&& (fn.fish.getVelocityX() != fish.getVelocityX()))
			{
				closestFishDist = fn.dist;
				closestFish = fn.fish;
			}
			
			if (fn.fish.getScale() > largestSafeFish.getScale() && fn.fish.getScale()-0.2 < fish.getScale())
			{
				largestSafeFish = fn.fish;
			}
			
			if (fish.getScale() > 3 && fish.distanceTo(largestSafeFish) < 5*fish.distanceTo(closestFish)){
				targetFish = largestSafeFish;
			}
			else
			{
				targetFish = closestFish;
			}
		}
		
		closestDangerousFish = fishes.get(0).fish;
		for(OtherFish f : otherFish){
			if (f.getScale() > fish.getScale() && f.distanceTo(fish) < closestDangerousFish.distanceTo(fish))
			{
				closestDangerousFish = f;
			}
		}
		if(closestDangerousFish.getScale() < fish.getScale())
			closestDangerousFish = null;
		
		if(closestDangerousFish != null){
			if(fish.distanceTo(closestDangerousFish) < 20 + 20*closestDangerousFish.getScale()){
				fish.moveFrom(closestDangerousFish);
				if(fish.getCenterX() < 5) {
					fish.moveTowards(6, fish.getCenterY());
					if(closestDangerousFish.getCenterY() > fish.getCenterY())
						fish.setVelocityY(-1);
					if(closestDangerousFish.getCenterY() < fish.getCenterY())
						fish.setVelocityY(1);
				}
				if(fish.getCenterX() > EatFishAndAI.WIDTH -5) {
					fish.moveTowards(EatFishAndAI.WIDTH -4, fish.getCenterY());
					if(closestDangerousFish.getCenterY() > fish.getCenterY())
						fish.setVelocityY(-1);
					if(closestDangerousFish.getCenterY() < fish.getCenterY())
						fish.setVelocityY(1);
				}
				if(fish.getCenterY() < 5) {
					fish.moveTowards(fish.getCenterX(), 6);
					if(closestDangerousFish.getCenterX() > fish.getCenterX())
						fish.setVelocityX(-1);
					if(closestDangerousFish.getCenterX() < fish.getCenterX())
						fish.setVelocityX(1);
				}
				if(fish.getCenterY() > EatFishAndAI.HEIGHT -8) {
					fish.moveTowards(fish.getCenterX(), EatFishAndAI.HEIGHT - 9);
					if(closestDangerousFish.getCenterX() > fish.getCenterX())
						fish.setVelocityX(-1);
					if(closestDangerousFish.getCenterX() < fish.getCenterX())
						fish.setVelocityX(1);
				}
				closestDangerousFish = null;
				Log.println("hAI is fleeing");
				
			}
			else 
			{
				fish.moveTowards(targetFish);
				float vx = fish.getVelocityX();
				float vy = fish.getVelocityY();
				float factor = 1/Math.max(Math.abs(vx), Math.abs(vy));
				fish.setVelocityX(vx*factor);
				fish.setVelocityY(vy*factor);
			}
		}
		else
		{
			fish.moveTowards(targetFish);
			float vx = fish.getVelocityX();
			float vy = fish.getVelocityY();
			float factor = 1/Math.max(Math.abs(vx), Math.abs(vy));
			fish.setVelocityX(vx*factor);
			fish.setVelocityY(vy*factor);
		}
		
			
	}
	
	// Iterates through the other fishes and collects data; dist to meeting point, angle, if dangerous
	private ArrayList<FishNode> GetAllFishNodes(List<OtherFish> otherFishes)
	{
		
		ArrayList<FishNode> fishes = new ArrayList<>();
		
		for(OtherFish f : otherFishes)
		{
			if(!f.isAlive()) 
				continue;
			/*
			float dist = 0.0f;
			float angleTo = 0f;
			while(true)
			{
				dist = Math.round(dist++);
				float px = dist * f.getVelocityX();
				float py = dist * f.getVelocityY();
			}
			*/
			
			fishes.add(new FishNode(fish.angleTo(f), fish.distanceTo(f), (fish.getScale() - 0.2) > f.getScale() ? false : true, f));
		}
		
		if (otherFishes.isEmpty())
			return fishes;
		
		Collections.sort(fishes);
		
		return fishes;
	}

	@Override
	public void ateFish(OtherFish handle) {
		fish.setVelocityX(0);
		fish.setVelocityY(0);
	}
	
	private float angleBetween(float d1, float d2)
	{
		return Math.abs(d1 - d2);
		
	}
	
	public class FishNode implements Comparable<FishNode> {
		public float angle;
		public float dist;
		public boolean dangerous = false;
		public OtherFish fish;
		
		public FishNode(float angle, float dist, boolean dangerous, OtherFish fish)
		{
			this.angle = angle;
			this.dist = dist;
			this.dangerous = dangerous;
			this.fish = fish;
		}

		@Override
		public int compareTo(FishNode o) {
			return (int)((o.angle - angle) * 1000);
		}
		
		public String ToString()
		{
			return "" + angle;
		}
		
	}
	
	private boolean fishMovingTowardsYouInX(OtherFish theOtherFish){
		if((theOtherFish.getCenterX() > fish.getCenterX() && theOtherFish.getVelocityX() < 0) ||
				theOtherFish.getCenterX() < fish.getCenterX() && theOtherFish.getVelocityX() > 0
				)
			return true;
		
		return false;
	}
}