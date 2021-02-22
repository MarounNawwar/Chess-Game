package Pieces;

import java.util.List;

import Position.Location;
import squares.Square;
import Board.Board;

public interface Movable {
	
	List<Location> getValidMoves(Board board);
	void makeMove(Square square);
	
}
