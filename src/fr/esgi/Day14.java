package fr.esgi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        System.out.println("Input : " + star1);
        System.out.println("Number of 1 : " + star1.replace("0", "").length());

        Map<Integer, List<Point>> groups = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {
            isInKnownGroup(groups, 128, i);
        }

        System.out.println("Number of groups : " + groups.size());

        List<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);

        List<Integer> test2 = new ArrayList<>();
        test2.add(3);
        test2.add(34);

        System.out.println("Contains All : " + containAtLeastOne(test, test2));

    }

    static <T> boolean containAtLeastOne(List<T> groups, List<T> others){
        for (T other : others) {
            if(groups.contains(other)){
                return true;
            }
        }
        return false;
    }

    static boolean isInKnownGroup(Map<Integer, List<Point>> groups, int numberOfElementPerLine, int actualPos){
        Point rookie = calculatePoint(numberOfElementPerLine, actualPos);
        List<Point> rookieNeighbors = getNeighborsPoint(numberOfElementPerLine, actualPos);

        boolean result = false;

        if(groups.size() == 0){
            groups.put(groups.size(), new ArrayList<>());
            groups.get(groups.size()-1).add(rookie);
            return false;
        }

        for (Map.Entry<Integer, List<Point>> group : groups.entrySet()) {
            if(containAtLeastOne(group.getValue(), rookieNeighbors)){
                groups.get(group).add(rookie);
                result = true;
            }else{
                groups.put(groups.size(), new ArrayList<>());
                groups.get(groups.size()-1).add(rookie);
            }
        }

        return result;
    }

    static List<Point> getNeighborsPoint(int numberOfElementPerLine, int actualPos){
        List<Point> neighbors = new ArrayList<>(4);

        neighbors.add(calculatePoint(numberOfElementPerLine, actualPos-numberOfElementPerLine));
        neighbors.add(calculatePoint(numberOfElementPerLine, actualPos+1));
        neighbors.add(calculatePoint(numberOfElementPerLine, actualPos+numberOfElementPerLine));
        neighbors.add(calculatePoint(numberOfElementPerLine, actualPos-1));

        return neighbors;

    }

    static Point calculatePoint(int numberOfElementPerLine, int actualPos){
        int y = (actualPos%numberOfElementPerLine)-1 < 0 ? 0 : (actualPos%numberOfElementPerLine)-1;
        int x = (actualPos-y)/numberOfElementPerLine;
        return new Point(x, y);
    }

    static boolean hasNeighbor(int numberOfElementPerLine, String input, int actualPos){
        // Up       = -16
        if(actualPos-numberOfElementPerLine >= 0 && input.charAt(actualPos-numberOfElementPerLine) == '1') {
            return true;
        }
        // Right    = +1
        if(actualPos+1 < input.length() && input.charAt(actualPos+1) == '1'){
            return true;
        }
        // Down     = +16
        if(actualPos+numberOfElementPerLine < input.length() && input.charAt(actualPos+numberOfElementPerLine) == '1'){
            return true;
        }
        // Left     = -1
        if(actualPos-1 >= 0 && input.charAt(actualPos-1) == '1'){
            return true;
        }

        return false;
    }
}

class Point{
    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
