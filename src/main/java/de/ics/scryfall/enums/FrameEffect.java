package de.ics.scryfall.enums;

/**
 * This enum contains all frame effects as of february 2019
 * 
 * @see https://scryfall.com/docs/api/layouts
 * @author QUE
 *
 */
public enum FrameEffect {
	LEGENDARY("legendary", "The legendary crown introduced in Dominaria"),
	MIRACLE("miracle", "The miracle frame effect"), NYXTOUCHED("nyxtouched", "The Nyx-touched frame effect"),
	DRAFT("draft", "The draft-matters frame effect"), DEVOID("devoid", "The Devoid frame effect"),
	TOMBSTONE("tombstone", "The Odyssey tombstone mark"), COLORSHIFTED("colorshifted", "A colorshifted frame"),
	SUNMOON_FC("sunmoonfc", "The sun and moon transform marks"),
	COMPASSLAND_FC("compasslandfc", "The compass and land transform marks"),
	ORIGINPWD_FC("originpwdfc", "The Origins and planeswalker transform marks"),
	MOONELDRAZID_FC("mooneldrazidfc", "The moon and Eldrazi transform marks");

	public static FrameEffect parseId(String id) {
		for (FrameEffect e : FrameEffect.values()) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	private final String id;

	private final String description;

	private FrameEffect(String id, String description) {
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
