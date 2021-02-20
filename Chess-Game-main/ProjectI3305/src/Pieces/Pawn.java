package Pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import Board.Board;
import Position.Location;
import Position.LocationFactory;
import squares.Square;

public class Pawn extends Piece implements Movable{
	
	private boolean isFirstMove;  
	
	
	//This is the constructor of the class
	public Pawn(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "Pawn";
		this.isFirstMove = true;
	}
	
	@Override
	  public List<Location> getValidMoves(Board board) {
	    List<Location> moveCandidates = new ArrayList<>();
	    Location current = this.getCurrSquare().getLocation();
	    int sign = (pieceColor.equals(PieceColor.BLACK)) ? -1 : 1;
	    
	    moveCandidates.add(LocationFactory.build(current, 0, sign));
	    //If it's the first move of the pawn so depending on it's color he can go 2 squares forward 
	    //if black btenzal(negative) 2 aw 1
	    //if white btetla3 2 aw 1
	    if(isFirstMove) {
	      moveCandidates.add(LocationFactory.build(current, 0, 2 * sign));
	      return moveCandidates;
	    }
	    	
	   //If not first move then he can go only 1 step forward
	    moveCandidates.add(LocationFactory.build(current, 1, sign));
	    moveCandidates.add(LocationFactory.build(current, -1, sign));
	    Map<Location, Square> squareMap = board.getLocationSquareMap();
	    List<Location> validMoves = moveCandidates.stream().filter(squareMap::containsKey).collect(Collectors.toList());

	    return validMoves.stream().filter((candidate) -> {
	      // occupied
	      if(candidate.getColumn().equals(this.getCurrSquare().getLocation().getColumn()) &&
	          squareMap.get(candidate).getOccupied()) {
	        return false;
	      }

	      // occupied in front.
	      if (squareMap.get(candidate).getOccupied() &&
	          candidate.getColumn().equals(this.getCurrSquare().getLocation().getColumn())) {
	        return false;
	      }

	      // occupied on diagonal with opposite color
	      if (squareMap.get(candidate).getOccupied() &&
	          squareMap.get(candidate).getCurrPiece().getPieceColor().equals(this.getPieceColor()) &&
	          candidate.getColumn().equals(this.getCurrSquare().getLocation().getColumn())
	      ) {
	        return false;
	      }

	      if (!squareMap.get(candidate).getOccupied() &&
	          !candidate.getColumn().equals(this.getCurrSquare().getLocation().getColumn())) {
	        return false;
	      }

	      return true;
	    }).collect(Collectors.toList());

	  }

	  @Override
	  public List<Location> getValidMoves(Board board, Square square) {
	    return null;
	  }

	  @Override
	  public void makeMove(Square square) {
	    if (isFirstMove) {
	      isFirstMove = false;
	    }
	    this.currentSquare.setOcuppied(false);
	    this.setCurrSquare(square);
	    square.SetCurrPiece(this);
	    square.setOcuppied(true);
	  }
	
	
	//Getters and Setters
	public boolean verifyIfFirstMove() {
		return this.isFirstMove;
	}

//	public void hasMoved() {
//		this.isFirstMove = false;
//	}

	


}
