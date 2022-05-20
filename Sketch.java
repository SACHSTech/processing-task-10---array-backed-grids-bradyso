import processing.core.PApplet;

public class Sketch extends PApplet {
	// Global variables
  int CELL_WIDTH = 20;
  int CELL_HEIGHT = 20;
  int MARGIN = 5;
  int ROW_COUNT = 10;
  int COLUMN_COUNT = 10;
  int SCREEN_WIDTH = (CELL_WIDTH + MARGIN) * COLUMN_COUNT + MARGIN;
  int SCREEN_HEIGHT = (CELL_HEIGHT + MARGIN) * ROW_COUNT + MARGIN;
  int[][] intGrid;
  int intCellSelected = 0;
  int intColumnCounter = 0;;
  int intRowCounter = 0;
  int intContinuingBlocks = 0;
  boolean blnIfGridPressed = false;
  boolean blnGridPrint = false;
  
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
    // put your size call here
    size(SCREEN_WIDTH, SCREEN_HEIGHT);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(0);
    
    intGrid = new int[ROW_COUNT][COLUMN_COUNT];
    
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    drawingGrid();
    gridSystem();
    //intGrid[1][5] = 1;
  }
  /*
  * drawing the grid and its functions
  */
  public void drawingGrid(){
    for (int column = 0; column < COLUMN_COUNT; column++){
      for (int row = 0; row < ROW_COUNT; row++){
        if (mouseY == row && blnIfGridPressed && mouseX == column){
          if (intGrid[row][column] == 0){
            intGrid[row][column] = 1;
            intCellSelected++;
          } else if (intGrid[row][column] == 1){
            intGrid[row][column] = 0;
            intCellSelected--;
          }
          if (column > 0 && intGrid[row][column - 1] == 0){
            intGrid[row][column - 1] = 1;
            intCellSelected++;
          } else if (column > 0 && intGrid[row][column - 1] == 1){ 
            intGrid[row][column - 1] = 0;
            intCellSelected--;
          }
          if (column < COLUMN_COUNT - 1 && intGrid[row][column + 1] == 0){
            intGrid[row][column + 1] = 1;
            intCellSelected++;
          } else if (column < COLUMN_COUNT - 1 && intGrid[row][column + 1] == 1){
            intGrid[row][column + 1] = 0;
            intCellSelected--;
          }
          if (row > 0 && intGrid[row - 1][column] == 0){
            intGrid[row - 1][column] = 1;
            intCellSelected++;
          } else if (row > 0 && intGrid[row - 1][column] == 1){
            intGrid[row - 1][column] = 0;
            intCellSelected--;
          }
          if (row < ROW_COUNT - 1 && intGrid[row + 1][column] == 0){
            intGrid[row + 1][column] = 1;
            intCellSelected++;
          } else if (row < ROW_COUNT - 1 && intGrid[row + 1][column] == 1){
            intGrid[row + 1][column] = 0;
            intCellSelected--;           
          }
          System.out.println("");
          // coordinate of pressed box
          System.out.println("mouse coordinates: " + "(" + mouseX + ", " + mouseY + ")" + ";" + " grid coordinates:" + " (" + "row: " + (row + 1) + "," + " column: " + (column + 1) + ")");
          // total number of cells
          System.out.println( "Total of " + intCellSelected + " cells are selected.");         
          blnIfGridPressed = false;
        }
        // pressed box becomes green
        if (intGrid[row][column] == 1){
          fill(0, 255, 51);     
        } else {
          fill(255, 255, 255);
        }
        // drawing grid
        rect(MARGIN + (column * (MARGIN + CELL_WIDTH)), MARGIN + (row * (MARGIN + CELL_HEIGHT)), CELL_WIDTH, CELL_HEIGHT);
        }
      }
    }
  /*
  * setting up number of cells on columns and rows and continous cells on different rows
  */
  public void gridSystem(){
    if(blnGridPrint){
      // row counter
      for(int r = 0; r < COLUMN_COUNT; r++){
        for(int rr = 0; rr < ROW_COUNT; rr++){
          if(intGrid[r][rr] == 1){
            intRowCounter++;
          }
          if(rr > COLUMN_COUNT - 1){
            if(intGrid[r][rr] == 1 && intGrid[r][rr + 1] == 1){
              intContinuingBlocks++;
              }
            }
          if(rr > 0 && rr < COLUMN_COUNT){
            if(rr < COLUMN_COUNT - 1 && intGrid[r][rr - 1] == 1 && intGrid[r][rr] == 1){
              intContinuingBlocks++;
              } else if(rr < COLUMN_COUNT - 1 && intGrid[r][rr - 1] == 1 && intGrid[r][rr + 1] == 0 && intGrid[r][rr] == 1){
              intContinuingBlocks++;
              }
            }
          }
        // continous rows
        if(intContinuingBlocks > 0 && intRowCounter > 2){
          System.out.println("There are " + intContinuingBlocks + " continuous blocks selected on row " + (r+1) + ".");
          }
        System.out.println("Row " + (r + 1) + " has " + intRowCounter + " cells " + "selected");
        // reset values
        intRowCounter = 0;
        intContinuingBlocks = 0;
        }
      System.out.println("");
      // column counter
      for(int c = 0; c < COLUMN_COUNT; c++){
        for(int cc = 0; cc < ROW_COUNT; cc++){
          if(intGrid[cc][c] == 1){
            intColumnCounter++;
            }
          }
        System.out.println("Column " + (c + 1) + " has " + intColumnCounter + " cells " + "selected");
        // reset value
        intColumnCounter = 0;
        }
      blnGridPrint = false;
      }
    }
  
  public void mousePressed(){
    mouseX = mouseX / (MARGIN + CELL_WIDTH);
    mouseY = mouseY / (MARGIN + CELL_HEIGHT);
    blnIfGridPressed = true;
    blnGridPrint = true;
  }
}