package de.ics.scryfall;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.LanguageCode;

import de.ics.scryfall.enums.BorderColor;
import de.ics.scryfall.enums.Frame;
import de.ics.scryfall.enums.FrameEffect;
import de.ics.scryfall.enums.Game;
import de.ics.scryfall.enums.ImageType;
import de.ics.scryfall.enums.Layout;
import de.ics.scryfall.enums.Legality;
import de.ics.scryfall.enums.PlayFormat;
import de.ics.scryfall.enums.PriceType;
import de.ics.scryfall.enums.Rarity;
import de.ics.scryfall.enums.RelatedSite;
import de.ics.scryfall.enums.Symbol;

/**
 * MtgCardInformation represents an "Magic: The Gathering"-Card as it is stored
 * on the scryfall database.
 * 
 * @see https://scryfall.com/docs/api/cards
 * @author QUE
 *
 */
public class MtgCardInformation {
	private String jsonString;
	private String id;
	private int arenaId;
	private int mtgoId;
	private int mtgoFoilId;
	private List<Integer> listMultiverseIds;
	private int tcgPlayerId;
	private String oracleId;
	private LanguageCode languageCode;
	private String printsApiSearchUrl;
	private String rulingsApiUrl;
	private String selfScryfallUrl;
	private String selfApiUrl;

	private List<RelatedCard> listAllParts;
	private List<CardFace> listCardFaces;
	private double cmc;
	private List<Symbol> listColors;
	private List<Symbol> listColorIdentities;
	private List<Symbol> listColorIndicators;
	private int edhrecRank;
	private boolean foilExists;
	private String handModifier;
	private Layout layout;
	private Map<PlayFormat, Legality> mapLegality;
	private String lifeModifier;
	private String loyalty;
	private String manaCost;
	private String name;
	private boolean nonFoilExists;
	private String oracleText;
	private boolean oversized;
	private String power;
	private boolean reserved;
	private String toughness;
	private String typeLine;

	private String artist;
	private BorderColor borderColor;
	private String collectorNumber;
	private boolean digitalCard;
	private String flavorText;
	private FrameEffect frameEffect;
	private Frame frame;
	private boolean fullArt;
	private List<Game> listGames;
	private boolean highResImageAvailable;
	private String illustrationId;
	private Map<ImageType, String> mapImageUrls;
	private Map<PriceType, BigDecimal> mapPricing;
	private String printedName;
	private String printedText;
	private String printedTypeLine;
	private boolean promo;
	private Map<RelatedSite, String> mapRelatedUrls;
	private Rarity rarity;
	private LocalDate releaseDate;
	private boolean reprint;
	private String setScryfallUrl;
	private String setName;
	private String setApiSearchUrl;
	private String setApiUrl;
	private String setCode;
	private boolean storySpotlight;
	private String watermark;

	public MtgCardInformation(JsonObject jObject) {
		this.jsonString = jObject.toString();
		this.id = JsonIO.parseString(jObject, "id");
		this.arenaId = JsonIO.parseInteger(jObject, "arena_id");
		this.mtgoId = JsonIO.parseInteger(jObject, "mtgo_id");
		this.mtgoFoilId = JsonIO.parseInteger(jObject, "mtgo_foil_id");
		this.listMultiverseIds = new ArrayList<>();
		for (JsonElement jElement : jObject.get("multiverse_ids").getAsJsonArray()) {
			listMultiverseIds.add(jElement.getAsInt());
		}
		this.tcgPlayerId = JsonIO.parseInteger(jObject, "tcgplayer_id");
		this.oracleId = JsonIO.parseString(jObject, "oracle_id");
		this.languageCode = ScryfallUtils.fromScryfallLanguageCode(JsonIO.parseString(jObject, "lang"));
		this.printsApiSearchUrl = JsonIO.parseString(jObject, "prints_search_uri");
		this.rulingsApiUrl = JsonIO.parseString(jObject, "rulings_uri");
		this.selfScryfallUrl = JsonIO.parseString(jObject, "scryfall_uri");
		this.selfApiUrl = JsonIO.parseString(jObject, "uri");

		this.listAllParts = new ArrayList<>();
		try {
			for (JsonElement jElement : jObject.get("all_parts").getAsJsonArray()) {
				listAllParts.add(new RelatedCard(jElement.getAsJsonObject()));
			}
		} catch (NullPointerException e) {

		}
		this.listCardFaces = new ArrayList<>();
		try {
			for (JsonElement jElement : jObject.get("card_faces").getAsJsonArray()) {
				listCardFaces.add(new CardFace(jElement.getAsJsonObject()));
			}
		} catch (NullPointerException e) {

		}
		this.cmc = JsonIO.parseDouble(jObject, "cmc");
		this.listColors = new ArrayList<>();
		try {
			for (JsonElement jElement : jObject.get("colors").getAsJsonArray()) {
				listColors.add(Symbol.parseId(jElement.getAsString()));
			}
		} catch (NullPointerException e) {

		}
		this.listColorIdentities = new ArrayList<>();
		for (JsonElement jElement : jObject.get("color_identity").getAsJsonArray()) {
			listColorIdentities.add(Symbol.parseId(jElement.getAsString()));
		}
		this.listColorIndicators = new ArrayList<>();
		try {
			for (JsonElement jElement : jObject.get("color_indicator").getAsJsonArray()) {
				listColorIndicators.add(Symbol.parseId(jElement.getAsString()));
			}
		} catch (NullPointerException e) {

		}
		this.edhrecRank = JsonIO.parseInteger(jObject, "edhrec_rank");
		this.foilExists = JsonIO.parseBoolean(jObject, "foil");
		this.handModifier = JsonIO.parseString(jObject, "hand_modifier");
		this.layout = Layout.parseId(JsonIO.parseString(jObject, "layout"));
		this.mapLegality = new HashMap<>();
		JsonObject jLegalities = jObject.get("legalities").getAsJsonObject();
		Set<String> setKeysLegalities = jLegalities.keySet();
		for (String key : setKeysLegalities) {
			mapLegality.put(PlayFormat.parseId(key), Legality.parseId(JsonIO.parseString(jLegalities, key)));
		}

		this.lifeModifier = JsonIO.parseString(jObject, "life_modifier");
		this.loyalty = JsonIO.parseString(jObject, "loyality");
		this.manaCost = JsonIO.parseString(jObject, "mana_cost");
		this.name = JsonIO.parseString(jObject, "name");
		this.nonFoilExists = JsonIO.parseBoolean(jObject, "nonfoil");
		this.oracleText = JsonIO.parseString(jObject, "oracle_text");
		this.oversized = JsonIO.parseBoolean(jObject, "oversized");
		this.power = JsonIO.parseString(jObject, "power");
		this.reserved = JsonIO.parseBoolean(jObject, "reserved");
		this.toughness = JsonIO.parseString(jObject, "toughness");
		this.typeLine = JsonIO.parseString(jObject, "type_line");

		this.artist = JsonIO.parseString(jObject, "artist");
		this.borderColor = BorderColor.parseId(JsonIO.parseString(jObject, "border_color"));
		this.collectorNumber = JsonIO.parseString(jObject, "collector_number");
		this.digitalCard = JsonIO.parseBoolean(jObject, "digital");
		this.flavorText = JsonIO.parseString(jObject, "flavor_text");
		try {
			this.frameEffect = FrameEffect.parseId(JsonIO.parseString(jObject, "frame_effect"));
		} catch (IllegalArgumentException e) {
			this.frameEffect = null;
		}
		this.frame = Frame.parseId(JsonIO.parseString(jObject, "frame"));
		this.fullArt = JsonIO.parseBoolean(jObject, "full_art");
		this.listGames = new ArrayList<>();
		for (JsonElement jElement : jObject.get("games").getAsJsonArray()) {
			listGames.add(Game.parseId(jElement.getAsString()));
		}
		this.highResImageAvailable = JsonIO.parseBoolean(jObject, "highres_image");
		this.illustrationId = JsonIO.parseString(jObject, "illustration_id");
		this.mapImageUrls = new HashMap<>();
		try {
			for (String key : jObject.get("image_uris").getAsJsonObject().keySet()) {
				mapImageUrls.put(ImageType.parseId(key),
						JsonIO.parseString(jObject.get("image_uris").getAsJsonObject(), key));
			}
		} catch (NullPointerException e) {

		}
		this.mapPricing = new HashMap<>();
		for (String key : jObject.get("prices").getAsJsonObject().keySet()) {
			mapPricing.put(PriceType.parseId(key),
					JsonIO.parseBigDecimal(jObject.get("prices").getAsJsonObject(), key));
		}
		this.printedName = JsonIO.parseString(jObject, "printed_name");
		this.printedText = JsonIO.parseString(jObject, "printed_text");
		this.printedTypeLine = JsonIO.parseString(jObject, "printed_type_line");
		this.promo = JsonIO.parseBoolean(jObject, "promo");
		this.mapRelatedUrls = new HashMap<>();
		for (String key : jObject.get("purchase_uris").getAsJsonObject().keySet()) {
			mapRelatedUrls.put(RelatedSite.parseId(key),
					JsonIO.parseString(jObject.get("related_uris").getAsJsonObject(), key));
		}
		this.rarity = Rarity.parseId(JsonIO.parseString(jObject, "rarity"));
		for (String key : jObject.get("related_uris").getAsJsonObject().keySet()) {
			mapRelatedUrls.put(RelatedSite.parseId(key),
					JsonIO.parseString(jObject.get("related_uris").getAsJsonObject(), key));
		}
		this.releaseDate = JsonIO.parseLocalDate(jObject, "released_at", DateTimeFormatter.ISO_DATE);
		this.reprint = JsonIO.parseBoolean(jObject, "reprint");
		this.setScryfallUrl = JsonIO.parseString(jObject, "scryfall_set_uri");
		this.setName = JsonIO.parseString(jObject, "set_name");
		this.setApiSearchUrl = JsonIO.parseString(jObject, "set_search_uri");
		this.setApiUrl = JsonIO.parseString(jObject, "set_uri");
		this.setCode = JsonIO.parseString(jObject, "set");
		this.storySpotlight = JsonIO.parseBoolean(jObject, "story_spotlight");
		this.watermark = JsonIO.parseString(jObject, "watermark");
	}

	public MtgCardInformation(String jsonString, String id, int arenaId, int mtgoId, int mtgoFoilId,
			List<Integer> listMultiverseIds, int tcgPlayerId, String oracleId, LanguageCode languageCode,
			String printsApiSearchUrl, String rulingsApiUrl, String selfScryfallUrl, String selfApiUrl,
			List<RelatedCard> listAllParts, List<CardFace> listCardFaces, double cmc, List<Symbol> listColors,
			List<Symbol> listColorIdentities, List<Symbol> listColorIndicators, int edhrecRank, boolean foilExists,
			String handModifier, Layout layout, Map<PlayFormat, Legality> mapLegality, String lifeModifier,
			String loyalty, String manaCost, String name, boolean nonFoilExists, String oracleText, boolean oversized,
			String power, boolean reserved, String toughness, String typeLine, String artist, BorderColor borderColor,
			String collectorNumber, boolean digitalCard, String flavorText, FrameEffect frameEffect, Frame frame,
			boolean fullArt, List<Game> listGames, boolean highResImageAvailable, String illustrationId,
			Map<ImageType, String> mapImageUrls, Map<PriceType, BigDecimal> mapPricing, String printedName,
			String printedText, String printedTypeLine, boolean promo, Map<RelatedSite, String> mapRelatedUrls,
			Rarity rarity, LocalDate releaseDate, boolean reprint, String setScryfallUrl, String setName,
			String setApiSearchUrl, String setApiUrl, String setCode, boolean storySpotlight, String watermark) {
		this.jsonString = jsonString;
		this.id = id;
		this.arenaId = arenaId;
		this.mtgoId = mtgoId;
		this.mtgoFoilId = mtgoFoilId;
		this.listMultiverseIds = listMultiverseIds;
		this.tcgPlayerId = tcgPlayerId;
		this.oracleId = oracleId;
		this.languageCode = languageCode;
		this.printsApiSearchUrl = printsApiSearchUrl;
		this.rulingsApiUrl = rulingsApiUrl;
		this.selfScryfallUrl = selfScryfallUrl;
		this.selfApiUrl = selfApiUrl;
		this.listAllParts = listAllParts;
		this.listCardFaces = listCardFaces;
		this.cmc = cmc;
		this.listColors = listColors;
		this.listColorIdentities = listColorIdentities;
		this.listColorIndicators = listColorIndicators;
		this.edhrecRank = edhrecRank;
		this.foilExists = foilExists;
		this.handModifier = handModifier;
		this.layout = layout;
		this.mapLegality = mapLegality;
		this.lifeModifier = lifeModifier;
		this.loyalty = loyalty;
		this.manaCost = manaCost;
		this.name = name;
		this.nonFoilExists = nonFoilExists;
		this.oracleText = oracleText;
		this.oversized = oversized;
		this.power = power;
		this.reserved = reserved;
		this.toughness = toughness;
		this.typeLine = typeLine;
		this.artist = artist;
		this.borderColor = borderColor;
		this.collectorNumber = collectorNumber;
		this.digitalCard = digitalCard;
		this.flavorText = flavorText;
		this.frameEffect = frameEffect;
		this.frame = frame;
		this.fullArt = fullArt;
		this.listGames = listGames;
		this.highResImageAvailable = highResImageAvailable;
		this.illustrationId = illustrationId;
		this.mapImageUrls = mapImageUrls;
		this.mapPricing = mapPricing;
		this.printedName = printedName;
		this.printedText = printedText;
		this.printedTypeLine = printedTypeLine;
		this.promo = promo;
		this.mapRelatedUrls = mapRelatedUrls;
		this.rarity = rarity;
		this.releaseDate = releaseDate;
		this.reprint = reprint;
		this.setScryfallUrl = setScryfallUrl;
		this.setName = setName;
		this.setApiSearchUrl = setApiSearchUrl;
		this.setApiUrl = setApiUrl;
		this.setCode = setCode;
		this.storySpotlight = storySpotlight;
		this.watermark = watermark;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MtgCardInformation other = (MtgCardInformation) obj;
		if (arenaId != other.arenaId)
			return false;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (borderColor != other.borderColor)
			return false;
		if (Double.doubleToLongBits(cmc) != Double.doubleToLongBits(other.cmc))
			return false;
		if (collectorNumber == null) {
			if (other.collectorNumber != null)
				return false;
		} else if (!collectorNumber.equals(other.collectorNumber))
			return false;
		if (digitalCard != other.digitalCard)
			return false;
		if (edhrecRank != other.edhrecRank)
			return false;
		if (flavorText == null) {
			if (other.flavorText != null)
				return false;
		} else if (!flavorText.equals(other.flavorText))
			return false;
		if (foilExists != other.foilExists)
			return false;
		if (frame != other.frame)
			return false;
		if (frameEffect != other.frameEffect)
			return false;
		if (fullArt != other.fullArt)
			return false;
		if (handModifier == null) {
			if (other.handModifier != null)
				return false;
		} else if (!handModifier.equals(other.handModifier))
			return false;
		if (highResImageAvailable != other.highResImageAvailable)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (illustrationId == null) {
			if (other.illustrationId != null)
				return false;
		} else if (!illustrationId.equals(other.illustrationId))
			return false;
		if (jsonString == null) {
			if (other.jsonString != null)
				return false;
		} else if (!jsonString.equals(other.jsonString))
			return false;
		if (languageCode != other.languageCode)
			return false;
		if (layout != other.layout)
			return false;
		if (lifeModifier == null) {
			if (other.lifeModifier != null)
				return false;
		} else if (!lifeModifier.equals(other.lifeModifier))
			return false;
		if (listAllParts == null) {
			if (other.listAllParts != null)
				return false;
		} else if (!listAllParts.equals(other.listAllParts))
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
		if (listColorIndicators == null) {
			if (other.listColorIndicators != null)
				return false;
		} else if (!listColorIndicators.equals(other.listColorIndicators))
			return false;
		if (listColors == null) {
			if (other.listColors != null)
				return false;
		} else if (!listColors.equals(other.listColors))
			return false;
		if (listGames == null) {
			if (other.listGames != null)
				return false;
		} else if (!listGames.equals(other.listGames))
			return false;
		if (listMultiverseIds == null) {
			if (other.listMultiverseIds != null)
				return false;
		} else if (!listMultiverseIds.equals(other.listMultiverseIds))
			return false;
		if (loyalty == null) {
			if (other.loyalty != null)
				return false;
		} else if (!loyalty.equals(other.loyalty))
			return false;
		if (manaCost == null) {
			if (other.manaCost != null)
				return false;
		} else if (!manaCost.equals(other.manaCost))
			return false;
		if (mapImageUrls == null) {
			if (other.mapImageUrls != null)
				return false;
		} else if (!mapImageUrls.equals(other.mapImageUrls))
			return false;
		if (mapLegality == null) {
			if (other.mapLegality != null)
				return false;
		} else if (!mapLegality.equals(other.mapLegality))
			return false;
		if (mapPricing == null) {
			if (other.mapPricing != null)
				return false;
		} else if (!mapPricing.equals(other.mapPricing))
			return false;
		if (mapRelatedUrls == null) {
			if (other.mapRelatedUrls != null)
				return false;
		} else if (!mapRelatedUrls.equals(other.mapRelatedUrls))
			return false;
		if (mtgoFoilId != other.mtgoFoilId)
			return false;
		if (mtgoId != other.mtgoId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nonFoilExists != other.nonFoilExists)
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
		if (printsApiSearchUrl == null) {
			if (other.printsApiSearchUrl != null)
				return false;
		} else if (!printsApiSearchUrl.equals(other.printsApiSearchUrl))
			return false;
		if (promo != other.promo)
			return false;
		if (rarity != other.rarity)
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (reprint != other.reprint)
			return false;
		if (reserved != other.reserved)
			return false;
		if (rulingsApiUrl == null) {
			if (other.rulingsApiUrl != null)
				return false;
		} else if (!rulingsApiUrl.equals(other.rulingsApiUrl))
			return false;
		if (selfApiUrl == null) {
			if (other.selfApiUrl != null)
				return false;
		} else if (!selfApiUrl.equals(other.selfApiUrl))
			return false;
		if (selfScryfallUrl == null) {
			if (other.selfScryfallUrl != null)
				return false;
		} else if (!selfScryfallUrl.equals(other.selfScryfallUrl))
			return false;
		if (setApiSearchUrl == null) {
			if (other.setApiSearchUrl != null)
				return false;
		} else if (!setApiSearchUrl.equals(other.setApiSearchUrl))
			return false;
		if (setApiUrl == null) {
			if (other.setApiUrl != null)
				return false;
		} else if (!setApiUrl.equals(other.setApiUrl))
			return false;
		if (setCode == null) {
			if (other.setCode != null)
				return false;
		} else if (!setCode.equals(other.setCode))
			return false;
		if (setName == null) {
			if (other.setName != null)
				return false;
		} else if (!setName.equals(other.setName))
			return false;
		if (setScryfallUrl == null) {
			if (other.setScryfallUrl != null)
				return false;
		} else if (!setScryfallUrl.equals(other.setScryfallUrl))
			return false;
		if (storySpotlight != other.storySpotlight)
			return false;
		if (tcgPlayerId != other.tcgPlayerId)
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
		if (watermark == null) {
			if (other.watermark != null)
				return false;
		} else if (!watermark.equals(other.watermark))
			return false;
		return true;
	}

	public int getArenaId() {
		return arenaId;
	}

	public String getArtist() {
		return artist;
	}

	public BorderColor getBorderColor() {
		return borderColor;
	}

	public double getCmc() {
		return cmc;
	}

	public String getCollectorNumber() {
		return collectorNumber;
	}

	public int getEdhrecRank() {
		return edhrecRank;
	}

	public String getFlavorText() {
		return flavorText;
	}

	public Frame getFrame() {
		return frame;
	}

	public FrameEffect getFrameEffect() {
		return frameEffect;
	}

	public String getHandModifier() {
		return handModifier;
	}

	public String getId() {
		return id;
	}

	public String getIllustrationId() {
		return illustrationId;
	}

	public String getJsonString() {
		return jsonString;
	}

	public LanguageCode getLanguageCode() {
		return languageCode;
	}

	public Layout getLayout() {
		return layout;
	}

	public String getLifeModifier() {
		return lifeModifier;
	}

	public List<RelatedCard> getListAllParts() {
		return listAllParts;
	}

	public List<CardFace> getListCardFaces() {
		return listCardFaces;
	}

	public List<Symbol> getListColorIdentities() {
		return listColorIdentities;
	}

	public List<Symbol> getListColorIndicators() {
		return listColorIndicators;
	}

	public List<Symbol> getListColors() {
		return listColors;
	}

	public List<Game> getListGames() {
		return listGames;
	}

	public List<Integer> getListMultiverseIds() {
		return listMultiverseIds;
	}

	public String getLoyalty() {
		return loyalty;
	}

	public String getManaCost() {
		return manaCost;
	}

	public Map<ImageType, String> getMapImageUrls() {
		return mapImageUrls;
	}

	public Map<PlayFormat, Legality> getMapLegality() {
		return mapLegality;
	}

	public Map<PriceType, BigDecimal> getMapPricing() {
		return mapPricing;
	}

	public Map<RelatedSite, String> getMapRelatedUrls() {
		return mapRelatedUrls;
	}

	public int getMtgoFoilId() {
		return mtgoFoilId;
	}

	public int getMtgoId() {
		return mtgoId;
	}

	public String getName() {
		return name;
	}

	public String getOracleId() {
		return oracleId;
	}

	public String getOracleText() {
		return oracleText;
	}

	public String getPower() {
		return power;
	}

	public String getPrintedName() {
		return printedName;
	}

	public String getPrintedText() {
		return printedText;
	}

	public String getPrintedTypeLine() {
		return printedTypeLine;
	}

	public String getPrintsApiSearchUrl() {
		return printsApiSearchUrl;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public String getRulingsApiUrl() {
		return rulingsApiUrl;
	}

	public String getSelfApiUrl() {
		return selfApiUrl;
	}

	public String getSelfScryfallUrl() {
		return selfScryfallUrl;
	}

	public String getSetApiSearchUrl() {
		return setApiSearchUrl;
	}

	public String getSetApiUrl() {
		return setApiUrl;
	}

	public String getSetCode() {
		return setCode;
	}

	public String getSetName() {
		return setName;
	}

	public String getSetScryfallUrl() {
		return setScryfallUrl;
	}

	public int getTcgPlayerId() {
		return tcgPlayerId;
	}

	public String getToughness() {
		return toughness;
	}

	public String getTypeLine() {
		return typeLine;
	}

	public String getWatermark() {
		return watermark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + arenaId;
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((borderColor == null) ? 0 : borderColor.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cmc);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((collectorNumber == null) ? 0 : collectorNumber.hashCode());
		result = prime * result + (digitalCard ? 1231 : 1237);
		result = prime * result + edhrecRank;
		result = prime * result + ((flavorText == null) ? 0 : flavorText.hashCode());
		result = prime * result + (foilExists ? 1231 : 1237);
		result = prime * result + ((frame == null) ? 0 : frame.hashCode());
		result = prime * result + ((frameEffect == null) ? 0 : frameEffect.hashCode());
		result = prime * result + (fullArt ? 1231 : 1237);
		result = prime * result + ((handModifier == null) ? 0 : handModifier.hashCode());
		result = prime * result + (highResImageAvailable ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((illustrationId == null) ? 0 : illustrationId.hashCode());
		result = prime * result + ((jsonString == null) ? 0 : jsonString.hashCode());
		result = prime * result + ((languageCode == null) ? 0 : languageCode.hashCode());
		result = prime * result + ((layout == null) ? 0 : layout.hashCode());
		result = prime * result + ((lifeModifier == null) ? 0 : lifeModifier.hashCode());
		result = prime * result + ((listAllParts == null) ? 0 : listAllParts.hashCode());
		result = prime * result + ((listCardFaces == null) ? 0 : listCardFaces.hashCode());
		result = prime * result + ((listColorIdentities == null) ? 0 : listColorIdentities.hashCode());
		result = prime * result + ((listColorIndicators == null) ? 0 : listColorIndicators.hashCode());
		result = prime * result + ((listColors == null) ? 0 : listColors.hashCode());
		result = prime * result + ((listGames == null) ? 0 : listGames.hashCode());
		result = prime * result + ((listMultiverseIds == null) ? 0 : listMultiverseIds.hashCode());
		result = prime * result + ((loyalty == null) ? 0 : loyalty.hashCode());
		result = prime * result + ((manaCost == null) ? 0 : manaCost.hashCode());
		result = prime * result + ((mapImageUrls == null) ? 0 : mapImageUrls.hashCode());
		result = prime * result + ((mapLegality == null) ? 0 : mapLegality.hashCode());
		result = prime * result + ((mapPricing == null) ? 0 : mapPricing.hashCode());
		result = prime * result + ((mapRelatedUrls == null) ? 0 : mapRelatedUrls.hashCode());
		result = prime * result + mtgoFoilId;
		result = prime * result + mtgoId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (nonFoilExists ? 1231 : 1237);
		result = prime * result + ((oracleId == null) ? 0 : oracleId.hashCode());
		result = prime * result + ((oracleText == null) ? 0 : oracleText.hashCode());
		result = prime * result + (oversized ? 1231 : 1237);
		result = prime * result + ((power == null) ? 0 : power.hashCode());
		result = prime * result + ((printedName == null) ? 0 : printedName.hashCode());
		result = prime * result + ((printedText == null) ? 0 : printedText.hashCode());
		result = prime * result + ((printedTypeLine == null) ? 0 : printedTypeLine.hashCode());
		result = prime * result + ((printsApiSearchUrl == null) ? 0 : printsApiSearchUrl.hashCode());
		result = prime * result + (promo ? 1231 : 1237);
		result = prime * result + ((rarity == null) ? 0 : rarity.hashCode());
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result + (reprint ? 1231 : 1237);
		result = prime * result + (reserved ? 1231 : 1237);
		result = prime * result + ((rulingsApiUrl == null) ? 0 : rulingsApiUrl.hashCode());
		result = prime * result + ((selfApiUrl == null) ? 0 : selfApiUrl.hashCode());
		result = prime * result + ((selfScryfallUrl == null) ? 0 : selfScryfallUrl.hashCode());
		result = prime * result + ((setApiSearchUrl == null) ? 0 : setApiSearchUrl.hashCode());
		result = prime * result + ((setApiUrl == null) ? 0 : setApiUrl.hashCode());
		result = prime * result + ((setCode == null) ? 0 : setCode.hashCode());
		result = prime * result + ((setName == null) ? 0 : setName.hashCode());
		result = prime * result + ((setScryfallUrl == null) ? 0 : setScryfallUrl.hashCode());
		result = prime * result + (storySpotlight ? 1231 : 1237);
		result = prime * result + tcgPlayerId;
		result = prime * result + ((toughness == null) ? 0 : toughness.hashCode());
		result = prime * result + ((typeLine == null) ? 0 : typeLine.hashCode());
		result = prime * result + ((watermark == null) ? 0 : watermark.hashCode());
		return result;
	}

	public boolean isDigitalCard() {
		return digitalCard;
	}

	public boolean isFoilExists() {
		return foilExists;
	}

	public boolean isFullArt() {
		return fullArt;
	}

	public boolean isHighResImageAvailable() {
		return highResImageAvailable;
	}

	public boolean isNonFoilExists() {
		return nonFoilExists;
	}

	public boolean isOversized() {
		return oversized;
	}

	public boolean isPromo() {
		return promo;
	}

	public boolean isReprint() {
		return reprint;
	}

	public boolean isReserved() {
		return reserved;
	}

	public boolean isStorySpotlight() {
		return storySpotlight;
	}

	public void setArenaId(int arenaId) {
		this.arenaId = arenaId;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setBorderColor(BorderColor borderColor) {
		this.borderColor = borderColor;
	}

	public void setCmc(double cmc) {
		this.cmc = cmc;
	}

	public void setCollectorNumber(String collectorNumber) {
		this.collectorNumber = collectorNumber;
	}

	public void setDigitalCard(boolean digitalCard) {
		this.digitalCard = digitalCard;
	}

	public void setEdhrecRank(int edhrecRank) {
		this.edhrecRank = edhrecRank;
	}

	public void setFlavorText(String flavorText) {
		this.flavorText = flavorText;
	}

	public void setFoilExists(boolean foilExists) {
		this.foilExists = foilExists;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public void setFrameEffect(FrameEffect frameEffect) {
		this.frameEffect = frameEffect;
	}

	public void setFullArt(boolean fullArt) {
		this.fullArt = fullArt;
	}

	public void setHandModifier(String handModifier) {
		this.handModifier = handModifier;
	}

	public void setHighResImageAvailable(boolean highResImageAvailable) {
		this.highResImageAvailable = highResImageAvailable;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIllustrationId(String illustrationId) {
		this.illustrationId = illustrationId;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public void setLanguageCode(LanguageCode languageCode) {
		this.languageCode = languageCode;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public void setLifeModifier(String lifeModifier) {
		this.lifeModifier = lifeModifier;
	}

	public void setListAllParts(List<RelatedCard> listAllParts) {
		this.listAllParts = listAllParts;
	}

	public void setListCardFaces(List<CardFace> listCardFaces) {
		this.listCardFaces = listCardFaces;
	}

	public void setListColorIdentities(List<Symbol> listColorIdentities) {
		this.listColorIdentities = listColorIdentities;
	}

	public void setListColorIndicators(List<Symbol> listColorIndicators) {
		this.listColorIndicators = listColorIndicators;
	}

	public void setListColors(List<Symbol> listColors) {
		this.listColors = listColors;
	}

	public void setListGames(List<Game> listGames) {
		this.listGames = listGames;
	}

	public void setListMultiverseIds(List<Integer> listMultiverseIds) {
		this.listMultiverseIds = listMultiverseIds;
	}

	public void setLoyalty(String loyalty) {
		this.loyalty = loyalty;
	}

	public void setManaCost(String manaCost) {
		this.manaCost = manaCost;
	}

	public void setMapImageUrls(Map<ImageType, String> mapImageUrls) {
		this.mapImageUrls = mapImageUrls;
	}

	public void setMapLegality(Map<PlayFormat, Legality> mapLegality) {
		this.mapLegality = mapLegality;
	}

	public void setMapPricing(Map<PriceType, BigDecimal> mapPricing) {
		this.mapPricing = mapPricing;
	}

	public void setMapRelatedUrls(Map<RelatedSite, String> mapRelatedUrls) {
		this.mapRelatedUrls = mapRelatedUrls;
	}

	public void setMtgoFoilId(int mtgoFoilId) {
		this.mtgoFoilId = mtgoFoilId;
	}

	public void setMtgoId(int mtgoId) {
		this.mtgoId = mtgoId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNonFoilExists(boolean nonFoilExists) {
		this.nonFoilExists = nonFoilExists;
	}

	public void setOracleId(String oracleId) {
		this.oracleId = oracleId;
	}

	public void setOracleText(String oracleText) {
		this.oracleText = oracleText;
	}

	public void setOversized(boolean oversized) {
		this.oversized = oversized;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public void setPrintedName(String printedName) {
		this.printedName = printedName;
	}

	public void setPrintedText(String printedText) {
		this.printedText = printedText;
	}

	public void setPrintedTypeLine(String printedTypeLine) {
		this.printedTypeLine = printedTypeLine;
	}

	public void setPrintsApiSearchUrl(String printsApiSearchUrl) {
		this.printsApiSearchUrl = printsApiSearchUrl;
	}

	public void setPromo(boolean promo) {
		this.promo = promo;
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setReprint(boolean reprint) {
		this.reprint = reprint;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public void setRulingsApiUrl(String rulingsApiUrl) {
		this.rulingsApiUrl = rulingsApiUrl;
	}

	public void setSelfApiUrl(String selfApiUrl) {
		this.selfApiUrl = selfApiUrl;
	}

	public void setSelfScryfallUrl(String selfScryfallUrl) {
		this.selfScryfallUrl = selfScryfallUrl;
	}

	public void setSetApiSearchUrl(String setApiSearchUrl) {
		this.setApiSearchUrl = setApiSearchUrl;
	}

	public void setSetApiUrl(String setApiUrl) {
		this.setApiUrl = setApiUrl;
	}

	public void setSetCode(String setCode) {
		this.setCode = setCode;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

	public void setSetScryfallUrl(String setScryfallUrl) {
		this.setScryfallUrl = setScryfallUrl;
	}

	public void setStorySpotlight(boolean storySpotlight) {
		this.storySpotlight = storySpotlight;
	}

	public void setTcgPlayerId(int tcgPlayerId) {
		this.tcgPlayerId = tcgPlayerId;
	}

	public void setToughness(String toughness) {
		this.toughness = toughness;
	}

	public void setTypeLine(String typeLine) {
		this.typeLine = typeLine;
	}

	public void setWatermark(String watermark) {
		this.watermark = watermark;
	}

	@Override
	public String toString() {
		return "MtgCardInformation [" + (jsonString != null ? "jsonString=" + jsonString + ", " : "")
				+ (id != null ? "id=" + id + ", " : "") + "arenaId=" + arenaId + ", mtgoId=" + mtgoId + ", mtgoFoilId="
				+ mtgoFoilId + ", " + (listMultiverseIds != null ? "listMultiverseIds=" + listMultiverseIds + ", " : "")
				+ "tcgPlayerId=" + tcgPlayerId + ", " + (oracleId != null ? "oracleId=" + oracleId + ", " : "")
				+ (languageCode != null ? "languageCode=" + languageCode + ", " : "")
				+ (printsApiSearchUrl != null ? "printsApiSearchUrl=" + printsApiSearchUrl + ", " : "")
				+ (rulingsApiUrl != null ? "rulingsApiUrl=" + rulingsApiUrl + ", " : "")
				+ (selfScryfallUrl != null ? "selfScryfallUrl=" + selfScryfallUrl + ", " : "")
				+ (selfApiUrl != null ? "selfApiUrl=" + selfApiUrl + ", " : "")
				+ (listAllParts != null ? "listAllParts=" + listAllParts + ", " : "")
				+ (listCardFaces != null ? "listCardFaces=" + listCardFaces + ", " : "") + "cmc=" + cmc + ", "
				+ (listColors != null ? "listColors=" + listColors + ", " : "")
				+ (listColorIdentities != null ? "listColorIdentities=" + listColorIdentities + ", " : "")
				+ (listColorIndicators != null ? "listColorIndicators=" + listColorIndicators + ", " : "")
				+ "edhrecRank=" + edhrecRank + ", foilExists=" + foilExists + ", "
				+ (handModifier != null ? "handModifier=" + handModifier + ", " : "")
				+ (layout != null ? "layout=" + layout + ", " : "")
				+ (mapLegality != null ? "mapLegality=" + mapLegality + ", " : "")
				+ (lifeModifier != null ? "lifeModifier=" + lifeModifier + ", " : "")
				+ (loyalty != null ? "loyalty=" + loyalty + ", " : "")
				+ (manaCost != null ? "manaCost=" + manaCost + ", " : "") + (name != null ? "name=" + name + ", " : "")
				+ "nonFoilExists=" + nonFoilExists + ", "
				+ (oracleText != null ? "oracleText=" + oracleText + ", " : "") + "oversized=" + oversized + ", "
				+ (power != null ? "power=" + power + ", " : "") + "reserved=" + reserved + ", "
				+ (toughness != null ? "toughness=" + toughness + ", " : "")
				+ (typeLine != null ? "typeLine=" + typeLine + ", " : "")
				+ (artist != null ? "artist=" + artist + ", " : "")
				+ (borderColor != null ? "borderColor=" + borderColor + ", " : "")
				+ (collectorNumber != null ? "collectorNumber=" + collectorNumber + ", " : "") + "digitalCard="
				+ digitalCard + ", " + (flavorText != null ? "flavorText=" + flavorText + ", " : "")
				+ (frameEffect != null ? "frameEffect=" + frameEffect + ", " : "")
				+ (frame != null ? "frame=" + frame + ", " : "") + "fullArt=" + fullArt + ", "
				+ (listGames != null ? "listGames=" + listGames + ", " : "") + "highResImageAvailable="
				+ highResImageAvailable + ", "
				+ (illustrationId != null ? "illustrationId=" + illustrationId + ", " : "")
				+ (mapImageUrls != null ? "mapImageUrls=" + mapImageUrls + ", " : "")
				+ (mapPricing != null ? "mapPricing=" + mapPricing + ", " : "")
				+ (printedName != null ? "printedName=" + printedName + ", " : "")
				+ (printedText != null ? "printedText=" + printedText + ", " : "")
				+ (printedTypeLine != null ? "printedTypeLine=" + printedTypeLine + ", " : "") + "promo=" + promo + ", "
				+ (mapRelatedUrls != null ? "mapRelatedUrls=" + mapRelatedUrls + ", " : "")
				+ (rarity != null ? "rarity=" + rarity + ", " : "")
				+ (releaseDate != null ? "releaseDate=" + releaseDate + ", " : "") + "reprint=" + reprint + ", "
				+ (setScryfallUrl != null ? "setScryfallUrl=" + setScryfallUrl + ", " : "")
				+ (setName != null ? "setName=" + setName + ", " : "")
				+ (setApiSearchUrl != null ? "setApiSearchUrl=" + setApiSearchUrl + ", " : "")
				+ (setApiUrl != null ? "setApiUrl=" + setApiUrl + ", " : "")
				+ (setCode != null ? "setCode=" + setCode + ", " : "") + "storySpotlight=" + storySpotlight + ", "
				+ (watermark != null ? "watermark=" + watermark : "") + "]";
	}
}
