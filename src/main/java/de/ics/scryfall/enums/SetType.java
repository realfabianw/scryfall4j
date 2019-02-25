
package de.ics.scryfall.enums;

/**
 * This enum contains all set types as of february 2019
 * 
 * @see https://scryfall.com/docs/api/sets
 * @author QUE
 *
 */
public enum SetType {
	CORE("core", "A yearly Magic core set (Tenth Edition, etc)"),
	EXPANSION("expansion", "A rotational expansion set in a block (Zendikar, etc)"),
	MASTERS("masters", "A reprint set that contains no new cards (Modern Masters, etc)"),
	MASTERPIECE("masterpiece", "Masterpiece Series premium foil cards"),
	FROM_THE_VAULT("from_the_vault", "From the Vault gift sets"), SPELLBOOK("spellbook", "Spellbook series gift sets"),
	PREMIUM_DECK("premium_deck", "Premium Deck Series decks"), DUEL_DECK("duel_deck", "Duel Decks"),
	DRAFT_INNOVATION("draft_innovation", "Special draft sets, like Conspiracy and Battlebond"),
	TREASURE_CHEST("treasure_chest", "Magic Online treasure chest prize sets"),
	COMMANDER("commander", "Commander preconstructed decks"), PLANECHASE("planechase", "Planechase sets"),
	ARCHENEMY("archenemy", "Archenemy sets"), VANGUARD("vanguard", "Vanguard card sets"),
	FUNNY("funny", "A funny un-set or set with funny promos (Unglued, Happy Holidays, etc)"),
	STARTER("starter", "A starter/introductory set (Portal, etc)"), BOX("box", "A gift box set"),
	PROMO("promo", "A set that contains purely promotional cards"),
	TOKEN("token", "A set made up of tokens and emblems"),
	MEMORABILIA("memorabilia", "A set made up of gold-bordered, oversize, or trophy cards that are not legal");

	public static SetType parseId(String id) {
		for (SetType e : SetType.values()) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	private final String id;

	private final String description;

	private SetType(String id, String description) {
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
