package application;
	
import Board.Board;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Board board;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			primaryStage.setTitle("Chess"); //title of the window
			
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			
			scene.getStylesheets().add("application/application.css");
			
			//Get the Instance from the Singleton
			board = Board.getInstance();
			root.setCenter(board);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		launch(args);
	}
	
	
}