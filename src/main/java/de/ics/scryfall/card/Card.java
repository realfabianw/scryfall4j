package de.ics.scryfall.card;

import java.util.List;

import com.google.gson.JsonObject;

import de.ics.scryfall.io.JsonHelper;

/**
 * This class tries to implement the data-structure for all available MTG
 * card-layouts. Currently supported card types:
 * 
 * @see (normal cards - any language):
 *      https://scryfall.com/card/a25/141?utm_source
 * @see (flip cards - any language): https://scryfall.com/card/chk/2
 * @see (transform cards - any language): https://scryfall.com/card/dka/50/de
 * @see (split cards - any language): https://scryfall.com/card/dis/153
 * @see (meld cards - any language):
 *      https://scryfall.com/card/emn/96b?utm_source=mw_MTGWiki
 * @author QUE
 *
 */
public class Card {
	private final String uniqueId;
	private final String oracleId;
	private final String name;
	private final String printedName;
	private final String languageCode;
	private final String scryfallUri;
	private final String layout;
	private final String imageUri;
	private final String manaCost;
	private final double cmc;
	private final String typeLine;
	private final String printedTypeLine;
	private final String oracleText;
	private final String printedText;
	private final String power;
	private final String toughness;
	private final List<String> listColors;
	private final List<String> listColorIdentities;
	private final List<CardFace> listCardFaces;
	private final List<RelatedCard> listRelatedCards;
	private final Legalities listLegalities;
	private final boolean reserved;
	private final boolean foil;
	private final boolean nonfoil;
	private final boolean oversized;
	private final boolean reprint;
	private final String setCode;
	private final String collectorNumber;
	private final boolean digital;
	private final String rarity;
	private final String illustrationId;
	private final String watermark;
	private final String flavorText;
	private final String artist;
	private final String frame;
	private final boolean fullArt;
	private final String borderColor;
	private final boolean timeshifted;
	private final boolean colorshifted;
	private final boolean futureshifted;

	public Card(JsonObject jObject) {
		this.uniqueId = JsonHelper.stringJsonResponse(jObject, "id");
		this.oracleId = JsonHelper.stringJsonResponse(jObject, "oracle_id");
		this.name = JsonHelper.stringJsonResponse(jObject, "name");
		this.printedName = JsonHelper.stringJsonResponse(jObject, "printed_name");
		this.languageCode = JsonHelper.stringJsonResponse(jObject, "lang");
		this.scryfallUri = JsonHelper.stringJsonResponse(jObject, "scryfall_uri");
		this.layout = JsonHelper.stringJsonResponse(jObject, "layout");
		this.imageUri = JsonHelper.largeImageJsonResponse(jObject, "image_uris");
		this.manaCost = JsonHelper.stringJsonResponse(jObject, "mana_cost");
		this.cmc = JsonHelper.doubleJsonResponse(jObject, "cmc");
		this.typeLine = JsonHelper.stringJsonResponse(jObject, "type_line");
		this.printedTypeLine = JsonHelper.stringJsonResponse(jObject, "printed_type_line");
		this.oracleText = JsonHelper.stringJsonResponse(jObject, "oracle_text");
		this.printedText = JsonHelper.stringJsonResponse(jObject, "printed_text");
		this.power = JsonHelper.stringJsonResponse(jObject, "power");
		this.toughness = JsonHelper.stringJsonResponse(jObject, "toughness");
		this.listColors = JsonHelper.listStringJsonResponse(jObject, "colors");
		this.listColorIdentities = JsonHelper.listStringJsonResponse(jObject, "color_identity");
		this.listCardFaces = JsonHelper.parseListCardFaces(jObject, "card_faces");
		this.listRelatedCards = JsonHelper.parseListRelatedCards(jObject, "all_parts");
		this.listLegalities = JsonHelper.parseLegalities(jObject, "legalities");
		this.reserved = JsonHelper.booleanJsonResponse(jObject, "reserved");
		this.foil = JsonHelper.booleanJsonResponse(jObject, "foil");
		this.nonfoil = JsonHelper.booleanJsonResponse(jObject, "nonfoil");
		this.oversized = JsonHelper.booleanJsonResponse(jObject, "oversized");
		this.reprint = JsonHelper.booleanJsonResponse(jObject, "reprint");
		this.setCode = JsonHelper.stringJsonResponse(jObject, "set");
		this.collectorNumber = JsonHelper.stringJsonResponse(jObject, "collector_number");
		this.digital = JsonHelper.booleanJsonResponse(jObject, "digital");
		this.rarity = JsonHelper.stringJsonResponse(jObject, "rarity");
		this.illustrationId = JsonHelper.stringJsonResponse(jObject, "illustration_id");
		this.watermark = JsonHelper.stringJsonResponse(jObject, "watermark");
		this.flavorText = JsonHelper.stringJsonResponse(jObject, "flavor_text");
		this.artist = JsonHelper.stringJsonResponse(jObject, "artist");
		this.frame = JsonHelper.stringJsonResponse(jObject, "frame");
		this.fullArt = JsonHelper.booleanJsonResponse(jObject, "full_art");
		this.borderColor = JsonHelper.stringJsonResponse(jObject, "border_color");
		this.timeshifted = JsonHelper.booleanJsonResponse(jObject, "timeshifted");
		this.colorshifted = JsonHelper.booleanJsonResponse(jObject, "colorshifted");
		this.futureshifted = JsonHelper.booleanJsonResponse(jObject, "futureshifted");
	}

	public Card(String uniqueId, String oracleId, String name, String printedName, String languageCode,
			String scryfallUri, String layout, String imageUri, String manaCost, double cmc, String typeLine,
			String printedTypeLine, String oracleText, String printedText, String power, String toughness,
			List<String> listColors, List<String> listColorIdentities, List<CardFace> listCardFaces,
			List<RelatedCard> listRelatedCards, Legalities listLegalities, boolean reserved, boolean foil,
			boolean nonfoil, boolean oversized, boolean reprint, String setCode, String collectorNumber,
			boolean digital, String rarity, String illustrationId, String watermark, String flavorText, String artist,
			String frame, boolean fullArt, String borderColor, boolean timeshifted, boolean colorshifted,
			boolean futureshifted) {
		this.uniqueId = uniqueId;
		this.oracleId = oracleId;
		this.name = name;
		this.printedName = printedName;
		this.languageCode = languageCode;
		this.scryfallUri = scryfallUri;
		this.layout = layout;
		this.imageUri = imageUri;
		this.manaCost = manaCost;
		this.cmc = cmc;
		this.typeLine = typeLine;
		this.printedTypeLine = printedTypeLine;
		this.oracleText = oracleText;
		this.printedText = printedText;
		this.power = power;
		this.toughness = toughness;
		this.listColors = listColors;
		this.listColorIdentities = listColorIdentities;
		this.listCardFaces = listCardFaces;
		this.listRelatedCards = listRelatedCards;
		this.listLegalities = listLegalities;
		this.reserved = reserved;
		this.foil = foil;
		this.nonfoil = nonfoil;
		this.oversized = oversized;
		this.reprint = reprint;
		this.setCode = setCode;
		this.collectorNumber = collectorNumber;
		this.digital = digital;
		this.rarity = rarity;
		this.illustrationId = illustrationId;
		this.watermark = watermark;
		this.flavorText = flavorText;
		this.artist = artist;
		this.frame = frame;
		this.fullArt = fullArt;
		this.borderColor = borderColor;
		this.timeshifted = timeshifted;
		this.colorshifted = colorshifted;
		this.futureshifted = futureshifted;
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
		Card other = (Card) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (borderColor == null) {
			if (other.borderColor != null)
				return false;
		} else if (!borderColor.equals(other.borderColor))
			return false;
		if (Double.doubleToLongBits(cmc) != Double.doubleToLongBits(other.cmc))
			return false;
		if (collectorNumber == null) {
			if (other.collectorNumber != null)
				return false;
		} else if (!collectorNumber.equals(other.collectorNumber))
			return false;
		if (colorshifted != other.colorshifted)
			return false;
		if (digital != other.digital)
			return false;
		if (flavorText == null) {
			if (other.flavorText != null)
				return false;
		} else if (!flavorText.equals(other.flavorText))
			return false;
		if (foil != other.foil)
			return false;
		if (frame == null) {
			if (other.frame != null)
				return false;
		} else if (!frame.equals(other.frame))
			return false;
		if (fullArt != other.fullArt)
			return false;
		if (futureshifted != other.futureshifted)
			return false;
		if (illustrationId == null) {
			if (other.illustrationId != null)
				return false;
		} else if (!illustrationId.equals(other.illustrationId))
			return false;
		if (imageUri == null) {
			if (other.imageUri != null)
				return false;
		} else if (!imageUri.equals(other.imageUri))
			return false;
		if (languageCode == null) {
			if (other.languageCode != null)
				return false;
		} else if (!languageCode.equals(other.languageCode))
			return false;
		if (layout == null) {
			if (other.layout != null)
				return false;
		} else if (!layout.equals(other.layout))
			return false;
		if (listCardFaces == null) {
			if (other.listCardFaces != null)
				return false;
		} else if (!listCardFaces.equals(other.listCardFaces))
			return false;
		if (listColorIdentities == null) {
			if (other.listColorIdentities != null)
				return false;
		} else if (!listColorIdentities.equals(other.listColorIdentities))
			return false;
		if (listColors == null) {
			if (other.listColors != null)
				return false;
		} else if (!listColors.equals(other.listColors))
			return false;
		if (listLegalities == null) {
			if (other.listLegalities != null)
				return false;
		} else if (!listLegalities.equals(other.listLegalities))
			return false;
		if (listRelatedCards == null) {
			if (other.listRelatedCards != null)
				return false;
		} else if (!listRelatedCards.equals(other.listRelatedCards))
			return false;
		if (manaCost == null) {
			if (other.manaCost != null)
				return false;
		} else if (!manaCost.equals(other.manaCost))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nonfoil != other.nonfoil)
			return false;
		if (oracleId == null) {
			if (other.oracleId != null)
				return false;
		} else if (!oracleId.equals(other.oracleId))
			return false;
		if (oracleText == null) {
			if (other.oracleText != null)
				return false;
		} else if (!oracleText.equals(other.oracleText))
			return false;
		if (oversized != other.oversized)
			return false;
		if (power == null) {
			if (other.power != null)
				return false;
		} else if (!power.equals(other.power))
			return false;
		if (printedName == null) {
			if (other.printedName != null)
				return false;
		} else if (!printedName.equals(other.printedName))
			return false;
		if (printedText == null) {
			if (other.printedText != null)
				return false;
		} else if (!printedText.equals(other.printedText))
			return false;
		if (printedTypeLine == null) {
			if (other.printedTypeLine != null)
				return false;
		} else if (!printedTypeLine.equals(other.printedTypeLine))
			return false;
		if (rarity == null) {
			if (other.rarity != null)
				return false;
		} else if (!rarity.equals(other.rarity))
			return false;
		if (reprint != other.reprint)
			return false;
		if (reserved != other.reserved)
			return false;
		if (scryfallUri == null) {
			if (other.scryfallUri != null)
				return false;
		} else if (!scryfallUri.equals(other.scryfallUri))
			return false;
		if (setCode == null) {
			if (other.setCode != null)
				return false;
		} else if (!setCode.equals(other.setCode))
			return false;
		if (timeshifted != other.timeshifted)
			return false;
		if (toughness == null) {
			if (other.toughness != null)
				return false;
		} else if (!toughness.equals(other.toughness))
			return false;
		if (typeLine == null) {
			if (other.typeLine != null)
				return false;
		} else if (!typeLine.equals(other.typeLine))
			return false;
		if (uniqueId == null) {
			if (other.uniqueId != null)
				return false;
		} else if (!uniqueId.equals(other.uniqueId))
			return false;
		if (watermark == null) {
			if (other.watermark != null)
				return false;
		} else if (!watermark.equals(other.watermark))
			return false;
		return true;
	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @return the borderColor
	 */
	public String getBorderColor() {
		return borderColor;
	}

	/**
	 * @return the cmc
	 */
	public double getCmc() {
		return cmc;
	}

	/**
	 * @return the collectorNumber
	 */
	public String getCollectorNumber() {
		return collectorNumber;
	}

	/**
	 * @return the flavorText
	 */
	public String getFlavorText() {
		return flavorText;
	}

	/**
	 * @return the frame
	 */
	public String getFrame() {
		return frame;
	}

	/**
	 * @return the illustrationId
	 */
	public String getIllustrationId() {
		return illustrationId;
	}

	/**
	 * @return the imageUri
	 */
	public String getImageUri() {
		return imageUri;
	}

	/**
	 * @return the languageCode
	 */
	public String getLanguageCode() {
		return languageCode;
	}

	/**
	 * @return the layout
	 */
	public String getLayout() {
		return layout;
	}

	/**
	 * @return the listCardFaces
	 */
	public List<CardFace> getListCardFaces() {
		return listCardFaces;
	}

	/**
	 * @return the listColorIdentities
	 */
	public List<String> getListColorIdentities() {
		return listColorIdentities;
	}

	/**
	 * @return the listColors
	 */
	public List<String> getListColors() {
		return listColors;
	}

	/**
	 * @return the listLegalities
	 */
	public Legalities getListLegalities() {
		return listLegalities;
	}

	/**
	 * @return the listRelatedCards
	 */
	public List<RelatedCard> getListRelatedCards() {
		return listRelatedCards;
	}

	/**
	 * @return the manaCost
	 */
	public String getManaCost() {
		return manaCost;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the oracleId
	 */
	public String getOracleId() {
		return oracleId;
	}

	/**
	 * @return the oracleText
	 */
	public String getOracleText() {
		return oracleText;
	}

	/**
	 * @return the power
	 */
	public String getPower() {
		return power;
	}

	/**
	 * @return the printedName
	 */
	public String getPrintedName() {
		return printedName;
	}

	/**
	 * @return the printedText
	 */
	public String getPrintedText() {
		return printedText;
	}

	/**
	 * @return the printedTypeLine
	 */
	public String getPrintedTypeLine() {
		return printedTypeLine;
	}

	/**
	 * @return the rarity
	 */
	public String getRarity() {
		return rarity;
	}

	/**
	 * @return the scryfallUri
	 */
	public String getScryfallUri() {
		return scryfallUri;
	}

	/**
	 * @return the setCode
	 */
	public String getSetCode() {
		return setCode;
	}

	/**
	 * @return the toughness
	 */
	public String getToughness() {
		return toughness;
	}

	/**
	 * @return the typeLine
	 */
	public String getTypeLine() {
		return typeLine;
	}

	/**
	 * @return the uniqueId
	 */
	public String getUniqueId() {
		return uniqueId;
	}

	/**
	 * @return the watermark
	 */
	public String getWatermark() {
		return watermark;
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
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((borderColor == null) ? 0 : borderColor.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cmc);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((collectorNumber == null) ? 0 : collectorNumber.hashCode());
		result = prime * result + (colorshifted ? 1231 : 1237);
		result = prime * result + (digital ? 1231 : 1237);
		result = prime * result + ((flavorText == null) ? 0 : flavorText.hashCode());
		result = prime * result + (foil ? 1231 : 1237);
		result = prime * result + ((frame == null) ? 0 : frame.hashCode());
		result = prime * result + (fullArt ? 1231 : 1237);
		result = prime * result + (futureshifted ? 1231 : 1237);
		result = prime * result + ((illustrationId == null) ? 0 : illustrationId.hashCode());
		result = prime * result + ((imageUri == null) ? 0 : imageUri.hashCode());
		result = prime * result + ((languageCode == null) ? 0 : languageCode.hashCode());
		result = prime * result + ((layout == null) ? 0 : layout.hashCode());
		result = prime * result + ((listCardFaces == null) ? 0 : listCardFaces.hashCode());
		result = prime * result + ((listColorIdentities == null) ? 0 : listColorIdentities.hashCode());
		result = prime * result + ((listColors == null) ? 0 : listColors.hashCode());
		result = prime * result + ((listLegalities == null) ? 0 : listLegalities.hashCode());
		result = prime * result + ((listRelatedCards == null) ? 0 : listRelatedCards.hashCode());
		result = prime * result + ((manaCost == null) ? 0 : manaCost.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (nonfoil ? 1231 : 1237);
		result = prime * result + ((oracleId == null) ? 0 : oracleId.hashCode());
		result = prime * result + ((oracleText == null) ? 0 : oracleText.hashCode());
		result = prime * result + (oversized ? 1231 : 1237);
		result = prime * result + ((power == null) ? 0 : power.hashCode());
		result = prime * result + ((printedName == null) ? 0 : printedName.hashCode());
		result = prime * result + ((printedText == null) ? 0 : printedText.hashCode());
		result = prime * result + ((printedTypeLine == null) ? 0 : printedTypeLine.hashCode());
		result = prime * result + ((rarity == null) ? 0 : rarity.hashCode());
		result = prime * result + (reprint ? 1231 : 1237);
		result = prime * result + (reserved ? 1231 : 1237);
		result = prime * result + ((scryfallUri == null) ? 0 : scryfallUri.hashCode());
		result = prime * result + ((setCode == null) ? 0 : setCode.hashCode());
		result = prime * result + (timeshifted ? 1231 : 1237);
		result = prime * result + ((toughness == null) ? 0 : toughness.hashCode());
		result = prime * result + ((typeLine == null) ? 0 : typeLine.hashCode());
		result = prime * result + ((uniqueId == null) ? 0 : uniqueId.hashCode());
		result = prime * result + ((watermark == null) ? 0 : watermark.hashCode());
		return result;
	}

	/**
	 * @return the colorshifted
	 */
	public boolean isColorshifted() {
		return colorshifted;
	}

	/**
	 * @return the digital
	 */
	public boolean isDigital() {
		return digital;
	}

	/**
	 * @return the foil
	 */
	public boolean isFoil() {
		return foil;
	}

	/**
	 * @return the fullArt
	 */
	public boolean isFullArt() {
		return fullArt;
	}

	/**
	 * @return the futureshifted
	 */
	public boolean isFutureshifted() {
		return futureshifted;
	}

	/**
	 * @return the nonfoil
	 */
	public boolean isNonfoil() {
		return nonfoil;
	}

	/**
	 * @return the oversized
	 */
	public boolean isOversized() {
		return oversized;
	}

	/**
	 * @return the reprint
	 */
	public boolean isReprint() {
		return reprint;
	}

	/**
	 * @return the reserved
	 */
	public boolean isReserved() {
		return reserved;
	}

	/**
	 * @return the timeshifted
	 */
	public boolean isTimeshifted() {
		return timeshifted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Card [uniqueId=" + uniqueId + ", oracleId=" + oracleId + ", name=" + name + ", printedName="
				+ printedName + ", languageCode=" + languageCode + ", scryfallUri=" + scryfallUri + ", layout=" + layout
				+ ", imageUri=" + imageUri + ", manaCost=" + manaCost + ", cmc=" + cmc + ", typeLine=" + typeLine
				+ ", printedTypeLine=" + printedTypeLine + ", oracleText=" + oracleText + ", printedText=" + printedText
				+ ", power=" + power + ", toughness=" + toughness + ", listColors=" + listColors
				+ ", listColorIdentities=" + listColorIdentities + ", listCardFaces=" + listCardFaces
				+ ", listRelatedCards=" + listRelatedCards + ", listLegalities=" + listLegalities + ", reserved="
				+ reserved + ", foil=" + foil + ", nonfoil=" + nonfoil + ", oversized=" + oversized + ", reprint="
				+ reprint + ", setCode=" + setCode + ", collectorNumber=" + collectorNumber + ", digital=" + digital
				+ ", rarity=" + rarity + ", illustrationId=" + illustrationId + ", watermark=" + watermark
				+ ", flavorText=" + flavorText + ", artist=" + artist + ", frame=" + frame + ", fullArt=" + fullArt
				+ ", borderColor=" + borderColor + ", timeshifted=" + timeshifted + ", colorshifted=" + colorshifted
				+ ", futureshifted=" + futureshifted + "]";
	}
}