package de.ics.scryfall;

import com.google.gson.JsonObject;

/**
 * RelatedCard represents a card that is closely related to this instances
 * parent. (Could be a meld card or a token)
 * 
 * @see https://scryfall.com/docs/api/cards#related-card-objects
 * @author QUE
 *
 */
public class RelatedCard {
	private String id;
	private String component;
	private String name;
	private String typeLine;
	private String selfUri;

	public RelatedCard(JsonObject jObject) {
		this.id = JsonIO.parseString(jObject, "id");
		this.component = JsonIO.parseString(jObject, "component");
		this.name = JsonIO.parseString(jObject, "name");
		this.typeLine = JsonIO.parseString(jObject, "type_line");
		this.selfUri = JsonIO.parseString(jObject, "uri");
	}

	public RelatedCard(String id, String component, String name, String typeLine, String selfUri) {
		this.id = id;
		this.component = component;
		this.name = name;
		this.typeLine = typeLine;
		this.selfUri = selfUri;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelatedCard other = (RelatedCard) obj;
		if (component == null) {
			if (other.component != null)
				return false;
		} else if (!component.equals(other.component))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (selfUri == null) {
			if (other.selfUri != null)
				return false;
		} else if (!selfUri.equals(other.selfUri))
			return false;
		if (typeLine == null) {
			if (other.typeLine != null)
				return false;
		} else if (!typeLine.equals(other.typeLine))
			return false;
		return true;
	}

	public String getComponent() {
		return component;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSelfUri() {
		return selfUri;
	}

	public String getTypeLine() {
		return typeLine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((component == null) ? 0 : component.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((selfUri == null) ? 0 : selfUri.hashCode());
		result = prime * result + ((typeLine == null) ? 0 : typeLine.hashCode());
		return result;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSelfUri(String selfUri) {
		this.selfUri = selfUri;
	}

	public void setTypeLine(String typeLine) {
		this.typeLine = typeLine;
	}

	@Override
	public String toString() {
		return "RelatedCard [" + (id != null ? "id=" + id + ", " : "")
				+ (component != null ? "component=" + component + ", " : "")
				+ (name != null ? "name=" + name + ", " : "") + (typeLine != null ? "typeLine=" + typeLine + ", " : "")
				+ (selfUri != null ? "selfUri=" + selfUri : "") + "]";
	}
}
