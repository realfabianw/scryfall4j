package de.ics.scryfall;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import com.google.gson.JsonObject;

/**
 * This class combines the most basic information of a set to identify and
 * display it.
 * 
 * @author QUE
 *
 */
public class SetInformation {
	/**
	 * The sets unique code.
	 */
	private final String code;
	/**
	 * The sets name.
	 */
	private final String name;
	/**
	 * The sets card count.
	 */
	private final int cardCount;
	/**
	 * A link to the sets icon (svg on server, png on disk)
	 */
	private final String iconUri;

	public SetInformation(JsonObject jObject) {
		this.code = jObject.get("code").getAsString();
		this.name = jObject.get("name").getAsString();
		this.cardCount = jObject.get("card_count").getAsInt();
		this.iconUri = jObject.get("icon_svg_uri").getAsString();
	}

	public SetInformation(String code, String name, int cardCount, String iconUri) {
		this.code = code;
		this.name = name;
		this.cardCount = cardCount;
		this.iconUri = iconUri;
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
		SetInformation other = (SetInformation) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	/**
	 * @return the cardCount
	 */
	public int getCardCount() {
		return cardCount;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

//	/**
//	 * Die getIcon()-Funktion wird aus dem Wrapper entfernt.
//	 * @return the icon
//	 * @throws IOException
//	 * @throws TranscoderException
//	 */
//	public BufferedImage getIcon(String pathSetDirectory) throws IOException, TranscoderException {
//		if (icon != null) {
//			return icon;
//		} else {
//			File dir = new File(pathSetDirectory);
//			if (!dir.exists()) {
//				dir.mkdirs();
//			}
//			File setIcon = new File(pathSetDirectory + code + ".png");
//			if (!setIcon.exists()) {
//				saveSVGtoPNG(iconUri, setIcon);
//				this.icon = ImageIO.read(setIcon);
//				return icon;
//			} else {
//				this.icon = ImageIO.read(setIcon);
//				return icon;
//			}
//		}
//	}

	/**
	 * @return the iconUri
	 */
	public String getIconUri() {
		return iconUri;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
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
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Set [code=" + code + ", name=" + name + ", cardCount=" + cardCount + ", iconUri=" + iconUri + "]";
	}

}
