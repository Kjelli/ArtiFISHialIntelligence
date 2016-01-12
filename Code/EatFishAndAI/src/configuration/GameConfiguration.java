package configuration;

import gameobjects.PlayerFish;

import java.util.ArrayList;
import java.util.List;

import ai.AIConfiguration;

public class GameConfiguration {

	public GameConfiguration() {
		players = new ArrayList<PlayerFish>();
	}

	public AIConfiguration aiconf;
	public String gamename = "Mind Your Fish";
	public List<PlayerFish> players;

}
