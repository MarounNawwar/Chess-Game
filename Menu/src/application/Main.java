package application;
	
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollPane;

import java.beans.Visibility;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.script.Bindings;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import Board.Board;
import Pieces.Movable;
import Pieces.Piece;
import Pieces.PieceColor;
import Pieces.PieceFactory;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;


public class Main extends Application{
	

	//Delay initialized to 0 for the Timer
	private Stage stage;
	private Scene boardScene;
	private Board board;

	public  Slider timeSlider;
	private Scene scene1;
	private VBox vbox1;
	private Button gameButton;
	private Button quitButton;
	private Scene scene2;
	private VBox vbox2;
	private Button gameRuleButton;
	public static Button startgameButton;
	public static ProgressBar progress;
	public static Timeline timeline;

	
	@Override
		public void start(Stage primaryStage) {
			try {
				stage=primaryStage;
				primaryStage.setTitle("Chess-Game");
				primaryStage.getIcons().add( new Image("assets/chess_icon.png") );
				
				scene1=createScene1();
				scene2=createScene2();
				boardScene = createBoardScene();
				
				primaryStage.setScene(scene1);
				primaryStage.show();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	
		private Scene createScene1() throws IOException {
			
			 vbox1=(VBox)FXMLLoader.load(getClass().getResource("HomePage.fxml"));
			 vbox1.setSpacing(15);
			 
			 gameButton=new Button("New Game");
			 gameButton.setOnAction(e -> switchScenes(boardScene));
			 gameButton.setId("btn1");
			 gameRuleButton=new Button("Game Rules");
			 gameRuleButton.setId("btn2");
			 gameRuleButton.setOnAction(e ->switchScenes(scene2));
			 
			 quitButton=new Button("Quit");
			 quitButton.setOnAction(e -> stage.close());
			 quitButton.setId("btn2");
			 
			 vbox1.getChildren().addAll(gameButton,gameRuleButton,quitButton);
			 
			 scene1= new Scene(vbox1,700,500);
			 scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			 
			return scene1;
		}
		
		private Scene createBoardScene() {
		
			HBox hbox6=new HBox();
			VBox vBox5=new VBox();
			
			 	Menu m = new Menu("Menu"); 
		        MenuItem restart = new MenuItem("Home Page"); 
		        //when m1 is clicked my whole app is reloaded
		        restart.setOnAction(e->{Main app=new Main();
		        app.start(stage);});
		  
		        m.getItems().add(restart); 
		  
		        MenuBar mb = new MenuBar(); 
		        mb.getMenus().add(m); 
		        
			board = Board.getInstance();
			
			//TODO: implement the function changeTurn(); to show in the console who's turn is it 
			System.out.println("The player color is :" +board.getPlayerTurn());
			progress = new ProgressBar();
			progress.setId("progress");
		     progress.setMinWidth(200);
		     progress.setMaxWidth(Double.MAX_VALUE);
		     IntegerProperty seconds = new SimpleIntegerProperty();
		     progress.progressProperty().bind(seconds.divide(60.0));
		      timeline = new Timeline(
		         new KeyFrame(Duration.ZERO, new KeyValue(seconds, 0)),
		         new KeyFrame(Duration.minutes(0.3), e-> {
		        	 PieceColor colorString =board.getPlayerTurn();
		   
		        	 Label label=new Label();
		        	
		        	 label.setText(colorString+" 's Turn");
		        	 
		     
		        	
		        	     
		        	 vBox5.getChildren().add(label);
					    System.out.println(colorString+ "  Turn");
		         }, new KeyValue(seconds, 60))   
		     );
		    timeline.setCycleCount(Animation.INDEFINITE);
		  

		    startgameButton=new Button("Start the match");
		    startgameButton.setId("start");
		    startgameButton.setVisible(true);

		    startgameButton.setOnAction(e-> { 
		    
		    	timeline.playFromStart();
		    	
				   
				    //After Clicking the Button Disappear
				    startgameButton.setVisible(false);
		    });

		    
		    VBox vBox7=new VBox();
		    vBox5.getChildren().addAll(mb,board);
		  
		    vBox7.getChildren().addAll(progress,startgameButton);
		    vBox7.setAlignment(Pos.CENTER);
			hbox6.getChildren().addAll(vBox5,vBox7);
			boardScene = new Scene(hbox6,700,500);
			boardScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			return boardScene;
		}

		private Scene createScene2() throws IOException {
			ScrollPane scrollPane=new ScrollPane();
			scrollPane.setPrefSize(100,300);
			Label ruleLabel=new Label("How to play?");
			ruleLabel.setId("labels");
			Text rulesText=new Text("So you now have your chess set and you're ready to go.\n"
					+ "The King can move one space in any direction.\n"
					+ "He can never move in to 'check' (where he is threatened by another piece).\n"
					+ "This means the king can never be in the space adjacent to the opposing King.\n"
					+ "The Queen is considered the most powerful chess piece on the chessboard.\n"
					+ "She is placed next to the king, on her own color.\n"
					+ "The game is not over when she is lost,\n"
					+ "Like the King, the Queen can move in any direction.\n"
					+ "She can move any number of spaces in any direction.\n "
					+ "If the obstruction is an opposing chess piece, she is free to capture it!\n"
					+ "Bishops are situated next to Queen and the King.\n"
					+ "These chess pieces move along the diagonals of the chessboard.\n"
					+ "They can move any number of spaces on the diagonals.\n "
					+ "There are two Knights.Situated between the Bishop and the Rook.\n"
					+ "They move in an 'L' shape \n "
					+ "There are two Rooks. Situated on the corners, next to the Knight.\n "
					+ "These chess pieces move up and down and can move any number of spaces\n"
					+ "Each pawn has the option to move forward one space or two spaces.\n "
					+ "After this move, they can only move one space forward.");
			rulesText.setId("text");
			scrollPane.setContent(rulesText);
			Button closeButton = new Button("Back");
			closeButton.setOnAction(e ->switchScenes(scene1));
			vbox2=new VBox();
			vbox2.setSpacing(5);
			vbox2.getChildren().addAll(ruleLabel,rulesText,scrollPane,closeButton);
			scene2= new Scene(vbox2,700,500);
			scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			return scene2;
		}
		
		public void switchScenes(Scene scene) {
			stage.setScene(scene);
			
		}

		public static void main(String[] args) {
						launch(args);
					}
		
}//whole application 



