package de.ics.scryfall.enums;

/**
 * This Enum contains all border colors as of 23.02.2019
 * 
 * @see https://scryfall.com/docs/api/cards
 * @author QUE
 *
 */
public enum RelatedSite {
	TCGPLAYER("tcgplayer"), CARDMARKET("cardmarket"), CARDHOARDER("cardhoarder"), GATHERER("gatherer"),
	TCGPLAYER_DECKS("tcgplayer_decks"), EDHREC("edhrec"), MTGTOP8("mtgtop8");

	public static RelatedSite parseId(String id) {
		for (RelatedSite e : RelatedSite.values()) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	private final String id;

	private RelatedSite(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
