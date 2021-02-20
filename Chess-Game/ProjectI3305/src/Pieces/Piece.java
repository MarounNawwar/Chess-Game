package Pieces;

import squares.Square;

public abstract class Piece {
	protected String name;
	protected PieceColor pieceColor;
	protected Square currentSquare;
	
	//Constructor 
	public Piece(PieceColor pieceColor) {
		this.pieceColor = pieceColor;
	}
	
	//Getters and Setters
	
	public String getName() {
		return this.name;
	}
	
	public PieceColor getPieceColor() {
		return this.pieceColor;
	}
	
	public Square getCurrSquare() {
		return this.currentSquare;
	}
	
	public void setCurrSquare(Square currSquare) {
		this.currentSquare = currSquare;
	}
	
}
