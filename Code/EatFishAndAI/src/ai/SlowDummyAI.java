package ai;

import static java.lang.Math.PI;
import static java.lang.Math.cos;

import java.util.List;

import fishhandles.OtherFish;
import fishhandles.YourFish;

public class SlowDummyAI extends AbstractAI {
	int angle;
	YourFish fish;

	static final float slowInterval = 100;
	float slowClock;
	boolean isSlow;

	public SlowDummyAI() {
		angle = (int) (Math.random() * 360);
	}

	@Override
	public void init(YourFish fish) {
		this.fish = fish;
	}

	@Override
	public void update(List<OtherFish> otherFish) {
		fish.setVelocityY((float) (cos(angle * PI / 180.0f) / 3));
		angle = (angle + 1) % 360;
		if (++slowClock >= slowInterval) {
			isSlow = !isSlow;
			slowClock = 0;
		}

		if (isSlow) {
			try {
				Thread.sleep(40);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
	
	@Override
	public void ateFish(OtherFish handle) {
		// TODO Auto-generated method stub
		
	}

}
