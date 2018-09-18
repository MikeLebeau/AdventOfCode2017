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
                    setDown(i);
                    break;
                case UP:
                    setLeft(i);
                    break;
                case DOWN:
                    setRight(i);
                    break;
                case RIGHT:
                    setUp(i);
                    break;
                default:
                    setRight(i);
                    break;
            }
        }
        return this;
    }

    public Spiral makeSquare(){
        // Minus one because I start with setLeft()
        posX = sideSize-1;
        posY = sideSize;

        fromMiddle = false;

        int startValue = sideSize*sideSize;

        for(double i = sideSize*sideSize; i > 0; i--){
            //System.out.println("POS => x:" + posX + ", y:" + posY);

            setNext(startValue, lastMove);

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
    private Spiral setNext(int value, Move direction){
        switch (direction){
            case LEFT:
                setLeft(value);
                break;
            case RIGHT:
                setRight(value);
                break;
            case UP:
                setUp(value);
                break;
            case DOWN:
                setDown(value);
                break;
            default:
                setLeft(value);
                break;
        }

        return this;
    }

    private int setLeft(int value){
        lastMove = Move.LEFT;

        if(posY-1 >= 0 && matrix[posX][posY-1] == 0){
            posY--;
            this.matrix[posX][posY] = value;
            return value;
        }

        if(fromMiddle){
            return (matrix[posX][posY-1] != 0) ? setUp(value) : value;
        }
        return (matrix[posX][posY-1] != 1) ? setUp(value) : value;
    }

    private int setRight(int value){
        lastMove = Move.RIGHT;

        if(posY+1 <= sideSize-1 && matrix[posX][posY+1] == 0){
            posY++;
            this.matrix[posX][posY] = value;
            return value;
        }

        if(fromMiddle){
            return (matrix[posX][posY+1] != 0) ? setDown(value) : value;
        }

        return setDown(value);
    }

    private int setUp(int value){
        lastMove = Move.UP;
        if(posX-1 >= 0 && matrix[posX-1][posY] == 0){
            posX--;
            this.matrix[posX][posY] = value;
            return value;
        }

        if(fromMiddle){
            return (matrix[posX-1][posY] != 0) ? setRight(value) : value;
        }
        return setRight(value);
    }

    private int setDown(int value){
        lastMove = Move.DOWN;
        if(posX+1 <= sideSize-1 && matrix[posX+1][posY] == 0){
            posX++;
            this.matrix[posX][posY] = value;
            return value;
        }

        if(fromMiddle){
            return (matrix[posX][posY+1] != 0) ? setLeft(value) : value;
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