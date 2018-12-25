package de.ics.scryfall.mtg.card;

import com.google.gson.JsonObject;

import de.ics.scryfall.io.JsonHelper;

/**
 * Bundles all information about a cards legality.
 * 
 * @author QUE
 *
 */
public class Legality {
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

	public Legality(boolean standard, boolean future, boolean frontier, boolean modern, boolean legacy, boolean pauper,
			boolean vintage, boolean penny, boolean commander, boolean oneVersusOne, boolean duel, boolean brawl) {
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

	public Legality(JsonObject jObject) {
		this.standard = JsonHelper.booleanFromStringJsonResponse(jObject, "standard", "legal", "not_legal");
		this.future = JsonHelper.booleanFromStringJsonResponse(jObject, "future", "legal", "not_legal");
		this.frontier = JsonHelper.booleanFromStringJsonResponse(jObject, "frontier", "legal", "not_legal");
		this.modern = JsonHelper.booleanFromStringJsonResponse(jObject, "modern", "legal", "not_legal");
		this.legacy = JsonHelper.booleanFromStringJsonResponse(jObject, "legacy", "legal", "not_legal");
		this.pauper = JsonHelper.booleanFromStringJsonResponse(jObject, "pauper", "legal", "not_legal");
		this.vintage = JsonHelper.booleanFromStringJsonResponse(jObject, "vintage", "legal", "not_legal");
		this.penny = JsonHelper.booleanFromStringJsonResponse(jObject, "penny", "legal", "not_legal");
		this.commander = JsonHelper.booleanFromStringJsonResponse(jObject, "commander", "legal", "not_legal");
		this.oneVersusOne = JsonHelper.booleanFromStringJsonResponse(jObject, "1v1", "legal", "not_legal");
		this.duel = JsonHelper.booleanFromStringJsonResponse(jObject, "duel", "legal", "not_legal");
		this.brawl = JsonHelper.booleanFromStringJsonResponse(jObject, "brawl", "legal", "not_legal");
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
		Legality other = (Legality) obj;
		if (brawl != other.brawl)
			return false;
		if (commander != other.commander)
			return false;
		if (duel != other.duel)
			return false;
		if (frontier != other.frontier)
			return false;
		if (future != other.future)
			return false;
		if (legacy != other.legacy)
			return false;
		if (modern != other.modern)
			return false;
		if (oneVersusOne != other.oneVersusOne)
			return false;
		if (pauper != other.pauper)
			return false;
		if (penny != other.penny)
			return false;
		if (standard != other.standard)
			return false;
		if (vintage != other.vintage)
			return false;
		return true;
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
		result = prime * result + (brawl ? 1231 : 1237);
		result = prime * result + (commander ? 1231 : 1237);
		result = prime * result + (duel ? 1231 : 1237);
		result = prime * result + (frontier ? 1231 : 1237);
		result = prime * result + (future ? 1231 : 1237);
		result = prime * result + (legacy ? 1231 : 1237);
		result = prime * result + (modern ? 1231 : 1237);
		result = prime * result + (oneVersusOne ? 1231 : 1237);
		result = prime * result + (pauper ? 1231 : 1237);
		result = prime * result + (penny ? 1231 : 1237);
		result = prime * result + (standard ? 1231 : 1237);
		result = prime * result + (vintage ? 1231 : 1237);
		return result;
	}

	/**
	 * @return the brawl
	 */
	public boolean isBrawl() {
		return brawl;
	}

	/**
	 * @return the commander
	 */
	public boolean isCommander() {
		return commander;
	}

	/**
	 * @return the duel
	 */
	public boolean isDuel() {
		return duel;
	}

	/**
	 * @return the frontier
	 */
	public boolean isFrontier() {
		return frontier;
	}

	/**
	 * @return the future
	 */
	public boolean isFuture() {
		return future;
	}

	/**
	 * @return the legacy
	 */
	public boolean isLegacy() {
		return legacy;
	}

	/**
	 * @return the modern
	 */
	public boolean isModern() {
		return modern;
	}

	/**
	 * @return the oneVersusOne
	 */
	public boolean isOneVersusOne() {
		return oneVersusOne;
	}

	/**
	 * @return the pauper
	 */
	public boolean isPauper() {
		return pauper;
	}

	/**
	 * @return the penny
	 */
	public boolean isPenny() {
		return penny;
	}

	/**
	 * @return the standard
	 */
	public boolean isStandard() {
		return standard;
	}

	/**
	 * @return the vintage
	 */
	public boolean isVintage() {
		return vintage;
	}

	/*
	 * (non-Javadoc)
	 * 
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
