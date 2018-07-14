package de.ics.scryfall;

import com.google.gson.JsonObject;

/**
 * This class combines the most basic information of a set to identify and
 * display it.
 * 
 * @author QUE
 *
 */
public class SetInformation {
	/**
	 * The sets unique code.
	 */
	private final String code;
	/**
	 * The sets name.
	 */
	private final String name;
	/**
	 * The sets card count.
	 */
	private final int cardCount;
	/**
	 * A link to the sets icon (svg on server, png on disk)
	 */
	private final String iconUri;

	public SetInformation(JsonObject jObject) {
		this.code = jObject.get("code").getAsString();
		this.name = jObject.get("name").getAsString();
		this.cardCount = jObject.get("card_count").getAsInt();
		this.iconUri = jObject.get("icon_svg_uri").getAsString();
	}

	public SetInformation(String code, String name, int cardCount, String iconUri) {
		this.code = code;
		this.name = name;
		this.cardCount = cardCount;
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
		SetInformation other = (SetInformation) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Set [code=" + code + ", name=" + name + ", cardCount=" + cardCount + ", iconUri=" + iconUri + "]";
	}

}
