package Pieces;

import squares.Square;

public class Bishop extends Piece implements Movable{

	//This is the constructor 
	public Bishop(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "Bishop";
	}

	@Override
	public void makeMove(Square square) {
		
	}

}
