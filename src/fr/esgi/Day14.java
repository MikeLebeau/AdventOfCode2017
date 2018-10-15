package fr.esgi;

public class Day14 {
    public static void main(String[] args) {

        String input = "wenycdww";

        String[] rows = new String[128];

        String star1 = "";

        for (int i = 0; i < 128; i++) {
            rows[i] = Day10.doKnotHash(input + "-" + i);

            for (int j = 0; j < rows[i].length(); j++) {
                int value = Integer.valueOf(rows[i].charAt(j) + "", 16);
                star1 += String.format("%04d", Integer.parseInt(Integer.toBinaryString(value)));
            }
        }

        System.out.println("Number of 1 : " + star1.replace("0", "").length());
    }
}
