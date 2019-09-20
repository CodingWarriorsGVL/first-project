package main;

public class Main {

	public static void main(String[] args) {
		
		Card[] deck = new Card[54];
		int deckIndex = 0;
		
		for (int i=0; i<4; i++) {
			for (int j=1; j<14; j++) {
				//create card
				Card c = new Card(i, j);
				//load into array
				deck[deckIndex] = c;
				deckIndex++;
				
			}
		}
		//shuffle
		for (int i=0; i<deck.length-2; i++) { //print the full deck.
			System.out.println("Suit: " + deck[i].getSuit() + " - Value: " + deck[i].getValue());
		}

	}

}
