package de.ics.scryfall.set;

import com.google.gson.JsonObject;

import de.ics.scryfall.io.JsonHelper;

/**
 * This class combines all information of a set to identify and display it.
 * 
 * @author QUE
 *
 */
public class Set {
	private final String code;
	private final String name;
	private final String scryfallUri;
	private final String setType;
	private final int cardCount;
	private final boolean digital;
	private final boolean foilOnly;
	private final String iconUri;

	public Set(JsonObject jObject) {
		this.code = JsonHelper.stringJsonResponse(jObject, "code");
		this.name = JsonHelper.stringJsonResponse(jObject, "name");
		this.scryfallUri = JsonHelper.stringJsonResponse(jObject, "scryfall_uri");
		this.setType = JsonHelper.stringJsonResponse(jObject, "set_type");
		this.cardCount = JsonHelper.integerJsonResponse(jObject, "card_count");
		this.digital = JsonHelper.booleanJsonResponse(jObject, "digital");
		this.foilOnly = JsonHelper.booleanJsonResponse(jObject, "foil_only");
		this.iconUri = JsonHelper.stringJsonResponse(jObject, "icon_svg_uri");
	}

	public Set(String code, String name, String scryfallUri, String setType, int cardCount, boolean digital,
			boolean foilOnly, String iconUri) {
		this.code = code;
		this.name = name;
		this.scryfallUri = scryfallUri;
		this.setType = setType;
		this.cardCount = cardCount;
		this.digital = digital;
		this.foilOnly = foilOnly;
		this.iconUri = iconUri;
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
		Set other = (Set) obj;
		if (cardCount != other.cardCount)
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (digital != other.digital)
			return false;
		if (foilOnly != other.foilOnly)
			return false;
		if (iconUri == null) {
			if (other.iconUri != null)
				return false;
		} else if (!iconUri.equals(other.iconUri))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (scryfallUri == null) {
			if (other.scryfallUri != null)
				return false;
		} else if (!scryfallUri.equals(other.scryfallUri))
			return false;
		if (setType == null) {
			if (other.setType != null)
				return false;
		} else if (!setType.equals(other.setType))
			return false;
		return true;
	}

	/**
	 * @return the cardCount
	 */
	public int getCardCount() {
		return cardCount;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the iconUri
	 */
	public String getIconUri() {
		return iconUri;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the scryfallUri
	 */
	public String getScryfallUri() {
		return scryfallUri;
	}

	/**
	 * @return the setType
	 */
	public String getSetType() {
		return setType;
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
		result = prime * result + cardCount;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + (digital ? 1231 : 1237);
		result = prime * result + (foilOnly ? 1231 : 1237);
		result = prime * result + ((iconUri == null) ? 0 : iconUri.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((scryfallUri == null) ? 0 : scryfallUri.hashCode());
		result = prime * result + ((setType == null) ? 0 : setType.hashCode());
		return result;
	}

	/**
	 * @return the digital
	 */
	public boolean isDigital() {
		return digital;
	}

	/**
	 * @return the foilOnly
	 */
	public boolean isFoilOnly() {
		return foilOnly;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Set [code=" + code + ", name=" + name + ", scryfallUri=" + scryfallUri + ", setType=" + setType
				+ ", cardCount=" + cardCount + ", digital=" + digital + ", foilOnly=" + foilOnly + ", iconUri="
				+ iconUri + "]";
	}
}
