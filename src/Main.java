import com.sun.javafx.scene.traversal.SubSceneTraversalEngine;

import java.io.*;
import java.util.*;

public class Main {

    public static Set<String> names = new HashSet<>();
    public static void main(String[] args)
    {
        File file = new File("names.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while((st = br.readLine()) != null){
                names.add(st);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int  countSpecificString(String specificString){
        int counterAppearance = 0;
        for(String name : names){
            if(name.contains(specificString))
                counterAppearance = counterAppearance +1;
        }
        return counterAppearance;
    }
    public static HashMap<String, Integer> countAllStrings(int length)
    {
       return countAllStringsHelp(length, true);
    }

    public static HashMap<String, Integer> countAllStringsHelp(int length, boolean isCaseS)
    {
        HashMap<String, Integer> numAppear = new HashMap<>();
        Set<String> namesTemp = new HashSet<>();
        if(!isCaseS){
            for(String name: names)
                namesTemp.add(name.toLowerCase());
        }
        else
            namesTemp = names;

        for(String name: namesTemp){
            for (int i = 0; i < name.length()-length + 1 ; i++) {
                String substring = name.substring(i, length + i);
                if (numAppear.containsKey(substring))
                    numAppear.put(substring, numAppear.get(substring) + 1);
                else
                    numAppear.put(substring, 1);
            }
        }
        return numAppear;
    }

    public static List<String> countMaxString(int length){
        HashMap<String, Integer> appearanceMap = countAllStringsHelp(length, false);
        return countMaxStringHelp(appearanceMap, length);
    }

    public static List<String> countMaxStringHelp(HashMap<String, Integer> appearanceMap, int length){
        List<String> mostCommon = new LinkedList<>();
        int most = 0 ;
        for(Integer count : appearanceMap.values()){
            if (count > most)
                most = count;
        }
        for(String str: appearanceMap.keySet()){
            if(appearanceMap.get(str) == most)
                mostCommon.add(str);
        }
        return mostCommon;
    }
    public static List<String> allIncludesString(String string){
        String string_ci = string.toLowerCase();
        List<String> namesInStr = new LinkedList<>();
        for(String name: names){
            if(string_ci.contains(name.toLowerCase()))
                namesInStr.add(name.toLowerCase());
        }
        return namesInStr;
    }

    public static String generateName(){
        return "";
    }

    public static String findHighestProbLetter(){
        HashMap<String,Integer> lettersCount = countAllStrings(1);
        //HashMap<String, Integer> firstLetter =
        for(String letter: lettersCount.keySet()){

        }
        return "";
    }
}
