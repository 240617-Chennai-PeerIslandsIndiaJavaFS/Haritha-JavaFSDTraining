import java.util.HashMap;
import java.util.Map;

public class HashMapEX {
  public static void main(String[] args) {
            Map<String, Integer> map = new HashMap<String, Integer>();
            String str = "Hi i am haritha";
            String str1[]=str.split(" ");
            for (int i = 0; i < str1.length; i++) {
              int count =0;
              for(int j=0;j<str1[i].length();j++) {
                count++;


              }
              map.put(str1[i],count);


            }
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
              System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
          }


    
  }
  
}
