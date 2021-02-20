package Pieces;

import java.util.List;

import Board.Board;
import Position.Location;
import squares.Square;

public interface Movable {
	 List<Location> getValidMoves(Board board);
	  List<Location> getValidMoves(Board board, Square square);
	  void makeMove(Square square);
}
