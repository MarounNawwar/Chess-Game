package Pieces;

import java.util.*;

import Board.Board;
import Position.Location;
import Position.LocationFactory;
import Squares.Square;

public class Bishop extends Piece implements DiagonalMovement {

    //This is the constructor
    public Bishop(PieceColor pieceColor) {
        super(pieceColor);
        this.name = "bishop";
    }

    //Get all the valid moves

    @Override
    public List<Location> getValidMoves(Board board){
        Location currLocation = this.getCurrSquare().getLocation();
        Map<Location,Square> squareMap = board.getLocationSquareMap();
        List<Location> moveCandidates = getDiagonalMoves(squareMap, currLocation);
        return moveCandidates;
    }

    //Get all the possible moves in a given Direction
    public List<Location> getMovesInDirection( Map<Location, Square> squareMap, Location currLocation, int RowOffSet, int ColOffSet) {
        List<Location> moveCandidates = new ArrayList<>();
        try {
            Location next = LocationFactory.build(currLocation, ColOffSet, RowOffSet);
            while(squareMap.containsKey(next) &&  next != null) {
                if(squareMap.get(next).isOccupied()) {
                    if(!squareMap.get(next).getPieceColor().equals(this.pieceColor)){
                        moveCandidates.add(next);
                    }
                    break;
                }
                moveCandidates.add(next);
                next = LocationFactory.build(next, ColOffSet, RowOffSet);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return moveCandidates;
    }

    @Override
    public List<Location> getDiagonalMoves(Map<Location,Square> squareMap, Location currLocation){

        List<Location> candidates = new ArrayList<>();

        //Retrieve the Moves in the allowed Direction of the Bishop
        candidates.addAll(getMovesInDirection(squareMap, currLocation,1,1));
        candidates.addAll(getMovesInDirection(squareMap, currLocation,1,-1));
        candidates.addAll(getMovesInDirection(squareMap, currLocation,-1,1));
        candidates.addAll(getMovesInDirection(squareMap, currLocation,-1,-1));

        return candidates;
    }

}