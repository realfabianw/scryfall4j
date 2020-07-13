package de.scryfall.enums;

/**
 * This enum contains all games as of february 2019
 * 
 * @see https://scryfall.com/docs/api/colors
 * @author QUE
 *
 */
public enum Game {
	PAPER("paper"), ARENA("arena"), MTGO("mtgo");

	public static Game parseId(String id) {
		for (Game e : Game.values()) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	private final String id;

	private Game(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
