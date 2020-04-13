package com.company;


/**the Cell class saves the value and coordinates of a cell.
 * @author Mohammad Hossein Asadi
 * @version 2/4/2020**/

public class Cell {

    private char value;
    private int row;
    private int column;

    /**construct the cell coordinates and empty the cell at first.
     * @param r x position
     * @param c y position
     * **/
    public Cell(int r, int c) {
        this.row = r;
        this.column = c;
        clear();
    }
    /**clear the cell**/
    public void clear() {
        setValue('e');
    }
    /**get value of the cell and draw it.**/
    public void draw() {
        switch (getValue()) {
            case 'b':
                System.out.print(Color.BLUE_BOLD);
                System.out.print(" O ");
                System.out.print(Color.RESET);
                break;
            case 'w':
                System.out.print(Color.RED_BOLD);
                System.out.print(" O ");
                System.out.print(Color.RESET);
                break;
            case 'e':
                System.out.print(Color.GREEN);
                System.out.print(" · ");
                System.out.print(Color.RESET);
                break;
        }
    }
    /**@return value of the cell**/
    public char getValue(){
        return this.value;
    }
    /**@param value of the cell that we want to set.**/
    public void setValue(char value) {
        this.value = value;
    }
}
