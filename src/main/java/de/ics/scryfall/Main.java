package de.ics.scryfall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.ics.scryfall.card.Card;

public class Main {

	public static void main(String[] args) throws IOException {
		Scryfall.getAllSets();
		List<Card> listFullCardInformation = new ArrayList<>();
		listFullCardInformation.addAll(Scryfall.getCardByName("Lightning Bolt"));
		listFullCardInformation.addAll(Scryfall.getCardByName("Kugelblitz"));
		listFullCardInformation.addAll(Scryfall.getCardByName("Bushi Tenderfoot"));
		listFullCardInformation.addAll(Scryfall.getCardByName("Odds"));
		listFullCardInformation.addAll(Scryfall.getCardByName("Seelenschnapper"));
		
		listFullCardInformation.addAll(Scryfall.getCardByName("Chittering Host"));
		listFullCardInformation.addAll(Scryfall.getCardByName("Ratos do Cemitério"));
		
		for (Card card : listFullCardInformation) {
			System.out.println(card.toString());
		}
	}

}
