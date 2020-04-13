package com.company;

/**the Board class set and draw the game plan and
 * count the number of each color.
 * @author Mohammad Hossein Asadi
 * @version 2/4/2020
 * **/
public class Board {

    public  int ROW=8;
    public  int COLUMN=8;
    private int numberOfBlacks;
    private int numberOfWhites;
    public Cell[][] cells;

    /**assign the coordinate of each cell and call initMap and gamePlan.
     * **/
    public Board() {
        cells=new Cell[ROW][COLUMN];
        for (int r=0;r<ROW;r++){
            for (int c=0;c<COLUMN;c++){
                cells[r][c]=new Cell(r,c);
            }
        }
        initMap();
        gamePlan();
    }

    /**set the color of the four cell in the middle.**/
    public void initMap(){
        cells[3][3].setValue('w');
        cells[4][4].setValue('w');
        cells[3][4].setValue('b');
        cells[4][3].setValue('b');
    }

    /**draw the game plan and clear the console at first.**/
    public void gamePlan() {

        for(int clear = 0; clear < 1000; clear++)
        {
            System.out.println("\b") ;
        }

        System.out.print(" ");
        System.out.print(Color.MAGENTA);
        System.out.println(" A  B  C  D  E  F  G  H");
        System.out.print(Color.RESET);
        for (int row=1;row<ROW+1;row++){
            System.out.print(Color.MAGENTA);
            System.out.print(row);
            System.out.print(Color.RESET);
            for (int column=0;column<COLUMN;column++){
                cells[row-1][column].draw();
            }
            System.out.println();
        }
        System.out.print(Color.RESET);
        countBlackCells();
        countWhiteCells();
        System.out.print(Color.CYAN);
        System.out.println("number of blacks: "+getNumberOfBlacks());
        System.out.println("number of whites: "+getNumberOfWhites());
        System.out.print(Color.RESET);
    }

    /**count Black cells (for showing more obvious the map I turn the black color to blue).**/
    public void countBlackCells(){
        this.numberOfBlacks=0;
        for (int r=0;r<ROW;r++){
            for (int c=0;c<COLUMN;c++){
                if (cells[r][c].getValue()=='b'){
                    this.numberOfBlacks++;
                }
            }
        }
    }
    /**count White cells (for showing more obvious the map I turn the white color to red).**/
    public void countWhiteCells(){
        this.numberOfWhites=0;
        for (int r=0;r<ROW;r++){
            for (int c=0;c<COLUMN;c++){
                if (cells[r][c].getValue()=='w'){
                    this.numberOfWhites++;
                }
            }
        }
    }

    /**get the final number of white cells.**/
    public int getNumberOfWhites() {
        return numberOfWhites;
    }
    /**get the final number of black cells.**/
    public int getNumberOfBlacks() {
        return numberOfBlacks;
    }
}
