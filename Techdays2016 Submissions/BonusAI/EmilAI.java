package ai;

import java.util.List;

import fishhandles.OtherFish;
import fishhandles.YourFish;

import java.math.*;

public class EmilAI extends AbstractAI {
	YourFish fish;
	float my_x = -1;
	float my_y = -1;

	public EmilAI() {
		
	}

	@Override
	public void init(YourFish fish) {
		this.fish = fish;
		my_x = fish.getCenterX();
		my_y = fish.getCenterY();

	}

	@Override
	public void update(List<OtherFish> otherFish) {		
		
		my_x = fish.getCenterX();
		my_y = fish.getCenterY();
		
		//Prepare to eat fish
		OtherFish target = findFish(otherFish);

		if(target != null){
			float x_dist = (float)Math.sqrt((target.getCenterX() - my_x)*(target.getCenterX() - my_x));
			float y_dist = (float)Math.sqrt((target.getCenterY() - my_y)*(target.getCenterY() - my_y));
		
			if(x_dist < y_dist){
				if(target.getCenterY() < my_y){
					fish.setVelocityY(-1);
				}else{
					fish.setVelocityY(1);
				}
				if(target.getCenterX() < my_x){
					fish.setVelocityX(1);	
				}else{
					fish.setVelocityX(-1);
				}
				return;
			}

			
			fish.moveTowards(target);
		}
	}
	
	//Find best fish to target
	public OtherFish findFish(List<OtherFish> others){
		OtherFish target_fish = null;
		float shortest_time = -1;
		for(int i = 0; i < others.size(); i++){		
			OtherFish other = others.get(i);
			if(other.isAlive()){
				//Find 
				//if(target_fish == null || other.distanceTo(fish) < target_fish.distanceTo(fish)){
				float time = catch_time(other);
				if(time != -1 && (target_fish == null || time < shortest_time)){
					if(fish.greaterThan(other)){
						target_fish = other;
						shortest_time = time;
					}
				}
			}
		}
		
		return target_fish;
	}
	
	public float catch_time(OtherFish other){
		float o_distance = other.distanceTo(fish);
		float o_speed_x = other.getVelocityX();
		float o_speed_y = other.getVelocityY();
		float o_pos_x = other.getCenterX();

		if(o_speed_x > 0){
			if(o_pos_x < my_x){
				return o_distance;
			}
		}else{
			if(o_pos_x > my_x){
				return o_distance;
			}
		}

		
		return -1;
	}
	
	@Override
	public void ateFish(OtherFish handle) {
		
	}
}
