package Pieces;

import Board.Board;
import Position.Location;
import Squares.Square;

import java.util.List;
import java.util.Map;

public interface StraightMovement {

    public List<Location> getVerticalMoves(Map<Location,Square> squareMap, Location location);
    public List<Location> getHorizontalMoves(Map<Location, Square> squareMap, Location location);

}