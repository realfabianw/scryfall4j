package de.ics.scryfall;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neovisionaries.i18n.LanguageCode;

import de.ics.scryfall.enums.ImageType;

/**
 * ScryfallUtils bundles several methods that help to use the API.
 * 
 * @author QUE
 *
 */
public class ScryfallUtils {
	private static Logger LOGGER = LoggerFactory.getLogger("ScryfallUtils");

	public static BufferedImage fetchCardImage(MtgCardInformation card, ImageType imageType)
			throws IOException, IllegalArgumentException {
		if (card.getMapImageUrls().containsKey(imageType)) {
			return fetchImage(card.getMapImageUrls().get(imageType));
		} else {
			throw new IllegalArgumentException("The card has no image of this type: " + imageType);
		}
	}

	public static BufferedImage fetchImage(String urlString) throws IOException {
		return ImageIO.read(new URL(urlString));
	}

	public static LanguageCode fromScryfallLanguageCode(String string) {
		if (string.equals("zhs") || string.equals("zht")) {
			return LanguageCode.zh;
		} else {
			LanguageCode code = LanguageCode.getByCode(string);
			if (code != null) {
				return code;
			} else {
				LOGGER.warn("LanguageCode " + string + " not recognised. Returning null");
				return null;
			}
		}
	}

	public static BufferedImage saveSetIconToFile(String path, MtgSetInformation set)
			throws TranscoderException, IOException {
		File fullPath = new File(path + "/set_" + set.getCode() + ".png");
		saveSVGtoPNG(set.getIconSvgUrl().toString(), fullPath);
		return ImageIO.read(fullPath);
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
	private static void saveSVGtoPNG(String svgUri, File output) throws TranscoderException, IOException {
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
}
