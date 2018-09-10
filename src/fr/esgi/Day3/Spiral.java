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

    // All numbers on right bottom diagonal
    private List<Integer> magicNums = new ArrayList<>();

    public Spiral(int sideSize) {
        posX = 0;
        posY = 0;
        this.sideSize = sideSize;

        matrix = new int[sideSize][sideSize];

        for(int i = 1; i < sideSize*sideSize; i += 2){
            magicNums.add(i*i);
        }
    }

    public Spiral fillSpiral(){

        for(int i = 1; i <= sideSize*sideSize; i++){
            if(i == 1){
                setMiddle(i);
            }else if(i == 2){
                setRight(i);
            }else if(i == 3){
                setUp(i);
            }else{
                if(magicNums.contains(i)){
                    setRight(i);
                }

                // Need to know how many time I've to call setUp, setRight, setLeft, setDown


            }
        }

        return this;
    }

    // ------------------ Tools -------------------------------
    private Spiral setMiddle(int value){
        posX = getMiddlePos();
        posY = getMiddlePos();

        this.matrix[posX][posY] = value;

        return this;
    }

    private Spiral setUp(int value){
        posY--;
        this.matrix[posX][posY] = value;
        return this;
    }

    private Spiral setDown(int value){
        posY++;
        this.matrix[posX][posY] = value;
        return this;
    }

    private Spiral setLeft(int value){
        posX--;
        this.matrix[posX][posY] = value;
        return this;
    }

    private Spiral setRight(int value){
        posX++;
        this.matrix[posX][posY] = value;
        return this;
    }







    // ------------------ GETTER AND SETTER -------------------------------
    public int getMiddlePos(){
        return (sideSize-1)/2;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getSideSize() {
        return sideSize;
    }

    public void setSideSize(int sideSize) {
        this.sideSize = sideSize;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
}
