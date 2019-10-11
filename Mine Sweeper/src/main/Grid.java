package main;

public class Grid {
	Tile[][] grid;
	public Grid(int width, int height) {
		grid = new Tile[height][width];
	}
	
	public void genMines(int numberMines) {
		for (int i=0; i<numberMines; i++) {
			Main.random.nextInt();
			//Do two random numbers and dump a mine in the grid at that spot.
		}
	}
}
