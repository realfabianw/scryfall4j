package de.scryfall;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonObject;

import de.scryfall.enums.SetType;

/**
 * MtgSetInformation represents a group of related "Magic: The Gathering"-Cards.
 * Scryfall created additional sets to group formerly ungrouped cards.
 * 
 * @see https://scryfall.com/docs/api/sets
 * @author QUE
 *
 */
public class MtgSetInformation {
	private String jsonString;
	private String id;
	private String code;
	private String mtgoCode;
	private int tcgPlayerId;
	private String name;
	private SetType setType;
	private LocalDate releaseDate;
	private String blockCode;
	private String block;
	private String parentSetCode;
	private int cardCount;
	private boolean digital;
	private boolean foilOnly;
	private String selfScryfallUrl;
	private String selfApiUrl;
	private String iconSvgUrl;
	private String searchApiUrl;

	public MtgSetInformation(JsonObject jObject) {
		this.jsonString = jObject.toString();
		this.id = JsonIO.parseString(jObject, "id");
		this.code = JsonIO.parseString(jObject, "code");
		this.mtgoCode = JsonIO.parseString(jObject, "mtgo_code");
		this.tcgPlayerId = JsonIO.parseInteger(jObject, "tcgplayer_id");
		this.name = JsonIO.parseString(jObject, "name");
		this.setType = SetType.parseId(JsonIO.parseString(jObject, "set_type"));
		this.releaseDate = JsonIO.parseLocalDate(jObject, "released_at", DateTimeFormatter.ISO_DATE);
		this.blockCode = JsonIO.parseString(jObject, "block_code");
		this.block = JsonIO.parseString(jObject, "block");
		this.parentSetCode = JsonIO.parseString(jObject, "parent_set_code");
		this.cardCount = JsonIO.parseInteger(jObject, "card_count");
		this.digital = JsonIO.parseBoolean(jObject, "digital");
		this.foilOnly = JsonIO.parseBoolean(jObject, "foil_only");
		this.selfScryfallUrl = JsonIO.parseString(jObject, "scryfall_uri");
		this.selfApiUrl = JsonIO.parseString(jObject, "uri");
		this.iconSvgUrl = JsonIO.parseString(jObject, "icon_svg_uri");
		this.searchApiUrl = JsonIO.parseString(jObject, "search_uri");
	}

	public MtgSetInformation(String jsonString, String id, String code, String mtgoCode, int tcgPlayerId, String name,
			SetType setType, LocalDate releaseDate, String blockCode, String block, String parentSetCode, int cardCount,
			boolean digital, boolean foilOnly, String selfScryfallUrl, String selfApiUrl, String iconSvgUrl,
			String searchApiUrl) {
		this.jsonString = jsonString;
		this.id = id;
		this.code = code;
		this.mtgoCode = mtgoCode;
		this.tcgPlayerId = tcgPlayerId;
		this.name = name;
		this.setType = setType;
		this.releaseDate = releaseDate;
		this.blockCode = blockCode;
		this.block = block;
		this.parentSetCode = parentSetCode;
		this.cardCount = cardCount;
		this.digital = digital;
		this.foilOnly = foilOnly;
		this.selfScryfallUrl = selfScryfallUrl;
		this.selfApiUrl = selfApiUrl;
		this.iconSvgUrl = iconSvgUrl;
		this.searchApiUrl = searchApiUrl;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MtgSetInformation other = (MtgSetInformation) obj;
		if (block == null) {
			if (other.block != null)
				return false;
		} else if (!block.equals(other.block))
			return false;
		if (blockCode == null) {
			if (other.blockCode != null)
				return false;
		} else if (!blockCode.equals(other.blockCode))
			return false;
		if (cardCount != other.cardCount)
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (digital != other.digital)
			return false;
		if (foilOnly != other.foilOnly)
			return false;
		if (iconSvgUrl == null) {
			if (other.iconSvgUrl != null)
				return false;
		} else if (!iconSvgUrl.equals(other.iconSvgUrl))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (jsonString == null) {
			if (other.jsonString != null)
				return false;
		} else if (!jsonString.equals(other.jsonString))
			return false;
		if (mtgoCode == null) {
			if (other.mtgoCode != null)
				return false;
		} else if (!mtgoCode.equals(other.mtgoCode))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parentSetCode == null) {
			if (other.parentSetCode != null)
				return false;
		} else if (!parentSetCode.equals(other.parentSetCode))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (searchApiUrl == null) {
			if (other.searchApiUrl != null)
				return false;
		} else if (!searchApiUrl.equals(other.searchApiUrl))
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
		if (setType != other.setType)
			return false;
		if (tcgPlayerId != other.tcgPlayerId)
			return false;
		return true;
	}

	public String getBlock() {
		return block;
	}

	public String getBlockCode() {
		return blockCode;
	}

	public int getCardCount() {
		return cardCount;
	}

	public String getCode() {
		return code;
	}

	public String getIconSvgUrl() {
		return iconSvgUrl;
	}

	public String getId() {
		return id;
	}

	public String getJsonString() {
		return jsonString;
	}

	public String getMtgoCode() {
		return mtgoCode;
	}

	public String getName() {
		return name;
	}

	public String getParentSetCode() {
		return parentSetCode;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public String getSearchApiUrl() {
		return searchApiUrl;
	}

	public String getSelfApiUrl() {
		return selfApiUrl;
	}

	public String getSelfScryfallUrl() {
		return selfScryfallUrl;
	}

	public SetType getSetType() {
		return setType;
	}

	public int getTcgPlayerId() {
		return tcgPlayerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((block == null) ? 0 : block.hashCode());
		result = prime * result + ((blockCode == null) ? 0 : blockCode.hashCode());
		result = prime * result + cardCount;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + (digital ? 1231 : 1237);
		result = prime * result + (foilOnly ? 1231 : 1237);
		result = prime * result + ((iconSvgUrl == null) ? 0 : iconSvgUrl.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jsonString == null) ? 0 : jsonString.hashCode());
		result = prime * result + ((mtgoCode == null) ? 0 : mtgoCode.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parentSetCode == null) ? 0 : parentSetCode.hashCode());
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result + ((searchApiUrl == null) ? 0 : searchApiUrl.hashCode());
		result = prime * result + ((selfApiUrl == null) ? 0 : selfApiUrl.hashCode());
		result = prime * result + ((selfScryfallUrl == null) ? 0 : selfScryfallUrl.hashCode());
		result = prime * result + ((setType == null) ? 0 : setType.hashCode());
		result = prime * result + tcgPlayerId;
		return result;
	}

	public boolean isDigital() {
		return digital;
	}

	public boolean isFoilOnly() {
		return foilOnly;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}

	public void setCardCount(int cardCount) {
		this.cardCount = cardCount;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDigital(boolean digital) {
		this.digital = digital;
	}

	public void setFoilOnly(boolean foilOnly) {
		this.foilOnly = foilOnly;
	}

	public void setIconSvgUrl(String iconSvgUrl) {
		this.iconSvgUrl = iconSvgUrl;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public void setMtgoCode(String mtgoCode) {
		this.mtgoCode = mtgoCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentSetCode(String parentSetCode) {
		this.parentSetCode = parentSetCode;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setSearchApiUrl(String searchApiUrl) {
		this.searchApiUrl = searchApiUrl;
	}

	public void setSelfApiUrl(String selfApiUrl) {
		this.selfApiUrl = selfApiUrl;
	}

	public void setSelfScryfallUrl(String selfScryfallUrl) {
		this.selfScryfallUrl = selfScryfallUrl;
	}

	public void setSetType(SetType setType) {
		this.setType = setType;
	}

	public void setTcgPlayerId(int tcgPlayerId) {
		this.tcgPlayerId = tcgPlayerId;
	}

	@Override
	public String toString() {
		return "MtgSetInformation [" + (jsonString != null ? "jsonString=" + jsonString + ", " : "")
				+ (id != null ? "id=" + id + ", " : "") + (code != null ? "code=" + code + ", " : "")
				+ (mtgoCode != null ? "mtgoCode=" + mtgoCode + ", " : "") + "tcgPlayerId=" + tcgPlayerId + ", "
				+ (name != null ? "name=" + name + ", " : "") + (setType != null ? "setType=" + setType + ", " : "")
				+ (releaseDate != null ? "releaseDate=" + releaseDate + ", " : "")
				+ (blockCode != null ? "blockCode=" + blockCode + ", " : "")
				+ (block != null ? "block=" + block + ", " : "")
				+ (parentSetCode != null ? "parentSetCode=" + parentSetCode + ", " : "") + "cardCount=" + cardCount
				+ ", digital=" + digital + ", foilOnly=" + foilOnly + ", "
				+ (selfScryfallUrl != null ? "selfScryfallUrl=" + selfScryfallUrl + ", " : "")
				+ (selfApiUrl != null ? "selfApiUrl=" + selfApiUrl + ", " : "")
				+ (iconSvgUrl != null ? "iconSvgUrl=" + iconSvgUrl + ", " : "")
				+ (searchApiUrl != null ? "searchApiUrl=" + searchApiUrl : "") + "]";
	}
}
