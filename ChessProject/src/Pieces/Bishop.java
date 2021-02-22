package Pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Board.Board;
import Position.Location;
import Position.LocationFactory;
import javafx.scene.image.Image;
import squares.Square;

public class Bishop extends Piece{

	//This is the constructor 
	public Bishop(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "bishop";
		
		String location = "assets/";
        String filename = this.getPieceColor() + "_" + this.getName() + ".png";
        this.image = new Image(location + filename);
	}

	//Get all the valid moves 
	@Override
	public List<Location> getValidMoves(Board board){
		  
		//Creating a list that will contain all the possible moves
		List<Location> moveCandidates = new ArrayList<>();
		Map<Location,Square> squareMap = board.getLocationSquareMap();
		Location currLocation = this.getCurrSquare().getLocation();
		  
		//Retrieve the Moves in the allowed Direction of the Bishop
		getMovesInDirection(moveCandidates,squareMap,currLocation,1,1);
		getMovesInDirection(moveCandidates,squareMap,currLocation,1,-1);
		getMovesInDirection(moveCandidates,squareMap,currLocation,-1,1);
		getMovesInDirection(moveCandidates,squareMap,currLocation,-1,-1);
		  
		return moveCandidates;
	  }
	

	//Get all the possible moves in a given Direction
	public void getMovesInDirection(List<Location> moveCandidates, Map<Location, Square> squareMap, Location currLocation, int RowOffSet, int ColOffSet) {
		try {
			
			//To be sure to not let the Location get stuck in an infinite loop,
			//we add a prev Location to compare the prev location fetched to the newer
			Location next = LocationFactory.build(currLocation, ColOffSet, RowOffSet);
			Location prev = null;
			
			while(squareMap.containsKey(next) && prev != next) {
				
				//Case the Square is Occupied
				if(squareMap.get(next).isOccupied()) {
					
					if(squareMap.get(next).getPieceColor().equals(this.pieceColor)){
						//Case where the square is occupied by a piece of the player
						break;
					}else {
						//Case where the square is occupied by a piece of the opponent (The square is added because it is an option to kill the piece)
						moveCandidates.add(next);
						break;	
					}
				}	
				
				moveCandidates.add(next);
				prev = next;
				next = LocationFactory.build(next, ColOffSet, RowOffSet);
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
