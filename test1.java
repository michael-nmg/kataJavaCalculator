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
        int calcOp(int x, int y);
    }

    public static String calc(String input) {
        Matcher matcher = Pattern.compile("\\w+|([+-/*])").matcher(scan);
        String[] arr = new String[3];
        for (int i = 0; matcher.find(); i++) {
            arr[i] = matcher.group();//.trim();
        }

        if (scan.equals("1")) {
        System.out.println("Stop");
        break;
      }

        arr.length == 3;
        isOper(arr[1]);
        isDig(arr[0]) && isDig(arr[1]);


        //try catch dlinna massiva
        //MessageFormat.format("Your text: {0};\nHis length: {1};\nExample: {2}\nExample: {3}",
        String result = returncalcLamb(arr[1], Integer.parseInt(arr[0]), Integer.parseInt(arr[2]));



        return result;
    }

    public static void main(String[] args) {
        //MessageFormat.format("Your text: {0};\nHis length: {1};\nExample: {2}\nExample: {3}",
        //System.out.println(romToInt("MMDCLXXXIX"));
        //System.out.println();
        //while (true) {
        String scan = new Scanner(System.in).nextLine().trim();


        calc(scan);




        //}
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
        String[] nameArray = new String[]{"+", "-", "*", "/"};
        List nameList = new ArrayList<>(Arrays.asList(nameArray));
        return nameList.contains(oper);
    }

}
