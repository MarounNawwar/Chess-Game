package Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Pieces.Piece;
import Pieces.PieceColor;
import Pieces.PieceFactory;
import Position.Location;
import squares.Square;
import squares.SquareColor;

public class Board {

	 private static final Integer BOARD_LENGTH = 8;
	 private final Map<Location, Square> locationSquareMap;
	 
	 //This is the array representing the square in the board
	 Square[][] boardSquares = new Square[BOARD_LENGTH][BOARD_LENGTH];
	
	 private final List<Piece> whitePieces = new ArrayList<>();
	 private final List<Piece> blackPieces = new ArrayList<>();

	 //This is the constructor
	 //Creating the board and adding the pieces in the right place 
	 public Board() {

		 locationSquareMap = new HashMap<>();
		 Map<Location, Piece> pieces = PieceFactory.getPieces();
		 
		 for(int i = 1; i <= BOARD_LENGTH; i++) {
		     
			  int column = 1;
		      SquareColor currentColor = (i % 2 != 0) ? SquareColor.WHITE : SquareColor.BLACK;

		      for(int j = 1; j <= BOARD_LENGTH; j++) {

		        Square newSquare = new Square(currentColor, new Location(i,j));

		        if (pieces.containsKey(newSquare.getLocation())) {
		        	
		        	Piece CurrentPiece = pieces.get(newSquare.getLocation());
		        	
		        	newSquare.SetCurrPiece(CurrentPiece);
		        	newSquare.setOcuppied(true);
		        	
		        	CurrentPiece.setCurrSquare(newSquare);
		        
		        	if (CurrentPiece.getPieceColor().equals(PieceColor.BLACK)) {
		        		
		        		blackPieces.add(CurrentPiece);
		        	
		        	}else {
		            
		        		whitePieces.add(CurrentPiece);
		        	
		        	}
		        }
		        
		        boardSquares[i][column] = newSquare;
		        locationSquareMap.put(newSquare.getLocation(), newSquare);
		        currentColor = (currentColor == SquareColor.BLACK) ? SquareColor.WHITE : SquareColor.BLACK;
		        column++;
		        
		      }
		 }
	 }
	 
	 //Getters and Setters
	 
	 public Map<Location, Square> getLocationSquareMap() {
		    return locationSquareMap;
		  }

	 public List<Piece> getLightPieces() {
		    return whitePieces;
		  }

	public List<Piece> getDarkPieces() {
		    return whitePieces;
	}

	
	
}
