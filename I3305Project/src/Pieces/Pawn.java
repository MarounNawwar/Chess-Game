package Pieces;

import squares.Square;

public class Pawn extends Piece implements Movable{
	private boolean isFirstMove;  
	
	
	//This is the constructor of the class
	public Pawn(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "Pawn";
		this.isFirstMove = true;
	}

	@Override
	public void makeMove(Square square) {
		
	}
	
	//Getters and Setters
	public boolean verifyIfFirstMove() {
		return this.isFirstMove;
	}


	
}
