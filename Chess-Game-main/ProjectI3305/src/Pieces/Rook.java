package Pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Board.Board;
import Position.Location;
import Position.LocationFactory;
import squares.Square;

public class Rook extends Piece implements Movable{

	//This is the constructor of the Class
	public Rook(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "Rook";
	}
	
	@Override
	public List<Location> getValidMoves(Board board) {
	    List<Location> moveCandidates = new ArrayList<>();
	    Map<Location, Square> squareMap = board.getLocationSquareMap();
	    Location current = this.getCurrSquare().getLocation();
	    getColumnCandidates(moveCandidates, squareMap, current, -1);
	    getColumnCandidates(moveCandidates, squareMap, current, 1);
	    getRowCandidates(moveCandidates, squareMap, current, -1);
	    getRowCandidates(moveCandidates, squareMap, current, 1);
	    return moveCandidates;
	  }
	
	//getRowCandidates
	private void getRowCandidates(List<Location> moveCandidates,Map<Location, Square> squareMap,Location current,int offset) {
		    try {
		    	Location next = LocationFactory.build(current, current.getColumn(), offset);
		      while (squareMap.containsKey(next)) {
		        if (squareMap.get(next).getOccupied()) {
		        	//If occupied and from the same color,break
		          if (squareMap.get(next).getCurrPiece().pieceColor.equals(this.pieceColor)) {
		            break;
		          }
		          //if not same color, I add to Location List
		          moveCandidates.add(next);
		          break;
		        }
		        //if not occupied, I add to location List
		        moveCandidates.add(next);
		        next = LocationFactory.build(next, next.getColumn(), offset);
		        
		      }//while
		      
		    } catch (Exception e) {
		    }
		 }
	
	// what are the columns that my rook  can move on 
	 private void getColumnCandidates(List<Location> moveCandidates,Map<Location, Square> squareMap,Location current, int offset) {
		    try {
		    	//row is 0 because we are on the same row 
		      Location next = LocationFactory.build(current, offset, 0);
		      while (squareMap.containsKey(next)) {
		        if (squareMap.get(next).getOccupied()) {
		          if (squareMap.get(next).getCurrPiece().pieceColor.equals(this.pieceColor)) {
		            break;
		          }
		          moveCandidates.add(next);
		          break;
		        }
		        moveCandidates.add(next);
		        next = LocationFactory.build(next, offset, 0);
		      }
		    } catch (Exception e) { 
		    	
		    }
		  }
	 
	  
		  @Override
		  public List<Location> getValidMoves(Board board, Square square) {
		    List<Location> moveCandidates = new ArrayList<>();
		    Map<Location, Square> squareMap = board.getLocationSquareMap();
		    Location current = square.getLocation();
		    //-1 means 1 to the left 
		    getColumnCandidates(moveCandidates, squareMap, current, -1);
		    // 1 means 1 to the right
		    getColumnCandidates(moveCandidates, squareMap, current, 1);
		    // -1 means 1 down 
		    getRowCandidates(moveCandidates, squareMap, current, -1);
		    //1 means 1 up
		    getRowCandidates(moveCandidates, squareMap, current, 1);
		    
		    return moveCandidates;
		  }
		  

	@Override
	public void makeMove(Square square) {
		
	}

}
