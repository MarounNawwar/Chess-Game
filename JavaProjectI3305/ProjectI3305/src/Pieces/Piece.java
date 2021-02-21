package Pieces;

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
