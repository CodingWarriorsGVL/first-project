package main;

public class Card {
	int value;
	int suit;
	
	public Card (int suit, int value) {
		this.suit = suit;
		this.value = value;
	}
	
	public int getSuit() {
		return suit;
	}
	public int getValue() {
		 return value;
	}
	
	public boolean isFace() {
		if (value > 10) 
			return true;
		else return false;
	}
	
}
