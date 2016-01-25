package configuration;

import gameobjects.fish.PlayerFish;

import java.util.ArrayList;
import java.util.List;

import ai.AIConfiguration;

public class GameConfiguration {

	public GameConfiguration() {
		players = new ArrayList<PlayerFish>();
		playersAlive = new ArrayList<PlayerFish>();
	}

	public AIConfiguration aiconf;
	public String gamename = "Mind Your Fish";
	public ArrayList<PlayerFish> players, playersAlive;
	public int winLimit = 3;

}
