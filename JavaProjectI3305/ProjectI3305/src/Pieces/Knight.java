package Pieces;

import javafx.scene.image.Image;
import squares.Square;

public class Knight extends Piece{

	//This is the constructor
	public Knight(PieceColor pieceColor) {
		super(pieceColor);
		this.name="knight";
		
		String location = "assets/";
        String filename = this.getPieceColor() + "_" + this.getName() + ".png";
        this.image = new Image(location + filename);
	}

	//Getters and Setters
	
	@Override
	public void makeMove(Square square) {
		//TODO
	}

	@Override
	public Image getImage() {
		return this.image;
	}

}