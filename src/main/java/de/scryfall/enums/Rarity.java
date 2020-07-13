package de.scryfall.enums;

/**
 * This enum contains all rarities as of february 2019
 * 
 * @see https://scryfall.com/docs/api/cards
 * @author QUE
 *
 */
public enum Rarity {
	COMMON("common"), UNCOMMON("uncommon"), RARE("rare"), MYTHIC("mythic");

	public static Rarity parseId(String id) {
		for (Rarity e : Rarity.values()) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	private final String id;

	private Rarity(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
