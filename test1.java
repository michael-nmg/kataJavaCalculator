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
import java.util.HashMap;
//import java.util.concurrent.Future;

////////////////////////////////////////////////////////////////////////////////////////

public class Main {
    public static void main (String[] args) {
          //String reg = "\\w+|([+-/*])";
          //Pattern pattern = Pattern.compile("\\w+|([+-/*])");
          HashMap<String, Runnable> hash = new HashMap<>();
          hash.put("+", () -> System.out.println(a + b));
          hash.put("*", () -> System.out.println(a * b));
          hash.put("/", () -> System.out.println(a / b));
          hash.put("-", () -> System.out.println(a - b));

          String scan = new Scanner(System.in).nextLine().trim();
          Matcher matcher = Pattern.compile("\\w+|([+-/*])").matcher(scan);
          ArrayList<String> list = new ArrayList<String>();
          String[] arr = new String[3];
          for (int i = 0; matcher.find(); i++) {
             arr[i] = matcher.group().trim();
          }
          int a = Integer.parseInt(arr[0]);
          int b = Integer.parseInt(arr[2]);
          hash.get(arr[1]).run();
    }
}



////////////////////////////////////////////////////////////////////////////////////////

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String now = scan.nextLine().trim();
        String x = "Hello";
        char[] arr = {};

        String result = MessageFormat.format(
          "Your text: {0};\nHis length: {1};\nExample: {2}\nExample: {3}",
          now, now.length(), now.compareTo(x), now.toCharArray(), now.indexOf('l')
        );

        System.out.println(result);
    }
}

////////////////////////////////////////////////////////////////////////////////////////

public class Result {
    int plus (int[] args) {
        return args[0] + args[1];
    }

    int minus (int[] args) {
        return args[0] - args[1];
    }

    int div (int[] args) {
        return args[0] / args[1];
    }

    int mult (int[] args) {
        return args[0] * args[1];
    }
}

int[] arr = {1,2,34,45};
for (int i = 0; i < arr.length; i++) {
  System.out.println(arr[i]);
}

///////////////////////////////////////////////////////////////////////////////////

function romanNumerals(number) {
    let data = (number + '').split('').reverse();
    let rome = [['I', 'V'], ['X', 'L'], ['C', 'D'], ['M']];
    function f(i, k) {
        let res = '';
        i = i * 1;
        if (i === 9) {
            res = rome[k][0] + rome[k + 1][0];
        } else if (i >= 5) {
            res = i === 5 ? rome[k][1] : rome[k][1] + rome[k][0].repeat(i - 5);
        } else if (i <= 4) {
            res = i === 4 ? rome[k].join('') : rome[k][0].repeat(i);
        }
        return res;
    }
    return data.map(f).reverse().join('');
}


import java.io.*;

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
