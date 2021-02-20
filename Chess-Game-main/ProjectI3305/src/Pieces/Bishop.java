package Pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Board.Board;
import Position.Location;
import Position.LocationFactory;
import squares.Square;

public class Bishop extends Piece implements Movable{

	//This is the constructor 
	public Bishop(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "Bishop";
	}
	
	@Override
	  public List<Location> getValidMoves(Board board) {
	    List<Location> moveCandidates = new ArrayList<>();
	    Map<Location, Square> squareMap = board.getLocationSquareMap();
	    Location current = this.getCurrSquare().getLocation();
	    //Bishop can go over diagonal up or down 
	    getMoves(moveCandidates, squareMap, current, 1, 1);
	    getMoves(moveCandidates, squareMap, current, 1, -1);
	    getMoves(moveCandidates, squareMap, current, -1, -1);
	    getMoves(moveCandidates, squareMap, current, -1, 1);
	    return moveCandidates;
	  }
	

	  private void getMoves(List<Location> candidates,Map<Location, Square> squareMap,Location current,int rowOffset,int columnOffset) {
	    try {
	      Location next = LocationFactory.build(current, columnOffset, rowOffset);
	      while (squareMap.containsKey(next)) {
	        if (squareMap.get(next).getOccupied()) {
	          if (squareMap.get(next).getCurrPiece().pieceColor.equals(this.pieceColor)) break;
	          candidates.add(next);
	          break;
	        }
	        candidates.add(next);
	        next = LocationFactory.build(next, columnOffset, rowOffset);
	      }
	    } catch (Exception e) { }
	  }

	  @Override
	  public List<Location> getValidMoves(Board board, Square square) {
	    List<Location> moveCandidates = new ArrayList<>();
	    Map<Location, Square> squareMap = board.getLocationSquareMap();
	    Location current = square.getLocation();
	    getMoves(moveCandidates, squareMap, current, 1, 1);
	    getMoves(moveCandidates, squareMap, current, 1, -1);
	    getMoves(moveCandidates, squareMap, current, -1, -1);
	    getMoves(moveCandidates, squareMap, current, -1, 1);
	    return moveCandidates;
	  }


	@Override
	public void makeMove(Square square) {
		
	}

}
