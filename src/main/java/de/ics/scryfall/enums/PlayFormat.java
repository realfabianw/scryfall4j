package de.ics.scryfall.enums;

/**
 * This enum contains all play formats as of february 2019
 * 
 * @see https://scryfall.com/docs/api/cards
 * @author QUE
 *
 */
public enum PlayFormat {
	STANDARD("standard"), FUTURE("future"), MODERN("modern"), LEGACY("legacy"), VINTAGE("vintage"),
	COMMANDER("commander"), FRONTIER("frontier"), PAUPER("pauper"), PENNY("penny"), DUEL_COMMANDER("duel"),
	OLDSCHOOL("oldschool"), ONE_VS_ONE("1v1"), BRAWL("brawl");

	public static PlayFormat parseId(String id) {
		for (PlayFormat e : PlayFormat.values()) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	private final String id;

	private PlayFormat(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
