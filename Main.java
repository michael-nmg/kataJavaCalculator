import java.io.*;
import java.util.Scanner;
import java.util.stream.*;
import java.util.regex.*;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
//import java.util.Exeption;
//import java.util.regex.Pattern;
//import java.util.function.*;
//import java.util.stream.*;
//import java.util.concurrent.Future;

///////////////////////////////////////////////////////////////

public class Main {
    static interface calcInterface {
        int calcOp(int x, int y);
    }

    public static String calc(String input) throws CalcExceptions {
        Matcher matcher = Pattern.compile("\\w+|([+-/*])").matcher(input);
        String[] arr = new String[3];

      try {
          for (int i = 0; matcher.find(); i++) {
            arr[i] = matcher.group();//.trim();
          }
        } catch (ArrayIndexOutOfBoundsException excep) {
          System.out.println("\n\n\tOnly binary operations are possible.\n\n");
          //break;
        }



      int var1 = Integer.parseInt(arr[0]);
      int var2 = Integer.parseInt(arr[2]);

      if (!(arr.length == 3)) {
        throw new CalcExceptions("");
      } else if (!isOper(arr[1])) {
        throw new CalcExceptions("\n\n\tThe operation is not defined.\n\tPlease, use the available: +, -, *, /\n\n");
      } else if (isDig(arr[0]) ^ isDig(arr[2])) {
        throw new CalcExceptions("\n\n\tDifferent number systems are used.\n\n");
      } else if (var1 < 1 && var1 > 10 || var2 < 1 && var2 > 10){

      };

      //isRome + romeFactorBoolean


      int result = calcLamb(arr[1], var1, var2));

      return result + "";
    }

    public static void main(String[] args) throws CalcExceptions {
        //System.out.println(MessageFormat.format("\n\n\tCalculator for binary operations.\n\tSupported operations: *, +, /, -.\n\tFor exit press 'CTRL+C'\n\n {0};\nHis length: {1};\nExample: {2}\nExample: {3}",)
        //System.out.println(romToInt("MMDCLXXXIX"));
        while (true) {
        String scan = new Scanner(System.in).nextLine().trim();

        System.out.println(calc(scan));


        }
    }

    public static int calcLamb(String oper, int x, int y) {
        calcInterface add = (a, b) -> a + b;
        calcInterface sub = (a, b) -> a - b;
        calcInterface mul = (a, b) -> a * b;
        calcInterface div = (a, b) -> a / b;

        switch(oper) {
            case "+":
                return add.calcOp(x, y);
            case "-":
                return sub.calcOp(x, y);
            case "*":
                return mul.calcOp(x, y);
            case "/":
                return div.calcOp (x, y);
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

    private static boolean isDig (String number) {
        char[] charArr = number.toCharArray();
        return IntStream.range(0, charArr.length).reduce(1, (acc, i) -> {
            acc = Character.isDigit(charArr[i]) && acc == 1 ? 1 : 0;
            return acc;
        }) == 1 ? true : false;
    }

    private static boolean isOper (String oper) {
        String[] operAr = new String[]{"+", "-", "*", "/"};
        List opList = new ArrayList<>(Arrays.asList(operAr));
        return opList.contains(oper);
    }

    private static boolean isRome (String num) {
        String charRom = "MDCLXVI";
        String[] numAr = num.toUpperCase().split("");
        boolean result = true;
        for (int i = 0; i < numAr.length; i++) {
          result = result && charRom.contains(numAr[i]) ? true : false;
        }
        return result;
    }

}
