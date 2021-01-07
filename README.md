# scryfall4j
> A Java wrapper for the official <a href="https://scryfall.com/docs/api">Scryfall API</a>
## Features
Scryfall4j tries to implement every feature offered by the official REST API.
* Get card by ID
* Get a list of all cards (this may take some time...)
* Get a list of all sets
* Get a list of cards matching your search query
* Get a list of all cards in a specified set
* Get a set specified by Code / ID / TCGPlayerID

## Example
### Get a list of cards matching your search criteria
```java
List<MtgCardInformation> listCards = getListCardsBySearch("Black Lotus", true, false, false);
		
for (MtgCardInformation card : listCards) {
	System.out.println(card);
}
```
### Get information about any set just by its code
```java
MtgSetInformation battlebond = getSetByCode("BBD");
battlebond.getCardCount();
battlebond.getReleaseDate();
		
List<MtgCardInformation> listCards = getListCardsBySet(battlebond);
```
