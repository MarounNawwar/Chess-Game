package Pieces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Board.Board;
import Position.Location;
import Position.LocationFactory;
import javafx.scene.image.Image;
import squares.Square;

public class King extends Piece {

	public King(PieceColor pieceColor) {
		super(pieceColor);
		this.name= "king";
		
		String location = "assets/";
        String filename = this.getPieceColor() + "_" + this.getName() + ".png";
        this.image = new Image(location + filename);
	}

	
	//Returns True The King is Currently Checked by an opponent's Piece(s)
	public Square isChecked(Board board) {
		
		//Retrieve the Map containing all the current Positions
		Map<Location,Square> squareMap = board.getLocationSquareMap();
		
		//Retrieving the keys of the Map
		Set<Location> keys = squareMap.keySet();
		
		//Creating the Iterator for the Map
		Iterator<Location> iter = keys.iterator();
		
		
		while(iter.hasNext()) {
			//Retrieving a Square from the Map
			Square CurrSquare = squareMap.get(iter.next());
			
			//Checking if the Square is occupied by a piece controlled by the opponent
			if(CurrSquare.isOccupied() && CurrSquare.getPieceColor() != null && CurrSquare.getPieceColor() != this.getPieceColor()) {
				
				//Retrieve all the valid Moves of the Opponent's piece
				List<Location> validMoves = CurrSquare.getCurrPiece().getValidMoves(board);
				
				//Check if the King's location is one of the valid Moves
				if(validMoves.contains(this.getCurrSquare().getLocation()))
					return CurrSquare;
			}
		}
		
		return null;
	}
	
	public boolean isAbleToMove(Board board) {
		if(getValidMoves(board).isEmpty())
			return false;
		return true;
	}
	
	@Override
	public void makeMove(Square destinationSquare) {
		if(this.getValidMoves(Board.getInstance()).contains(destinationSquare.getLocation())) {
			
			if(this.isChecked(Board.getInstance()) != null)
				this.getCurrSquare().getStyleClass().remove("chess-king-checked");
			
			this.getCurrSquare().releasePiece();
			this.setCurrSquare(destinationSquare);
			destinationSquare.SetCurrPiece(this);
			
			if(this.getPieceColor() == PieceColor.BLACK) {
				Board.setBlackKing(this);
			}else {
				Board.setWhiteKing(this);
			}
		}
	}
	
	@Override
	public List<Location> getValidMoves(Board board) {
		PieceColor Color;
		
		List<Location> moveCandidates = new ArrayList<>();
		Location current = this.getCurrSquare().getLocation();
		int CurrRow = this.getRow();
		int CurrColumn = this.getColumn();
		
		//TODO: Add condition that King is not in danger
		
		for(int i = -1;i <= 1; i++) {
			for(int j= -1;j <= 1; j++) {
				if(i==0 && j==0) {
					//Case to be avoided by the loop
					continue;
				}else if((Color = Board.squareIsOccupied(CurrRow+i, CurrColumn+j)) != null && Color == this.getPieceColor()) {
					continue;
				}else if(CurrRow + i <8 && CurrColumn + j < 8){
					//Case where the square is either empty or occupied by a piece of the opponent
					Location NewLocation = LocationFactory.build(current, j, i);
					
					if(board.getLocationSquareMap().containsKey(NewLocation) &&
							!board.getLocationSquareMap().get(NewLocation).isAnOpponentMove(board,this.getPieceColor()))
						moveCandidates.add(NewLocation);
				}
			}
		}
		return moveCandidates;
	}
	
	@Override
	public List<Location> getValidMoves(Board board, List<Location> targetSquares) {

		return getValidMoves(board);
	}
	
	//Getters and Setters

	@Override
	public Image getImage() {
		return this.image;
	}

}
