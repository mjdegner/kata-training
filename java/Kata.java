import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Kata {
  
  public static void main(String[] args) {
    System.out.println("Kata Training!");

    System.out.println(bmi(80.0, 1.8));
  }

  public static String bmi(double w, double h) {
    double bmi = w/(h*h);
    
    if(bmi <= 18.5) return "Underweight";
    if(bmi <= 25.0) return "Normal";
    if(bmi <= 30)   return "Overweight";
    return "Obese";
  }

  public static int[] deleteNth(int[] elements, int max) {
    if(max < 1) return new int[] {};

    Map<Integer, Integer> distinct = new HashMap<>();

    for (int i = 0; i < elements.length; i++) {
      int e = elements[i];
      int v = distinct.put(e, distinct.getOrDefault(e,0) + 1);
      if(v > max) elements[i] = -1;
    }

		return Arrays.stream(elements).filter(e -> e > 0).toArray();
	}

   public static int[] deleteNth2(int[] elements, int max) {
    if(max < 1) return new int[] {};

    Map<Integer, Integer> distinct = new HashMap<>();
    List<Integer> values = new ArrayList<>();

    for (Integer i : elements) {
      Integer v = distinct.put(i, distinct.getOrDefault(i,0) + 1);
      if(v == null || v < max) values.add(i);
    }

		return values.stream().mapToInt(i -> i).toArray();
	}


  static String encode(String s, int n) {
    String[] rows = new String[n];
    Arrays.fill(rows, "");

    int currentRow = 0;
    int direction = 1;

    for (char c : s.toCharArray()) {
      rows[currentRow] += String.valueOf(c);

      if(currentRow == 0) direction = 1;
      else if(currentRow == n-1) direction = -1;

      currentRow += direction;
    }

    return String.join("", rows);
  }
  
  static String decode(String s, int n) {
    String[] decoded = new String[s.length()];
    int[][] spaces = calcRowSpaces(n);

    int offset = 0;
    for (int column = 0; column < n; column++) {
      int currentIndex = column;
      int nextRow = 0;

      while(currentIndex < s.length()) {
        if(nextRow == 2) nextRow = 0;

        decoded[currentIndex] = ""+s.charAt(offset);
        currentIndex += spaces[column][nextRow++];
        offset++;
      }
    }

    return String.join("", decoded);
  }


  static int[][] calcRowSpaces(int n) {
    int[][] rowSpaces = new int[n][2];
    int baseSpace = n + (n-2);

    for (int i = 0; i < n; i++) {
      int rowSpace = baseSpace - (i * 2);
      rowSpaces[i][0] = rowSpace == 0 ? baseSpace : rowSpace;
      rowSpaces[i][1] = baseSpace == rowSpace ? rowSpace : baseSpace - rowSpace ;
    }

    return rowSpaces;
  }

  public static int roundToNext5(int n) {
    return n % 5 == 0 ? n : n + (5 - (n % 5));
  }

  public static int duplicateCount(String text) {
    int duplicates = 0;
    for (int i : text.toLowerCase().chars().distinct().toArray()) {
      var count = text.toLowerCase().chars().filter(ch -> ch == i).count();
      if(count > 1) duplicates++;
    }

    return duplicates;
  }

  static String alphabetPosition3(String text) {
    return text.chars()
              .filter(Character::isLetter)
              .map(Character::toLowerCase)
              .map(c -> c - 'a' + 1)
              .mapToObj(String::valueOf)
              .collect(Collectors.joining(" "));
  }

  static String alphabetPosition(String text) {
    char[] chars = text.replaceAll("[^\\p{L}]+","").toLowerCase().toCharArray();
    String[] result = new String[chars.length];

    for (int i = 0; i < chars.length; i++) 
      result[i] = String.valueOf(chars[i] - 96);

    return String.join(" ", result);
  }

  static String alphabetPosition2(String text) {
    int[] result =  text.replaceAll("[^\\p{L}]+","").toLowerCase().codePoints().map(c -> Integer.valueOf(c) - 96).toArray();
    return String.join(" ", String.valueOf(result));
  }

  public static int strCount(String str, char letter) {
    return str.length() - str.replaceAll(""+letter, "").length();
  }

  public static int[] DataReverse(int[] data) {
    int[] newData = new int[data.length];
    int bytes = data.length/8;

    for (int i = 0; i < bytes; i++) {
      for (int j = 0; j < 8; j++) {
        newData[i*8 + j] = data[(bytes-1-i)*8 + j];
      }
    }

    return newData;
  }

  public static int[] DataReverse2(int[] data) {
    int bytes = data.length/8;
    String bits = String.valueOf(data);

    for (int i = 0; i < bytes; i++) {
      bits.substring(i*8,8+i*8);
    }

    return data;
  }

  public static String meeting(String s) {
    String[] names = s.split(";");

    for (int i = 0; i < names.length; i++) {
      String[] parts = names[i].toUpperCase().split(":",2);
      names[i] = "(" + parts[1] + ", " + parts[0] + ")";

      // guests[i]  = guests[i].replaceAll("(^\\w+):(\\w+$)", "($2, $1)"); 
    }
    Arrays.sort(names);

    return String.join("", names);
  }

  public static String meeting2(String s) {
    return Arrays.stream(s.split(";")).map(name -> {
            String[] parts = name.toUpperCase().split(":", 2);
            return "(" + parts[1] + ", " + parts[0] + ")";
          })
          .sorted()
          .collect(Collectors.joining(""));
  }

  public static String meeting3(String s) {
    return Arrays.stream(s.split(";"))
            .map(name -> name.toUpperCase().split(":", 2))
            .map(parts -> "(" + parts[1] + ", " + parts[0] + ")")
            .sorted()
            .collect(Collectors.joining(""));
  }

  public static String getDay(int n) {
    // return
    return switch (n) {
      case 1 -> "x";
      default -> "";
    };
  }

  public static int dutyFree(int normPrice, int discount, int hol) {
    return hol * 100 / normPrice * discount;
  }

  public static String peopleWithAgeDrink(int age){
    if(age < 14) return "drink toddy";
    if(age < 18) return "drink coke";
    if(age < 21) return "drink beer";
    return "drink whisky";
  }

  public static int largestPairSum(int[] numbers){
    int max1 = Integer.MIN_VALUE;
    int max2 = Integer.MIN_VALUE;

    for (int num : numbers) {
      if(num > max1) {
        max2 = max1;
        max1 = num;
      }
      else if(num > max2) {
        max2 = num;
      }
    }
      
    return max1 + max2;
  }

  public static String isSortedAndHow(int[] array) {

    boolean isAscending = true, isDescending = true;

    for (int i = 0; i < array.length-1; i++) {
      if(array[i] < array[i+1]) isDescending = false;
      if(array[i] > array[i+1]) isAscending = false;
    }

    if(isAscending) return "yes, ascending";
    if(isDescending) return "yes, descending";

    return "no";
  }

  public static int mango(int quantity, int price){
    return (quantity - (quantity % 3)) * price;
  }

  public static int binToDecimal(String inp){
    return Integer.parseInt(inp, 10);
  }

  public static int findAverage(int[] nums) {
    int sum = 0;

    for (int i = 0; i < nums.length; i++) 
      sum += nums[i];

    return sum/nums.length;
  }

  public static int[] rowWeights (final int[] weights) {
    int t1 = 0, t2 = 0;
    for (int i = 0; i < weights.length; i++) {
      if(i % 2 == 0) t1 += weights[i];
      else t2 += weights[i];
    }

    return new int[]{t1, t2};
  }

  public static int factorial(int n) {
    if(n < 0 || n > 12) throw new IllegalArgumentException();

    int result = 1;
    for (int i = 2; i <= n; i++) result *= i;

    return result;
  }

  public static String warnTheSheep(String[] a) {
    
    for (int i = 0; i < a.length-1; i++) 
      if("wolf".equals(a[i])) return "Oi! Sheep number "+(a.length-i-1)+"! You are about to be eaten by a wolf!";
    return "Pls go away and stop eating my sheep";
  }


  private record MinMax(int min, int max) {}

  public static int mxdiflg(String[] a1, String[] a2) {
    if(a1.length <= 0 || a2.length <= 0) return -1;

    var a = getMinAndMax(a1);
    var b = getMinAndMax(a2);

    return Math.max(Math.abs(b.max - a.min), Math.abs(a.max - b.min));
  }

  public static MinMax getMinAndMax(String[] a) {
    int min = Integer.MAX_VALUE;
    int max = 0;

    for (String s : a) {
      int len = s.length();
      max = Math.max(len, max);
      min = Math.min(len, min);
    }

    return new MinMax(min, max);
  }

  public static int[] getMinAndMax2(String[] a) {

    Stream<Integer> stream = Arrays.stream(a).map(i -> i.length());
    int max = stream.max(Integer::compare).get();
    int min = stream.min(Integer::compare).get();

    return new int[]{min, max};
  }

  public static int mxdiflg2(String[] a1, String[] a2) {
    if(a1.length <= 0 || a2.length <= 0) return -1;

    // List<String> y = new ArrayList<>(List.of(a1));
    Stream<Integer> stream1 = Arrays.stream(a1).map(i -> i.length());
    int max1 = stream1.max(Integer::compare).get();
    int min1 = stream1.min(Integer::compare).get();

    Stream<Integer> stream2 = Arrays.stream(a2).map(i -> i.length());
    int max2 = stream2.max(Integer::compare).get();
    int min2 = stream2.min(Integer::compare).get();

    return 0;
  }

  public static List<String> friend(List<String> x){
    List<String> y = new ArrayList<>(x);
    y.removeIf(name -> name.length() != 4);

    System.out.println(y);

     return y;
  }

  public static List<String> friend2(List<String> x){
    List<String> y = new ArrayList<>(x);
    Iterator<String> it = y.iterator();
    while(it.hasNext()) {
      String friend = it.next();
      if(friend.length() != 4) it.remove();
    }

    System.out.println(y);


     return y;
  }

  public static List<String> friend3(List<String> x){
    return x.stream().filter(name -> name.length() != 4).toList();
  }

  public static int sumDigits(int number){
    int result = 0;
    var values = String.valueOf(Math.abs(number)).split("");
    for (int i = 0; i < values.length; i++) result += Integer.parseInt(values[i]);
    
    return result;
  }

  public static int sumDigits3(int number){
    int result = 0;
    var values = String.valueOf(Math.abs(number)).chars().map(null).sum();
    
    return result;
  }

  public static int sumDigits2(int number){
    int sum = 0;
    number = Math.abs(number);

    while(number > 0) {
      sum += number % 10;
      number /= 10;
    }

    return sum;
  }


  public static long[] powersOfTwo(int n){
    long[] res = new long[n+1];
    for (int i = 0; i < n+1; i++) res[i] = (long) Math.pow(2, i);
    return res;
  }

  public static int nbYear(int p0, double percent, int aug, int p) {
    int years = 0;
    for (; p0 < p; years++) p0 += p0 * percent / 100 + aug;
    return years;
  }

  public static int[][] Spiralize(int n)
  {
    int[][] result = new int[n][n];

    int top = 0;
    int bottom = n - 1;
    int left = 0;
    int right = n - 1;

    while (top <= bottom && left <= right)
    {
      for (int i = left; i <= right; i++) result[top][i] = 1;

      if (top > bottom) break;
      for (int i = top; i < bottom; i++) result[i][ right] = 1;
      
      if(left % 2 == 1) left++;

      if (right < left) break;
      for (int i = right; i > left; i--) result[bottom][ i] = 1;

      if (bottom < top+1) break;
      for (int i = bottom; i > top+1; i--) result[i][ left] = 1;

      top+=2;
      bottom-=2;
      left++;
      right-=2;
    }
    return result;
  }

  
}

