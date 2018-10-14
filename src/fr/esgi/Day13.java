package fr.esgi;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13 {
    public static void main(String[] args) {

        List<Character[]> firewall = setFirewall(getRealInput());

        System.out.println("Star 1 : Severity : " + getSeverity(firewall, 0).getKey());

        System.out.println("-------------------------------------------------------------------");

        for (int i = 0; i < 1000000000; i++) {
            Pair<Integer, Boolean> star2 = getSeverity(firewall, i);
            System.out.println("Delay : " + i + " star2 : " + star2.getKey());
            if(!star2.getValue()){
                break;
            }
        }
    }

    static Pair<Integer, Boolean> getSeverity(List<Character[]> firewall, int delay){
        int result = 0;
        boolean spotted = false;

        for (int picosecond = 0; picosecond < firewall.size(); picosecond++) {
            int depth = firewall.get(picosecond).length;
            int depthForMod = (depth-1)*2;

            if(depth != 0 && firewall.get(picosecond) != null && (picosecond+delay)%depthForMod == 0){
                result += picosecond*depth;
                System.out.println("SPOTTED !! Picosecond : " + picosecond + ", Depth : " + depth);
                spotted = true;
            }
        }

        return new Pair<>(result, spotted);
    }

    static List<Character[]> setFirewall(String input){
		int inputLength = input.split("\n").length;

		Pattern pattern = Pattern.compile("([0-9]+):\\s+([0-9]+)");
		Matcher matcher = pattern.matcher(input.split("\n")[inputLength-1]);

		int nbLayers = (matcher.find()) ? Integer.parseInt(matcher.group(1)) : 0 ;
		List<Character[]> firewall = new ArrayList<>(Collections.nCopies(nbLayers+1, new Character[0]));

		for (int i = 0; i < inputLength; i++) {
			matcher = pattern.matcher(input.split("\n")[i]);
			if(matcher.find()){
				firewall.set(Integer.parseInt(matcher.group(1)), new Character[Integer.parseInt(matcher.group(2))]);
			}
		}

		return firewall;
	}

    static String getTestInput(){
        return "0: 3\n" +
                "1: 2\n" +
                "4: 4\n" +
                "6: 4";
    }

    static String getRealInput(){
        return "0: 3\n" +
                "1: 2\n" +
                "2: 4\n" +
                "4: 6\n" +
                "6: 4\n" +
                "8: 6\n" +
                "10: 5\n" +
                "12: 6\n" +
                "14: 8\n" +
                "16: 8\n" +
                "18: 8\n" +
                "20: 6\n" +
                "22: 12\n" +
                "24: 8\n" +
                "26: 8\n" +
                "28: 10\n" +
                "30: 9\n" +
                "32: 12\n" +
                "34: 8\n" +
                "36: 12\n" +
                "38: 12\n" +
                "40: 12\n" +
                "42: 14\n" +
                "44: 14\n" +
                "46: 12\n" +
                "48: 12\n" +
                "50: 12\n" +
                "52: 12\n" +
                "54: 14\n" +
                "56: 12\n" +
                "58: 14\n" +
                "60: 14\n" +
                "62: 14\n" +
                "64: 14\n" +
                "70: 10\n" +
                "72: 14\n" +
                "74: 14\n" +
                "76: 14\n" +
                "78: 14\n" +
                "82: 14\n" +
                "86: 17\n" +
                "88: 18\n" +
                "96: 26";
    }
}
