package fr.esgi;

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

        doProcess(input.split(","), sequenceNum, 1);
        System.out.println(sequenceNum[0] + " x " + sequenceNum[1] + " = " + (sequenceNum[0]*sequenceNum[1]));

        // ----------------------------- PART TWO -----------------------------
        String knotHash = doKnotHash(input);

        System.out.println("KnotHash : " + knotHash);

        if("decdf7d377879877173b7f2fb131cf1b".equals(knotHash)){
            System.out.println("Rien de cassé !");
        }
    }

    static String doKnotHash(String input){
        int[] sequenceNum = createFulfilledIntArray(256);
        String line = convertToAsciiCode(input);

        line = addSuffix(line);
        doProcess(line.split(","), sequenceNum, 64);

        List<Integer> denseHashResult = doDenseSparse(sequenceNum);

        String knotHash = "";
        for (Integer integer : denseHashResult) {
            knotHash += Integer.toHexString(integer);
        }

        return knotHash;
    }

    static List<Integer> doDenseSparse(int[] sequenceNum){
        List<Integer> denseHashResult = new ArrayList<>(16);
        int denseHashTmp = 0;
        for (int i = 1; i <= sequenceNum.length; i++) {
            denseHashTmp ^= sequenceNum[i-1];

            if(i%16 == 0){
                denseHashResult.add(denseHashTmp);
                denseHashTmp = 0;
            }
        }

        return denseHashResult;
    }

    static void doProcess(String[] inputSequence, int[] sequenceNum, int nbRound){
        int startAt = 0;
        int skipSize = 0;

        for (int k = 0; k < nbRound; k++) {
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
            }
        }

	}

    static String convertToAsciiCode(String line){
		String result = "";
		for (int i = 0; i < line.toCharArray().length; i++) {
//		    if(line.charAt(i) == ','){
//		        continue;
//            }
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
