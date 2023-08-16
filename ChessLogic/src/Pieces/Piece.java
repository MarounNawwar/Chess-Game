package Pieces;

import java.util.ArrayList;
import java.util.List;

import Board.Board;
import Position.Location;
import Squares.Square;

public abstract class Piece implements Movable {

    protected String name;
    protected PieceColor pieceColor;
    protected Square currentSquare;

    // Constructor

    public Piece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    // Functions

    @Override
    public void makeMove(Square destinationSquare) {
        if(this.getValidMoves(Board.getInstance()).contains(destinationSquare.getLocation())) {
            this.getCurrSquare().releasePiece();
            this.setCurrSquare(destinationSquare);
            destinationSquare.SetCurrPiece(this);
        }
    }

    // Return the moves that are available in the targetSquares list and that are valid
    @Override
    public List<Location> getValidMoves(Board board, List<Location> targetSquares) {
        List<Location> validMoves = new ArrayList<>();
        List<Location> possibleMoves = this.getValidMoves(board);
        for(Location loc : possibleMoves)
            if(targetSquares.contains(loc))
                validMoves.add(loc);
        return validMoves;
    }

    // Getters and Setters

    public String getName() {
        return this.name;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    public Square getCurrSquare() {
        return this.currentSquare;
    }

    public void setCurrSquare(Square currSquare) {
        this.currentSquare = currSquare;
    }

    public int getRow() {
        return this.getCurrSquare().getRow();
    }

    public int getColumn() {
        return this.getCurrSquare().getColumn();
    }

}