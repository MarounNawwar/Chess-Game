package Pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Board.Board;
import Position.Location;
import Position.LocationMaker;
import javafx.scene.image.Image;
import squares.Square;

public class Queen extends Piece{

	public Queen(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "queen";
		
		String location = "images/";
        String filename = this.getPieceColor() + "_" + this.getName() + ".png";
        this.image = new Image(location + filename);
	}

	@Override
	public List<Location> getValidMoves(Board board) {

		List<Location> moveCandidates = new ArrayList<>();
		Map<Location, Square> squareMap = board.getLocationSquareMap();
		Location current = this.getCurrSquare().getLocation();
		
		getMovesInDirection(moveCandidates,squareMap,current,1,1);
		getMovesInDirection(moveCandidates,squareMap,current,1,-1);
		getMovesInDirection(moveCandidates,squareMap,current,-1,1);
		getMovesInDirection(moveCandidates,squareMap,current,-1,-1);
		getMovesInDirection(moveCandidates,squareMap,current,1,0);
		getMovesInDirection(moveCandidates,squareMap,current,0,1);
		getMovesInDirection(moveCandidates,squareMap,current,0,-1);
		getMovesInDirection(moveCandidates,squareMap,current,-1,0);	
		
		return moveCandidates;
	}
	
	
	//Get all the possible moves in a given Direction
	public void getMovesInDirection(List<Location> moveCandidates, Map<Location, Square> squareMap, Location currLocation, int RowOffSet, int ColOffSet) {
			try {
				
				Location next = LocationMaker.build(currLocation, ColOffSet, RowOffSet);
				
				while(squareMap.containsKey(next) && next != null) {
					//Case the Square is Occupied
					if(squareMap.get(next).isOccupied() && squareMap.get(next).getPieceColor() != null) {
						//Case where the square is occupied by a piece belonging to the player
						if(squareMap.get(next).getPieceColor() != null && squareMap.get(next).getPieceColor().equals(this.pieceColor)) {
							break;
						}else {
							//Case where the square is occupied by a piece belonging to the opponent
							moveCandidates.add(next);
							break;
						}
					}	
					moveCandidates.add(next);
					next = LocationMaker.build(next, ColOffSet, RowOffSet);
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
