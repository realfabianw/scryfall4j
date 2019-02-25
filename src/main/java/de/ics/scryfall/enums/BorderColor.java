package de.ics.scryfall.enums;

/**
 * This enum contains all border colors as of february 2019
 * 
 * @see https://scryfall.com/docs/api/cards
 * @author QUE
 *
 */
public enum BorderColor {
	BLACK("black"), BORDERLESS("borderless"), GOLD("gold"), SILVER("silver"), WHITE("white");

	public static BorderColor parseId(String id) {
		for (BorderColor e : BorderColor.values()) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	private final String id;

	private BorderColor(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
