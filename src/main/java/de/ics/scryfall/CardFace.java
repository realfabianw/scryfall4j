package de.ics.scryfall;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.gson.JsonObject;

/**
 * This class bundles all information of a cardface. Cardfaces exist on split-,
 * flip-, and transform cards.
 * 
 * @author QUE
 *
 */
public class CardFace {
	private final String name;
	private final String printedName;
	private final String manaCost;
	private final String typeLine;
	private final String printedTypeLine;
	private final String oracleText;
	private final String printedText;
	private final List<String> listColors;
	private final List<String> listColorIndicators;
	private final String power;
	private final String toughness;
	private final String flavorText;
	private final String illustrationId;
	private final Map<ImageType, String> imageUri;

	public CardFace(JsonObject jObject) {
		this.name = JsonHelper.stringJsonResponse(jObject, "name");
		this.printedName = JsonHelper.stringJsonResponse(jObject, "printed_name");
		this.manaCost = JsonHelper.stringJsonResponse(jObject, "mana_cost");
		this.typeLine = JsonHelper.stringJsonResponse(jObject, "type_line");
		this.printedTypeLine = JsonHelper.stringJsonResponse(jObject, "printed_type_line");
		this.oracleText = JsonHelper.stringJsonResponse(jObject, "oracle_text");
		this.printedText = JsonHelper.stringJsonResponse(jObject, "printed_text");
		this.listColors = JsonHelper.listStringJsonResponse(jObject, "colors");
		this.listColorIndicators = JsonHelper.listStringJsonResponse(jObject, "color_indicator");
		this.power = JsonHelper.stringJsonResponse(jObject, "power");
		this.toughness = JsonHelper.stringJsonResponse(jObject, "toughness");
		this.flavorText = JsonHelper.stringJsonResponse(jObject, "flavor_text");
		this.illustrationId = JsonHelper.stringJsonResponse(jObject, "illustration_id");
		this.imageUri = new HashMap<>();
		this.imageUri.put(ImageType.SMALL, JsonHelper.stringJsonResponse(jObject.get("image_uris").getAsJsonObject(), "small"));
		this.imageUri.put(ImageType.NORMAL, JsonHelper.stringJsonResponse(jObject.get("image_uris").getAsJsonObject(), "normal"));
		this.imageUri.put(ImageType.LARGE, JsonHelper.stringJsonResponse(jObject.get("image_uris").getAsJsonObject(), "large"));
		this.imageUri.put(ImageType.PNG, JsonHelper.stringJsonResponse(jObject.get("image_uris").getAsJsonObject(), "png"));
		this.imageUri.put(ImageType.ART_CROP, JsonHelper.stringJsonResponse(jObject.get("image_uris").getAsJsonObject(), "art_crop"));
		this.imageUri.put(ImageType.BORDER_CROP, JsonHelper.stringJsonResponse(jObject.get("image_uris").getAsJsonObject(), "border_crop"));
	}

	public CardFace(String name, String printedName, String manaCost, String typeLine, String printedTypeLine,
			String oracleText, String printedText, List<String> listColors, List<String> listColorIndicators,
			String power, String toughness, String flavorText, String illustrationId, Map<ImageType, String> imageUri) {
		this.name = name;
		this.printedName = printedName;
		this.manaCost = manaCost;
		this.typeLine = typeLine;
		this.printedTypeLine = printedTypeLine;
		this.oracleText = oracleText;
		this.printedText = printedText;
		this.listColors = listColors;
		this.listColorIndicators = listColorIndicators;
		this.power = power;
		this.toughness = toughness;
		this.flavorText = flavorText;
		this.illustrationId = illustrationId;
		this.imageUri = imageUri;
	}
	
	public Image getImage(ImageType imageType) throws IOException, IllegalArgumentException {
		if (getImageUri().containsKey(imageType)) {
		return ImageIO.read(new URL(getImageUri().get(imageType)));
		} else {
			throw new IllegalArgumentException("The card has no image of this the type: " + imageType);
		}
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
		CardFace other = (CardFace) obj;
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
		if (imageUri == null) {
			if (other.imageUri != null)
				return false;
		} else if (!imageUri.equals(other.imageUri))
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
		return true;
	}

	/**
	 * @return the flavorText
	 */
	public String getFlavorText() {
		return flavorText;
	}

	/**
	 * @return the illustrationId
	 */
	public String getIllustrationId() {
		return illustrationId;
	}

	/**
	 * @return the listColorIndicators
	 */
	public List<String> getListColorIndicators() {
		return listColorIndicators;
	}

	/**
	 * @return the listColors
	 */
	public List<String> getListColors() {
		return listColors;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flavorText == null) ? 0 : flavorText.hashCode());
		result = prime * result + ((illustrationId == null) ? 0 : illustrationId.hashCode());
		result = prime * result + ((imageUri == null) ? 0 : imageUri.hashCode());
		result = prime * result + ((listColorIndicators == null) ? 0 : listColorIndicators.hashCode());
		result = prime * result + ((listColors == null) ? 0 : listColors.hashCode());
		result = prime * result + ((manaCost == null) ? 0 : manaCost.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((oracleText == null) ? 0 : oracleText.hashCode());
		result = prime * result + ((power == null) ? 0 : power.hashCode());
		result = prime * result + ((printedName == null) ? 0 : printedName.hashCode());
		result = prime * result + ((printedText == null) ? 0 : printedText.hashCode());
		result = prime * result + ((printedTypeLine == null) ? 0 : printedTypeLine.hashCode());
		result = prime * result + ((toughness == null) ? 0 : toughness.hashCode());
		result = prime * result + ((typeLine == null) ? 0 : typeLine.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CardFace [name=" + name + ", printedName=" + printedName + ", manaCost=" + manaCost + ", typeLine="
				+ typeLine + ", printedTypeLine=" + printedTypeLine + ", oracleText=" + oracleText + ", printedText="
				+ printedText + ", listColors=" + listColors + ", listColorIndicators=" + listColorIndicators
				+ ", power=" + power + ", toughness=" + toughness + ", flavorText=" + flavorText + ", illustrationId="
				+ illustrationId + ", imageUri=" + imageUri + "]";
	}

	/**
	 * @return the imageUri
	 */
	public Map<ImageType, String> getImageUri() {
		return imageUri;
	}
}
