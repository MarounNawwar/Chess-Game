package squares;

import Pieces.Piece;

public class Square {

	private final SquareColor squarecolor;
	private boolean isOccupied;
	private Piece currPiece;
	
	
	//Constructor for the class
	//The square at creation is not Occupied by any Piece
	public Square(SquareColor squarecolor) {
		this.squarecolor = squarecolor;
		this.isOccupied=false;
	}
	
	//Function to reset the Object
	public void reset() {
		this.isOccupied=false;
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
	
}
