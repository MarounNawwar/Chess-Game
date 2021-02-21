package Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Pieces.Piece;
import Pieces.PieceColor;
import Pieces.PieceFactory;
import Position.Location;
import javafx.scene.layout.GridPane;
import squares.Square;
import squares.SquareColor;

public class Board extends GridPane{

	 private static final Integer BOARD_LENGTH = 8;
	 private final Map<Location, Square> locationSquareMap;
	 
	 //This is the array representing the square in the board
	 Square[][] boardSquares = new Square[BOARD_LENGTH][BOARD_LENGTH];
	
	 private final List<Piece> whitePieces = new ArrayList<>();
	 private final List<Piece> blackPieces = new ArrayList<>();

	 private static Board Instance = null;
	 
	 //This is the constructor
	 //Creating the board and adding the pieces in the right place 
	 private Board() {
		 
		 //call for Constructor of the class Gridpane
		 //This constructor creates a listener for all the children to be added to the Gridpane and requests the layout
		 super();
		 
		 locationSquareMap = new HashMap<>();
		 Map<Location, Piece> pieces = PieceFactory.getPieces();
		 
		 for(int i = 0; i < BOARD_LENGTH; i++) {
		     
		      for(int j = 0; j < BOARD_LENGTH; j++) {
		    	
		    	//Creating new Square for the current location
		    	SquareColor currentColor = ( (i + j) % 2 == 0 )? SquareColor.WHITE : SquareColor.BLACK; 
		        Square newSquare = new Square(currentColor, new Location(i,j));

		        //Add the object in the gridpane
		        this.add(newSquare, j, i);
		        
		        //Case where the new Sqaure contains a piece
		        if (pieces.containsKey(newSquare.getLocation())) {
		        	
		        	Piece CurrentPiece = pieces.get(newSquare.getLocation());
		        	
		        	//Make the needed changes in the Square Object
		        	newSquare.SetCurrPiece(CurrentPiece);
		        	newSquare.setOcuppied(true);
		       
		        	//Make the needed changes in the Piece object
		        	CurrentPiece.setCurrSquare(newSquare);
		        
		        	//Adding the piece to the appropriate List (List of white/black pieces)
		        	if (CurrentPiece.getPieceColor().equals(PieceColor.BLACK)) {
		        		
		        		blackPieces.add(CurrentPiece);
		        	
		        	}else {
		        	
		        		whitePieces.add(CurrentPiece);
		        	
		        	}
		        	
		        }
		        
		        //Get variables to be used in the event handler
		        final int row = i;
		        final int column = j;
		        
		        boardSquares[i][j] = newSquare;
		        boardSquares[i][j].setOnAction( e -> onSquareClick(row, column) );
		        locationSquareMap.put(newSquare.getLocation(), newSquare);
		      
		      }
		 }
	 }
	 

	 //Event handler when a square is clicked 
	 private void onSquareClick(int row, int column) {
		 
		 Square clickedSquare = boardSquares[row][column];
		 
	}

		 
	 //Getters and Setters

	public Map<Location, Square> getLocationSquareMap() {
		    return locationSquareMap;
		  }

	 public List<Piece> getWhitePieces() {
		    return whitePieces;
		  }

	public List<Piece> getBlackPieces() {
		    return blackPieces;
	}

	public static Board getInstance() {
		if(Instance == null) {
			Instance = new Board();
		}
		return Instance;
	}

	
}
