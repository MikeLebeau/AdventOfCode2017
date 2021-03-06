package fr.esgi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by LEBEAU Mike
 * ESGI - 5 AL 1
 * on 11/09/2018
 */
public class Day2 {
    public static void main(String args[])
    {
        String input = "798	1976	1866	1862	559	1797	1129	747	85	1108	104	2000	248	131	87	95\n" +
                "201	419	336	65	208	57	74	433	68	360	390	412	355	209	330	135\n" +
                "967	84	492	1425	1502	1324	1268	1113	1259	81	310	1360	773	69	68	290\n" +
                "169 264	107	298	38	149	56	126	276	45	305	403	89	179	394	172\n" +
                "3069	387	2914	2748	1294	1143	3099	152	2867	3082	113	145	2827	2545	134	469\n" +
                "3885	1098	2638	5806	4655	4787	186	4024	2286	5585	5590	215	5336	2738	218	266\n" +
                "661	789	393	159	172	355	820	891	196	831	345	784	65	971	396	234\n" +
                "4095	191	4333	161	3184	193	4830	4153	2070	3759	1207	3222	185	176	2914	4152\n" +
                "131	298	279	304	118	135	300	74	269	96	366	341	139	159	17	149\n" +
                "1155	5131	373	136	103	5168	3424	5126	122	5046	4315	126	236	4668	4595	4959\n" +
                "664	635	588	673	354	656	70	86	211	139	95	40	84	413	618	31\n" +
                "2163	127	957	2500	2370	2344	2224	1432	125	1984	2392	379	2292	98	456	154\n" +
                "271	4026	2960	6444	2896	228	819	676	6612	6987	265	2231	2565	6603	207	6236\n" +
                "91	683	1736	1998	1960	1727	84	1992	1072	1588	1768	74	58	1956	1627	893\n" +
                "3591	1843	3448	1775	3564	2632	1002	3065	77	3579	78	99	1668	98	2963	3553\n" +
                "2155	225	2856	3061	105	204	1269	171	2505	2852	977	1377	181	1856	2952	2262\n";

        String[] inputArray = input.split("\\n");

        int[] diffArray = new int[inputArray.length];

        int result = 0;

        for(int i = 0; i < inputArray.length; i++){ // i => actual row
//            diffArray[i] = getDiff(inputArray[i]);
            diffArray[i] = newGetDiff(inputArray[i]);
        }

        for(Integer oneValue : diffArray){
            result += oneValue;
        }

        System.out.println(result);
    }

    //For part one
    static int getDiff(String row){
        return getDiff(row, null, null);
    }

    static int getDiff(String row, Integer max, Integer min){
        for(String num : row.split("\\s+")){ // j => actual column
            int actualValue = Integer.parseInt(num);

            if(min == null && max == null){
                min = actualValue;
                max = actualValue;
            }

            if(max < actualValue){
                max = actualValue;
            }

            if(min > actualValue){
                min = actualValue;
            }
        }

        System.out.println("\tMin => " + min);
        System.out.println("\tMax => " + max);

        return max - min;
    }

    // For part two
    static int newGetDiff(String row){
        List<Integer> asc = new ArrayList<>();
        List<Integer> desc = new ArrayList<>();

        for(String num : row.split("\\s+")) { // j => actual column
            asc.add(Integer.parseInt(num));
            desc.add(Integer.parseInt(num));
        }

        Collections.sort(asc);
        Collections.sort(desc);
        Collections.reverse(desc);

        for (int i = 0; i < desc.size(); i++) {
            for (int j = 0; j < asc.size(); j++) {
                if(!desc.get(i).equals(asc.get(j)) && desc.get(i)%asc.get(j) == 0){
                    System.out.println("Result => " + desc.get(i) + "/" + asc.get(j) + " = " + (desc.get(i) / asc.get(j)));
                    return desc.get(i) / asc.get(j);
                }
            }
        }

        return -1;
    }
}
