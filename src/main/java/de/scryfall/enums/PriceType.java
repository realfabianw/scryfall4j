package de.scryfall.enums;

/**
 * This enum contains all price types as of february 2019
 * 
 * @see https://scryfall.com/docs/api/cards
 * @author QUE
 *
 */
public enum PriceType {
	USD("usd"), USD_FOIL("usd_foil"), EUR("eur"), TIX("tix");

	public static PriceType parseId(String id) {
		for (PriceType e : PriceType.values()) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	private final String id;

	private PriceType(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
