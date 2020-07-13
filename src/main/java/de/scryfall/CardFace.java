package de.scryfall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import de.scryfall.enums.ImageType;
import de.scryfall.enums.Symbol;

/**
 * Multiface Cards contain several instances of CardFace.
 * 
 * @see https://scryfall.com/docs/api/cards#card-face-objects
 * @author QUE
 *
 */
public class CardFace {
	private String artist;
	private List<Symbol> listColorIndicators;
	private List<Symbol> listColors;
	private String flavorText;
	private String illustrationId;
	private Map<ImageType, String> mapImageUrls;
	private String loyalty;
	private String mana_cost;
	private String name;
	private String oracleText;
	private String power;
	private String printedName;
	private String printedText;
	private String printedTypeLine;
	private String toughness;
	private String typeLine;
	private String watermark;

	public CardFace(JsonObject jObject) {
		this.artist = JsonIO.parseString(jObject, "artist");
		this.listColorIndicators = new ArrayList<>();
		for (JsonElement jElement : jObject.get("color_indicator").getAsJsonArray()) {
			listColorIndicators.add(Symbol.parseId(jElement.getAsString()));
		}
		this.listColors = new ArrayList<>();
		for (JsonElement jElement : jObject.get("colors").getAsJsonArray()) {
			listColors.add(Symbol.parseId(jElement.getAsString()));
		}
		this.flavorText = JsonIO.parseString(jObject, "flavor_text");
		this.illustrationId = JsonIO.parseString(jObject, "illustration_id");
		this.mapImageUrls = new HashMap<>();
		for (String key : jObject.get("image_uris").getAsJsonObject().keySet()) {
			mapImageUrls.put(ImageType.parseId(key),
					JsonIO.parseString(jObject.get("image_uris").getAsJsonObject(), key));
		}
		this.loyalty = JsonIO.parseString(jObject, "loyalty");
		this.mana_cost = JsonIO.parseString(jObject, "mana_cost");
		this.name = JsonIO.parseString(jObject, "name");
		this.oracleText = JsonIO.parseString(jObject, "oracle_text");
		this.power = JsonIO.parseString(jObject, "power");
		this.printedName = JsonIO.parseString(jObject, "printed_name");
		this.printedText = JsonIO.parseString(jObject, "printed_text");
		this.printedTypeLine = JsonIO.parseString(jObject, "printed_type_line");
		this.toughness = JsonIO.parseString(jObject, "toughness");
		this.typeLine = JsonIO.parseString(jObject, "type_line");
		this.watermark = JsonIO.parseString(jObject, "watermark");
	}

	public CardFace(String artist, List<Symbol> listColorIndicators, List<Symbol> listColors, String flavorText,
			String illustrationId, Map<ImageType, String> mapImageUrls, String loyalty, String mana_cost, String name,
			String oracleText, String power, String printedName, String printedText, String printedTypeLine,
			String toughness, String typeLine, String watermark) {
		this.artist = artist;
		this.listColorIndicators = listColorIndicators;
		this.listColors = listColors;
		this.flavorText = flavorText;
		this.illustrationId = illustrationId;
		this.mapImageUrls = mapImageUrls;
		this.loyalty = loyalty;
		this.mana_cost = mana_cost;
		this.name = name;
		this.oracleText = oracleText;
		this.power = power;
		this.printedName = printedName;
		this.printedText = printedText;
		this.printedTypeLine = printedTypeLine;
		this.toughness = toughness;
		this.typeLine = typeLine;
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
		CardFace other = (CardFace) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (flavorText == null) {
			if (other.flavorText != null)
				return false;
		} else if (!flavorText.equals(other.flavorText))
			return false;
		if (illustrationId == null) {
			if (other.illustrationId != null)
				return false;
		} else if (!illustrationId.equals(other.illustrationId))
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
		if (loyalty == null) {
			if (other.loyalty != null)
				return false;
		} else if (!loyalty.equals(other.loyalty))
			return false;
		if (mana_cost == null) {
			if (other.mana_cost != null)
				return false;
		} else if (!mana_cost.equals(other.mana_cost))
			return false;
		if (mapImageUrls == null) {
			if (other.mapImageUrls != null)
				return false;
		} else if (!mapImageUrls.equals(other.mapImageUrls))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (oracleText == null) {
			if (other.oracleText != null)
				return false;
		} else if (!oracleText.equals(other.oracleText))
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

	public String getArtist() {
		return artist;
	}

	public String getFlavorText() {
		return flavorText;
	}

	public String getIllustrationId() {
		return illustrationId;
	}

	public List<Symbol> getListColorIndicators() {
		return listColorIndicators;
	}

	public List<Symbol> getListColors() {
		return listColors;
	}

	public String getLoyalty() {
		return loyalty;
	}

	public String getMana_cost() {
		return mana_cost;
	}

	public Map<ImageType, String> getMapImageUrls() {
		return mapImageUrls;
	}

	public String getName() {
		return name;
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
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((flavorText == null) ? 0 : flavorText.hashCode());
		result = prime * result + ((illustrationId == null) ? 0 : illustrationId.hashCode());
		result = prime * result + ((listColorIndicators == null) ? 0 : listColorIndicators.hashCode());
		result = prime * result + ((listColors == null) ? 0 : listColors.hashCode());
		result = prime * result + ((loyalty == null) ? 0 : loyalty.hashCode());
		result = prime * result + ((mana_cost == null) ? 0 : mana_cost.hashCode());
		result = prime * result + ((mapImageUrls == null) ? 0 : mapImageUrls.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((oracleText == null) ? 0 : oracleText.hashCode());
		result = prime * result + ((power == null) ? 0 : power.hashCode());
		result = prime * result + ((printedName == null) ? 0 : printedName.hashCode());
		result = prime * result + ((printedText == null) ? 0 : printedText.hashCode());
		result = prime * result + ((printedTypeLine == null) ? 0 : printedTypeLine.hashCode());
		result = prime * result + ((toughness == null) ? 0 : toughness.hashCode());
		result = prime * result + ((typeLine == null) ? 0 : typeLine.hashCode());
		result = prime * result + ((watermark == null) ? 0 : watermark.hashCode());
		return result;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setFlavorText(String flavorText) {
		this.flavorText = flavorText;
	}

	public void setIllustrationId(String illustrationId) {
		this.illustrationId = illustrationId;
	}

	public void setListColorIndicators(List<Symbol> listColorIndicators) {
		this.listColorIndicators = listColorIndicators;
	}

	public void setListColors(List<Symbol> listColors) {
		this.listColors = listColors;
	}

	public void setLoyalty(String loyalty) {
		this.loyalty = loyalty;
	}

	public void setMana_cost(String mana_cost) {
		this.mana_cost = mana_cost;
	}

	public void setMapImageUrls(Map<ImageType, String> mapImageUrls) {
		this.mapImageUrls = mapImageUrls;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOracleText(String oracleText) {
		this.oracleText = oracleText;
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
		return "CardFace [" + (artist != null ? "artist=" + artist + ", " : "")
				+ (listColorIndicators != null ? "listColorIndicators=" + listColorIndicators + ", " : "")
				+ (listColors != null ? "listColors=" + listColors + ", " : "")
				+ (flavorText != null ? "flavorText=" + flavorText + ", " : "")
				+ (illustrationId != null ? "illustrationId=" + illustrationId + ", " : "")
				+ (mapImageUrls != null ? "mapImageUrls=" + mapImageUrls + ", " : "")
				+ (loyalty != null ? "loyalty=" + loyalty + ", " : "")
				+ (mana_cost != null ? "mana_cost=" + mana_cost + ", " : "")
				+ (name != null ? "name=" + name + ", " : "")
				+ (oracleText != null ? "oracleText=" + oracleText + ", " : "")
				+ (power != null ? "power=" + power + ", " : "")
				+ (printedName != null ? "printedName=" + printedName + ", " : "")
				+ (printedText != null ? "printedText=" + printedText + ", " : "")
				+ (printedTypeLine != null ? "printedTypeLine=" + printedTypeLine + ", " : "")
				+ (toughness != null ? "toughness=" + toughness + ", " : "")
				+ (typeLine != null ? "typeLine=" + typeLine + ", " : "")
				+ (watermark != null ? "watermark=" + watermark : "") + "]";
	}
}
