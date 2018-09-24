package fr.esgi;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day10 {
    public static void main(String[] args) {

        // On array 256 [0 to 255]
        String input = "70,66,255,2,48,0,54,48,80,141,244,254,160,108,1,41";

        // On array 5 [0 to 4]
        String inputTest = "1,2,3";

        int sizeArray = 256;

        int[] sequenceNum = createFulfilledIntArray(sizeArray);

        String line = convertToAsciiCode(inputTest);
        line = addSuffix(line);
		System.out.println("Sequence : " + line);
        String[] inputSequence = line.split(",44,");

//		for (int i = 0; i < 64; i++) {
//			doProcess(inputSequence, sequenceNum, 0, 0);
//		}

		System.out.println("Sequence : " + Arrays.toString(inputSequence));
		doProcess(inputSequence, sequenceNum, 0, 0);

//      printIntArray(sequenceNum);
        System.out.println(sequenceNum[0] + " x " + sequenceNum[1] + " = " + (sequenceNum[0]*sequenceNum[1]));

    }

    static void doProcess(String[] inputSequence, int[] sequenceNum, int startAt, int skipSize){
		for (int i = 0; i < inputSequence.length; i++) {
			int[] subArray;

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
	}

    static String convertToAsciiCode(String line){
		String result = "";
		for (int i = 0; i < line.toCharArray().length; i++) {
			result += String.valueOf((int)line.charAt(i));
			result += (i != (line.length()-1)) ? "," : "";
		}

		return result;
	}

	static String addSuffix(String line){
    	return line += ",17,31,73,47,23";
	}

	static int[] createFulfilledIntArray(int size){
		int[] array = new int[size];
    	//Fulfill the array
		for (int i = 0; i < array.length; i++) {
			array[i] = i;
		}

		return array;
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
