package fr.esgi.Day3;

import java.util.*;

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
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                System.out.print(String.format("%07d", getValue(x, y)) + "  ");
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
    private int getSumOfSquare(int x, int y){

        int result = 0;

        Map<String, Integer> listValue = new HashMap<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int tmpX = x+i;
                int tmpY = y+j;

                if(tmpX < 0){
                    tmpX = 0;
                }

                if(tmpX >= sideSize){
                    tmpX = sideSize-1;
                }

                if(tmpY < 0){
                    tmpY = 0;
                }

                if(tmpY >= sideSize){
                    tmpY = sideSize-1;
                }

                if(tmpX == x && tmpY == y){
                }else{
                    listValue.put("(" + tmpX + "," + tmpY + ")", getValue(tmpX, tmpY));
                }


            }
        }

        for (Integer integer : listValue.values()) {
//            System.out.println("Dans le set : " + integer);
            result += integer;
        }

        return result;
    }


    // ------------------ Tools -------------------------------
    private int setLeftAndMove(int value){
        lastMove = Move.LEFT;

        if(posY-1 >= 0 && matrix[posX][posY-1] == 0){
            posY--;
            this.matrix[posX][posY] = (fromMiddle) ? getSumOfSquare(posX, posY) : value;
            return value;
        }

        if(fromMiddle){
            return (matrix[posX][posY] != 0) ? setUpAndMove(value) : value;
        }
        return (matrix[posX][posY] != 1) ? setUpAndMove(value) : value;
    }

    private int setRightAndMove(int value){
        lastMove = Move.RIGHT;

        if(posY+1 <= sideSize-1 && matrix[posX][posY+1] == 0){
            posY++;
            this.matrix[posX][posY] = (fromMiddle) ? getSumOfSquare(posX, posY) : value;
            return value;
        }

        if(fromMiddle){
            return (matrix[posX][posY] != 0) ? setDownAndMove(value) : value;
        }

        return setDownAndMove(value);
    }

    private int setUpAndMove(int value){
        lastMove = Move.UP;

        if(posX-1 >= 0 && matrix[posX-1][posY] == 0){
            posX--;
            this.matrix[posX][posY] = (fromMiddle) ? getSumOfSquare(posX, posY) : value;
            return value;
        }

        if(fromMiddle){
            return (matrix[posX-1][posY] != 0) ? setRightAndMove(value) : value;
        }
        return setRightAndMove(value);
    }

    private int setDownAndMove(int value){
        lastMove = Move.DOWN;

        if(posX+1 <= sideSize-1 && matrix[posX+1][posY] == 0){
            posX++;
            this.matrix[posX][posY] = (fromMiddle) ? getSumOfSquare(posX, posY) : value;
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

    private int getValue(int x, int y){
        return matrix[x][y];
    }
}
