package Pieces;

import java.util.ArrayList;
import java.util.List;

import Board.Board;
import Position.Location;
import Position.LocationFactory;
import javafx.scene.image.Image;

public class King extends Piece {

	public King(PieceColor pieceColor) {
		super(pieceColor);
		this.name= "king";
		
		String location = "assets/";
        String filename = this.getPieceColor() + "_" + this.getName() + ".png";
        this.image = new Image(location + filename);
	}

	
	@Override
	public List<Location> getValidMoves(Board board) {
		PieceColor Color;
		
		List<Location> moveCandidates = new ArrayList<>();
		Location current = this.getCurrSquare().getLocation();
		int CurrRow = this.getRow();
		int CurrColumn = this.getColumn();
		
		//TODO: Add condition that King is not in danger
		
		for(int i = -1;i <= 1; i++) {
			for(int j= -1;j <= 1; j++) {
				if(i==0 && j==0) {
					//Case to be avoided by the loop
					continue;
				}else if((Color = Board.squareIsOccupied(CurrRow+i, CurrColumn+j)) != null && Color == this.getPieceColor()) {
					continue;
				}else {
					//Case where the square is either empty or occupied by a piece of the opponent
					//TODO: add the detect of check and the possibility if the piece can be added later
					moveCandidates.add(LocationFactory.build(current, j, i));
			
				}
			}
		}
		return moveCandidates;
	}
	
	//Getters and Setters

	@Override
	public Image getImage() {
		return this.image;
	}

}
