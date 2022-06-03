import java.io.*;
import java.util.Scanner;
import java.util.regex.*;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.ArrayList;
//import java.util.regex.Pattern;
//import java.util.function.*;
//import java.util.List;
//import java.util.stream.*;
//import java.util.concurrent.Future;
import java.util.HashMap;

////////////////////////////////////////////////////////////////////////////////////////

public class Main {
    static interface calcInterface {
        int calc(int x, int y);
    }

    public static void main(String[] args) {
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
