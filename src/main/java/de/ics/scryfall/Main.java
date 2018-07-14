package de.ics.scryfall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		List<CardInformation> listCards = new ArrayList<>();
		
		listCards.addAll(Scryfall.getCardByName("Lightning Bolt"));
		listCards.addAll(Scryfall.getCardByName("Erratic Mutation"));
		listCards.addAll(Scryfall.getCardByName("Lore Weaver"));
		listCards.addAll(Scryfall.getCardByName("Brine Elemental"));
		
		System.out.println(listCards);
	}

}
