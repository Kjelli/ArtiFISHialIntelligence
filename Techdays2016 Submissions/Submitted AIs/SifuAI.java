package ai;

import java.lang.reflect.Array;
import java.util.*;

import fishhandles.OtherFish;
import fishhandles.YourFish;
import java.util.Random;
import javafx.stage.Screen;
import utils.Log;

import javax.print.attribute.standard.MediaSize;

public class SifuAI extends AbstractAI {
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
    int aliveCounter = 0;

    @Override
    public void init(YourFish fish) {
        this.fish = fish;
    }

    @Override
    public void update(List<OtherFish> otherFish) {

        OtherFish nearest = findNearest(otherFish);
        if (nearest != null) {
            fish.moveTowards(nearest);
        }
    }

    @Override
    public void ateFish(OtherFish handle) {
    }

    private OtherFish findNearest(List<OtherFish> otherFish) {
        OtherFish nearestFish = null;
        for(OtherFish f: otherFish) {
            if (nearestFish == null) {
                nearestFish = f;
            }
            else if(nearestFish != null &&
                    (nearestFish.distanceTo(fish) > f.distanceTo(fish) && f.isAlive())) {
                if (checkTrajectory(fish, f)) {
                    nearestFish = f;
                }
                else {

                }
            }
        }
        return nearestFish;
    }

    private boolean checkTrajectory(YourFish fish, OtherFish f) {
        if (aliveCounter > 10) {
            aliveCounter = 0;
            Random generator = new Random();
            int random = generator.nextInt(100);
            fish.moveTowards(fish.getX() - random, fish.getY() - random);
            return false;
        }
        else {
            aliveCounter++;
        }
        return true;
    }
}
