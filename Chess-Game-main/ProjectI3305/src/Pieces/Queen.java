package Pieces;

import java.util.ArrayList;
import java.util.List;

import Board.Board;
import Position.Location;
import squares.Square;

public class Queen extends Piece implements Movable{

	private Movable bishop;
	  private Movable rook;


	  public Queen(PieceColor pieceColor) {
	    this(pieceColor, new Bishop(pieceColor), new Rook(pieceColor));
	  }

	  public Queen(PieceColor pieceColor, Movable bishop, Movable rook) {
	    super(pieceColor);
	    this.name = "Queen";
	    this.bishop = bishop;
	    this.rook = rook;
	  }

	  @Override
	  public List<Location> getValidMoves(Board board) {
	    List<Location> moveCandidates = new ArrayList<>();
	    moveCandidates.addAll(bishop.getValidMoves(board, this.getCurrSquare()));
	    moveCandidates.addAll(rook.getValidMoves(board, this.getCurrSquare()));
	    return moveCandidates;
	  }

	  @Override
	  public List<Location> getValidMoves(Board board, Square square) {
	    return null;
	  }

	  @Override
	  public void makeMove(Square square) {
	    Square current = this.getCurrSquare();
	    this.setCurrSquare(square);
	    current.reset();
	  }
	}

