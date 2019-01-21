package de.ics.scryfall;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Class that catches NullPointerExceptions while getting responses from the
 * Json-File.
 * 
 * @author QUE
 *
 */
public class JsonHelper {
	protected static BigDecimal bigDecimalJsonResponse(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsBigDecimal();
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}

	protected static boolean booleanFromStringJsonResponse(JsonObject jObject, String fieldName, String trueString,
			String falseString) {
		try {
			String returnedString = jObject.get(fieldName).getAsString();
			if (returnedString.equals(trueString)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	protected static boolean booleanJsonResponse(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsBoolean();
		} catch (Exception e) {
			return false;
		}
	}

	protected static double doubleJsonResponse(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsDouble();
		} catch (Exception e) {
			return 0;
		}
	}

	protected static int integerJsonResponse(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsInt();
		} catch (Exception e) {
			return 0;
		}
	}

	protected static List<String> listStringJsonResponse(JsonObject jObject, String arrayName) {
		List<String> listString = new ArrayList<>();
		try {
			JsonArray jArray = jObject.get(arrayName).getAsJsonArray();
			for (JsonElement jElement : jArray) {
				listString.add(jElement.getAsString());
			}
			return listString;
		} catch (Exception e) {
			return new ArrayList<String>();
		}
	}

	protected static LocalDateTime localDateTimeJsonResponse(JsonObject jObject, String fieldName,
			DateTimeFormatter dtf) {
		try {
			return LocalDateTime.parse(jObject.get(fieldName).getAsString(), dtf);
		} catch (Exception e) {
			return LocalDateTime.now();
		}
	}

	protected static Legality parseLegalities(JsonObject jObject, String fieldName) {
		try {
			return new Legality(jObject.get(fieldName).getAsJsonObject());
		} catch (Exception e) {
			return new Legality(false, false, false, false, false, false, false, false, false, false, false, false);
		}
	}

	protected static List<CardFace> parseListCardFaces(JsonObject jObject, String arrayName) {
		try {
			List<CardFace> listCardFaces = new ArrayList<>();
			JsonArray jArray = jObject.get(arrayName).getAsJsonArray();
			for (JsonElement jElement : jArray) {
				JsonObject jCardFace = jElement.getAsJsonObject();
				listCardFaces.add(new CardFace(jCardFace));
			}
			return listCardFaces;
		} catch (Exception e) {
			return new ArrayList<CardFace>();
		}
	}

	protected static List<RelatedCard> parseListRelatedCards(JsonObject jObject, String arrayName) {
		try {
			List<RelatedCard> listRelatedCards = new ArrayList<>();
			JsonArray jArray = jObject.get(arrayName).getAsJsonArray();
			for (JsonElement jElement : jArray) {
				JsonObject jRelatedCard = jElement.getAsJsonObject();
				listRelatedCards.add(new RelatedCard(jRelatedCard));
			}
			return listRelatedCards;
		} catch (Exception e) {
			return new ArrayList<RelatedCard>();
		}
	}

	protected static String stringJsonResponse(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsString();
		} catch (Exception e) {
			return "";
		}
	}

}
