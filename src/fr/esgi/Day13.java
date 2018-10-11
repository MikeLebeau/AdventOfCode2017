package fr.esgi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13 {
    public static void main(String[] args) {

        String input = getTestInput();

        List<Character[]> firewall = setFirewall(input);

        System.out.println("Firewall size : " + firewall.size());

		for (int i = 0; i < firewall.size(); i++) {
			System.out.println(i + " : " + Arrays.toString(firewall.get(i)));
		}

		// Order -> I move then Scanner move
        for (int i = 1; i < firewall.size(); i++) {

            int depthForMod = (firewall.get(i).length != 0) ? (firewall.get(i).length-1)*2 : 0;

            System.out.println("Picosecond " + i );
            if(firewall.get(i).length != 0 && depthForMod != 0){
                System.out.println(
                        "\tScanner pos : " + (i+1)%depthForMod + "\n" +
                        "\tDepth : " + firewall.get(i).length
                );
            }else{
                System.out.println(
                        "\tScanner pos : NONE\n" +
                        "\tDepth : " + firewall.get(i).length
                );
            }

        }

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
