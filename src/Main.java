import java.io.*;
import java.util.*;


public class Main {

    public static Set<String> n = new HashSet<>();
    public static int longest_length = 0;
    public static void main(String[] args)
    {
        loadNamesFromFile();
        try {
            switch (args[0]) {
                case "CountSpecificString":
                    System.out.println(countSpecificString(args[1]));
                    break;
                case "CountAllStrings":
                    printStringAndCounter(countAllStrings(Integer.parseInt(args[1])));
                    break;
                case "CountMaxString":
                    printResultedList(countMaxString(Integer.parseInt(args[1])));
                    break;
                case "AllIncludesString":
                    printResultedList(allIncludesString(args[1]));
                    break;
                case "GenerateName":
                    System.out.println(generateName());
                    break;
                default:
                    System.out.println("Invalid function");
            }
        }
        catch(Exception exception)
        {
            System.out.println("Invalid arguments");
        }
    }

    public static void loadNamesFromFile(){
        File f = new File("n.txt");
        try {
            BufferedReader fr = new BufferedReader(new FileReader(f));
            String name = fr.readLine();
            while(name != null){
                if(longest_length < name.length())
                    longest_length = name.length();
                n.add(name);
                name = fr.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int countSpecificString(String specificString){
        int counterAppearance = 0;
        for(String name : n) {
            if(name.contains(specificString))
                counterAppearance++;
        }
        return counterAppearance;
    }

    public static HashMap<String, Integer> countAllStrings(int substringLength) {
       return funcHelp(substringLength, n);
    }
    public static HashMap<String, Integer> funcHelp(int substringLength, Set<String> names) {
        HashMap<String, Integer> appearencePerStr = new HashMap<>();
        for(String name: names){
            for (int i = 0; i < name.length() - substringLength + 1 ; i++) {
                String substring = name.substring(i, substringLength + i);
                if (appearencePerStr.containsKey(substring))
                    appearencePerStr.put(substring, appearencePerStr.get(substring) + 1);
                else
                    appearencePerStr.put(substring, 1);
            }
        }
        return appearencePerStr;
    }
    private static void printStringAndCounter(HashMap<String, Integer> counterPerString) {
        for(Map.Entry<String, Integer> entry: counterPerString.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    public static List<String> countMaxString(int length){
        Set<String> namesCaseInsensitive = new HashSet<>();
        for (String name: n)
            namesCaseInsensitive.add(name.toLowerCase());
        HashMap<String, Integer> appearanceMap = funcHelp(length, namesCaseInsensitive);
        return mostCommonKey(appearanceMap);
    }
    public static List<String> mostCommonKey(HashMap<String, Integer> appearanceMap){
        List<String> mostCommonStr = new LinkedList<>();
        int most = 0 ;
        for(Integer counter : appearanceMap.values()){
            if (counter > most)
                most = counter;
        }
        for(String substringOfSizeLength: appearanceMap.keySet()){
            if(appearanceMap.get(substringOfSizeLength) == most)
                mostCommonStr.add(substringOfSizeLength);
        }
        return mostCommonStr;
    }

    public static List<String> allIncludesString(String string){
        String stringCaseInsensitive = string.toLowerCase();
        List<String> namesInGivenString = new LinkedList<>();
        for(String name: n){
            if(stringCaseInsensitive.contains(name.toLowerCase()))
                namesInGivenString.add(name.toLowerCase());
        }
        return namesInGivenString;
    }

    private static void printResultedList(List<String> resultedList) {
        for(String string: resultedList){
            System.out.println(string);
        }
    }

    public static String generateName(){
        String generated = "";
        char previousLetter = '\0';
        while(true){
            String nextChar= findHighestProbabilityLetterByIndex(previousLetter);
            if(!generated.contains(nextChar)){
                generated += nextChar;
                previousLetter = generated.charAt(generated.length() - 1);
            }
            else break;
        }
        generated = generated.toUpperCase().charAt(0) + generated.substring(1);
        return generated;
    }
    public static String findHighestProbabilityLetterByIndex(char previousLetter){
        HashMap<String, Integer> charsAppearence = new HashMap<>();
        for (int index = 0; index < 26; index++){
            charsAppearence.put(String.valueOf((char)(index + 97)), 0);
        }
        for (int index = 0;  index < 26; index++){
            charsAppearence.put(String.valueOf((char)(index + 65)), 0);
        }
          for (String name : n) {
              if(previousLetter == '\0'){
                  String currentChar = String.valueOf(name.charAt(0));
                  charsAppearence.put(currentChar, charsAppearence.get(currentChar) + 1);
              }
              else {
                  String substringName = name;
                  while(substringName.length() > 0 && substringName.contains(String.valueOf(previousLetter))){
                      int indexOfPreviousLetter = substringName.indexOf(previousLetter);
                      if (substringName.length() > indexOfPreviousLetter + 1) {
                          String currentChar = String.valueOf(name.charAt(indexOfPreviousLetter + 1));
                          if(charsAppearence.containsKey(currentChar))
                              charsAppearence.put(currentChar, charsAppearence.get(currentChar) + 1);
                          substringName = substringName.substring(indexOfPreviousLetter + 1);
                      }
                      else
                          substringName = "";
                  }
              }
          }
        return mostCommonKey(charsAppearence).get(0);
    }
}
