package squares;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Board.Board;
import Pieces.Piece;
import Pieces.PieceColor;
import Position.Location;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class Square extends Button{
	//SquareColor:BLACK-WHITE
	private final SquareColor squarecolor;
	
	//Location: row,column
	private final Location location;
	//to see if a square is occupied
	private boolean isOccupied;
	
	//Piece that occupy the square
	private Piece currPiece;
	
	
	//Constructor for the class
	//The square at creation is not Occupied by any Piece
	//Adding the Style Class of the given color
	public Square(SquareColor squarecolor, Location location) {
		
		super();
		
		this.squarecolor = squarecolor;
		this.location = location;
		this.isOccupied = false;
		this.currPiece = null;
		
		//size of each cell
		this.getStyleClass().add("chess-space");
		
		if(this.squarecolor == SquareColor.BLACK) {
			//color of the cell
			this.getStyleClass().add("chess-square-black");
		
		}else {
			//color of the cell
			this.getStyleClass().add("chess-square-white");
			
		}
	}

	
	//Will be used to make the square no longer Occupied when piece move from it
	//The function returns the Piece that has moved
	//Used after MakeMove()
	public void releasePiece() {
		this.isOccupied = false;
		SetCurrPiece(null);
	}
	
	//Used to set a Piece on the square and display it
	public void SetCurrPiece(Piece piece) {
		
		this.currPiece = piece;
		setOcuppied(true);
		
		if(this.currPiece != null) {
			
			this.setGraphic(new ImageView(piece.getImage()));
		
		}else {
		
			this.setGraphic(null);
		
		}
	}

	//Return an empty String if no Pieces Available on the Square
	//Return the color of the piece if the square is Occupied
	public PieceColor getPieceColor() {
		if(getCurrPiece()!=null)
			return getCurrPiece().getPieceColor();
		return null;
	}
	
	
	public boolean isAnOpponentMove(Board board,PieceColor playerColor) {
		
		//Retrieve the Map containing all the current Positions for each square
		Map<Location,Square> squareMap = board.getLocationSquareMap();
				
		//Retrieving the keys of the Map
		Set<Location> keys = squareMap.keySet();
				
		//Creating the Iterator for the Map
		Iterator<Location> iter = keys.iterator();
				
				
		while(iter.hasNext()) {
			
			//Retrieving a Square from the Map
			Square CurrSquare = squareMap.get(iter.next());
				
			
			//Checking if the Square is occupied by a piece controlled by the opponent
			if(CurrSquare.isOccupied() 
					&& CurrSquare.getPieceColor() != null 
					&& CurrSquare.getPieceColor() != playerColor 
					&& !CurrSquare.getCurrPiece().getName().equals("king")) {
						
				//Retrieve all the valid Moves of the Opponent's piece
				List<Location> validMoves = CurrSquare.getCurrPiece().getValidMoves(board);
						
				//Check if the King's location is one of the valid Moves
				if(validMoves.contains(this.getLocation()))
					return true;
				}
		}
		return false;
	}
	
	
	//Returns the Row of the Square's Location
	public int getRow() {
		return this.getLocation().getRow();
	}
	
	//Return the Column of the Square's Location
	public int getColumn() {
		return this.getLocation().getColumn();
	}
	
	
	//Getters and Setters
	
	
	public boolean isOccupied() {
		return this.isOccupied;
	}
	
	public void setOcuppied(boolean status) {
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

}
