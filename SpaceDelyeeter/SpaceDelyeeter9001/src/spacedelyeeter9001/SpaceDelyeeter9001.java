package spacedelyeeter9001;

import spacedelyeeter9001.game.GameManager;

public class SpaceDelyeeter9001 {

	private static GameManager manager;

	public static void main(String[] args) {
		manager = new GameManager();
		manager.start();
	}

	public static GameManager getManager() {
		return manager;
	}

}
