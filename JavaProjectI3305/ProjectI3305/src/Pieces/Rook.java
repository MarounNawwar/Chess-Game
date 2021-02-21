package Pieces;

import javafx.scene.image.Image;
import squares.Square;

public class Rook extends Piece {

	//This is the constructor of the Class
	public Rook(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "rook";
		
		String location = "assets/";
        String filename = this.getPieceColor() + "_" + this.getName() + ".png";
        this.image = new Image(location + filename);
	}

	@Override
	public void makeMove(Square square) {
		//TODO
	}

	@Override
	public Image getImage() {
		return this.image;
	}

}
