package de.ics.scryfall.mtg.card;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
public class MtgCard {
	private final String jsonString;
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
	private Legality legality;
	private final boolean reserved;
	private final boolean foil;
	private final boolean nonFoil;
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
	private int edhrecRank;
	private BigDecimal priceUsd;
	private BigDecimal priceEur;
	private BigDecimal priceTix;
	private Set<Link> setLinks;

	public MtgCard(JsonObject jObject) {
		this.jsonString = jObject.toString();
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
		this.legality = JsonHelper.parseLegalities(jObject, "legalities");
		this.reserved = JsonHelper.booleanJsonResponse(jObject, "reserved");
		this.foil = JsonHelper.booleanJsonResponse(jObject, "foil");
		this.nonFoil = JsonHelper.booleanJsonResponse(jObject, "nonfoil");
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
		this.edhrecRank = JsonHelper.integerJsonResponse(jObject, "edhrec_rank");
		this.priceUsd = JsonHelper.bigDecimalJsonResponse(jObject, "usd");
		this.priceEur = JsonHelper.bigDecimalJsonResponse(jObject, "eur");
		this.priceTix = JsonHelper.bigDecimalJsonResponse(jObject, "tix");
		this.setLinks = new HashSet<>();
		this.setLinks.add(new Link("Gatherer",
				JsonHelper.stringJsonResponse(jObject.get("related_uris").getAsJsonObject(), "gatherer")));
		this.setLinks.add(new Link("TCGPlayer Decks",
				JsonHelper.stringJsonResponse(jObject.get("related_uris").getAsJsonObject(), "tcgplayer_decks")));
		this.setLinks.add(new Link("EDHREC",
				JsonHelper.stringJsonResponse(jObject.get("related_uris").getAsJsonObject(), "edhrec")));
		this.setLinks.add(new Link("MTGTOP8",
				JsonHelper.stringJsonResponse(jObject.get("related_uris").getAsJsonObject(), "mtgtop8")));
		this.setLinks.add(new Link("TCGPlayer",
				JsonHelper.stringJsonResponse(jObject.get("related_uris").getAsJsonObject(), "tcgplayer")));
		this.setLinks.add(new Link("Cardmarket",
				JsonHelper.stringJsonResponse(jObject.get("related_uris").getAsJsonObject(), "cardmarket")));
		this.setLinks.add(new Link("Cardhoarder",
				JsonHelper.stringJsonResponse(jObject.get("related_uris").getAsJsonObject(), "cardhoarder")));
	}

	public MtgCard(String jsonString, String uniqueId, String oracleId, String name, String printedName,
			String languageCode, String scryfallUri, String layout, String imageUri, String manaCost, double cmc,
			String typeLine, String printedTypeLine, String oracleText, String printedText, String power,
			String toughness, List<String> listColors, List<String> listColorIdentities, List<CardFace> listCardFaces,
			List<RelatedCard> listRelatedCards, Legality legality, boolean reserved, boolean foil, boolean nonFoil,
			boolean oversized, boolean reprint, String setCode, String collectorNumber, boolean digital, String rarity,
			String illustrationId, String watermark, String flavorText, String artist, String frame, boolean fullArt,
			String borderColor, boolean timeshifted, boolean colorshifted, boolean futureshifted, int edhrecRank,
			BigDecimal priceUsd, BigDecimal priceEur, BigDecimal priceTix, Set<Link> links) {
		this.jsonString = jsonString;
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
		this.legality = legality;
		this.reserved = reserved;
		this.foil = foil;
		this.nonFoil = nonFoil;
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
		this.edhrecRank = edhrecRank;
		this.priceUsd = priceUsd;
		this.priceEur = priceEur;
		this.priceTix = priceTix;
		this.setLinks = links;
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
		MtgCard other = (MtgCard) obj;
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
		if (legality == null) {
			if (other.legality != null)
				return false;
		} else if (!legality.equals(other.legality))
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
		if (nonFoil != other.nonFoil)
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

	public int getEdhrecRank() {
		return edhrecRank;
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
	 * @return the listLegalities
	 */
	public Legality getLegality() {
		return legality;
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

	public BigDecimal getPriceEur() {
		return priceEur;
	}

	public BigDecimal getPriceTix() {
		return priceTix;
	}

	public BigDecimal getPriceUsd() {
		return priceUsd;
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

	public Set<Link> getSetLinks() {
		return setLinks;
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
		result = prime * result + ((legality == null) ? 0 : legality.hashCode());
		result = prime * result + ((listRelatedCards == null) ? 0 : listRelatedCards.hashCode());
		result = prime * result + ((manaCost == null) ? 0 : manaCost.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (nonFoil ? 1231 : 1237);
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
	public boolean isNonFoil() {
		return nonFoil;
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

	@Override
	public String toString() {
		final int maxLen = 10;
		return "Card [" + (uniqueId != null ? "uniqueId=" + uniqueId + ", " : "")
				+ (oracleId != null ? "oracleId=" + oracleId + ", " : "") + (name != null ? "name=" + name + ", " : "")
				+ (printedName != null ? "printedName=" + printedName + ", " : "")
				+ (languageCode != null ? "languageCode=" + languageCode + ", " : "")
				+ (scryfallUri != null ? "scryfallUri=" + scryfallUri + ", " : "")
				+ (layout != null ? "layout=" + layout + ", " : "")
				+ (imageUri != null ? "imageUri=" + imageUri + ", " : "")
				+ (manaCost != null ? "manaCost=" + manaCost + ", " : "") + "cmc=" + cmc + ", "
				+ (typeLine != null ? "typeLine=" + typeLine + ", " : "")
				+ (printedTypeLine != null ? "printedTypeLine=" + printedTypeLine + ", " : "")
				+ (oracleText != null ? "oracleText=" + oracleText + ", " : "")
				+ (printedText != null ? "printedText=" + printedText + ", " : "")
				+ (power != null ? "power=" + power + ", " : "")
				+ (toughness != null ? "toughness=" + toughness + ", " : "")
				+ (listColors != null ? "listColors=" + toString(listColors, maxLen) + ", " : "")
				+ (listColorIdentities != null ? "listColorIdentities=" + toString(listColorIdentities, maxLen) + ", "
						: "")
				+ (listCardFaces != null ? "listCardFaces=" + toString(listCardFaces, maxLen) + ", " : "")
				+ (listRelatedCards != null ? "listRelatedCards=" + toString(listRelatedCards, maxLen) + ", " : "")
				+ (legality != null ? "legality=" + legality + ", " : "") + "reserved=" + reserved + ", foil=" + foil
				+ ", nonFoil=" + nonFoil + ", oversized=" + oversized + ", reprint=" + reprint + ", "
				+ (setCode != null ? "setCode=" + setCode + ", " : "")
				+ (collectorNumber != null ? "collectorNumber=" + collectorNumber + ", " : "") + "digital=" + digital
				+ ", " + (rarity != null ? "rarity=" + rarity + ", " : "")
				+ (illustrationId != null ? "illustrationId=" + illustrationId + ", " : "")
				+ (watermark != null ? "watermark=" + watermark + ", " : "")
				+ (flavorText != null ? "flavorText=" + flavorText + ", " : "")
				+ (artist != null ? "artist=" + artist + ", " : "") + (frame != null ? "frame=" + frame + ", " : "")
				+ "fullArt=" + fullArt + ", " + (borderColor != null ? "borderColor=" + borderColor + ", " : "")
				+ "timeshifted=" + timeshifted + ", colorshifted=" + colorshifted + ", futureshifted=" + futureshifted
				+ ", edhrecRank=" + edhrecRank + ", " + (priceUsd != null ? "priceUsd=" + priceUsd + ", " : "")
				+ (priceEur != null ? "priceEur=" + priceEur + ", " : "")
				+ (priceTix != null ? "priceTix=" + priceTix + ", " : "")
				+ (setLinks != null ? "links=" + toString(setLinks, maxLen) : "") + "]";
	}

	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}

	public void setLegality(Legality legality) {
		this.legality = legality;
	}

	public void setEdhrecRank(int edhrecRank) {
		this.edhrecRank = edhrecRank;
	}

	public void setPriceUsd(BigDecimal priceUsd) {
		this.priceUsd = priceUsd;
	}

	public void setPriceEur(BigDecimal priceEur) {
		this.priceEur = priceEur;
	}

	public void setPriceTix(BigDecimal priceTix) {
		this.priceTix = priceTix;
	}

	public void setSetLinks(Set<Link> setLinks) {
		this.setLinks = setLinks;
	}

	public String getJsonString() {
		return jsonString;
	}
}
