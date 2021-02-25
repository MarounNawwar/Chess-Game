package Pieces;

import java.util.HashMap;
import java.util.Map;

import Position.Location;

public final class PieceMap {

	//This class is a singleton
	
	//This is the constructor 
	private PieceMap() {}
	
	public static Map<Location, Piece> getPieces(){
		
		Map<Location, Piece> pieces = new HashMap<>();
		
		// rooks ( Tour )
	    pieces.put(new Location(0, 0), new Rook(PieceColor.BLACK));
	    pieces.put(new Location(0, 7), new Rook(PieceColor.BLACK));
	    pieces.put(new Location(7, 0), new Rook(PieceColor.WHITE));
	    pieces.put(new Location(7, 7), new Rook(PieceColor.WHITE));
		
	    // Knights ( Cheval )
	    pieces.put(new Location(0, 1), new Knight(PieceColor.BLACK));
	    pieces.put(new Location(0, 6), new Knight(PieceColor.BLACK));
	    pieces.put(new Location(7, 1), new Knight(PieceColor.WHITE));
	    pieces.put(new Location(7, 6), new Knight(PieceColor.WHITE));
		
	    // Bishops ( Foux )
	    pieces.put(new Location(0, 2), new Bishop(PieceColor.BLACK));
	    pieces.put(new Location(0, 5), new Bishop(PieceColor.BLACK));
	    pieces.put(new Location(7, 2), new Bishop(PieceColor.WHITE));
	    pieces.put(new Location(7, 5), new Bishop(PieceColor.WHITE));
	    
	    // Queen
	    pieces.put(new Location(0, 3), new Queen(PieceColor.BLACK));
	    pieces.put(new Location(7, 3), new Queen(PieceColor.WHITE));
	    
	    // King
	    pieces.put(new Location(0, 4), new King(PieceColor.BLACK));
	    pieces.put(new Location(7, 4), new King(PieceColor.WHITE));
	    
	    for(int i = 0; i < 8; i++) {
	        pieces.put(new Location(1, i), new Pawn(PieceColor.BLACK));
	        pieces.put(new Location(6, i), new Pawn(PieceColor.WHITE));
	      }
	    
		return pieces;
	}
	
	
	
}
