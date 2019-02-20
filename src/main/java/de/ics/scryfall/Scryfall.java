package de.ics.scryfall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * This class is the entry-point for all requests to the Scryfall-API.
 * 
 * @author QUE
 *
 */
public class Scryfall {
	private static final String URI_API = "https://api.scryfall.com/";
	private static final String SETS = "sets/";
	private static final String CARDS = "cards/";
	private static final String SEARCH_QUERY = "search?q=";

	/**
	 * Returns a Json response of a base level api call. The api call can be
	 * modified by the parameter.
	 * 
	 * @param uri
	 * @return {@code JsonElement jsonResponse}
	 * @throws IOException
	 */
	private static JsonElement apiConnection(String uri) throws IOException {
		return getJsonResponse(URI_API + uri);
	}

	/**
	 * Makes an (card) api call based on the given search query. The given string
	 * will be encoded by the UTF-8 standard.
	 * 
	 * @see https://scryfall.com/docs/api/cards/search
	 * @see https://scryfall.com/docs/reference
	 * @example input: "!\"Rattenkolonie\" lang:any" output:
	 *          https://api.scryfall.com/cards/search?q=%21%22Rattenkolonie%22+lang%3Aany
	 * @param searchQuery
	 * @throws IOException
	 */
	private static JsonElement cardSearchQuery(String searchQuery) throws IOException {
		return apiConnection(CARDS + SEARCH_QUERY + URLEncoder.encode(searchQuery, "UTF-8"));
	}

	/**
	 * Returns all currently listed sets.
	 * 
	 * @return {@code List<Set> listSets}
	 * @throws IOException
	 */
	public static List<MtgSetInformation> getAllSets() throws IOException {
		List<MtgSetInformation> listSets = new ArrayList<>();
		JsonObject result = apiConnection(SETS).getAsJsonObject();
		JsonArray setArray = result.get("data").getAsJsonArray();
		for (JsonElement setElement : setArray) {
			listSets.add(new MtgSetInformation(setElement.getAsJsonObject()));
		}
		return listSets;
	}

	/**
	 * Returns a list of cards based on the given searchQuery. Includes all
	 * languages and reprints.
	 * 
	 * @param searchQuery
	 * @return {@code List<MtgCardInformation> listCardInformation}
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static List<MtgCardInformation> getCardByCustomSearch(String searchQuery)
			throws IOException, InterruptedException {
		List<MtgCardInformation> listCards = new ArrayList<>();
		JsonObject result = cardSearchQuery(searchQuery + " lang:any unique:prints").getAsJsonObject();

		boolean hasMore = true;
		String nextPage;
		while (hasMore) {
			hasMore = result.get("has_more").getAsBoolean();

			JsonArray cardArray = result.get("data").getAsJsonArray();
			for (JsonElement cardElement : cardArray) {
				listCards.add(new MtgCardInformation(cardElement.getAsJsonObject()));
			}

			if (hasMore) {
				nextPage = result.get("next_page").getAsString();
				Thread.sleep(100);
				result = getJsonResponse(nextPage).getAsJsonObject();
			}
		}
		return listCards;
	}

	/**
	 * Returns all prints of the given card in the correct language if the given
	 * name is not english, or in all languages and all prints if the given name is
	 * english.
	 * 
	 * @param cardName
	 * @example getCardByName("Lightning Bolt") returns 40+ cards. (All reprints,
	 *          all languages)
	 * @return {@code List<Card> listCards}
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static List<MtgCardInformation> getCardByName(String cardName) throws IOException, InterruptedException {
		List<MtgCardInformation> listCards = new ArrayList<>();
		JsonObject result = cardSearchQuery(cardName + " lang:any unique:prints").getAsJsonObject();

		boolean hasMore = true;
		String nextPage;
		while (hasMore) {
			hasMore = result.get("has_more").getAsBoolean();

			JsonArray cardArray = result.get("data").getAsJsonArray();
			for (JsonElement cardElement : cardArray) {
				listCards.add(new MtgCardInformation(cardElement.getAsJsonObject()));
			}

			if (hasMore) {
				nextPage = result.get("next_page").getAsString();
				Thread.sleep(100);
				result = getJsonResponse(nextPage).getAsJsonObject();
			}
		}
		return listCards;
	}

	/**
	 * Returns all prints of the given card in the correct language if the given
	 * name is not english, or in all languages and all prints if the given name is
	 * english.
	 * 
	 * @param uniqueId
	 * @example getCardByName("Lightning Bolt") returns 40+ cards. (All reprints,
	 *          all languages)
	 * @return {@code List<Card> listCards}
	 * @throws IOException
	 */
	public static MtgCardInformation getCardByUniqueId(String uniqueId) throws IOException {
		JsonObject result = apiConnection(CARDS + uniqueId).getAsJsonObject();
		return new MtgCardInformation(result.getAsJsonObject());
	}

	/**
	 * Returns all prints of the given card in the correct language if the given
	 * name is not english, or in all languages and all prints if the given name is
	 * english.
	 * 
	 * @param cardName
	 * @example getCardByName("Lightning Bolt") returns 40+ cards. (All reprints,
	 *          all languages)
	 * @return {@code List<Card> listCards}
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static List<MtgCardInformation> getEnglishCardByName(String cardName)
			throws IOException, InterruptedException {
		List<MtgCardInformation> listCards = new ArrayList<>();
		JsonObject result = cardSearchQuery(cardName + " unique:prints").getAsJsonObject();

		boolean hasMore = true;
		String nextPage;
		while (hasMore) {
			hasMore = result.get("has_more").getAsBoolean();

			JsonArray cardArray = result.get("data").getAsJsonArray();
			for (JsonElement cardElement : cardArray) {
				listCards.add(new MtgCardInformation(cardElement.getAsJsonObject()));
			}

			if (hasMore) {
				nextPage = result.get("next_page").getAsString();
				Thread.sleep(100);
				result = getJsonResponse(nextPage).getAsJsonObject();
			}
		}
		return listCards;
	}

	/**
	 * Returns the response from the api as a jsonElement ready to parse.
	 * 
	 * @param urlString
	 * @return {@code JsonElement jsonResponse}
	 * @throws IOException
	 */
	private static JsonElement getJsonResponse(String urlString) throws IOException {
		URL url = new URL(urlString);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder jsonString = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			jsonString.append(line);
		}
		bufferedReader.close();
		return new JsonParser().parse(jsonString.toString());
	}

	/**
	 * Returns a specific set.
	 * 
	 * @return {@code List<Set> listSets}
	 * @throws IOException
	 */
	public static MtgSetInformation getSetByCode(String setCode) throws IOException {
		JsonObject result = apiConnection(SETS + setCode).getAsJsonObject();
		return new MtgSetInformation(result);
	}
}
