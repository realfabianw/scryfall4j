package de.ics.scryfall;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javafx.util.Pair;

/**
 * This class tries to implement the data-structure for all available
 * MTG-Cardtypes. Currently supported card types:
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
public class CardInformation {
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

	public CardInformation(JsonObject jObject) {
		this.uniqueId = stringJsonResponse(jObject, "id");
		this.oracleId = stringJsonResponse(jObject, "oracle_id");
		this.name = stringJsonResponse(jObject, "name");
		this.printedName = stringJsonResponse(jObject, "printed_name");
		this.languageCode = stringJsonResponse(jObject, "lang");
		this.scryfallUri = stringJsonResponse(jObject, "scryfall_uri");
		this.layout = stringJsonResponse(jObject, "layout");
		this.imageUri = largeImageJsonResponse(jObject, "image_uris");
		this.manaCost = stringJsonResponse(jObject, "mana_cost");
		this.cmc = doubleJsonResponse(jObject, "cmc");
		this.typeLine = stringJsonResponse(jObject, "type_line");
		this.printedTypeLine = stringJsonResponse(jObject, "printed_type_line");
		this.oracleText = stringJsonResponse(jObject, "oracle_text");
		this.printedText = stringJsonResponse(jObject, "printed_text");
		this.power = stringJsonResponse(jObject, "power");
		this.toughness = stringJsonResponse(jObject, "toughness");
		this.listColors = listStringJsonResponse(jObject, "colors");
		this.listColorIdentities = listStringJsonResponse(jObject, "color_identity");
		this.listCardFaces = parseListCardFaces(jObject, "card_faces");
		this.listRelatedCards = parseListRelatedCards(jObject, "all_parts");
		this.listLegalities = parseLegalities(jObject, "legalities");
		this.reserved = booleanJsonResponse(jObject, "reserved");
		this.foil = booleanJsonResponse(jObject, "foil");
		this.nonfoil = booleanJsonResponse(jObject, "nonfoil");
		this.oversized = booleanJsonResponse(jObject, "oversized");
		this.reprint = booleanJsonResponse(jObject, "reprint");
		this.setCode = stringJsonResponse(jObject, "set");
		this.collectorNumber = stringJsonResponse(jObject, "collector_number");
		this.digital = booleanJsonResponse(jObject, "digital");
		this.rarity = stringJsonResponse(jObject, "rarity");
		this.illustrationId = stringJsonResponse(jObject, "illustration_id");
		this.watermark = stringJsonResponse(jObject, "watermark");
		this.flavorText = stringJsonResponse(jObject, "flavor_text");
		this.artist = stringJsonResponse(jObject, "artist");
		this.frame = stringJsonResponse(jObject, "frame");
		this.fullArt = booleanJsonResponse(jObject, "full_art");
		this.borderColor = stringJsonResponse(jObject, "border_color");
		this.timeshifted = booleanJsonResponse(jObject, "timeshifted");
		this.colorshifted = booleanJsonResponse(jObject, "colorshifted");
		this.futureshifted = booleanJsonResponse(jObject, "futureshifted");
	}

	private final List<CardFace> parseListCardFaces(JsonObject jObject, String arrayName) {
		try {
			List<CardFace> listCardFaces = new ArrayList<>();
			JsonArray jArray = jObject.get(arrayName).getAsJsonArray();
			for (JsonElement jElement : jArray) {
				JsonObject jCardFace = jElement.getAsJsonObject();
				listCardFaces.add(new CardFace(jCardFace));
			}
			return listCardFaces;
		} catch (Exception e) {
			return new ArrayList<CardFace>();
		}
	}

	private final List<RelatedCard> parseListRelatedCards(JsonObject jObject, String arrayName) {
		try {
			List<RelatedCard> listRelatedCards = new ArrayList<>();
			JsonArray jArray = jObject.get(arrayName).getAsJsonArray();
			for (JsonElement jElement : jArray) {
				JsonObject jRelatedCard = jElement.getAsJsonObject();
				listRelatedCards.add(new RelatedCard(jRelatedCard));
			}
			return listRelatedCards;
		} catch (Exception e) {
			return new ArrayList<RelatedCard>();
		}
	}

	private final Legalities parseLegalities(JsonObject jObject, String fieldName) {
		try {
			return new Legalities(jObject.get(fieldName).getAsJsonObject());
		} catch (Exception e) {
			return new Legalities(false, false, false, false, false, false, false, false, false, false, false, false);
		}
	}

	/**
	 * Class that catches NullPointerExceptions while getting responses from the
	 * Json-File.
	 * 
	 * @param jObject
	 * @param fieldName
	 * @param trueString
	 * @param falseString
	 * @return
	 */
	private final boolean booleanJsonResponse(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsBoolean();
		} catch (Exception e) {
			return false;
		}
	}

	private final String stringJsonResponse(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsString();
		} catch (Exception e) {
			return "";
		}
	}

	private final double doubleJsonResponse(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsDouble();
		} catch (Exception e) {
			return 0;
		}
	}

	private final String largeImageJsonResponse(JsonObject jObject, String childObject) {
		try {
			return jObject.get(childObject).getAsJsonObject().get("large").getAsString();
		} catch (Exception e) {
			return "";
		}
	}

	private final List<String> listStringJsonResponse(JsonObject jObject, String arrayName) {
		List<String> listString = new ArrayList<>();
		try {
			JsonArray jArray = jObject.get("colors").getAsJsonArray();
			for (JsonElement jElement : jArray) {
				listString.add(jElement.getAsString());
			}
			return listString;
		} catch (Exception e) {
			return new ArrayList<String>();
		}
	}

	/**
	 * @return the uniqueId
	 */
	public String getUniqueId() {
		return uniqueId;
	}

	/**
	 * @return the oracleId
	 */
	public String getOracleId() {
		return oracleId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the printedName
	 */
	public String getPrintedName() {
		return printedName;
	}

	/**
	 * @return the languageCode
	 */
	public String getLanguageCode() {
		return languageCode;
	}

	/**
	 * @return the scryfallUri
	 */
	public String getScryfallUri() {
		return scryfallUri;
	}

	/**
	 * @return the layout
	 */
	public String getLayout() {
		return layout;
	}

	/**
	 * @return the imageUri
	 */
	public String getImageUri() {
		return imageUri;
	}

	/**
	 * @return the manaCost
	 */
	public String getManaCost() {
		return manaCost;
	}

	/**
	 * @return the cmc
	 */
	public double getCmc() {
		return cmc;
	}

	/**
	 * @return the typeLine
	 */
	public String getTypeLine() {
		return typeLine;
	}

	/**
	 * @return the printedTypeLine
	 */
	public String getPrintedTypeLine() {
		return printedTypeLine;
	}

	/**
	 * @return the oracleText
	 */
	public String getOracleText() {
		return oracleText;
	}

	/**
	 * @return the printedText
	 */
	public String getPrintedText() {
		return printedText;
	}

	/**
	 * @return the power
	 */
	public String getPower() {
		return power;
	}

	/**
	 * @return the toughness
	 */
	public String getToughness() {
		return toughness;
	}

	/**
	 * @return the listColors
	 */
	public List<String> getListColors() {
		return listColors;
	}

	/**
	 * @return the listColorIdentities
	 */
	public List<String> getListColorIdentities() {
		return listColorIdentities;
	}

	/**
	 * @return the listCardFaces
	 */
	public List<CardFace> getListCardFaces() {
		return listCardFaces;
	}

	/**
	 * @return the listLegalities
	 */
	public Legalities getListLegalities() {
		return listLegalities;
	}

	/**
	 * @return the reserved
	 */
	public boolean isReserved() {
		return reserved;
	}

	/**
	 * @return the foil
	 */
	public boolean isFoil() {
		return foil;
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
	 * @return the setCode
	 */
	public String getSetCode() {
		return setCode;
	}

	/**
	 * @return the collectorNumber
	 */
	public String getCollectorNumber() {
		return collectorNumber;
	}

	/**
	 * @return the digital
	 */
	public boolean isDigital() {
		return digital;
	}

	/**
	 * @return the rarity
	 */
	public String getRarity() {
		return rarity;
	}

	/**
	 * @return the illustrationId
	 */
	public String getIllustrationId() {
		return illustrationId;
	}

	/**
	 * @return the watermark
	 */
	public String getWatermark() {
		return watermark;
	}

	/**
	 * @return the flavorText
	 */
	public String getFlavorText() {
		return flavorText;
	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @return the frame
	 */
	public String getFrame() {
		return frame;
	}

	/**
	 * @return the fullArt
	 */
	public boolean isFullArt() {
		return fullArt;
	}

	/**
	 * @return the borderColor
	 */
	public String getBorderColor() {
		return borderColor;
	}

	/**
	 * @return the timeshifted
	 */
	public boolean isTimeshifted() {
		return timeshifted;
	}

	/**
	 * @return the colorshifted
	 */
	public boolean isColorshifted() {
		return colorshifted;
	}

	/**
	 * @return the futureshifted
	 */
	public boolean isFutureshifted() {
		return futureshifted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CardInformation [uniqueId=" + uniqueId + ", oracleId=" + oracleId + ", name=" + name + ", printedName="
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uniqueId == null) ? 0 : uniqueId.hashCode());
		return result;
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
		CardInformation other = (CardInformation) obj;
		if (uniqueId == null) {
			if (other.uniqueId != null)
				return false;
		} else if (!uniqueId.equals(other.uniqueId))
			return false;
		return true;
	}

	/**
	 * @return the listRelatedCards
	 */
	public List<RelatedCard> getListRelatedCards() {
		return listRelatedCards;
	}
}
