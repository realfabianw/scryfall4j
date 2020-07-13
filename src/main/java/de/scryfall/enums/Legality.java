package de.scryfall.enums;

/**
 * This enum contains all legalities as of february 2019
 * 
 * @see https://scryfall.com/docs/api/cards
 * @author QUE
 *
 */
public enum Legality {
	LEGAL("legal"), NOTLEGAL("not_legal"), RESTRICTED("restricted"), BANNED("banned");

	public static Legality parseId(String id) {
		for (Legality e : Legality.values()) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	private final String id;

	private Legality(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
