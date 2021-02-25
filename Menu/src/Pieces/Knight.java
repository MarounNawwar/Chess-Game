package Pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Board.Board;
import Position.Location;
import Position.LocationMaker;
import javafx.scene.image.Image;
import squares.Square;

public class Knight extends Piece{

	//This is the constructor
	public Knight(PieceColor pieceColor) {
		super(pieceColor);
		this.name="knight";
		
		String location = "images/";
        String filename = this.getPieceColor() + "_" + this.getName() + ".png";
        this.image = new Image(location + filename);
	}

	
	@Override
	public List<Location> getValidMoves(Board board) {
		List<Location> moveCandidates = new ArrayList<>();
		Map<Location, Square> squareMap = board.getLocationSquareMap();
		Location current = this.getCurrSquare().getLocation();
		
		getMoves(moveCandidates, squareMap, current, 2, 1);
	    getMoves(moveCandidates, squareMap, current, 2, -1);
	    getMoves(moveCandidates, squareMap, current, -2, 1);
	    getMoves(moveCandidates, squareMap, current, -2, -1);
	    getMoves(moveCandidates, squareMap, current, 1, 2);
	    getMoves(moveCandidates, squareMap, current, -1, -2);
	    getMoves(moveCandidates, squareMap, current, 1, -2);
	    getMoves(moveCandidates, squareMap, current, -1, 2);
	    return moveCandidates;
	}
	
	
	private void getMoves(List<Location> moveCandidates, Map<Location, Square> squareMap, Location current, int columnOffSet, int rowOffSet) {
		try {
			
			Location next= LocationMaker.build(current, columnOffSet, rowOffSet);
			
			//Check that the location is valid and available on the board
			if(squareMap.containsKey(next)) {
				//Case where the square is occupied by a piece belonging to the player
				if(squareMap.get(next).getPieceColor() != null && squareMap.get(next).getPieceColor().equals(this.pieceColor)) {
					return;
				}else {
					//Case where the square is either occupied by a piece belonging to the opponent OR is not occupied
					moveCandidates.add(next);
					return;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//Getters and Setters

	@Override
	public Image getImage() {
		return this.image;
	}


}