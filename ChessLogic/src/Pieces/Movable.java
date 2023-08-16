package Pieces;

import java.util.List;

import Position.Location;
import Squares.Square;
import Board.Board;

public interface Movable {

    List<Location> getValidMoves(Board board);
    List<Location> getValidMoves(Board board, List<Location> targetSquares);
    void makeMove(Square square);

}
