//Class for each cell in game 
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Cell extends Rectangle{

	protected boolean alive = false;
	private Cell[][] allCells;
	private int i, j;
	public int neighbors = 0, incrementValue = 0;

	public Cell(int i, int j, Cell[][] allCells){
		super(20, 20, Color.WHITE);
		super.setStroke(Color.BLACK);

		this.i = i;
		this.j = j;
		this.allCells = allCells;

		super.setOnMouseClicked(e->{
			if(!alive){
				super.setFill(Color.BLACK);
				alive = true;
				incrementNeighbors(1);
			}else{
				super.setFill(Color.WHITE);
				alive = false;
				incrementNeighbors(-1);
			}
			
		});
	}

	private void incrementNeighbors(int n){
		int bli = 30;
		int blj = 30;

		if(i>0) 		allCells[i-1][j].neighbors+=n;
		if(i<bli-1)	 	allCells[i+1][j].neighbors+=n;
		if(j>0) 		allCells[i][j-1].neighbors+=n;
		if(j<blj-1) 		allCells[i][j+1].neighbors+=n;
		if(j>0 && i>0) 		allCells[i-1][j-1].neighbors+=n;
		if(j>0 && i<bli-1)	allCells[i+1][j-1].neighbors+=n;
		if(j<blj-1 && i>0)	allCells[i-1][j+1].neighbors+=n;
		if(j<blj-1 && i<bli-1)	allCells[i+1][j+1].neighbors+=n;
	}

	
	//Trickiest part: for each update, incrementation of neighbors must be done
	//AFTER all pieces are updated. This is why we have two separate methods for updating and incrementing.
	public void update(){
		 
		
		if(alive && (neighbors<2 || neighbors>3)){
			super.setFill(Color.WHITE);
			alive = false;
			incrementValue = -1;	
		}
		else if(!alive && neighbors == 3){
			super.setFill(Color.BLACK);
			alive = true;
			incrementValue = 1;
		}
	}

	public void increment(){
		if(incrementValue!=0){
			incrementNeighbors(incrementValue);
			incrementValue = 0;
		}
	}
}
