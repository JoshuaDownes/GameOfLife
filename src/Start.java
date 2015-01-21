import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ToolBar;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

public class Start extends Application{
	
	private Cell[][] cells = new Cell[30][30];
	private int speed = 300;
	private Timeline timeline;

	@Override	
	public void start(Stage primaryStage){
		BorderPane controlWindow = new BorderPane();
		Button startButton = new Button("Start");
		Button pauseButton = new Button("Pause");
		Button clear = new Button("Clear");	
		Button faster = new Button("Faster");
		Button slower = new Button("Slower");
		ToolBar options = new ToolBar(startButton, pauseButton, clear, faster, slower);

		GridPane board = new GridPane();

		controlWindow.setCenter(board);
		controlWindow.setTop(options);

		Scene gameOfLife = new Scene(controlWindow, 600, 600);
		
		//Generate cells
		for(int i = 0; i<30; i++){
			for(int j = 0; j<30; j++){
				cells[i][j] = new Cell(i,j,cells);
				board.add(cells[i][j],i,j);
			}	
		}
		
		buildTimeline();

		//Setup buttons
		startButton.setOnAction((EventHandler<ActionEvent>) e->{
			timeline.play();
		});
		pauseButton.setOnAction((EventHandler<ActionEvent>) e->{
			timeline.stop();
		});
		clear.setOnAction((EventHandler<ActionEvent>) e->{
			System.out.print("NOT DONE YET");
		});
		faster.setOnAction((EventHandler<ActionEvent>) e->{
			if(speed>1000) speed = 500;
			else if(speed>50) speed -= 50;
		});
		slower.setOnAction((EventHandler<ActionEvent>) e->{
			speed+=50;
		});
	
		primaryStage.setScene(gameOfLife);
		primaryStage.setTitle("Conway's Game of Life");
		primaryStage.show();
	}

	public void buildTimeline() { 
		timeline = TimelineBuilder.create().cycleCount(Animation.INDEFINITE)
			.keyFrames(new KeyFrame(Duration.millis(speed), (ActionEvent event) -> {
		for(Cell[] innerArr : cells){
			for(Cell cell : innerArr){
				cell.update();
			}
		}
		for(Cell[] innerArr : cells){
			for(Cell cell : innerArr){
				cell.increment();
			}
		}
		})).build();

	}

	public static void main(String[] args){
		launch(args);
	}
}
