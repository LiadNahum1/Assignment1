import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static List<String> names = new LinkedList<>();
    public static void main(String[] args)
    {

    }

    public static int  CountSpecificString(String specificString){
        int counterAppearance = 0;
        for(String name : names){
            if(name.contains(specificString))
                counterAppearance = counterAppearance +1;
        }
        return counterAppearance;
    }
    public static HashMap<String, Integer>  CountAllStrings( int length)
    {
        HashMap<String, Integer> numAppear = new HashMap<>();
        for(String name: names){
            if(name.length() >= length) {
                for (int i = 0; i < name.length(); i++) {
                    String substring = name.substring(i, length+i);
                    if(numAppear.containsKey(substring))
                        numAppear.put(substring, numAppear.get(substring) + 1);
                    else
                        numAppear.put(substring, 1);
                }
            }
        }
        return numAppear;
    }
/*
    public static List<String> getMostCommonStringsInNLen(List<String> names, int n){
        HashMap<String, Integer> appearanceMap = getNumStringsInNLen(names, n);
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

    public static List<String> getAllNamesInStr(List<String> names, String str){
        List<String> namesInStr = new LinkedList<>();
        for(String name: names){
            if(str.contains(name))
                namesInStr.add(name);
        }
        return namesInStr;
    }*/
}
