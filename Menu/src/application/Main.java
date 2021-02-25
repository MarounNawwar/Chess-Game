package application;
	
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollPane;
import java.io.IOException;
import Board.Board;
import Pieces.King;
import Pieces.PieceColor;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;


public class Main extends Application{
	

	//Delay initialized to 0 for the Timer
	public static Stage stage;
	private Scene boardScene;
	private Board board;
	public static  Scene scene1;
	private VBox vbox1;
	private VBox vbox2;
	private HBox hbox1;
	private VBox vBox4;
	private ScrollPane scrollPane;
	private Button gameButton;
	private Button quitButton;
	private Scene scene2;
	private Button gameRuleButton;
	public static ProgressBar progress;
	public static Timeline timeline;

	
	@Override
		public void start(Stage primaryStage) {
			try {
				stage=primaryStage;
				primaryStage.setTitle("Chess-Game");
				primaryStage.getIcons().add( new Image("images/chess_icon.png") );
				
				scene1=createScene1();
				scene2=createScene2();
				boardScene = createBoardScene();
				
				primaryStage.setScene(scene1);
				primaryStage.show();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	
		public Scene createScene1() throws IOException {
			
			 vbox1=(VBox)FXMLLoader.load(getClass().getResource("HomePage.fxml"));
			 vbox1.setSpacing(15);
			 
			 gameButton=new Button("New Game");
			 gameButton.setPrefWidth(150);
			 gameButton.setOnAction(e -> switchScenes(boardScene));
			 gameButton.setId("btn1");
			 gameRuleButton=new Button("Game Rules");
			 gameRuleButton.setPrefWidth(150);
			 gameRuleButton.setId("btn2");
			 gameRuleButton.setOnAction(e ->switchScenes(scene2));
			 
			 quitButton=new Button("Quit");
			 quitButton.setPrefWidth(150);
			 quitButton.setOnAction(e -> stage.close());
			 quitButton.setId("quit");
			 
			 vbox1.getChildren().addAll(gameButton,gameRuleButton,quitButton);
			 
			 scene1= new Scene(vbox1,700,500);
			 scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			 
			return scene1;
		}
		
		public Scene createBoardScene() {
		
			BorderPane b=new BorderPane();
	
			Menu m = new Menu("Menu"); 
			 
			 	ImageView view=new ImageView("images/homeIcon.png");
			 	view.setFitHeight(20);
			 	view.setFitWidth(20);
			 	m.setGraphic(view);
			 	
		        MenuItem restart = new MenuItem("Home Page"); 
		        
		        //when m1 is clicked my whole app is reloaded
		        restart.setOnAction(e->{
		        	//reset() function in Board Class
		        	Board.reset();
		        	//restarGame function here in Main Class
			       restartGame();
			        
		        });
		        
		        m.getItems().add(restart); 
		        MenuBar mb = new MenuBar(); 
		        mb.getMenus().add(m); 
		        
		        //To make the menuBar fit the Parent's Width
		    	mb.prefWidthProperty().bind(stage.widthProperty());
		    	//Set MenuBar in Top of BorderPane
		        b.setTop(mb);
		        
		        //if already exist return instance, if not create new one.
		        //Singleton->Board
		    	board = Board.getInstance();
		    
			
		    	
		    	progress = new ProgressBar();
				progress.setId("progress");
				progress.setMinWidth(200);
				progress.setMaxWidth(Double.MAX_VALUE);
				//simpleIntegerProperty is the Constructor of IntegerProperty
				//Used to wrap an int value
				IntegerProperty seconds = new SimpleIntegerProperty();
				//it must be between 0 and 1 so we devide integer second to 60 
				//so we devide 30/60->0.5 and 0.5 is between 0 and 1
				progress.progressProperty().bind(seconds.divide(60.0));
				
				
				timeline = new Timeline(
						//set start at 0
		         new KeyFrame(Duration.ZERO, new KeyValue(seconds, 0)),
		         new KeyFrame(Duration.minutes(0.3), e-> {
		         }, new KeyValue(seconds, 60))   
		     );
				//do only one cycle 
		    timeline.setCycleCount(1);
		   
		    timeline.setOnFinished(e-> {		    	
		    	if(board.getActiveSquare() !=null) {
		    		board.RemoveDisplayedValidMoves(board.getActiveSquare());
		    		board.getActiveSquare().getStyleClass().remove("chess-square-active");
		    	}	
		    	
		    	board.goNextTurn();		
		    StartTurn();
		    });
		    
		    vBox4=new VBox();
		    hbox1=new HBox(board);
		    hbox1.setAlignment(Pos.CENTER);
		    board.setId("board");
		    vBox4.getChildren().add(hbox1);
		    vBox4.setAlignment(Pos.CENTER);
		
		    b.setCenter(vBox4);
		    b.setBottom(progress);
		    
		    b.setId("BoardScene");
			boardScene = new Scene(b,700,500);
			boardScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			return boardScene;
			
		}
		
		public static void restartGame() {
			 Main app=new Main();
		        app.start(stage);
		        app.createBoardScene();
		}
	
		public static void StartTurn() {
			timeline.playFromStart();
		}		
		
		
		//used in isCheckMate() function in Board Class
		//when (true) the time line will stop
		public static void StopTime() {
			timeline.stop();
		}

		private Scene createScene2() throws IOException {
			scrollPane=new ScrollPane();
			scrollPane.setPrefSize(100,300);
			Label ruleLabel=new Label("How to play?");
			ruleLabel.setId("labels");
			Text rulesText=new Text("So you now have your chess set and you're ready to go.\n"
					+"•KING:  \n"
					+ "The King can move one space in any direction.\n"
					+ "He can never move in to 'check' (where he is threatened by another piece).\n"
					+ "This means the king can never be in the space adjacent to the opposing King.\n"
					+"\n"
					
					+"•QUEEN:  \n"
					+ "The Queen is considered the most powerful chess piece on the chessboard.\n"
					+ "She is placed next to the king, on her own color.\n"
					+ "The game is not over when she is lost,\n"
					+ "Like the King, the Queen can move in any direction.\n"
					+ "She can move any number of spaces in any direction.\n"
					+ "If the obstruction is an opposing chess piece, she is free to capture it!\n"
					+"\n"
					
					+"•BISHOP:  \n"
					+ "Bishops are situated next to Queen and the King.\n"
					+ "These chess pieces move along the diagonals of the chessboard.\n"
					+ "They can move any number of spaces on the diagonals.\n "
					+"\n"
					
					+"•KNIGHT:  \n"
					+ "There are two Knights.Situated between the Bishop and the Rook.\n"
					+ "They move in an 'L' shape \n "
					+"\n"
					+"•ROOK:  \n"
					+ "There are two Rooks. Situated on the corners, next to the Knight.\n"
					+ "These chess pieces move up and down and can move any number of spaces\n"
					+"\n"
					
					+"•PAWN:  \n"
					+ "Each pawn has the option to move forward one space or two spaces.\n"
					+ "After this move, they can only move one space forward.");
			rulesText.setId("text");
			scrollPane.setContent(rulesText);
			Button backButton = new Button("Back");
			backButton.setPrefWidth(100);
			backButton.setId("back");
			backButton.setOnAction(e ->switchScenes(scene1));
			vbox2=new VBox();
			vbox2.setSpacing(15);
			vbox2.getChildren().addAll(ruleLabel,scrollPane,backButton);
			scene2= new Scene(vbox2,700,500);
			scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			return scene2;
		}
		
		public static  void switchScenes(Scene scene) {
			stage.setScene(scene);
			
		}

		public static void main(String[] args) {
						launch(args);
					}
		

		public static Scene getScene1() {
			return scene1;
		}

}//whole application

