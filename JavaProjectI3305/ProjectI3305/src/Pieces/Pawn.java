package Pieces;

import javafx.scene.image.Image;
import squares.Square;

public class Pawn extends Piece{
	
	private boolean isFirstMove;  
	
	
	//This is the constructor of the class
	public Pawn(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "pawn";
		
		this.isFirstMove = true;
		
		String location = "assets/";
        String filename = this.getPieceColor() + "_" + this.getName() + ".png";
        this.image = new Image(location + filename);
	}

	@Override
	public void makeMove(Square square) {
		//TODO
	}
	
	//Getters and Setters
	
	public boolean verifyIfFirstMove() {
		return this.isFirstMove;
	}

	public void hasMoved() {
		this.isFirstMove = false;
	}

	@Override
	public Image getImage() {
		return this.image;
	}

}
