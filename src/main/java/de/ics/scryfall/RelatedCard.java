package de.ics.scryfall;

import com.google.gson.JsonObject;

/**
 * Saves a reference to a related card. This is used to display all available
 * information of meld cards.
 * 
 * @see https://mtg.gamepedia.com/Meld
 * @see https://api.scryfall.com/cards/emn/96b?format=json&pretty=true
 * @author QUE
 *
 */
public class RelatedCard {
	/**
	 * The unique-id.
	 */
	private final String uniqueId;
	/**
	 * The name (always in english)
	 */
	private final String name;

	public RelatedCard(JsonObject jObject) {
		this.uniqueId = stringJsonResponse(jObject, "id");
		this.name = stringJsonResponse(jObject, "name");
	}

	private final String stringJsonResponse(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsString();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * @return the uniqueId
	 */
	public String getUniqueId() {
		return uniqueId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
