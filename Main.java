import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;
import java.util.function.*;

public class Main {
    public static String calc(String input) throws CalcExceptions, ArrayIndexOutOfBoundsException {
        String blanc = input.replace(" ", "");
        String[] operArr = new String[] {"/", "*", "-", "+"};

        if (blanc.isEmpty()) {
            throw new CalcExceptions("\n\n\tString is empty. Try again.\n\n");
        }

        String operation = isEntryOper(blanc, operArr);
        if (operation.isEmpty() || !isOperBinary(blanc, operation)) {
            throw new CalcExceptions("\n\n\tThe operation is not defined.\n\tСalculator only supports binary operations.\n\n");
        }

        String[] varrs = blanc.split("\\" + operation);
        boolean romuls = isRome(varrs[0]) && isRome(varrs[1]);
        boolean digits = isDigital(varrs[0]) && isDigital(varrs[1]);

        if ( !(romuls ^ digits) ) {
            throw new CalcExceptions("\n\n\tDifferent number systems are used.\n\n");
        }

        int var1 = romuls ? romToInt(varrs[0]) : Integer.parseInt(varrs[0]);
        int var2 = romuls ? romToInt(varrs[1]) : Integer.parseInt(varrs[1]);

        if ((var1 < 1 || var1 > 10) || (var2 < 1 || var2 > 10)) {
            throw new CalcExceptions("\n\n\tThe variable exceeded the allowed values: [1, 10].\n\n");
        }

        int result = calcLamb(operation, var1, var2);

        if (romuls && result < 1) {
            throw new CalcExceptions("\n\n\tRoman numerals are integers and can't be less than one.\n\n");
        }

        return (romuls ? intToRom(result): result) + "";
    }

    public static void main(String[] args) throws CalcExceptions {
        System.out.println("\n\n\tCalculator for binary operations.\n\tSupported operations: *, +, /, -.\n\tFor exit press CTRL+C\n\n");
        while (true) {
            String scan = new Scanner(System.in).nextLine().trim();
            System.out.println(calc(scan));
        }
    }

    public static int calcLamb(String oper, int x, int y) {
        HashMap<String, IntBinaryOperator> hh = new HashMap<>() {{
            put("+", (int a,int b) -> a + b);
            put("-", (int a,int b) -> a - b);
            put("*", (int a,int b) -> a * b);
            put("/", (int a,int b) -> a / b);
        }};
        return hh.get(oper).applyAsInt(x, y);
    }

    private static String isEntryOper (String str, String[] opArr) {
        String[] arr = Stream.of(opArr).filter(i -> str.contains(i)).toArray(String[]::new);
        return arr.length == 1 ? arr[0] : "";
    }

    private static boolean isOperBinary (String str, String oper) {
        int ind = str.indexOf(oper);
        int indL = str.lastIndexOf(oper);
        return !oper.isEmpty() && ind != -1 && ind == indL ? true : false;
    }

    private static boolean isRome (String number) {
        String charRom = "MDCLXVI";
        String[] numArr = number.toUpperCase().split("");
        return Stream.of(numArr).filter(i -> charRom.contains(i)).count() == number.length();
    }

    private static boolean isDigital (String number) {
        String[] arr = number.split("");
        return Stream.of(arr).filter(i -> Character.isDigit(i.charAt(0))).count() == number.length();
    }

    private static Integer romToInt (String num) {
        HashMap<String, Integer> romChar = new HashMap<>() {{
            put("M", 1000);
            put("D", 500);
            put("C", 100);
            put("L", 50);
            put("X", 10);
            put("V", 5);
            put("I", 1);
        }};

        Integer[] array = Stream.of(num.toUpperCase().split(""))
            .map(x -> romChar.get(x)).toArray(Integer[]::new);
        return IntStream.range(0, array.length - 1).reduce(0, (acc, i) -> {
            acc += array[i] >= array[i + 1] ? array[i] : -array[i];
            return acc;
        }) + array[array.length - 1];
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
