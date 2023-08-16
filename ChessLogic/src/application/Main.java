package application;

import Board.Board;

public class Main {

    private static Board board;

    public static void main(String[] args){
        try{

            board = Board.getInstance();

            board.getPlayerTurn();

        }catch (Exception e){
            e.getMessage();
        }
    }

}
