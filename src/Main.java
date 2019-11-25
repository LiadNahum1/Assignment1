import java.io.*;
import java.util.*;


public class Main {

    public static String FILE_NAME = "names.txt";
    public static Set<String> names = new HashSet<>();
    public static void main(String[] args)
    {
        File namesFile = new File(FILE_NAME);
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(namesFile));
            String name = fileReader.readLine();
            while(name != null){
                names.add(name);
                name = fileReader.readLine();
            }

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static int  countSpecificString(String specificString){
        int counterAppearance = 0;
        for(String name : names){
            if(name.contains(specificString))
                counterAppearance++;
        }
        return counterAppearance;
    }
    public static HashMap<String, Integer> countAllStrings(int substringLength)
    {
       return countAllStringsByCaseSensitive(substringLength, names);
    }

    public static HashMap<String, Integer> countAllStringsByCaseSensitive(int substringLength, Set<String> names)
    {
        HashMap<String, Integer> appearencePerString = new HashMap<>();
        for(String name: names){
            for (int index = 0; index < name.length() - substringLength + 1 ; index++) {
                String substring = name.substring(index, substringLength + index);
                if (appearencePerString.containsKey(substring))
                    appearencePerString.put(substring, appearencePerString.get(substring) + 1);
                else
                    appearencePerString.put(substring, 1);
            }
        }
        return appearencePerString;
    }

    public static List<String> countMaxString(int length){
        Set<String> namesCaseInsensitive = new HashSet<>();
        for (String name: names)
            namesCaseInsensitive.add(name.toLowerCase());
        HashMap<String, Integer> appearanceMap = countAllStringsByCaseSensitive(length, namesCaseInsensitive);
        return mostCommonKey(appearanceMap, length);
    }

    public static List<String> mostCommonKey(HashMap<String, Integer> appearanceMap, int length){
        List<String> mostCommonStrings = new LinkedList<>();
        int most = 0 ;
        for(Integer counter : appearanceMap.values()){
            if (counter > most)
                most = counter;
        }
        for(String substringOfSizeLength: appearanceMap.keySet()){
            if(appearanceMap.get(substringOfSizeLength) == most)
                mostCommonStrings.add(substringOfSizeLength);
        }
        return mostCommonStrings;
    }
    public static List<String> allIncludesString(String string){
        String stringCaseInsensitive = string.toLowerCase();
        List<String> namesInGivenString = new LinkedList<>();
        for(String name: names){
            if(stringCaseInsensitive.contains(name.toLowerCase()))
                namesInGivenString.add(name.toLowerCase());
        }
        return namesInGivenString;
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
