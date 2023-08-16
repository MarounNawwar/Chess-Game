package Pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Board.Board;
import Position.Location;
import Position.LocationFactory;
import Squares.Square;

public class Rook extends Piece implements StraightMovement{

    //This is the constructor of the Class
    public Rook(PieceColor pieceColor) {
        super(pieceColor);
        this.name = "rook";
    }


    @Override
    public List<Location> getValidMoves(Board board) {
        List<Location> moveCandidates = new ArrayList<>();
        Map<Location, Square> squareMap = board.getLocationSquareMap();
        Location current = this.getCurrSquare().getLocation();
        moveCandidates.addAll(getHorizontalMoves(squareMap,current));
        moveCandidates.addAll(getVerticalMoves(squareMap,current));
        return moveCandidates;
    }

    private void getColumnCandidates(List<Location> moveCandidates, Map<Location, Square> squareMap, Location current,int ColumnOffSet) {
        PieceColor Color;
        try {
            Location next = LocationFactory.build(current, ColumnOffSet, 0);
            while(squareMap.containsKey(next) && next != null) {
                if((Color = Board.squareIsOccupied(next.getRow(), next.getColumn())) != null) {
                    if(Color != this.pieceColor) {
                        moveCandidates.add(next);
                    }
                    break;
                }
                moveCandidates.add(next);
                next = LocationFactory.build(next, ColumnOffSet, 0);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    private void getRowCandidates(List<Location> moveCandidates, Map<Location, Square> squareMap, Location current, int rowOffSet) {
        PieceColor Color;
        try {
            Location next = LocationFactory.build(current,0, rowOffSet);
            while(squareMap.containsKey(next) && next != null) {
                if((Color = Board.squareIsOccupied(next.getRow(), next.getColumn())) != null) {
                    if(Color == this.pieceColor) {
                        break;
                    }else {
                        moveCandidates.add(next);
                        break;
                    }
                }
                moveCandidates.add(next);
                next = LocationFactory.build(next, 0, rowOffSet);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Location> getVerticalMoves(Map<Location,Square> squareMap, Location currLocation){
        List<Location> allowedMoves = new ArrayList<>();
        getRowCandidates(allowedMoves,squareMap,currLocation,-1);
        getRowCandidates(allowedMoves,squareMap,currLocation,1);
        return allowedMoves;
    }

    @Override
    public List<Location> getHorizontalMoves(Map<Location,Square> squareMap, Location currLocation){
        List<Location> allowedMoves = new ArrayList<>();
        getColumnCandidates(allowedMoves,squareMap,currLocation,1);
        getColumnCandidates(allowedMoves,squareMap,currLocation,-1);
        return allowedMoves;
    }

}