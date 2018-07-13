package de.ics.scryfall;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * This class is the entry-point for all requests to the Scryfall-API.
 * @author QUE
 *
 */
public class Scryfall {
	private static final String URL_API = "https://api.scryfall.com/";
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
		return getJsonResponse(URL_API + uri);
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
		return getJsonResponse(URL_API + CARDS + SEARCH_QUERY + URLEncoder.encode(searchQuery, "UTF-8"));
	}

	/**
	 * Returns all currently listed sets.
	 * 
	 * @return {@code List<Set> listSets}
	 * @throws IOException
	 */
	public static List<SetInformation> getAllSets() throws IOException {
		List<SetInformation> listSets = new ArrayList<>();
		JsonObject result = apiConnection(SETS).getAsJsonObject();
		JsonArray setArray = result.get("data").getAsJsonArray();
		for (JsonElement setElement : setArray) {
			listSets.add(new SetInformation(setElement.getAsJsonObject()));
		}
		return listSets;
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
	 */
	public static List<CardInformation> getCardByName(String cardName) throws IOException {
		List<CardInformation> listCards = new ArrayList<>();
		JsonObject result = cardSearchQuery(cardName + " lang:any unique:prints").getAsJsonObject();
		JsonArray cardArray = result.get("data").getAsJsonArray();
		for (JsonElement cardElement : cardArray) {
			listCards.add(new CardInformation(cardElement.getAsJsonObject()));
		}

		return listCards;
	}

	/**
	 * returns the response from the api as a jsonElement ready to parse.
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
}
