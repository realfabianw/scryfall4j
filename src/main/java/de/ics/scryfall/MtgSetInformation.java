package de.ics.scryfall;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * This class combines all information of a set to identify and display it.
 * 
 * @author QUE
 *
 */
public class MtgSetInformation {
	private final String jsonString;
	private final String code;
	private final String name;
	private final String scryfallUri;
	private final String cardListUri;
	private final String setType;
	private final LocalDateTime releaseDate;
	private final int cardCount;
	private final boolean digital;
	private final boolean foilOnly;
	private final String iconUri;
	private final Set<MtgCardInformation> setCards;

	public MtgSetInformation(JsonObject jObject) {
		this.jsonString = jObject.toString();
		this.code = JsonHelper.stringJsonResponse(jObject, "code");
		this.name = JsonHelper.stringJsonResponse(jObject, "name");
		this.scryfallUri = JsonHelper.stringJsonResponse(jObject, "scryfall_uri");
		this.cardListUri = JsonHelper.stringJsonResponse(jObject, "search_uri");
		this.setType = JsonHelper.stringJsonResponse(jObject, "set_type");
		this.releaseDate = JsonHelper.localDateTimeJsonResponse(jObject, "releaseDate", DateTimeFormatter.ISO_DATE);
		this.cardCount = JsonHelper.integerJsonResponse(jObject, "card_count");
		this.digital = JsonHelper.booleanJsonResponse(jObject, "digital");
		this.foilOnly = JsonHelper.booleanJsonResponse(jObject, "foil_only");
		this.iconUri = JsonHelper.stringJsonResponse(jObject, "icon_svg_uri");
		this.setCards = new HashSet<>();
	}

	public MtgSetInformation(String jsonString, String code, String name, String scryfallUri, String cardListUri,
			String setType, LocalDateTime releaseDate, int cardCount, boolean digital, boolean foilOnly, String iconUri,
			Set<MtgCardInformation> setCards) {
		this.jsonString = jsonString;
		this.code = code;
		this.name = name;
		this.scryfallUri = scryfallUri;
		this.cardListUri = cardListUri;
		this.setType = setType;
		this.releaseDate = releaseDate;
		this.cardCount = cardCount;
		this.digital = digital;
		this.foilOnly = foilOnly;
		this.iconUri = iconUri;
		this.setCards = setCards;
	}

	/**
	 * Die getIcon()-Funktion wird aus dem Wrapper entfernt.
	 * 
	 * @return the icon
	 * @throws IOException
	 * @throws TranscoderException
	 */
	public BufferedImage downloadIcon(String path) throws TranscoderException, IOException {
		File fullPath = new File(path + "/" + getCode() + ".png");
		saveSVGtoPNG(getIconUri(), fullPath);
		return ImageIO.read(fullPath);
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
		MtgSetInformation other = (MtgSetInformation) obj;
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
		if (iconUri == null) {
			if (other.iconUri != null)
				return false;
		} else if (!iconUri.equals(other.iconUri))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (scryfallUri == null) {
			if (other.scryfallUri != null)
				return false;
		} else if (!scryfallUri.equals(other.scryfallUri))
			return false;
		if (setType == null) {
			if (other.setType != null)
				return false;
		} else if (!setType.equals(other.setType))
			return false;
		return true;
	}

	public Set<MtgCardInformation> fetchCards() throws InterruptedException, IOException {
		String nextPageUri = getCardListUri();
		boolean hasNextPage = true;

		while (hasNextPage) {
			URL url = new URL(nextPageUri);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuilder jsonString = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				jsonString.append(line);
			}
			bufferedReader.close();
			JsonObject listObject = new JsonParser().parse(jsonString.toString()).getAsJsonObject();

			hasNextPage = JsonHelper.booleanJsonResponse(listObject, "has_more");
			nextPageUri = JsonHelper.stringJsonResponse(listObject, "next_page");
			for (JsonElement jElement : listObject.get("data").getAsJsonArray()) {
				setCards.add(new MtgCardInformation(jElement.getAsJsonObject()));
			}
			Thread.sleep(100);
		}

		return setCards;
	}

	/**
	 * @return the cardCount
	 */
	public int getCardCount() {
		return cardCount;
	}

	public String getCardListUri() {
		return cardListUri;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the iconUri
	 */
	public String getIconUri() {
		return iconUri;
	}

	public String getJsonString() {
		return jsonString;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public LocalDateTime getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @return the scryfallUri
	 */
	public String getScryfallUri() {
		return scryfallUri;
	}

	public Set<MtgCardInformation> getSetCards() {
		return setCards;
	}

	/**
	 * @return the setType
	 */
	public String getSetType() {
		return setType;
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
		result = prime * result + cardCount;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + (digital ? 1231 : 1237);
		result = prime * result + (foilOnly ? 1231 : 1237);
		result = prime * result + ((iconUri == null) ? 0 : iconUri.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((scryfallUri == null) ? 0 : scryfallUri.hashCode());
		result = prime * result + ((setType == null) ? 0 : setType.hashCode());
		return result;
	}

	/**
	 * @return the digital
	 */
	public boolean isDigital() {
		return digital;
	}

	/**
	 * @return the foilOnly
	 */
	public boolean isFoilOnly() {
		return foilOnly;
	}

	/**
	 * this method converts a given svg image (by url) to a png image on the disk at
	 * the given location.
	 * 
	 * @author techhunter @ stackoverflow
	 * @see https://stackoverflow.com/questions/42340833/convert-svg-image-to-png-in-java-by-servlet
	 * @param svgUri
	 * @param output
	 * @throws TranscoderException
	 * @throws IOException
	 */
	private void saveSVGtoPNG(String svgUri, File output) throws TranscoderException, IOException {
		// Step -1: We read the input SVG document into Transcoder Input
		// We use Java NIO for this purpose
		String svg_URI_input = svgUri;
		TranscoderInput input_svg_image = new TranscoderInput(svg_URI_input);
		// Step-2: Define OutputStream to PNG Image and attach to TranscoderOutput
		OutputStream png_ostream = new FileOutputStream(output);
		TranscoderOutput output_png_image = new TranscoderOutput(png_ostream);
		// Step-3: Create PNGTranscoder and define hints if required
		PNGTranscoder my_converter = new PNGTranscoder();
		// Step-4: Convert and Write output
		my_converter.transcode(input_svg_image, output_png_image);
		// Step 5- close / flush Output Stream
		png_ostream.flush();
		png_ostream.close();
	}

	@Override
	public String toString() {
		return "Set [" + (code != null ? "code=" + code + ", " : "") + (name != null ? "name=" + name + ", " : "")
				+ (scryfallUri != null ? "scryfallUri=" + scryfallUri + ", " : "")
				+ (setType != null ? "setType=" + setType + ", " : "")
				+ (releaseDate != null ? "releaseDate=" + releaseDate + ", " : "") + "cardCount=" + cardCount
				+ ", digital=" + digital + ", foilOnly=" + foilOnly + ", "
				+ (iconUri != null ? "iconUri=" + iconUri : "") + "]";
	}
}
