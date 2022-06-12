import java.io.*;
import java.util.Scanner;
import java.util.regex.*;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;
import java.util.function.IntBinaryOperator;

public class Main {

    public static String calc(String input) throws CalcExceptions, ArrayIndexOutOfBoundsException {
        Matcher matcher = Pattern.compile("\\d*[.,]\\d*|\\w+|([+-/*!;%:?@#$%^&])").matcher(input);
        String[] arr = new String[3];
        boolean calcError = false;

        try {
            for (int i = 0; matcher.find(); i++) {
                arr[i] = matcher.group();
            }
        } catch (ArrayIndexOutOfBoundsException exec) {
            calcError = true;
        }

        if (calcError) {
            throw new CalcExceptions("\n\n\t Сalculator only supports binary operations \n\n");
        };
        boolean rom = (isRome(arr[0]) && isRome(arr[2])) ? true : false;
        boolean dig = (isDig(arr[0]) && isDig(arr[2])) ? true : false;

        if ( !(rom ^ dig) ) {
            throw new CalcExceptions("\n\n\tDifferent number systems are used.\n\n");
        } else if (!isOper(arr[1])) {
            throw new CalcExceptions("\n\n\tThe operation is not defined.\n\tPlease, use the available: +, -, *, /\n\n");
        }

        int var1 = rom ? romToInt(arr[0]) : Integer.parseInt(arr[0]);
        int var2 = rom ? romToInt(arr[2]) : Integer.parseInt(arr[2]);

        if ((var1 < 1 || var1 > 10) || (var2 < 1 || var2 > 10)) {
            throw new CalcExceptions("\n\n\tThe variable exceeded the allowed values: [1, 10].\n\n");
        };

        int result = calcLamb(arr[1], var1, var2);
        if (rom && result < 1) {
            throw new CalcExceptions("\n\n\tRoman numerals are integers and can't be less than one.\n\n");
        };

        return (rom ? intToRom(result): result) + "";
    }

    public static void main(String[] args) throws CalcExceptions {
        System.out.println("\n\n\tCalculator for binary operations.\n\tSupported operations: *, +, /, -.\n\tFor exit press CTRL+C\n\n");

        while (true) {
            String scan = new Scanner(System.in).nextLine().trim();
            System.out.println(calc(scan));
        }
    }

    public static int calcLamb(String oper, int x, int y) {
      HashMap<String, IntBinaryOperator> hh = new HashMap<>();
      hh.put("+", (int a,int b) -> a + b);
      hh.put("-", (int a,int b) -> a - b);
      hh.put("*", (int a,int b) -> a * b);
      hh.put("/", (int a,int b) -> a / b);
      return hh.get(oper).applyAsInt(x, y);
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

    private static String intToRom (Integer num) {
        String[] blank = new StringBuilder(num + "").reverse().toString().split("");
        String result = "";
        for (int i = 0; i < blank.length; i++) {
            result = funcForintToRom(blank[i], i) + result;
        }
        return result;
    }

    private static String funcForintToRom (String elem, Integer index) {
        String[][] romuls = new String[][]{{"I", "V"}, {"X", "L"}, {"C", "D"}, {"M"}};
        String result = "";
        int e = Integer.parseInt(elem);
        if (e == 9) {
            result = romuls[index][0] + romuls[index + 1][0];
        } else if (e >= 5) {
            result = e == 5 ? romuls[index][1] : romuls[index][1] + romuls[index][0].repeat(e - 5);
        } else if (e <= 4) {
            result = e == 4 ? String.join("", romuls[index]) : romuls[index][0].repeat(e);
        }
        return result;
    }
}
