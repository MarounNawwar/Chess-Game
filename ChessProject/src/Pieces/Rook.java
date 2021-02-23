package Pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Board.Board;
import Position.Location;
import Position.LocationFactory;
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
	public List<Location> getValidMoves(Board board) {
		List<Location> moveCandidates = new ArrayList<>();
	    Map<Location, Square> squareMap = board.getLocationSquareMap();
	    Location current = this.getCurrSquare().getLocation();
	    
		getRowCandidates(moveCandidates,squareMap,current,-1);
		getRowCandidates(moveCandidates,squareMap,current,1);
		getColumnCandidates(moveCandidates,squareMap,current,1);
		getColumnCandidates(moveCandidates,squareMap,current,-1);
		
		return moveCandidates;
	}

	private void getColumnCandidates(List<Location> moveCandidates, Map<Location, Square> squareMap, Location current,int ColumnOffSet) {
		PieceColor Color;
		try {
			Location next = LocationFactory.build(current, ColumnOffSet, 0);
			while(squareMap.containsKey(next) && next != null) {
				if((Color = Board.squareIsOccupied(next.getRow(), next.getColumn())) != null) {
					if(Color == this.pieceColor) {
						break;
					}else {
						moveCandidates.add(next);
						break;
					}
				}
				moveCandidates.add(next);
				next = LocationFactory.build(next, ColumnOffSet, 0);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private void getRowCandidates(List<Location> moveCandidates, Map<Location, Square> squareMap, Location current, int rowOffSet) {
		PieceColor Color;
		
		try {
		
			Location next = LocationFactory.build(current,0, rowOffSet);
			
			while(squareMap.containsKey(next) && next != null) {
				if((Color = Board.squareIsOccupied(next.getRow(), next.getColumn())) != null) {
					if(Color == this.pieceColor) {
						break;
					}else {
						moveCandidates.add(next);
						break;
					}
				}
				moveCandidates.add(next);
				next = LocationFactory.build(next, 0, rowOffSet);
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
