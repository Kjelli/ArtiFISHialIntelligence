package configuration;

import gameobjects.fish.PlayerFish;

import java.util.ArrayList;
import java.util.List;

import ai.AIConfiguration;

public class GameConfiguration {

	public GameConfiguration() {
		players = new ArrayList<PlayerFish>();
		discardedPlayers = new ArrayList<PlayerFish>();
	}

	public AIConfiguration aiconf;
	public String gamename = "Mind Your Fish";
	public List<PlayerFish> players, discardedPlayers;
	public int winLimit = 3;

}
