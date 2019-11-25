import java.io.*;
import java.util.*;


public class Main {

    public static String FILE_NAME = "names.txt";
    public static int ENGLISH_ALPHABET_LENGTH = 26;
    public static char A_ASCII_VALUE = 97;
    public static Set<String> names = new HashSet<>();
    public static int longestLength = 0;
    public static void main(String[] args)
    {
        loadNamesFromFile();
        switch(args[0]){
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

    public static void loadNamesFromFile(){
        File namesFile = new File(FILE_NAME);
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(namesFile));
            String name = fileReader.readLine();
            while(name != null){
                if(longestLength < name.length())
                    longestLength = name.length();
                names.add(name);
                name = fileReader.readLine();
            }

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static int countSpecificString(String specificString){
        int counterAppearance = 0;
        for(String name : names){
            if(name.contains(specificString))
                counterAppearance++;
        }
        return counterAppearance;
    }

    public static HashMap<String, Integer> countAllStrings(int substringLength) {
       return countAllStringsByCaseSensitive(substringLength, names);
    }
    public static HashMap<String, Integer> countAllStringsByCaseSensitive(int substringLength, Set<String> names) {
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
    private static void printStringAndCounter(HashMap<String, Integer> counterPerString) {
        for(Map.Entry<String, Integer> entry: counterPerString.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    public static List<String> countMaxString(int length){
        Set<String> namesCaseInsensitive = new HashSet<>();
        for (String name: names)
            namesCaseInsensitive.add(name.toLowerCase());
        HashMap<String, Integer> appearanceMap = countAllStringsByCaseSensitive(length, namesCaseInsensitive);
        return mostCommonKey(appearanceMap);
    }
    public static List<String> mostCommonKey(HashMap<String, Integer> appearanceMap){
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

    private static void printResultedList(List<String> resultedList) {
        for(String string: resultedList){
            System.out.println(string);
        }
    }

    public static String generateName(){
        String generated = "";
        char previousLetter = '\0';
        for (int index = 0; index < longestLength; index++){
            generated += findHighestProbabilityLetterByIndex(index, previousLetter);
            previousLetter = generated.charAt(index);
        }
        generated = generated.toUpperCase().charAt(0) + generated.substring(1);
        return generated;
    }
    public static String findHighestProbabilityLetterByIndex(int indexInString, char previousLetter){
        HashMap<String, Integer> charsAppearence = new HashMap<>();
        for (int index = 0; index < ENGLISH_ALPHABET_LENGTH; index++){
            charsAppearence.put(String.valueOf((char)(index + A_ASCII_VALUE)), 0);
        }
          for (String name : names) {
              name = name.toLowerCase();
              if (name.length() > indexInString) {
                  if (indexInString == 0 || name.charAt(indexInString - 1) == previousLetter) {
                      char current = name.charAt(indexInString);
                      String currentChar = String.valueOf(current);
                      charsAppearence.put(currentChar, charsAppearence.get(currentChar) + 1);
                  }
              }
          }

        return mostCommonKey(charsAppearence).get(0);
    }
}
