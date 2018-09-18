package fr.esgi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day6 {
    public static void main(String[] args) {

        String input = "11\t11\t13\t7\t0\t15\t5\t5\t4\t4\t1\t1\t7\t1\t15\t11";

        List<Integer> banks = new ArrayList<>();
        List<String> states = new ArrayList<>();

        for (String s : input.split("\t")) {
            banks.add(Integer.parseInt(s));
        }

        boolean ok = false;

        do{
            banks = doRedistribution(banks);

            String latestState = banks.toString();

            System.out.println("Latest state :");
            System.out.println("\t" + latestState);
            System.out.println("-----------------------------------------------------------");

            if(states.contains(latestState)){
                ok = true;
            }else{
                states.add(latestState);
            }
        }while ( ! ok);

        System.out.println("States size : " + states.size());
    }

    static List<Integer> doRedistribution(List<Integer> banks){
        int maxPos = getMaxBankPos(banks);
        int toRedistribute = banks.get(maxPos);
        int rest = toRedistribute-(banks.size()-1);

        if(rest < 0){
            rest = 0;
        }

        banks.set(maxPos, rest);

        for (int i = 1; i <= toRedistribute; i++) {
            int nextPos = (maxPos+i)%banks.size();
            int nextVal = banks.get(nextPos) + (toRedistribute/(banks.size()-1) );

            if(nextVal < 0){
                nextVal = 0;
            }

            banks.set( nextPos,  nextVal);
        }

        System.out.println("-----------------------------------------------------------");
        System.out.println("MaxPos : " + maxPos);
        System.out.println("ToRedistribute : " + toRedistribute);
        System.out.println("banks.size() : " + banks.size());
        System.out.println("Rest : " + rest);


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
