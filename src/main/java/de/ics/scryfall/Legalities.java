package de.ics.scryfall;

import com.google.gson.JsonObject;

public class Legalities {
	private final boolean standard;
	private final boolean future;
	private final boolean frontier;
	private final boolean modern;
	private final boolean legacy;
	private final boolean pauper;
	private final boolean vintage;
	private final boolean penny;
	private final boolean commander;
	private final boolean oneVersusOne;
	private final boolean duel;
	private final boolean brawl;

	public Legalities(boolean standard, boolean future, boolean frontier, boolean modern, boolean legacy,
			boolean pauper, boolean vintage, boolean penny, boolean commander, boolean oneVersusOne, boolean duel,
			boolean brawl) {
		this.standard = standard;
		this.future = future;
		this.frontier = frontier;
		this.modern = modern;
		this.legacy = legacy;
		this.pauper = pauper;
		this.vintage = vintage;
		this.penny = penny;
		this.commander = commander;
		this.oneVersusOne = oneVersusOne;
		this.duel = duel;
		this.brawl = brawl;
	}

	public Legalities(JsonObject jObject) {
		this.standard = booleanJsonResponse(jObject, "standard", "legal", "not_legal");
		this.future = booleanJsonResponse(jObject, "future", "legal", "not_legal");
		this.frontier = booleanJsonResponse(jObject, "frontier", "legal", "not_legal");
		this.modern = booleanJsonResponse(jObject, "modern", "legal", "not_legal");
		this.legacy = booleanJsonResponse(jObject, "legacy", "legal", "not_legal");
		this.pauper = booleanJsonResponse(jObject, "pauper", "legal", "not_legal");
		this.vintage = booleanJsonResponse(jObject, "vintage", "legal", "not_legal");
		this.penny = booleanJsonResponse(jObject, "penny", "legal", "not_legal");
		this.commander = booleanJsonResponse(jObject, "commander", "legal", "not_legal");
		this.oneVersusOne = booleanJsonResponse(jObject, "1v1", "legal", "not_legal");
		this.duel = booleanJsonResponse(jObject, "duel", "legal", "not_legal");
		this.brawl = booleanJsonResponse(jObject, "brawl", "legal", "not_legal");
	}

	/**
	 * Class that catches NullPointerExceptions while getting responses from the Json-File.
	 * @param jObject
	 * @param fieldName
	 * @param trueString
	 * @param falseString
	 * @return
	 */
	private final boolean booleanJsonResponse(JsonObject jObject, String fieldName, String trueString,
			String falseString) {
		try {
			String returnedString = jObject.get(fieldName).getAsString();
			if (returnedString.equals(trueString)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @return the standard
	 */
	public boolean isStandard() {
		return standard;
	}

	/**
	 * @return the future
	 */
	public boolean isFuture() {
		return future;
	}

	/**
	 * @return the frontier
	 */
	public boolean isFrontier() {
		return frontier;
	}

	/**
	 * @return the modern
	 */
	public boolean isModern() {
		return modern;
	}

	/**
	 * @return the legacy
	 */
	public boolean isLegacy() {
		return legacy;
	}

	/**
	 * @return the pauper
	 */
	public boolean isPauper() {
		return pauper;
	}

	/**
	 * @return the vintage
	 */
	public boolean isVintage() {
		return vintage;
	}

	/**
	 * @return the penny
	 */
	public boolean isPenny() {
		return penny;
	}

	/**
	 * @return the commander
	 */
	public boolean isCommander() {
		return commander;
	}

	/**
	 * @return the oneVersusOne
	 */
	public boolean isOneVersusOne() {
		return oneVersusOne;
	}

	/**
	 * @return the duel
	 */
	public boolean isDuel() {
		return duel;
	}

	/**
	 * @return the brawl
	 */
	public boolean isBrawl() {
		return brawl;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Legalities [standard=" + standard + ", future=" + future + ", frontier=" + frontier + ", modern="
				+ modern + ", legacy=" + legacy + ", pauper=" + pauper + ", vintage=" + vintage + ", penny=" + penny
				+ ", commander=" + commander + ", oneVersusOne=" + oneVersusOne + ", duel=" + duel + ", brawl=" + brawl
				+ "]";
	}
}
