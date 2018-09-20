package fr.esgi.Day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {
    public static void main(String[] args) {

        String input = "pbga (66)\n" +
                "xhth (57)\n" +
                "ebii (61)\n" +
                "havc (66)\n" +
                "ktlj (57)\n" +
                "fwft (72) -> ktlj, cntj, xhth\n" +
                "qoyq (66)\n" +
                "padx (45) -> pbga, havc, qoyq\n" +
                "tknk (41) -> ugml, padx, fwft\n" +
                "jptl (61)\n" +
                "ugml (68) -> gyxo, ebii, jptl\n" +
                "gyxo (61)\n" +
                "cntj (57)";

        /*
            Group 1 = ([a-z]+)        => To get the name
            Group 2 = \(([0-9]+)\)    => To get the value
            Group 3 = ([a-z].*[a-z])  => To get child
         */

        Pattern pattern = Pattern.compile("([a-z]+).*\\(([0-9]+)\\) -> ([a-z].*[a-z])");
        Matcher matcher;

        Map<String, Node> nodeMap = new HashMap<>();

        for (String s : input.split("\n")) {
            matcher = pattern.matcher(s);
            Node newNode = new Node(
                    matcher.group(1),
                    Integer.parseInt(matcher.group(2))
                );

            if(matcher.groupCount() == 4){
                nodeMap.put(matcher.group(3), newNode);
            }else{
                nodeMap.put("", newNode);
            }
        }

        for (Map.Entry<String, Node> stringNodeEntry : nodeMap.entrySet()) {
            if( ! stringNodeEntry.getKey().equals("")){
                // LOL I'm not about what i'm doing x'D
            }
        }

    }
}
