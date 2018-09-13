package fr.esgi.Day3;

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

    private String lastMove = "none";

    public Spiral(int sideSize) {
        posX = 0;
        posY = 0;
        this.sideSize = sideSize;

        matrix = new int[sideSize][sideSize];
    }

    public Spiral makeSquare(){
        // Minus one because I start with setLeft()
        posX = sideSize-1;
        posY = sideSize;

        int startValue = sideSize*sideSize;

        for(double i = sideSize*sideSize; i > 0; i--){
            //System.out.println("POS => x:" + posX + ", y:" + posY);

            switch (lastMove){
                case "left":
                    setLeft(startValue);
                    break;
                case "right":
                    setRight(startValue);
                    break;
                case "up":
                    setUp(startValue);
                    break;
                case "down":
                    setDown(startValue);
                    break;
                default:
                    setLeft(startValue);
                    break;
            }

            startValue--;
        }

        return this;
    }

    public void printSpiral(){
        for (int[] y : matrix) {
            for (int x : y) {
                System.out.print(String.format("%03d", x) + "  ");
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

    // ------------------ Tools -------------------------------
    private Spiral setLeft(int value){
        lastMove = "left";
        if(posY-1 >= 0 && matrix[posX][posY-1] == 0){
            posY--;
            this.matrix[posX][posY] = value;
            return this;
        }

        return (posY != 0 && matrix[posX][posY-1] == 1) ? this : setUp(value);
    }

    private Spiral setRight(int value){
        lastMove = "right";
        if(posY+1 <= sideSize-1 && matrix[posX][posY+1] == 0){
            posY++;
            this.matrix[posX][posY] = value;
            return this;
        }
        return setDown(value);
    }

    private Spiral setUp(int value){
        lastMove = "up";
        if(posX-1 >= 0 && matrix[posX-1][posY] == 0){
            posX--;
            this.matrix[posX][posY] = value;
            return this;
        }
        return setRight(value);
    }

    private Spiral setDown(int value){
        lastMove = "down";
        if(posX+1 <= sideSize-1 && matrix[posX+1][posY] == 0){
            posX++;
            this.matrix[posX][posY] = value;
            return this;
        }
        return setLeft(value);
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