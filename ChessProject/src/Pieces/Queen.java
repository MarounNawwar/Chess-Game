package Pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Board.Board;
import Position.Location;
import Position.LocationFactory;
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
	public List<Location> getValidMoves(Board board) {
		PieceColor Color;
		List<Location> moveCandidates = new ArrayList<>();
		Map<Location, Square> squareMap = board.getLocationSquareMap();
		Location current = this.getCurrSquare().getLocation();
		int CurrRow = this.getRow();
		int CurrColumn = this.getColumn();
		
		for(int i = -1;i <= 1; i++) {
			for(int j= -1;j <= 1; j++) {
				if(i==0 && j==0) {
					continue;
				}else if((Color = Board.squareIsOccupied(CurrRow+i, CurrColumn+j)) != null) {
					if( Color != this.getPieceColor()) {
					
						moveCandidates.add(LocationFactory.build(current, j, i));
						break;
					
					}else {
						
						break;
					
					}
				}else{
					
					getMovesInDirection(moveCandidates,squareMap,current,i,j);
				
				}
			}
		}
		return moveCandidates;
	}
	
	
	//Get all the possible moves in a given Direction
		public void getMovesInDirection(List<Location> moveCandidates, Map<Location, Square> squareMap, Location currLocation, int RowOffSet, int ColOffSet) {
			try {
				Location next = LocationFactory.build(currLocation, ColOffSet, RowOffSet);
				Location prev= null;
				
				while(squareMap.containsKey(next)&& prev != next) {
					//Case the Square is Occupied
					if(squareMap.get(next).isOccupied()) {
						//Case where the square is occupied by a piece belonging to the player
						if(squareMap.get(next).getPieceColor().equals(this.pieceColor)) {
							break;
						}else {
							//Case where the square is occupied by a piece belonging to the opponent
							moveCandidates.add(next);
							break;
						}
					}	
					
					moveCandidates.add(next);
					prev= next;
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
