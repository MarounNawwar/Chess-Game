package Pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Board.Board;
import Position.Location;
import squares.Square;

public class King extends Piece implements Movable{
	 private final Movable rook;
	  private final Movable bishop;

	public King(PieceColor pieceColor,Movable rook, Movable bishop) {
		super(pieceColor);
		this.name= "King";
		this.rook = rook;
		this.bishop = bishop;
	}

	  public King(PieceColor pieceColor) {
	    this(pieceColor, new Bishop(pieceColor), new Rook(pieceColor));
	  }

	  @Override
	  public List<Location> getValidMoves(Board board) {
	    List<Location> moveCandidates = new ArrayList<>();
	    moveCandidates.addAll(rook.getValidMoves(board, this.getCurrSquare()));
	    moveCandidates.addAll(bishop.getValidMoves(board, this.getCurrSquare()));
	    Location current = this.getCurrSquare().getLocation();
	    return moveCandidates.stream()
	        .filter(candidate -> (
	            Math.abs(candidate.getColumn() - current.getColumn()) == 1 &&
	                Math.abs(candidate.getRow() - current.getRow()) == 1))
	        .collect(Collectors.toList());
	  }

	  @Override
	  public List<Location> getValidMoves(Board board, Square square) {
	    return null;
	  }
	@Override
	public void makeMove(Square square) {
		
	}

}
