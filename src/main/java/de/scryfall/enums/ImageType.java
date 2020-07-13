package de.scryfall.enums;

/**
 * This enum contains all image types as of february 2019
 * 
 * @see https://scryfall.com/docs/api/images
 * @author QUE
 *
 */
public enum ImageType {
	SMALL("small"), NORMAL("normal"), LARGE("large"), PNG("png"), ART_CROP("art_crop"), BORDER_CROP("border_crop");

	public static ImageType parseId(String id) {
		for (ImageType e : ImageType.values()) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	private final String id;

	private ImageType(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
