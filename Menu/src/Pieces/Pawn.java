package Pieces;

import javafx.scene.image.Image;
import squares.Square;

import java.util.ArrayList;
import java.util.List;

import Board.Board;
import Position.Location;
import Position.LocationMaker;

public class Pawn extends Piece{
	
	private boolean isFirstMove;  
	
	
	//This is the constructor of the class
	public Pawn(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "pawn";
		
		this.isFirstMove = true;
		
		String location = "images/";
        String filename = this.getPieceColor() + "_" + this.getName() + ".png";
        this.image = new Image(location + filename);
        
	}
	
	
	@Override
	public void makeMove(Square square) {
		if(this.getValidMoves(Board.getInstance()).contains(square.getLocation())) {
			if(isFirstMove)
				this.isFirstMove = false;			
			this.getCurrSquare().releasePiece();
			this.setCurrSquare(square);
			square.SetCurrPiece(this);
		}
	}
	
	//Obtaining the moves possible to be used
	@Override
	public List<Location> getValidMoves(Board board) {
	    PieceColor Color;
		  
		//List that will contain the possible results
		List<Location> moveCandidates = new ArrayList<>();

		//Obtain the current location of the piece
	    Location current = this.getCurrSquare().getLocation();
	    int CurrRow = this.getRow();
	    int CurrColumn = this.getColumn();
	    
	    //To find if the move is up or down (Black pawns go down and White pawns go up)
	    int sign = (pieceColor.equals(PieceColor.BLACK)) ? 1 : -1;

	    
	    //Case where it is the first move of the user
	    if(this.isFirstMove()) 
	    	if(Board.squareIsOccupied(CurrRow+ (2*sign),CurrColumn) == null) {
	    	
	    		moveCandidates.add(LocationMaker.build(current, 0, 2*sign));
	    	}
	    //else of isFirstMove	
	    if(Board.squareIsOccupied(CurrRow + sign,CurrColumn) == null) {
    		moveCandidates.add(LocationMaker.build(current, 0, sign));
	    }
	    
		
	    //For diagonal move (case where there is a kill)
    	if(CurrColumn != 7 && (Color = Board.squareIsOccupied(CurrRow+sign,CurrColumn+1)) != null && Color != this.getPieceColor())
    		moveCandidates.add(LocationMaker.build(current, 1, sign));
    	
    	//For Diagonal move (case where there is a kill)
    	if(CurrColumn != 0 &&(Color = Board.squareIsOccupied(CurrRow+sign,CurrColumn-1)) != null && Color != this.getPieceColor())
    		moveCandidates.add(LocationMaker.build(current, -1, sign));
    	return moveCandidates;
	  }
	
	
	//Getters and Setters
	
	
	public boolean isFirstMove() {
		return this.isFirstMove;
	}

	@Override
	public Image getImage() {
		return this.image;
	}

}
