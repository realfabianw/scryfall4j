package de.ics.scryfall;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderException;

import com.google.gson.JsonObject;

/**
 * This class combines the most basic information of a card to identify and
 * display it.
 * 
 * @author QUE
 *
 */
public class CardInformation_OLD {
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
	 * The cards (print-)text. If this is a non-english card this variable will be
	 * the localized text.
	 */
	private final String text;
	/**
	 * the cards flavor if any exists.
	 */
	private final String flavor;
	/**
	 * The cards artist(-s).
	 */
	private final String artist;
	/**
	 * The cards collector-number of this expansion.
	 */
	private final String collectorNumber;
	/**
	 * The cards set-code.
	 */
	private final String setCode;
	/**
	 * The cards language.
	 */
	private final String language;
	/**
	 * A link to the cards image.
	 */
	private final String imageUri;

	public CardInformation_OLD(JsonObject jObject) {
		this.uniqueId = jObject.get("id").getAsString();
		this.oracleId = jObject.get("oracle_id").getAsString();
		this.name = jObject.get("printed_name") == null ? jObject.get("name").getAsString()
				: jObject.get("printed_name").getAsString();
		this.text = jObject.get("printed_text") == null ? jObject.get("oracle_text").getAsString()
				: jObject.get("printed_text").getAsString();
		this.flavor = jObject.get("flavor_text") == null ? "" : jObject.get("flavor_text").getAsString();
		this.artist = jObject.get("artist").getAsString();
		this.collectorNumber = jObject.get("collector_number").getAsString();
		this.setCode = jObject.get("set").getAsString();
		this.language = jObject.get("lang").getAsString();
		this.imageUri = jObject.get("image_uris").getAsJsonObject().get("large").getAsString();
	}

	public CardInformation_OLD(String uniqueId, String oracleId, String name, String text, String flavor, String artist,
			String collectorNumber, String setCode, String language, String imageUri) {
		this.uniqueId = uniqueId;
		this.oracleId = oracleId;
		this.name = name;
		this.text = text;
		this.flavor = flavor;
		this.artist = artist;
		this.collectorNumber = collectorNumber;
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
		CardInformation_OLD other = (CardInformation_OLD) obj;
		if (uniqueId == null) {
			if (other.uniqueId != null)
				return false;
		} else if (!uniqueId.equals(other.uniqueId))
			return false;
		return true;
	}

//	/**
//	 * Die getImage()-Funktion wird aus Scryfall entfernt und in TCG Inventory gepackt.
//	 * @param pathCardDirectory
//	 * @return
//	 * @throws IOException
//	 * @throws TranscoderException
//	 */
//	public BufferedImage getImage(String pathCardDirectory) throws IOException, TranscoderException {
//		if (image != null) {
//			return image;
//		} else {
//			File dir = new File(pathCardDirectory);
//			if (!dir.exists()) {
//				dir.mkdirs();
//			}
//			File card = new File(pathCardDirectory + uniqueId + ".jpg");
//			if (!card.exists()) {
//				this.image = ImageIO.read(new URL(imageUri));
//				ImageIO.write(image, "jpg", card);
//				return image;
//			} else {
//				this.image = ImageIO.read(card);
//				return image;
//			}
//		}
//	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @return the collectorNumber
	 */
	public String getCollectorNumber() {
		return collectorNumber;
	}

	/**
	 * @return the flavor
	 */
	public String getFlavor() {
		return flavor;
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
	public String getLanguage() {
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
	 * @return the text
	 */
	public String getText() {
		return text;
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
		return "CardInformation [uniqueId=" + uniqueId + ", oracleId=" + oracleId + ", name=" + name + ", text=" + text
				+ ", flavor=" + flavor + ", artist=" + artist + ", collectorNumber=" + collectorNumber + ", setCode="
				+ setCode + ", language=" + language + ", imageUri=" + imageUri + "]";
	}
}
