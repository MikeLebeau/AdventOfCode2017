package fr.esgi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day6 {
    public static void main(String[] args) {

        String input = "11\t11\t13\t7\t0\t15\t5\t5\t4\t4\t1\t1\t7\t1\t15\t11";
//        String input = "0\t2\t7\t0";

        List<Integer> banks = new ArrayList<>();
        List<String> states = new ArrayList<>();

        boolean ok = false;

        int nbCycle = 0;

        for (String s : input.split("\t")) {
            banks.add(Integer.parseInt(s));
        }


        do{
            banks = doRedistribution(banks);

            String latestState = banks.toString();

            System.out.println("Latest state :");
            System.out.println("\t" + latestState);
            System.out.println("-----------------------------------------------------------");

            if(states.contains(latestState)){
                nbCycle = states.size() - states.indexOf(latestState);
                ok = true;
            }else{
                states.add(latestState);
            }
        }while ( ! ok);

        System.out.println("States size : " + (states.size()+1) );
        System.out.println("Cycle size : " + nbCycle );
    }

    static List<Integer> doRedistribution(List<Integer> banks){
        int maxPos = getMaxBankPos(banks);
        int toRedistribute = banks.get(maxPos);

        banks.set(maxPos, 0);

        for (int i = 1; i <= toRedistribute; i++) {
            int nextPos = (maxPos+i)%banks.size();
            int nextVal = banks.get(nextPos) + 1;

            banks.set( nextPos,  nextVal);
        }

        System.out.println("-----------------------------------------------------------");
        System.out.println("MaxPos : " + maxPos);
        System.out.println("ToRedistribute : " + toRedistribute);
        System.out.println("banks.size() : " + banks.size());

        return banks;
    }

    static int getMaxBankPos(List<Integer> banks){
        for (int i = 0; i < banks.size(); i++) {
            if(banks.get(i).equals(Collections.max(banks))){
                return i;
            }
        }

        return -1;
    }
}
