package Squares;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Board.Board;
import Pieces.Piece;
import Pieces.PieceColor;
import Position.Location;

public class Square {

    private Piece currPiece;
    private boolean isOccupied;
    private final Location location;
    private final SquareColor squarecolor;

    public Square(SquareColor squarecolor, Location location) {
        this.squarecolor = squarecolor;
        this.location = location;
        this.isOccupied = false;
        this.currPiece = null;
    }

    public void releasePiece() {
        this.isOccupied = false;
        SetCurrPiece(null);
    }

    public boolean isAnOpponentMove(Board board,PieceColor playerColor) {
        Map<Location,Square> squareMap = board.getLocationSquareMap();
        Set<Location> keys = squareMap.keySet();
        Iterator<Location> iteration = keys.iterator();

        while(iteration.hasNext()) {
            Square CurrSquare = squareMap.get(iteration.next());
            if(CurrSquare.isOccupied() && CurrSquare.getPieceColor() != null
                    && CurrSquare.getPieceColor() != playerColor
                    && !CurrSquare.getCurrPiece().getName().equals("king")) {
                List<Location> validMoves = CurrSquare.getCurrPiece().getValidMoves(board);
                if(validMoves.contains(this.getLocation()))
                    return true;
            }
        }
        return false;
    }

    //Getters and Setters

    public boolean isOccupied() {
        return this.isOccupied && this.getPieceColor() != null;
    }

    public void setOccupied(boolean status) {
        this.isOccupied = status;
    }

    public Piece getCurrPiece() {
        return this.currPiece;
    }

    public SquareColor getSquareColor() {
        return this.squarecolor;
    }

    public Location getLocation() {
        return location;
    }

    public void SetCurrPiece(Piece piece) {
        this.currPiece = piece;
        setOccupied(true);
    }

    //Returns the Row of the Square's Location
    public int getRow() {
        return this.getLocation().getRow();
    }

    //Return the Column of the Square's Location
    public int getColumn() {
        return this.getLocation().getColumn();
    }

    //Return an empty String if no Pieces Available on the Square
    //Return the color of the piece if the square is Occupied
    public PieceColor getPieceColor() {
        if(getCurrPiece()!=null)
            return getCurrPiece().getPieceColor();
        return null;
    }

}