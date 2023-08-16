package Pieces;

import Board.Board;
import Position.Location;
import Squares.Square;

import java.util.List;
import java.util.Map;

public interface DiagonalMovement {

    List<Location> getDiagonalMoves(Map<Location, Square> squareMap, Location currLocation);

}