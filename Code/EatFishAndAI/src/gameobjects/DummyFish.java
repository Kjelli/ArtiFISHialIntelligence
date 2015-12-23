package gameobjects;

public class DummyFish extends AbstractFish {

	public DummyFish(float x, float y) {
		super(x, y, 16, 12);
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		move(delta);
	}

	@Override
	public void onSpawn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDespawn() {
		// TODO Auto-generated method stub

	}

}
