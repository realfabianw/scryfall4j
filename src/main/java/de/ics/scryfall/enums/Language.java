package de.ics.scryfall.enums;

public enum Language {
	ENGLISH("en"), SPANISH("es"), FRENCH("fr"), GERMAN("de"), ITALIAN("it"), PORTUGUESE("pt"), JAPANESE("ja"),
	KOREAN("ko"), RUSSIAN("ru"), SIMPLIFIEDCHINESE("zhs"), TRADITIONALCHINESE("zht");

	public static Language getLanguage(String languageCode) {
		for (Language language : Language.values()) {
			if (language.getCode().equalsIgnoreCase(languageCode)) {
				return language;
			}
		}
		return null;
	}

	private String code;

	private Language(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
