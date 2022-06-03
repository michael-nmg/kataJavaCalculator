import java.io.*;
import java.util.Scanner;
import java.util.stream.*;
import java.util.regex.*;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
//import java.util.regex.Pattern;
//import java.util.function.*;
//import java.util.List;
//import java.util.stream.*;
//import java.util.concurrent.Future;

////////////////////////////////////////////////////////////////////////////////////////

public class Main {
    static interface calcInterface {
        int calc(int x, int y);
    }

    public static void main(String[] args) {
        //System.out.println(romToInt("MMDCLXXXIX"));
        while (true) {
            //System.out.println();
            String scan = new Scanner(System.in).nextLine().trim();
            Matcher matcher = Pattern.compile("\\w+|([+-/*])").matcher(scan);
            String[] arr = new String[3];
            //try catch dlinna massiva
            for (int i = 0; matcher.find(); i++) {
                arr[i] = matcher.group();//.trim();
            }
            //MessageFormat.format("Your text: {0};\nHis length: {1};\nExample: {2}\nExample: {3}",
            System.out.println(calcLamb(arr[1], Integer.parseInt(arr[0]), Integer.parseInt(arr[2])));
        }
    }
    public static int calcLamb(String oper, int x, int y) {
        calcInterface add = (a, b) -> a + b;
        calcInterface sub = (a, b) -> a - b;
        calcInterface mul = (a, b) -> a * b;
        calcInterface div = (a, b) -> a / b;

        switch(oper) {
            case "+":
                return add.calc(x, y);
            case "-":
                return sub.calc(x, y);
            case "*":
                return mul.calc(x, y);
            case "/":
                return div.calc (x, y);
            }
        return 0;//"Bad operator";//try catch
    }

    public static int romToInt (String num) {
        HashMap<String, Integer> romChar = new HashMap<>();
        romChar.put("M", 1000);
        romChar.put("D", 500);
        romChar.put("C", 100);
        romChar.put("L", 50);
        romChar.put("X", 10);
        romChar.put("V", 5);
        romChar.put("I", 1);

        Integer[] array = Arrays.stream(
            num.trim().toUpperCase().replaceAll("\\s*", "").split(""))
            .map(x -> romChar.get(x))
            .toArray(Integer[]::new);

        return IntStream.range(0, array.length - 1).reduce(0, (acc, i) -> {
            acc += (array[i] >= array[i + 1] ? array[i] : -array[i]);
            return acc;
        }) + array[array.length - 1];
    }
}

///////////////////////////////////////////////////////////////////////////////////

public class Test {

   public static void main(String args[]) {
      String Str1 = new String("Добро пожаловать на ProgLang.su");
      char[] Str2 = new char[7];

      try {
         Str1.getChars(2, 9, Str2, 0);
         System.out.print("Скопированное значение: " );
         System.out.println(Str2);

      } catch (Exception ex) {
         System.out.println("Возникает исключение...");
      }
   }
}
