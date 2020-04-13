package com.company;

import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.setOut;

/**the Otello class contains rules and steps of the game.
 * @author Mohammad Hossein Asadi
 * @version 2/4/2020**/

public class Otello {
    public Board board;
    private Turn turn;
    Scanner input=new Scanner(System.in);

    /**define general steps of the game.**/
    public Otello(){
        this.board=new Board();
        startTheGame();
        boolean c;
        do {
            c =move();
            changeTurn();
        }while (c);
    }
    /**set that which color starts the game.**/
    public void startTheGame(){
        System.out.println("which one should start the game? (b/r)");
        String first= input.nextLine();
        if(first.equalsIgnoreCase("b")){
            turn=Turn.BLACK;
        }if (first.equalsIgnoreCase("r")){
            turn=Turn.WHITE;
        }
    }
    /**steps of a movement and checking the rules.
     * @return true if the game is going on and false if someone win the game.**/
    public boolean move(){
        findWinner(1);
        System.out.println("enter the ROW and COLUMN to put a disc.");
        int r=input.nextInt();
        String alphabet=input.nextLine();
        //String alphabetToUpper=alphabet.toUpperCase();
        char ch = alphabet.charAt(1);
        int c;
        switch (ch){
            case 'A':
                c=0;
                break;
            case 'B':
                c=1;
                break;
            case 'C':
                c=2;
                break;
            case 'D':
                c=3;
                break;
            case 'E':
                c=4;
                break;
            case 'F':
                c=5;
                break;
            case 'G':
                c=6;
                break;
            case 'H':
                c=7;
                break;
            default:
                c=8;
                break;
        }

        if (validInput(r-1,c)) {
            char value=(turn==Turn.BLACK)?'b':'w';
            if(!checkPosMovement(value)){
                changeTurn();
                value=(turn==Turn.BLACK)?'b':'w';
                if(!checkPosMovement(value)){
                    findWinner(0);
                    return false;
                }
            }else{
                boolean b=action(r-1,c);
                if (!b){
                    move();
                }
            }
        }else{
            move();
        }

        board.gamePlan();
        return true;

    }

    /**check whether the input is valid or not.
     * @return true if it is valid.**/
    public boolean validInput(int r,int c){
        if(r>=0 && r<board.ROW && c>=0 && c<board.COLUMN && board.cells[r][c].getValue()=='e'){
            return true;
        }
        System.out.println("invalid input");
        return false;
    }

    /**check if movement for a color is possible or not.
     * @return true if possible.**/
    public boolean checkPosMovement(char value){
        for (int r=0;r<board.ROW;r++){
            for (int c=0;c<board.COLUMN;c++){
                if(board.cells[r][c].getValue()==value){
                    if (checkPos(r,c)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**check if movement in r and c is possible or not.
     * @return true if it is possible.**/
    public boolean checkPos(int r,int c){
        char curr=(turn==Turn.BLACK)?'b':'w';
        char opos=(curr=='b')?'w':'b';
        boolean flag=true;

        if (r>1 && board.cells[r-1][c].getValue()==opos){
            int rTemp=r;
            while (rTemp>1 && board.cells[rTemp][c].getValue()!='e'){
                rTemp--;
                if (board.cells[rTemp][c].getValue()==curr){
                    flag=false;
                    break;
                }
            }
            if (flag){
                return true;
            }
        }

        if (r<board.ROW-1 && board.cells[r+1][c].getValue()==opos){
            int rTemp=r;
            while (rTemp<board.ROW && board.cells[rTemp][c].getValue()==opos){
                rTemp++;
                if (board.cells[rTemp][c].getValue()==curr){
                    flag=false;
                    break;
                }
            }
            if (flag){
                return true;
            }
        }

        if (c>1 && board.cells[r][c-1].getValue()==opos){
            int cTemp=c;
            while (cTemp>1 && board.cells[r][cTemp].getValue()!='e'){
                cTemp--;
                if (board.cells[r][cTemp].getValue()==curr){
                    flag=false;
                    break;
                }
            }
            if (flag){
                return true;
            }
        }

        if (c<board.COLUMN-1 && board.cells[r][c+1].getValue()==opos){
            int cTemp=c;
            while (cTemp<board.COLUMN && board.cells[r][cTemp].getValue()==opos){
                cTemp++;
                if (board.cells[r][cTemp].getValue()==curr){
                    flag=false;
                    break;
                }
            }
            if (flag){
                return true;
            }
        }

        if (c>1 && r>1 && board.cells[r-1][c-1].getValue()==opos){
            int cTemp=c;
            int rTemp=r;
            while (cTemp>1 && rTemp>1 && board.cells[rTemp][cTemp].getValue()!='e'){
                cTemp--;
                rTemp--;
                if (board.cells[rTemp][cTemp].getValue()==curr){
                    flag=false;
                    break;
                }
            }
            if (flag){
                return true;
            }
        }

        if (c<board.COLUMN-1 && r<board.ROW-1 && board.cells[r+1][c+1].getValue()==opos){
            int cTemp=c;
            int rTemp=r;
            while (cTemp<board.COLUMN && rTemp<board.ROW && board.cells[rTemp][cTemp].getValue()==opos){
                cTemp++;
                rTemp++;
                if (board.cells[rTemp][cTemp].getValue()==curr){
                    flag=false;
                    break;
                }
            }
            if (flag){
                return true;
            }
        }

        if (c<board.COLUMN-1 && r>1 && board.cells[r-1][c+1].getValue()==opos){
            int cTemp=c;
            int rTemp=r;
            while (cTemp<board.COLUMN && rTemp>1 && board.cells[rTemp][cTemp].getValue()==opos){
                cTemp++;
                rTemp--;
                if (board.cells[rTemp][cTemp].getValue()==curr){
                    flag=false;
                    break;
                }
            }
            if (flag){
                return true;
            }
        }

        if (c>1 && r<board.ROW-1 && board.cells[r+1][c-1].getValue()==opos){
            int cTemp=c;
            int rTemp=r;
            while (cTemp>1 && rTemp<board.ROW && board.cells[rTemp][cTemp].getValue()==opos){
                cTemp--;
                rTemp++;
                if (board.cells[rTemp][cTemp].getValue()==curr){
                    flag=false;
                    break;
                }
            }
            return flag;
        }


        return false;
    }

    /**make an action(movement) in r and c.
     * @param r row number
     * @param c column number
     * @return true if the action successfully is done. **/
    public boolean action(int r,int c){

        char curr=(turn==Turn.BLACK)?'b':'w';
        char opos=(curr=='b')?'w':'b';

        if (r>1 && board.cells[r-1][c].getValue()==opos){
            int rTemp=r , i=r;
            while (rTemp>1 && board.cells[rTemp-1][c].getValue()==opos){
                rTemp--;
                if (board.cells[rTemp][c].getValue()==curr){
                    System.out.println("error");
                    return false;
                }
            }
            if (rTemp-1>=0 && board.cells[rTemp-1][c].getValue()==curr){
                while (i>rTemp){
                    i--;
                    board.cells[i][c].setValue(curr);
                }
                board.cells[r][c].setValue(curr);
            }
        }

        if (r<(board.ROW)-1 && board.cells[r+1][c].getValue()==opos){
            int rTemp=r , i=r;
            while (rTemp<(board.ROW)-1 && board.cells[rTemp+1][c].getValue()==opos){
                rTemp++;
                if (board.cells[rTemp][c].getValue()==curr){
                    System.out.println("error");
                    return false;
                }
            }
            if (rTemp+1<board.ROW && board.cells[rTemp+1][c].getValue()==curr){
                while (i<rTemp){
                    i++;
                    board.cells[i][c].setValue(curr);
                }
                board.cells[r][c].setValue(curr);
            }
        }

        if (c>1 && board.cells[r][c-1].getValue()==opos){
            int cTemp=c ,j=c;
            while (cTemp>1 && board.cells[r][cTemp-1].getValue()==opos){
                cTemp--;
                if (board.cells[r][cTemp].getValue()==curr){
                    System.out.println("error");
                    return false;
                }
            }
            if (cTemp-1>=0 && board.cells[r][cTemp-1].getValue()==curr){
                while (j>cTemp){
                    j--;
                    board.cells[r][j].setValue(curr);
                }
                board.cells[r][c].setValue(curr);
            }
        }

        if (c<(board.COLUMN)-1 && board.cells[r][c+1].getValue()==opos){
            int cTemp=c, j=c;
            while (cTemp<(board.COLUMN)-1 && board.cells[r][cTemp+1].getValue()==opos){
                cTemp++;
                if (board.cells[r][cTemp].getValue()==curr){
                    System.out.println("error");
                    return false;
                }
            }
            if (cTemp+1<board.COLUMN && board.cells[r][cTemp+1].getValue()==curr){
                while (j<cTemp){
                    j++;
                    board.cells[r][j].setValue(curr);
                }
                board.cells[r][c].setValue(curr);
            }
        }

        if (c>1 && r>1 && board.cells[r-1][c-1].getValue()==opos){
            int cTemp=c;
            int rTemp=r;
            int i=r;
            int j=c;
            while (cTemp>1 && rTemp>1 && board.cells[rTemp-1][cTemp-1].getValue()==opos){
                cTemp--;
                rTemp--;
                if (board.cells[rTemp][cTemp].getValue()==curr){
                    System.out.println("error");
                    return false;
                }
            }
            if (board.cells[rTemp-1][cTemp-1].getValue()==curr){
                while (j>cTemp && i>rTemp){
                    i--;
                    j--;
                    board.cells[i][j].setValue(curr);
                }
                board.cells[r][c].setValue(curr);
            }
        }

        if (c<(board.COLUMN)-1 && r<(board.ROW)-1 && board.cells[r+1][c+1].getValue()==opos){
            int cTemp=c;
            int rTemp=r;
            int i=r;
            int j=c;
            while (cTemp<(board.COLUMN)-1 && rTemp<(board.ROW)-1 && board.cells[rTemp+1][cTemp+1].getValue()==opos){
                cTemp++;
                rTemp++;
                if (board.cells[rTemp][cTemp].getValue()==curr){
                    System.out.println("error");
                    return false;
                }
            }
            if (rTemp+1<board.ROW && cTemp+1<board.COLUMN && board.cells[rTemp+1][cTemp+1].getValue()==curr){
                while (j<cTemp && i<rTemp){
                    i++;
                    j++;
                    board.cells[i][j].setValue(curr);
                }
                board.cells[r][c].setValue(curr);
            }
        }


        if (c<(board.COLUMN)-1 && r>1 && board.cells[r-1][c+1].getValue()==opos){
            int cTemp=c;
            int rTemp=r;
            int i=r;
            int j=c;
            while (cTemp<(board.COLUMN)-1 && rTemp>1 && board.cells[rTemp-1][cTemp+1].getValue()==opos){
                cTemp++;
                rTemp--;
                if (board.cells[rTemp][cTemp].getValue()==curr){
                    System.out.println("error");
                    return false;
                }
            }
            if (cTemp+1<(board.COLUMN) && board.cells[rTemp-1][cTemp+1].getValue()==curr){
                while (j<cTemp && i>rTemp){
                    i--;
                    j++;
                    board.cells[i][j].setValue(curr);
                }
                board.cells[r][c].setValue(curr);
            }
        }

        if (c>1 && r<(board.ROW)-1 && board.cells[r+1][c-1].getValue()==opos){
            int cTemp=c;
            int rTemp=r;
            int i=r;
            int j=c;
            while (cTemp>1 && rTemp<(board.ROW)-1 && board.cells[rTemp+1][cTemp-1].getValue()==opos){
                cTemp--;
                rTemp++;
                if (board.cells[rTemp][cTemp].getValue()==curr){
                    System.out.println("error");
                    return false;
                }
            }
            if (rTemp+1<(board.ROW) && board.cells[rTemp+1][cTemp-1].getValue()==curr){
                while (j>cTemp && i<rTemp){
                    i++;
                    j--;
                    board.cells[i][j].setValue(curr);
                }
                board.cells[r][c].setValue(curr);
            }
        }

        if (board.cells[r][c].getValue()!='e'){
            return true;
        }else{
            return false;
        }
    }

    /**change the turn in the game.**/
    public void changeTurn(){
        this.turn=(turn==Turn.BLACK)?turn.WHITE:turn.BLACK;
        String colorOfTurn=(this.turn==Turn.BLACK)?"BLUE":"RED";
        if (colorOfTurn.equals("BLUE")){
            System.out.print(Color.BLUE_BOLD);
            System.out.println("it is "+colorOfTurn+" turn");
        }else {
            System.out.print(Color.RED_BOLD);
            System.out.println("it is "+colorOfTurn+" turn");
        }
        System.out.print(Color.RESET);
    }

    /**find the winner.
     * @param s kind of finding the winner. if it happen in the game or at the end of game.**/
    public void findWinner(int s){
        if (s==0){
            board.countWhiteCells();
            board.countBlackCells();
            if (board.getNumberOfWhites()>board.getNumberOfBlacks()){
                System.out.print(Color.GREEN);
                System.out.println("********"+"RED"+" has win the game."+"********");
            }else {
                System.out.print(Color.GREEN);
                System.out.println("********"+"BLUE"+" has win the game."+"********");

            }
            System.out.print(Color.RESET);
        }else if (s==1){
            for (int r=0;r<board.ROW;r++){
                for (int c=0;c<board.COLUMN;c++){
                    if (board.cells[r][c].getValue()=='e' && checkPosMovement(board.cells[r][c].getValue())){////////////////////
                        return;
                    }
                }
            }
            board.countWhiteCells();
            board.countBlackCells();
            if (board.getNumberOfWhites()>board.getNumberOfBlacks()){
                System.out.println("RED"+" has win the game.");
            }else {
                System.out.println("BLUE"+" has win the game.");
            }
            exit(-1);
        }

    }

    /**the main method that make an otello game.**/
    public static void main(String[] args) {
	    Otello otello=new Otello();
    }
}
