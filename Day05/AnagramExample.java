import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnagramExample {
  public static void main(String[] args) {

    String str[] = {"Apple","Orange","Mango","pAple","elppA","roaneg","Guava","ongam","Jackfruit","elpap","gamno"};
    String[] lowerCaseWords = convertArrayToLowercase(str);
    System.out.println("Lowercase array: " + Arrays.toString(lowerCaseWords));
    String[] sortedWords = sortEachWord(lowerCaseWords);
    System.out.println("Sorted array: " + Arrays.toString(sortedWords));


    Map<String ,Integer> map = new HashMap<String ,Integer>();
    for (int i = 0; i < sortedWords.length; i++) {
      if (map.containsKey(sortedWords[i])) {
        map.put(sortedWords[i], map.get(sortedWords[i]) + 1);
      } else {
        map.put(sortedWords[i], 1);
      }
    }

    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      if(entry.getValue()>1){
        System.out.println(entry.getKey() + " : " + entry.getValue());
      }
      
    }

    
  }
  public static String[] convertArrayToLowercase(String[] words) {
    for (int i = 0; i < words.length; i++) {
        words[i] = words[i].toLowerCase();
    }
    return words;
}
public static String[] sortEachWord(String[] words) {
  String[] sortedWords = new String[words.length];
        
  for (int i = 0; i < words.length; i++) {
      char[] charArray = words[i].toCharArray();
      
      Arrays.sort(charArray);
      
      sortedWords[i] = new String(charArray);
  }
  
  return sortedWords;
}
}


