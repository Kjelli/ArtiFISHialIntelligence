package ai;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import ai.loader.AIFactory;
import ai.loader.AIFactoryLoader;
import ai.loader.InvalidAIException;
import ai.loader.MaliciousAICodeException;
import ai.loader.ObjectNotImplementingAIException;

public class AIConfiguration {
	List<AIFactory<? extends AI>> ais;

	public AIConfiguration() {
		ais = new ArrayList<AIFactory<? extends AI>>();
	}

	public boolean prompt() {
		JFileChooser filechooser = new JFileChooser();
		boolean chosen = filechooser.showOpenDialog(null) == 0;

		// File was picked
		if (chosen) {
			// Success indicates true if ai was successfully loaded, false if
			// some exception occured
			boolean success = loadAI(filechooser.getSelectedFile().getPath());
			return success;
		}

		// File was not chosen
		return false;
	}

	public boolean loadAI(String filename) {
		try {
			AIFactory<?> aif = AIFactoryLoader.load(filename);
			if (aif != null) {
				ais.add(aif);
				return true;
			}
		} catch (ClassNotFoundException e) {
			// TODO popup warning
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO popup warning
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO popup warning
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO popup warning
			e.printStackTrace();
		} catch (MaliciousAICodeException e) {
			// TODO popup warning
			e.printStackTrace();
		} catch (ObjectNotImplementingAIException e) {
			// TODO popup warning
			e.printStackTrace();
		} catch (InvalidAIException e) {
			// TODO popup warning
			e.printStackTrace();
		} catch (IOException e) {
			// TODO popup warning
			e.printStackTrace();
		}
		return false;
	}

	public List<AIFactory<? extends AI>> getAIs() {
		return ais;
	}
}
