package Pieces;

import squares.Square;

public class King extends Piece implements Movable{

	public King(PieceColor pieceColor) {
		super(pieceColor);
		this.name= "King";
	}

	@Override
	public void makeMove(Square square) {
		
	}

}
