package Pieces;

import javafx.scene.image.Image;
import squares.Square;

public class King extends Piece {

	public King(PieceColor pieceColor) {
		super(pieceColor);
		this.name= "king";
		
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
