package de.ics.scryfall.mtg.card;

public class Link {
	private final String description;
	private final String url;

	public Link(String description, String url) {
		this.description = description;
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public String toString() {
		return "Link [description=" + description + ", url=" + url + "]";
	}

}
