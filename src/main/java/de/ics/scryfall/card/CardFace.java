package de.ics.scryfall.card;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import de.ics.scryfall.io.JsonHelper;

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
	private final String imageUri;

	public CardFace(String name, String printedName, String manaCost, String typeLine, String printedTypeLine,
			String oracleText, String printedText, List<String> listColors, List<String> listColorIndicators,
			String power, String toughness, String flavorText, String illustrationId, String imageUri) {
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
		this.imageUri = JsonHelper.largeImageJsonResponse(jObject, "image_uris");

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
	 * @return the manaCost
	 */
	public String getManaCost() {
		return manaCost;
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
	 * @return the listColors
	 */
	public List<String> getListColors() {
		return listColors;
	}

	/**
	 * @return the listColorIndicators
	 */
	public List<String> getListColorIndicators() {
		return listColorIndicators;
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
	 * @return the imageUri
	 */
	public String getImageUri() {
		return imageUri;
	}

	/* (non-Javadoc)
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
}
