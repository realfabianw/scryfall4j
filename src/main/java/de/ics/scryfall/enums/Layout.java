package de.ics.scryfall.enums;

/**
 * This enum contains all card layouts as of february 2019
 * 
 * @see https://scryfall.com/docs/api/layouts
 * @author QUE
 *
 */
public enum Layout {
	NORMAL("normal", "A standard Magic card with one face"), SPLIT("split", "A split-faced card"),
	FLIP("flip", "Cards that invert vertically with the flip keyword"),
	TRANSFORM("transform", "Double-sided cards that transform"),
	MELD("meld", "Cards with meld parts printed on the back"), LEVELER("leveler", "Cards with Level Up"),
	SAGA("saga", "Saga-type cards"), PLANAR("planar", "Plane and Phenomenon-type cards"),
	SCHEME("scheme", "Scheme-type cards"), VANGUARD("vanguard", "Vanguard-type cards"), TOKEN("token", "Token cards"),
	DOUBLEFACEDTOKEN("double_faced_token", "Tokens with another token printed on the back"),
	EMBLEM("emblem", "Emblem cards"), AUGMENT("augment", "Cards with Augment"), HOST("host", "Host-type cards"),
	ADVENTURE("adventure", "Cards with an Adventure spell part"), UNKNOWN("unknown", "Unknown layout");

	public static Layout parseId(String id) {
		for (Layout e : Layout.values()) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		return Layout.UNKNOWN;
	}

	private final String id;

	private final String description;

	private Layout(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getId() {
		return id;
	}
}
