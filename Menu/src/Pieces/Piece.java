package Pieces;

import java.util.ArrayList;
import java.util.List;

import Board.Board;
import Position.Location;
import javafx.scene.image.*;
import squares.Square;

public abstract class Piece implements Movable{
	
	protected String name;
	protected PieceColor pieceColor;
	protected Square currentSquare;
	protected Image image;
	
	//Constructor 
	public Piece(PieceColor pieceColor) {
		this.pieceColor = pieceColor;
	}
	
	@Override
	public void makeMove(Square destinationSquare) {
		if(this.getValidMoves(Board.getInstance()).contains(destinationSquare.getLocation())) {
			this.getCurrSquare().releasePiece();
			this.setCurrSquare(destinationSquare);
			destinationSquare.SetCurrPiece(this);
		}
	}

	
	//Return the moves that are available in the targetSquares list and that are valid 
	@Override
	public List<Location> getValidMoves(Board board, List<Location> targetSquares) {
		List<Location> validMoves = new ArrayList<>();
		List<Location> possibleMoves = this.getValidMoves(board);
			
		for(Location loc : possibleMoves)
			if(targetSquares.contains(loc))
				validMoves.add(loc);

		return validMoves;
	}
	
	public boolean moveResultInCheck(Board board, Square square) {
		King playerKing;
		
		//Retrieve The king of this Piece
		if(this.getPieceColor().equals(PieceColor.BLACK)) 
			playerKing = board.getBlackKing();
		playerKing = board.getWhiteKing();
		
		Piece tmp = square.getCurrPiece();
	
		square.releasePiece();
		
		//if the piece that I want to remove will result as check to my king so [get back the piece after release]+Return true
	if(playerKing.isChecked(board)!=null) {
		square.SetCurrPiece(tmp);
		square.setOcuppied(true);
		return true;
		}
	
		//if it doesn't affect my king, so [get back piece after release]+Return False
		square.SetCurrPiece(tmp);
		square.setOcuppied(true);
	return false;
		
	}

	
	
	
 	public int getRow() {
		return this.getCurrSquare().getRow();
	}
	
	public int getColumn() {
		return this.getCurrSquare().getColumn();
	}
	
	//Getters and Setters
	
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

	public abstract Image getImage();
	
}
