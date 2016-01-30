package ai;

import java.util.List;

import fishhandles.OtherFish;
import fishhandles.YourFish;
import game.EatFishAndAI;
import utils.Log;

public class Loser extends AbstractAI {
	Thread sendToDisp = new Thread();
	YourFish fish;
	float fSpeedy;
	float fSpeedx;
	float biggestFishDist = 100;
	float biggestFishSize = 0;
	int biggestFish = 0;
	int counter = 0;
	
	
	
	
	@Override
	public void init(YourFish fish) {
		this.fish = fish;
		fish.setVelocityX(-1); // initspeed
		fish.setVelocityY(0); // initspeed
		float fSpeedx = fish.getVelocityX();
		float fSpeedy = fish.getVelocityY();

			
		
	}


	@Override
	public void update(List<OtherFish> otherFish) {
		
		checkCollision_UpdateSpeed();
		
		
		
		
		
		
		
		if (counter<20){
			
		for(int i=0; i< otherFish.size(); i++){
			if ( otherFish.get(i).getScale() < fish.getScale()){
				if (otherFish.get(i).distanceTo(fish)<otherFish.get(biggestFish).distanceTo(fish) ){
					biggestFish = i;
					biggestFishDist = otherFish.get(i).distanceTo(fish) ;
				biggestFishSize = otherFish.get(i).getScale();
				
				if (otherFish.get(i).getX()<fish.getX() && otherFish.get(i).getVelocityX()<0){
					biggestFishSize = 0;
				}
				if (otherFish.get(i).getX()>fish.getX() && otherFish.get(i).getVelocityX()>0){
					biggestFishSize = 0;
				}
				
			}
			}
		Log.println(" asd");
		counter = 0;
		}
		
		if(biggestFishSize>0){
		if(otherFish.get(biggestFish).getX()<fish.getX()){
			fish.setVelocityX(-1);
		}else{
			fish.setVelocityX(1);
		}
		
		if(otherFish.get(biggestFish).getY()<fish.getY()){
			fish.setVelocityY(-1);
		}else{
			fish.setVelocityY(1);
		}
		}
		}
		counter ++;
	
	}
		
	
		



	private void checkCollision_UpdateSpeed() {
		if ((fish.getX() + fish.getWidth()) > EatFishAndAI.WIDTH) {
			fish.setVelocityX(fSpeedx * -1);
			//while((fish.getX() + fish.getWidth()) > EatFishAndAI.WIDTH);
		}
		if ((fish.getX() - fish.getWidth()) < 0) {
			fish.setVelocityX(fSpeedx * -1);
			//while((fish.getX() - fish.getWidth()) < 0);
		}

		if ((fish.getY() + fish.getHeight()) > EatFishAndAI.HEIGHT) {
			fish.setVelocityY(fSpeedy * -1);
			//while(fish.getY() + fish.getHeight() > EatFishAndAI.HEIGHT);
		}
		if ((fish.getY() - fish.getHeight()) < 0) {
			fish.setVelocityY(fSpeedy * -1);
			//while((fish.getY() - fish.getHeight()) < 0);
		}

		fSpeedx = fish.getVelocityX();
		fSpeedy = fish.getVelocityY();
	}

	@Override
	public void ateFish(OtherFish handle) {
		float biggestFishDist = 100;
		float biggestFishSize = 0;
		int biggestFish = 0;
		
		
	}

}
