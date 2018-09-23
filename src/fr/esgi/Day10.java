package fr.esgi;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Day10 {
    public static void main(String[] args) {

        // On array 256 [0 to 255]
        String input = "70,66,255,2,48,0,54,48,80,141,244,254,160,108,1,41";

        // On array 5[0 to 4]
        String inputTest = "3,4,1,5";

        int sizeArray = 256;
        int skipSize = 0;

        int[] sequenceNum = new int[sizeArray];
        String[] inputSequence = input.split(",");

        //Fulfill the array
        for (int i = 0; i < sequenceNum.length; i++) {
            sequenceNum[i] = i;
        }

        int startAt = 0;

        for (int i = 0; i < inputSequence.length; i++) {
            int[] subArray;

            System.out.println("START AT : " + startAt);

            int length = Integer.parseInt(inputSequence[i]);

            subArray = copyOfRange(sequenceNum, startAt, length);
            subArray = reverseArray(subArray);

            for (int i1 = 0; i1 < subArray.length; i1++) {
                int index = (i1+startAt)%sequenceNum.length;

                sequenceNum[index] = subArray[i1];
            }

            startAt += length;
            startAt += skipSize;
            skipSize++;
            printIntArray(sequenceNum);
        }

//        printIntArray(sequenceNum);
        System.out.println(sequenceNum[0] + " x " + sequenceNum[1] + " = " + (sequenceNum[0]*sequenceNum[1]));

    }

    static String convert

    static String convertToPrintableChar(String sequence){
        List<String> result = new ArrayList<>(sequence.split(",").length);

        for (String s : sequence.split(",")) {
            result.add();
        }

        return StringUtils.join(result, ",");
    }

    static void printIntArray(int[] array){
        System.out.println("----------PRINT ARRAY----------");
        for (int i : array) {
            System.out.print(i + ",");
        }
        System.out.println("");
        System.out.println("----------END PRINT ARRAY----------");
    }

    static int[] copyOfRange(int[] array, int startAt, int length){
        int[] resultArray = new int[length];

        for (int i = 0; i < resultArray.length; i++) {
            int index = (i+startAt)%array.length;
            resultArray[i] = array[index];
        }

        return resultArray;
    }

    static int[] reverseArray(int[] array){
        for(int i = 0; i < array.length / 2; i++)
        {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }

        return array;
    }
}
