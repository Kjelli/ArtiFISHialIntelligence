package ai;

import fishhandles.OtherFish;
import fishhandles.YourFish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FishFood extends AbstractAI {
    private YourFish fish;

    private Map<Integer, Float> otherLastXs = new HashMap<>();
    private Map<Integer, Boolean> otherOrientations = new HashMap<>();

    private OtherFish target;

    private boolean orientation = true;
    private float lastPosX;

    @Override
    public void init(YourFish fish) {
        this.fish = fish;

        lastPosX = fish.getCenterX();
    }

    @Override
    public void update(List<OtherFish> otherFish) {
        setOrientation();
        setOtherOrientations(otherFish);

        ArrayList<OtherFish> potential = new ArrayList<>();

        for (int i = 0; i < otherFish.size(); i++) {
            OtherFish food = otherFish.get(i);

            boolean orientation = otherOrientations.getOrDefault(food.hashCode(), true);

            if (food.getCenterX() < fish.getCenterX()) {
                if (orientation) {
                    potential.add(food);
                }
            } else {
                if (!orientation) {
                    potential.add(food);
                }
            }
        }

        float min = Float.MAX_VALUE;

        for (OtherFish food : potential) {
            float distance = fish.distanceTo(food);

            if (target != null && target.isAlive() && fish.distanceTo(target) < 10 && potential.contains(target)) {
                break;
            }

            if (distance < min && food.isAlive() && fish.greaterThan(food)) {
                min = distance;

                target = food;
            }
        }

        if (target != null) {
            fish.moveTowards(target);
        } else {
            fish.setVelocityX(0);
            fish.setVelocityY(-1);
        }
    }

    private void setOtherOrientations(List<OtherFish> otherFish) {
        for (int i = 0; i < otherFish.size(); i++) {
            OtherFish food = otherFish.get(i);
            int id = food.hashCode();

            float newPosX = food.getCenterX();
            float diffPos = otherLastXs.getOrDefault(id, newPosX) - newPosX;

            if (diffPos != 0) {
                otherOrientations.put(id, diffPos < 0);
            }

            otherLastXs.put(id, newPosX);
        }
    }

    private void setOrientation() {
        float newPosX = fish.getCenterX();
        float diffPos = lastPosX - newPosX;

        if (diffPos != 0) {
            orientation = diffPos < 0;
        }

        lastPosX = newPosX;
    }

    @Override
    public void ateFish(OtherFish handle) {
        target = null;
    }

}

/* CLUSTERS
        List<List<OtherFish>> clusters = new ArrayList<List<OtherFish>>(otherFish.size());

        for (int i = 0; i < otherFish.size(); i++) {
            clusters.add(new ArrayList<OtherFish>());
        }

        int radius = 50;

        for (int i = 0; i < otherFish.size(); i++) {
            OtherFish centerFish = otherFish.get(i);

            for (int j = 0; j < otherFish.size(); j++) {
                if (i == j) {
                    continue;
                }

                OtherFish other = otherFish.get(j);

                float distance = other.distanceTo(centerFish);

                if (distance < radius) {
                    clusters.get(i).add(other);
                }
            }
        }

        for (List<OtherFish> cluster : clusters) {
            if (cluster.size() > 4) {
                utils.Log.println(cluster.toString());
            }
        }
 */