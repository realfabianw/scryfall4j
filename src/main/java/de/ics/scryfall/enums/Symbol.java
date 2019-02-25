package de.ics.scryfall.enums;

/**
 * This enum contains all colors and symbols as of 23.02.2019
 * 
 * @see https://scryfall.com/docs/api/colors
 * @author QUE
 *
 */
public enum Symbol {
	WHITE("W"), BLUE("U"), BLACK("B"), RED("R"), GREEN("G"), TAP("{T}"), UNTAP("{Q}"), ENERGY_COUNTER("{E}"),
	PLANESWALKER("{PW}"), CHAOS("{CHAOS}"), MANA_GENERIC_X("{X}"), MANA_GENERIC_Y("{Y}"), MANA_GENERIC_Z("{Z}"),
	MANA_GENERIC_0("{0}"), MANA_GENERIC_HALF("{½}"), MANA_GENERIC_1("{1}"), MANA_GENERIC_2("{2}"),
	MANA_GENERIC_3("{3}"), MANA_GENERIC_4("{4}"), MANA_GENERIC_5("{5}"), MANA_GENERIC_6("{6}"), MANA_GENERIC_7("{7}"),
	MANA_GENERIC_8("{8}"), MANA_GENERIC_9("{9}"), MANA_GENERIC_10("{10}"), MANA_GENERIC_11("{11}"),
	MANA_GENERIC_12("{12}"), MANA_GENERIC_13("{13}"), MANA_GENERIC_14("{14}"), MANA_GENERIC_15("{15}"),
	MANA_GENERIC_16("{16}"), MANA_GENERIC_17("{17}"), MANA_GENERIC_18("{18}"), MANA_GENERIC_19("{19}"),
	MANA_GENERIC_20("{20}"), MANA_GENERIC_100("{100}"), MANA_GENERIC_1000000("{1000000}"), MANA_GENERIC_INFINITE("{∞}"),
	MANA_WHITE_OR_BLUE("{W/U}"), MANA_WHITE_OR_BLACK("{W/B}"), MANA_BLACK_OR_RED("{B/R}"), MANA_BLACK_OR_GREEN("{B/G}"),
	MANA_BLUE_OR_BLACK("{U/B}"), MANA_BLUE_OR_RED("{U/R}"), MANA_RED_OR_GREEN("{R/G}"), MANA_RED_OR_WHITE("{R/W}"),
	MANA_GREEN_OR_WHITE("{G/W}"), MANA_GREEN_OR_BLUE("{G/U}"), MANA_GENERIC_2_OR_WHITE("{2/W}"),
	MANA_GENERIC_2_OR_BLUE("{2/U}"), MANA_GENERIC_2_OR_BLACK("{2/B}"), MANA_GENERIC_2_OR_RED("{2/R}"),
	MANA_GENERIC_2_OR_GREEN("{2/G}"), MANA_COLORED_OR_2_LIFE("{P}"), MANA_WHITE_OR_2_LIFE("{W/P}"),
	MANA_BLUE_OR_2_LIFE("{U/P}"), MANA_BLACK_OR_2_LIFE("{B/P}"), MANA_RED_OR_2_LIFE("{R/P}"),
	MANA_GREEN_OR_2_LIFE("{G/P}"), MANA_HALF_WHITE("{H/W}"), MANA_HALF_RED("{H/R}"), MANA_WHITE("{W}"),
	MANA_BLUE("{U}"), MANA_BLACK("{B}"), MANA_RED("{R}"), MANA_GREEN("{G}"), MANA_COLORLESS("{C}"), MANA_SNOW("{S}");

	public static Symbol parseId(String id) {
		for (Symbol e : Symbol.values()) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	private final String id;

	private Symbol(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
