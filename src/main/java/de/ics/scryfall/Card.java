package de.ics.scryfall;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderException;

import com.google.gson.JsonObject;

import de.ics.scryfall.enums.Language;

/**
 * This class combines the most basic information of a card to identify and
 * display it.
 * 
 * @author QUE
 *
 */
public class Card {
	/**
	 * The cards unique id.
	 */
	private final String uniqueId;
	/**
	 * The cards oracle id. The oracle id is identical over all reprints and
	 * languages of the same card.
	 */
	private final String oracleId;
	/**
	 * The cards (print-)name. If this is a non-english card this variable will be
	 * the localized name.
	 */
	private final String name;
	/**
	 * The cards set-code.
	 */
	private final String setCode;
	/**
	 * The cards language.
	 */
	private final Language language;
	/**
	 * A link to the cards image.
	 */
	private final String imageUri;
	/**
	 * Saves the cards image. This variable is not final as it's loaded if called.
	 * If the image can't be found in the given folder it will be downloaded and
	 * saved for further usage.
	 */
	private BufferedImage image;

	public Card(JsonObject jObject) {
		this.uniqueId = jObject.get("id").getAsString();
		this.oracleId = jObject.get("oracle_id").getAsString();
		this.name = jObject.get("printed_name") == null ? jObject.get("name").getAsString()
				: jObject.get("printed_name").getAsString();
		this.setCode = jObject.get("set").getAsString();
		this.language = Language.getLanguage(jObject.get("lang").getAsString());
		this.imageUri = jObject.get("image_uris").getAsJsonObject().get("large").getAsString();
	}

	public Card(String uniqueId, String oracleId, String name, String setCode, Language language, String imageUri) {
		this.uniqueId = uniqueId;
		this.oracleId = oracleId;
		this.name = name;
		this.setCode = setCode;
		this.language = language;
		this.imageUri = imageUri;
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
		if (uniqueId == null) {
			if (other.uniqueId != null)
				return false;
		} else if (!uniqueId.equals(other.uniqueId))
			return false;
		return true;
	}

	public BufferedImage getImage(String pathCardDirectory) throws IOException, TranscoderException {
		if (image != null) {
			return image;
		} else {
			File dir = new File(pathCardDirectory);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File card = new File(pathCardDirectory + uniqueId + ".jpg");
			if (!card.exists()) {
				this.image = ImageIO.read(new URL(imageUri));
				ImageIO.write(image, "jpg", card);
				return image;
			} else {
				this.image = ImageIO.read(card);
				return image;
			}
		}
	}

	/**
	 * @return the imageUri
	 */
	public String getImageUri() {
		return imageUri;
	}

	/**
	 * @return the language
	 */
	public Language getLanguage() {
		return language;
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
	 * @return the setCode
	 */
	public String getSetCode() {
		return setCode;
	}

	/**
	 * @return the uniqueId
	 */
	public String getUniqueId() {
		return uniqueId;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Card [uniqueId=" + uniqueId + ", oracleId=" + oracleId + ", name=" + name + ", setCode=" + setCode
				+ ", language=" + language + ", imageUri=" + imageUri + "]";
	}
}
