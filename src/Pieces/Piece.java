package Pieces;

import Board.Board;
import javafx.scene.image.*;
import squares.Square;

public abstract class Piece implements Movable{
	protected String name;
	protected PieceColor pieceColor;
	protected Square currentSquare;
	protected Image image;
	
	//Constructor 
	public Piece(PieceColor pieceColor) {
		this.pieceColor = pieceColor;
	}
	
	
	@Override
	public void makeMove(Square square) {
		if(this.getValidMoves(Board.getInstance()).contains(square.getLocation())) {
			Piece MovingPiece = this.getCurrSquare().releasePiece();
			MovingPiece.setCurrSquare(square);
			square.SetCurrPiece(MovingPiece);
		}
	}
	
	public int getRow() {
		return this.getCurrSquare().getRow();
	}
	
	public int getColumn() {
		return this.getCurrSquare().getColumn();
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

	public abstract Image getImage();
	
}
