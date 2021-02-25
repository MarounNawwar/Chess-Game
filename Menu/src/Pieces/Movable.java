package Pieces;

import java.util.List;

import Position.Location;
import squares.Square;
import Board.Board;

public interface Movable {
	
	List<Location> getValidMoves(Board board);
	//used for Check&checkmate
	List<Location> getValidMoves(Board board, List<Location> targetSquares);
	
	void makeMove(Square square);
	
}
