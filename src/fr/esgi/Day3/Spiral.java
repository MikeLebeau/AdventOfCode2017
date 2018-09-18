package fr.esgi.Day3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LEBEAU Mike
 * ESGI - 5 AL 1
 * on 11/09/2018
 */
public class Spiral {
    private int posX;
    private int posY;

    private int sideSize;

    private int[][] matrix;

    private Move lastMove;
    private boolean fromMiddle;

    public Spiral(int sideSize) {
        posX = 0;
        posY = 0;
        this.sideSize = sideSize;

        matrix = new int[sideSize][sideSize];
        lastMove = Move.NONE;
    }

    public Spiral makeSpiralFromMiddle(){
        posX = (sideSize-1)/2;
        posY = (sideSize-1)/2;

        fromMiddle = true;

        matrix[getMiddlePos()][getMiddlePos()] = 1;

        for (int i = 2; i <= sideSize*sideSize; i++) {
            switch (lastMove){
                case LEFT:
                    setDownAndMove(i);
                    break;
                case UP:
                    setLeftAndMove(i);
                    break;
                case DOWN:
                    setRightAndMove(i);
                    break;
                case RIGHT:
                    setUpAndMove(i);
                    break;
                default:
                    setRightAndMove(i);
                    break;
            }
        }
        return this;
    }

    public Spiral makeSquare(){
        // Minus one because I start with setLeftAndMove()
        posX = sideSize-1;
        posY = sideSize;

        fromMiddle = false;

        int startValue = sideSize*sideSize;

        for(int i = sideSize*sideSize; i > 0; i--){
            //System.out.println("POS => x:" + posX + ", y:" + posY);

            switch (lastMove){
                case LEFT:
                    setLeftAndMove(i);
                    break;
                case RIGHT:
                    setRightAndMove(i);
                    break;
                case UP:
                    setUpAndMove(i);
                    break;
                case DOWN:
                    setDownAndMove(i);
                    break;
                default:
                    setLeftAndMove(i);
                    break;
            }

            startValue--;
        }

        return this;
    }

    public void printSpiral(){
        for (int[] y : matrix) {
            for (int x : y) {
                System.out.print(String.format("%02d", x) + "  ");
            }
            System.out.println("");
        }
    }

    // Final goal of the day 3
    public int getDistanceFromMiddle(int target){
        int targetX = 0;
        int targetY = 0;

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix.length; x++) {
                if(target == matrix[x][y]){
                    targetX = x;
                    targetY = y;
                }
            }
        }

        return Math.abs(getMiddlePos() - targetX) + Math.abs(getMiddlePos() - targetY);
    }

    // Final goal of the day 3 (Part two)
    // OK, it's not sexy... but it is 4am... :P
    private int getSumOfSquare(int x, int y){

        int result = 0;

        List<String> listCalcul = new ArrayList<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j < 1; j++) {
                listCalcul.add((posX+i) + " + " + (posY+j) + " = " + getValue(posX+i, posY+j));
                result += getValue(posX+i, posY+j);
            }
        }

        System.out.println("Pour " + result + " : ");
        for (String s : listCalcul) {
            System.out.println("\t" + s);
        }

        return result;
    }

    // ------------------ Tools -------------------------------
    private int getValue(int x, int y){

        if(x < sideSize && x >= 0 && y < sideSize && y >= 0){
            return matrix[x][y];
        }
        return 0;
    }

    private int setLeftAndMove(int value){
        lastMove = Move.LEFT;
        // For part two
        value = getSumOfSquare(posX, posY-1);

        if(posY-1 >= 0 && matrix[posX][posY-1] == 0){
            posY--;
            this.matrix[posX][posY] = value;
            return value;
        }

        if(fromMiddle){
            return (matrix[posX][posY-1] != 0) ? setUpAndMove(value) : value;
        }
        return (matrix[posX][posY] != 1) ? setUpAndMove(value) : value;
    }

    private int setRightAndMove(int value){
        lastMove = Move.RIGHT;
        // For part two
        value = getSumOfSquare(posX, posY+1);

        if(posY+1 <= sideSize-1 && matrix[posX][posY+1] == 0){
            posY++;
            this.matrix[posX][posY] = value;
            return value;
        }

        if(fromMiddle){
            return (matrix[posX][posY+1] != 0) ? setDownAndMove(value) : value;
        }

        return setDownAndMove(value);
    }

    private int setUpAndMove(int value){
        lastMove = Move.UP;
        // For part two
        value = getSumOfSquare(posX-1, posY);

        if(posX-1 >= 0 && matrix[posX-1][posY] == 0){
            posX--;
            this.matrix[posX][posY] = value;
            return value;
        }

        if(fromMiddle){
            return (matrix[posX-1][posY] != 0) ? setRightAndMove(value) : value;
        }
        return setRightAndMove(value);
    }

    private int setDownAndMove(int value){
        lastMove = Move.DOWN;
        // For part two
        value = getSumOfSquare(posX+1, posY);

        if(posX+1 <= sideSize-1 && matrix[posX+1][posY] == 0){
            posX++;
            this.matrix[posX][posY] = value;
            return value;
        }

        if(fromMiddle){
            return (matrix[posX][posY+1] != 0) ? setLeftAndMove(value) : value;
        }
        return setLeftAndMove(value);
    }

    // ------------------ GETTER AND SETTER -------------------------------
    public int getMiddlePos(){
        return (sideSize-1)/2;
    }
}