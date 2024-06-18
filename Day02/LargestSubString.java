public class LargestSubString {
  public static void main(String[] args) {
      String str = "abcdabcfabc";
      int len = lengthOfLongestSubstring(str);
      System.out.println(len); 
  }

  public static int lengthOfLongestSubstring(String s) {
    int max = 0;
    int start = 0, end = 0;
    int[] chars = new int[128]; 

    while (end < s.length()) {
        char right = s.charAt(end);
        chars[right]++;

        while (chars[right] > 1) {
            chars[s.charAt(start)]--;
            start++;
        }

        max = Math.max(max, end - start + 1);

        end++;
    }

    return max;
  }
}
