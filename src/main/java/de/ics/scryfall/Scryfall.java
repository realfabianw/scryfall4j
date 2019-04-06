package de.ics.scryfall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * This class serves as an entry-point to the Scryfall-API. All implemented
 * features are accessible through this class.
 * 
 * @author QUE
 *
 */
public class Scryfall {
	private static Logger LOGGER = LoggerFactory.getLogger("Scryfall");
	private static final String API = "https://api.scryfall.com/";
	private static final String SETS = "sets/";
	private static final String CARDS = "cards/";
	private static final String SEARCH_QUERY = "search?q=";

	private static URL createCardSearchUrl(String urlString, boolean includeExtras, boolean includeAllLanguages,
			boolean includeReprints) throws UnsupportedEncodingException, MalformedURLException {
		return new URL(API + CARDS + SEARCH_QUERY
				+ URLEncoder.encode(urlString + (includeExtras ? " include:extras" : "")
						+ (includeAllLanguages ? " lang:any" : "") + (includeReprints ? " unique:prints" : ""),
						"UTF-8"));
	}

	/**
	 * @see https://scryfall.com/docs/api/cards/id
	 * @param id
	 * @return {@code MtgCardInformation card}
	 * @throws IOException
	 */
	public static MtgCardInformation getCardById(String id) throws IOException {
		return new MtgCardInformation(request(API + CARDS + id).getAsJsonObject());
	}

	/**
	 * @see https://scryfall.com/docs/api/cards/all
	 * @return {@code List<MtgCardInformation> listCards}
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static List<MtgCardInformation> getListAllCards() throws IOException, InterruptedException {
		List<MtgCardInformation> listCards = new ArrayList<>();
		boolean firstIteration = true;
		boolean hasMore = false;
		URL nextPage = new URL(API + CARDS);
		do {
			if (!firstIteration) {
				Thread.sleep(100);
			} else {
				firstIteration = false;
			}
			JsonObject jsonResponse = request(nextPage).getAsJsonObject();
			hasMore = JsonIO.parseBoolean(jsonResponse, "has_more");
			try {
				nextPage = new URL(JsonIO.parseString(jsonResponse, "next_page"));
			} catch (MalformedURLException e) {

			}
			for (JsonElement jElement : jsonResponse.get("data").getAsJsonArray()) {
				listCards.add(new MtgCardInformation(jElement.getAsJsonObject()));
			}
		} while (hasMore);
		return listCards;
	}

	/**
	 * @see https://scryfall.com/docs/api/sets/all
	 * @return {@code List<MtgSetInformation> listAllSets}
	 * @throws IOException
	 */
	public static List<MtgSetInformation> getListAllSets() throws IOException {
		List<MtgSetInformation> listAllSets = new ArrayList<>();
		JsonObject jsonResponse = request(API + SETS).getAsJsonObject();
		for (JsonElement jElement : jsonResponse.get("data").getAsJsonArray()) {
			listAllSets.add(new MtgSetInformation(jElement.getAsJsonObject()));
		}
		return listAllSets;
	}

	/**
	 * @see https://scryfall.com/docs/api/cards/search
	 * @param searchQuery
	 * @param includeExtras
	 * @param includeAllLanguages
	 * @param includeReprints
	 * @return {@code List<MtgCardInformation> listCards}
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static List<MtgCardInformation> getListCardsBySearch(String searchQuery, boolean includeExtras,
			boolean includeAllLanguages, boolean includeReprints) throws IOException, InterruptedException {
		List<MtgCardInformation> listCards = new ArrayList<>();
		boolean firstIteration = true;
		boolean hasMore = false;
		URL nextPage = createCardSearchUrl(searchQuery, includeExtras, includeAllLanguages, includeReprints);
		do {
			if (!firstIteration) {
				Thread.sleep(100);
			} else {
				firstIteration = false;
			}
			JsonObject jsonResponse = request(nextPage).getAsJsonObject();
			hasMore = JsonIO.parseBoolean(jsonResponse, "has_more");
			try {
				nextPage = new URL(JsonIO.parseString(jsonResponse, "next_page"));
			} catch (MalformedURLException e) {

			}
			for (JsonElement jElement : jsonResponse.get("data").getAsJsonArray()) {
				listCards.add(new MtgCardInformation(jElement.getAsJsonObject()));
			}
		} while (hasMore);
		return listCards;
	}

	/**
	 * @see https://scryfall.com/docs/api/cards/search
	 * @param searchQuery
	 * @param includeAllLanguages
	 * @param includeReprints
	 * @return {@code List<MtgCardInformation> listCards}
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static List<MtgCardInformation> getListCardsBySet(MtgSetInformation mtgSetInformation)
			throws IOException, InterruptedException {
		List<MtgCardInformation> listCards = new ArrayList<>();
		boolean firstIteration = true;
		boolean hasMore = false;
		URL nextPage = new URL(mtgSetInformation.getSearchApiUrl());
		do {
			if (!firstIteration) {
				Thread.sleep(100);
			} else {
				firstIteration = false;
			}
			JsonObject jsonResponse = request(nextPage).getAsJsonObject();
			hasMore = JsonIO.parseBoolean(jsonResponse, "has_more");
			try {
				nextPage = new URL(JsonIO.parseString(jsonResponse, "next_page"));
			} catch (MalformedURLException e) {

			}
			for (JsonElement jElement : jsonResponse.get("data").getAsJsonArray()) {
				listCards.add(new MtgCardInformation(jElement.getAsJsonObject()));
			}
		} while (hasMore);
		return listCards;
	}

	/**
	 * @see https://scryfall.com/docs/api/sets/code
	 * @param setCode
	 * @return {@code MtgSetInformation mtgSetInformation}
	 * @throws IOException
	 */
	public static MtgSetInformation getSetByCode(String setCode) throws IOException {
		JsonObject jsonResponse = request(API + SETS + setCode).getAsJsonObject();
		return new MtgSetInformation(jsonResponse);
	}

	/**
	 * @see https://scryfall.com/docs/api/sets/id
	 * @param id
	 * @return {@code MtgSetInformation mtgSetInformation}
	 * @throws IOException
	 */
	public static MtgSetInformation getSetById(String id) throws IOException {
		JsonObject jsonResponse = request(API + SETS + id).getAsJsonObject();
		return new MtgSetInformation(jsonResponse);
	}

	/**
	 * @see https://scryfall.com/docs/api/sets/tcgplayer
	 * @param id
	 * @return {@code MtgSetInformation mtgSetInformation}
	 * @throws IOException
	 */
	public static MtgSetInformation getSetByTcgPlayerId(String id) throws IOException {
		JsonObject jsonResponse = request(API + SETS + "tcgplayer/" + id).getAsJsonObject();
		return new MtgSetInformation(jsonResponse);
	}

	private static JsonElement request(String urlString) throws IOException {
		URL url = new URL(urlString);
		LOGGER.debug("Request: {}", url.toString());
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder jsonString = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			jsonString.append(line);
		}
		bufferedReader.close();
		LOGGER.trace("Response: {}", jsonString.toString());
		return new JsonParser().parse(jsonString.toString());
	}

	private static JsonElement request(URL url) throws IOException {
		LOGGER.debug("Request: {}", url.toString());
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder jsonString = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			jsonString.append(line);
		}
		bufferedReader.close();
		LOGGER.trace("Response: {}", jsonString.toString());
		return new JsonParser().parse(jsonString.toString());
	}

}
