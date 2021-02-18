package Pieces;

import squares.Square;

public class Queen extends Piece implements Movable{

	public Queen(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "Queen";
	}

	@Override
	public void makeMove(Square square) {
		
	}

}
