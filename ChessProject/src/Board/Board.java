package Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Pieces.King;
import Pieces.Piece;
import Pieces.PieceColor;
import Pieces.PieceFactory;
import Position.Location;
import Position.LocationFactory;
import javafx.scene.layout.GridPane;
import squares.Square;
import squares.SquareColor;

public class Board extends GridPane{

	 private static final Integer BOARD_LENGTH = 8;
	 private final Map<Location, Square> locationSquareMap;
	 //This will be the square that will represent the square currently clicked On
	 public Square ActiveSquare = null;
	 //By default, the rule is that the white starts the game
	 private PieceColor playerTurn = PieceColor.WHITE;
	 //This is the array representing the square in the board
	 private static Square[][] boardSquares = new Square[BOARD_LENGTH][BOARD_LENGTH];
	 private final List<Piece> whitePieces = new ArrayList<>();
	 private final List<Piece> blackPieces = new ArrayList<>();
	 private static List<Location> pathOfCheck = new ArrayList<>();
	 private static Board Instance = null;	 
	 public boolean checked = false;
	 
	 private static King whiteKing;
	 private static King blackKing;
	 
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
		        	
		        	if(CurrentPiece.getName().equals("king")) {
		        		if(CurrentPiece.getPieceColor().equals(PieceColor.WHITE)) {
		        			Board.whiteKing = (King)CurrentPiece; 
		        		}else {
		        			Board.blackKing = (King)CurrentPiece;
		        		}
		        	}
		        	
		        	
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
		
		//Case where the Player clicks on one of his Pieces
		if(clickedSquare.isOccupied() && clickedSquare.getCurrPiece() != null && clickedSquare.getPieceColor().equals(this.playerTurn)) {
			
				makeNewActiveSquare(clickedSquare);
			
		}else if(ActiveSquare != null && ActiveSquare.isOccupied()) {  
			
			if(!this.checked && ActiveSquare.getCurrPiece().getValidMoves(this).contains(clickedSquare.getLocation())) {
				
				makeMove(clickedSquare);
				
		}else if(this.checked && ActiveSquare.getCurrPiece().getValidMoves(this,pathOfCheck).contains(clickedSquare.getLocation())){
			
				makeMove(clickedSquare);
			
		}else {
				ActiveSquare.getStyleClass().remove("chess-square-active");
				RemoveDisplayedValidMoves(ActiveSquare);
				setActiveSquare(null);
			}
		}
		
	}
	 
	 
	 private void goNextTurn() {
		ActiveSquare = null;
		this.playerTurn = (this.playerTurn.equals(PieceColor.BLACK))? PieceColor.WHITE : PieceColor.BLACK;
		//TODO:Implment timer
	}

	 
	 //Display all the currently valid moves for a given Piece
	 private void DisplayValidMoves(Square activeSquare) {
		 Square CurrSquare;
		 
		//Case where the activeSquare has either a piece from the opponent or has no pieces on it
		if(activeSquare.getPieceColor()!= this.playerTurn) return;
		
		List<Location> validMoves;
		
		//List containing all the possible moves
		if(this.checked) {
			validMoves = activeSquare.getCurrPiece().getValidMoves(this, pathOfCheck);
		}else {
			validMoves = activeSquare.getCurrPiece().getValidMoves(this);
		}
		
		for(int i = 0; i < validMoves.size(); i++) {
			//Check if it is a valid location on the board 
			if((CurrSquare = locationSquareMap.get(validMoves.get(i))) != null) {	
				CurrSquare.getStyleClass().add("chess-square-valid");
			}
		}
	}

	 
	 //Remove Display from all currently displayed moves
	 private void RemoveDisplayedValidMoves(Square activeSquare) {
		 Square CurrSquare;
		 
			//Case where the activeSquare has either a piece from the opponent or has no pieces on it
			if(activeSquare.getPieceColor()!= this.playerTurn) return;
			
			List<Location> validMoves;
			
			//List containing all the possible moves
			if(this.checked) {
				validMoves = activeSquare.getCurrPiece().getValidMoves(this,pathOfCheck);
			}else {
				validMoves = activeSquare.getCurrPiece().getValidMoves(this);
			}
			
			//Check if it is a valid location on the board then check if it is currently displayed
			//Remove the display if it is currently displayed
			for(int i = 0; i < validMoves.size(); i++)
				if((CurrSquare = locationSquareMap.get(validMoves.get(i))) != null && CurrSquare.getStyleClass().contains("chess-square-valid"))
					CurrSquare.getStyleClass().remove("chess-square-valid");
	 }

	 
	 //Function that returns the Color of a Square's piece if available 
	 //Returns null if the square is not occupied
	 public static PieceColor squareIsOccupied(int row,int column) {
		 if(row < 8 && row >=0 && column < 8 && column >= 0 && boardSquares[row][column].isOccupied())
			 return boardSquares[row][column].getPieceColor();
		 return null;
	 }
		 
	 
	 //Function that allows to make the changes to do make the parameter the new Active Square
	 private void makeNewActiveSquare(Square clickedSquare) {
		 
		 if(ActiveSquare != null) {
				ActiveSquare.getStyleClass().remove("chess-square-active");
				RemoveDisplayedValidMoves(ActiveSquare);
				if(ActiveSquare == clickedSquare) {ActiveSquare = null; return;}
			} 
			
			ActiveSquare = clickedSquare;
			clickedSquare.getStyleClass().add("chess-square-active");
			DisplayValidMoves(ActiveSquare);
		 
	 }
	 
	 
	 //Invoke the makeMove method in pieces and remove current displays then go next turn
	 private void makeMove(Square clickedSquare) {
		 PieceColor kingColor;
		 		 
		 ActiveSquare.getStyleClass().remove("chess-square-active");
		 RemoveDisplayedValidMoves(ActiveSquare);
		 ActiveSquare.getCurrPiece().makeMove(clickedSquare);
		 goNextTurn();
		 
		 if((kingColor = kingIsChecked()) != null) {
			 checked = true;
			 NotifyCheck(kingColor);
		 }else {
			 if(checked) {
				 checked = false;
				 whiteKing.getCurrSquare().getStyleClass().remove("chess-king-checked");
				 blackKing.getCurrSquare().getStyleClass().remove("chess-king-checked");
			 }
			 
		 }
		 
	 }

	 
	 private PieceColor kingIsChecked() {
		 Square sourceSquare; 
		 
		 //Case where the white king is checked
		 if((sourceSquare = whiteKing.isChecked(this))!=null) {
			 resetPathOfCheck();
			 findCheckPath(sourceSquare,whiteKing.getCurrSquare());
			 if(isCheckMate(whiteKing)) {
				 System.out.println("Black Win");
			 }
			 return PieceColor.WHITE;
		 }
		 
		 //Case where the black king is checked
		 if((sourceSquare = blackKing.isChecked(this))!=null) {
			 resetPathOfCheck();
			 findCheckPath(sourceSquare,blackKing.getCurrSquare());
			 if(isCheckMate(blackKing)) {
				 System.out.println("White win");
			 }
			 return PieceColor.BLACK;
		 }
		 return null;
	 }
	 
	 
	 private void NotifyCheck(PieceColor kingColor) {
		 
		 if(kingColor.equals(PieceColor.WHITE)) {
			 
			 whiteKing.getCurrSquare().getStyleClass().add("chess-king-checked");
			 
		 }else {
			 
			 blackKing.getCurrSquare().getStyleClass().add("chess-king-checked");
			 
		 }
		 
	 }
	 
	 
	 public List<Location> findCheckPath(Square source,Square destination) {
		 
		 int startRow = source.getRow();
		 int startCol = source.getColumn();
		 int destRow = destination.getRow();
		 int destCol = destination.getColumn();
		 
		 int RowOffSet;
		 int ColOffSet;
		 
		 
		 //Determining the RowOffSet
		 if(startRow==destRow) {
			 RowOffSet = 0;
		 }else if(startRow > destRow) {
			 RowOffSet = -1;
		 }else {
			 RowOffSet = 1;
		 }
		 
		 //Determing the ColOffSet
		 if(startCol == destCol) {
			 ColOffSet = 0;
		 }else if (startCol > destCol) {
			 ColOffSet = -1;
		 }else {
			 ColOffSet = 1;
		 }
		 
		 int row = startRow;
		 int col = startCol;
		 while(row != destRow && col != destCol) {
			Location tmp = new Location(row,col);
			 
			if(locationSquareMap.containsKey(tmp)) {
				 pathOfCheck.add(tmp);
			 }
			
			 row += RowOffSet;
			 col += ColOffSet;
		 }
		 
		 
		 return pathOfCheck;
	 }
	 
	 
	 public void resetPathOfCheck() {
		 pathOfCheck.clear();
	 }
	 
	 private boolean isCheckMate(King king) {
		 
		 //Check if the king is checked or not
		 if( king.isChecked(this) == null) return false;
	 
		 //Check if the Kind is able to move or not
		 if(king.isAbleToMove(this)) return false;
		 
		 
		 
		//Retrieve the Map containing all the current Positions
		Map<Location,Square> squareMap = this.getLocationSquareMap();
					
		//Retrieving the keys of the Map
		Set<Location> keys = squareMap.keySet();
					
		//Creating the Iterator for the Map
		Iterator<Location> iter = keys.iterator();
					
					
		while(iter.hasNext()) {
				
			//Retrieving a Square from the Map
			Square CurrSquare = squareMap.get(iter.next());
			
			//Square is occupied by a friendly piece that is not the king
			if(CurrSquare.isOccupied() 
				&& CurrSquare.getCurrPiece() != null 
				&& CurrSquare.getPieceColor().equals(king.getPieceColor())
				&& !CurrSquare.getCurrPiece().getName().equals("king")) {
				
				//Check if the Friendly does have a Valid Move
				if(!CurrSquare.getCurrPiece().getValidMoves(this, pathOfCheck).isEmpty())
					return false;
			}
					
		}
		 return true;
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

	public PieceColor getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(PieceColor playerTurn) {
		this.playerTurn = playerTurn;
	}
	
	public Square getActiveSquare() {
		return ActiveSquare;
	}

	public void setActiveSquare(Square activeSquare) {
		ActiveSquare = activeSquare;
	}

	public static Square[][] getBoardSquares() {
		return boardSquares;
	}

	public static void setBoardSquares(Square[][] boardSquares) {
		Board.boardSquares = boardSquares;
	}
	
	public King getWhiteKing() {
		return whiteKing;
	}

	public static void setWhiteKing(King whiteKing) {
		Board.whiteKing = whiteKing;
	}

	public King getBlackKing() {
		return blackKing;
	}

	public static void setBlackKing(King blackKing) {
		Board.blackKing = blackKing;
	}
	
	public static List<Location> getPathOfCheck() {
		return pathOfCheck;
	}

	public static void setPathOfCheck(List<Location> pathOfCheck) {
		Board.pathOfCheck = pathOfCheck;
	}

	public static Board getInstance() {
		if(Instance == null) {
			Instance = new Board();
		}
		return Instance;
	}
}
