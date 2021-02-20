package Pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Board.Board;
import Position.Location;
import Position.LocationFactory;
import squares.Square;

public class Knight extends Piece implements Movable{

	//This is the constructor
	public Knight(PieceColor pieceColor) {
		super(pieceColor);
		this.name="Knight";
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


	  private void getMoves(
	      List<Location> candidates,
	      Map<Location, Square> squareMap,
	      Location current,
	      int rankOffset,
	      int fileOffset) {
	    try {
	      Location next = LocationFactory.build(current, fileOffset, rankOffset);
	      if (squareMap.containsKey(next)) {
	        if (squareMap.get(next).getOccupied()) {
	          if (squareMap.get(next).getCurrPiece().pieceColor.equals(this.pieceColor)) return;
	          candidates.add(next);
	          return;
	        }
	        candidates.add(next);
	      }
	    } catch (Exception e) { }
	  }

	  @Override
	  public List<Location> getValidMoves(Board board, Square square) {
	    return null;
	  }

	  @Override
	  public void makeMove(Square square) {
	    this.currentSquare.setOcuppied(false);
	    this.setCurrSquare(square);
	    square.SetCurrPiece(this);
	    square.setOcuppied(true);
	  }
	}