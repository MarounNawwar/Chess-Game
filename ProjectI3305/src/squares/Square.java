package squares;

import Pieces.Piece;
import Position.Location;

public class Square {

	private final SquareColor squarecolor;
	private final Location location;
	private boolean isOccupied;
	private Piece currPiece;
	
	
	//Constructor for the class
	//The square at creation is not Occupied by any Piece
	public Square(SquareColor squarecolor, Location location) {
		this.squarecolor = squarecolor;
		this.location = location;
		this.isOccupied = false;
	}

	
	//Will be used to make the square no longer Occupied when piece move from it
	public void reset() {
		this.isOccupied = false;
		this.currPiece = null;
	}
	
	//Getters and Setters
	
	public boolean getOccupied() {
		return this.isOccupied;
	}
	
	public void setOcuppied(boolean status) {
		this.isOccupied = status;
	}
	
	public Piece getCurrPiece() {
		return this.currPiece;
	}
	
	public void SetCurrPiece(Piece piece) {
		this.currPiece = piece;
		setOcuppied(true);
	}

	public SquareColor getSquareColor() {
		return this.squarecolor;
	}
	
	public Location getLocation() {
		return location;
	}

}
