package de.ics.scryfall.enums;

/**
 * This Enum contains all card frames as of february 2019
 * 
 * @see https://scryfall.com/docs/api/layouts
 * @author QUE
 *
 */
public enum Frame {
	FRAME_1993("1993", "The original Magic card frame, starting from Limited Edition Alpha"),
	FRAME_1997("1997", "The updated classic frame starting from Mirage block"),
	FRAME_2003("2003", "The “modern” Magic card frame, introduced in Eighth Edition and Mirrodin block"),
	FRAME_2015("2015", "The holofoil-stamp Magic card frame, introduced in Magic 2015"),
	FRAME_FUTURE("future", "The frame used on cards from the future");

	public static Frame parseId(String id) {
		for (Frame e : Frame.values()) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}
	private final String id;

	private final String description;

	private Frame(String id, String description) {
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
