package Pieces;

import java.util.HashMap;
import java.util.Map;

import Position.Location;

public final class PieceFactory {

	//This class is a singleton
	
	//This is the constructor 
	private PieceFactory() {}
	
	public static Map<Location, Piece> getPieces(){
		
		Map<Location, Piece> pieces = new HashMap<>();
		
		// rooks ( Tour )
	    pieces.put(new Location(1, 1), new Rook(PieceColor.WHITE));
	    pieces.put(new Location(1, 8), new Rook(PieceColor.WHITE));
	    pieces.put(new Location(8, 1), new Rook(PieceColor.BLACK));
	    pieces.put(new Location(8, 8), new Rook(PieceColor.BLACK));
		
	    // Knights ( Cheval )
	    pieces.put(new Location(1, 2), new Knight(PieceColor.WHITE));
	    pieces.put(new Location(1, 7), new Knight(PieceColor.WHITE));
	    pieces.put(new Location(8, 2), new Knight(PieceColor.BLACK));
	    pieces.put(new Location(8, 7), new Knight(PieceColor.BLACK));
		
	    // Bishops ( Foux )
	    pieces.put(new Location(1, 3), new Bishop(PieceColor.WHITE));
	    pieces.put(new Location(1, 6), new Bishop(PieceColor.WHITE));
	    pieces.put(new Location(8, 3), new Bishop(PieceColor.BLACK));
	    pieces.put(new Location(8, 6), new Bishop(PieceColor.BLACK));
	    
	    // Queen
	    pieces.put(new Location(1, 4), new Queen(PieceColor.WHITE));
	    pieces.put(new Location(8, 4), new Queen(PieceColor.BLACK));
	    
	    // King
	    pieces.put(new Location(1, 5), new King(PieceColor.WHITE));
	    pieces.put(new Location(8, 5), new King(PieceColor.BLACK));
	    
	    for(int i =1; i <= 8; i++) {
	        pieces.put(new Location(2, i), new Pawn(PieceColor.WHITE));
	        pieces.put(new Location(7, i), new Pawn(PieceColor.BLACK));
	      }
	    
		return pieces;
	}
	
	
	
}
