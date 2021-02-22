package squares;

import Pieces.Piece;
import Pieces.PieceColor;
import Position.Location;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class Square extends Button{

	private final SquareColor squarecolor;
	private final Location location;
	private boolean isOccupied;
	private Piece currPiece;
	
	
	//Constructor for the class
	//The square at creation is not Occupied by any Piece
	//Adding the Style Class of the given color
	public Square(SquareColor squarecolor, Location location) {
		
		super();
		
		this.squarecolor = squarecolor;
		this.location = location;
		this.isOccupied = false;
		
		this.getStyleClass().add("chess-space");
		
		if(this.squarecolor == SquareColor.BLACK) {
		
			this.getStyleClass().add("chess-square-black");
		
		}else {
			
			this.getStyleClass().add("chess-square-white");
			
		}
	}

	
	//Will be used to make the square no longer Occupied when piece move from it
	//The function returns the Piece that has moved
	public Piece releasePiece() {
		this.isOccupied = false;
		Piece tmp = this.currPiece;
		SetCurrPiece(null);
		return tmp;
	}
	
	//Used to set a Piece on the square and display it
	public void SetCurrPiece(Piece piece) {
		
		this.currPiece = piece;
		setOcuppied(true);
		
		if(this.currPiece != null) {
			
			this.setGraphic(new ImageView(piece.getImage()));
		
		}else {
		
			this.setGraphic(new ImageView());
		
		}
	}

	//Return an empty String if no Pieces Available on the Square
	//Return the color of the piece if the square is Occupied
	public PieceColor getPieceColor() {
		if(getCurrPiece()!=null)
			return getCurrPiece().getPieceColor();
		return null;
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
