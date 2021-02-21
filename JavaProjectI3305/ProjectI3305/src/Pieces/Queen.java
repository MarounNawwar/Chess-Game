package Pieces;

import javafx.scene.image.Image;
import squares.Square;

public class Queen extends Piece{

	public Queen(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "queen";
		
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
