package Pieces;

import squares.Square;

public class Rook extends Piece implements Movable{

	//This is the constructor of the Class
	public Rook(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "Rook";
	}

	@Override
	public void makeMove(Square square) {
		
	}

}
