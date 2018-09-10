package fr.esgi.Day3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LEBEAU Mike
 * ESGI - 5 AL 1
 * on 11/09/2018
 */
public class Day3 {
    public static void main(String[] args) {

        /*
            0   1   2   3   4
          ___________________
        0 |17  16  15  14  13  30
        1 |18   5   4   3  12  29
        2 |19   6   1   2  11  28
        3 |20   7   8   9  10  27
        4 |21  22  23  24  25  26

        */

        //int target = 277678;
        int target = 10;


//        int[][] matrix = new int[sideSize][sideSize];

//        drawSpiral(target);

    }

    /**
     * To find the side size needed to find the target value
     * @param target is the value we're looking for
     * @return
     */
    static int findSideSize(int target){
        int sideSize = 1;

        while(sideSize*sideSize <= target){
            sideSize += 2;

        }

        return sideSize;
    }

//    static void drawSpiral(int target){
//
//        System.out.println("List size needed");
//        for(int i = 1; i <= sideSize; i += 2){
//            System.out.println("\t" + i + " : " + ((i*i)-1) );
//        }
//
//        System.out.println("Target => " + target);
//        System.out.println("SideSize => " + sideSize);
//
//        // Minus one because we start at 0
//        System.out.println("Le 1 est en => " + (sideSize-1)/2 );
//
//
//    }

}
