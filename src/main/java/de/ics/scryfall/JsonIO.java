package de.ics.scryfall;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.google.gson.JsonObject;

class JsonIO {
	static BigDecimal parseBigDecimal(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsBigDecimal();
		} catch (Exception e) {
			return null;
		}
	}

	static boolean parseBoolean(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsBoolean();
		} catch (Exception e) {
			return false;
		}
	}

	static double parseDouble(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsDouble();
		} catch (Exception e) {
			return 0;
		}
	}

	static int parseInteger(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsInt();
		} catch (Exception e) {
			return 0;
		}
	}

	static LocalDate parseLocalDate(JsonObject jObject, String fieldName, DateTimeFormatter dtf) {
		try {
			return LocalDate.parse(jObject.get(fieldName).getAsString(), dtf);
		} catch (Exception e) {
			return LocalDate.now();
		}
	}

	static String parseString(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsString();
		} catch (Exception e) {
			return null;
		}
	}
}
