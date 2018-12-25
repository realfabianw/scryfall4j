package de.ics.scryfall.mtg.card;

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
	private final String uniqueId;
	private final String name;

	public RelatedCard(JsonObject jObject) {
		this.uniqueId = stringJsonResponse(jObject, "id");
		this.name = stringJsonResponse(jObject, "name");
	}

	public RelatedCard(String uniqueId, String name) {
		this.uniqueId = uniqueId;
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelatedCard other = (RelatedCard) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (uniqueId == null) {
			if (other.uniqueId != null)
				return false;
		} else if (!uniqueId.equals(other.uniqueId))
			return false;
		return true;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the uniqueId
	 */
	public String getUniqueId() {
		return uniqueId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((uniqueId == null) ? 0 : uniqueId.hashCode());
		return result;
	}

	private final String stringJsonResponse(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsString();
		} catch (Exception e) {
			return "";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RelatedCard [uniqueId=" + uniqueId + ", name=" + name + "]";
	}
}
