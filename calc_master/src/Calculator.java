import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        System.out.print("Введите выражение c числами от 1 до 10: ");
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();
        if (!(calc(input) == null)) {
            System.out.println(calc(input));
        }
    }

    public static String calc(String input) {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        int actionIndex = -1;
        int firstOperandIndex = -10;
        int lastOperandIndex = -10;
        int lastActionIndex = -2;
        for (int i = actions.length - 1; i >= 0; i--) {
            if (input.lastIndexOf(actions[i]) != -1) {
                lastActionIndex = i;
                lastOperandIndex = input.lastIndexOf(actions[i]);
                break;
            }
        }
        for (int i = 0; i < actions.length; i++) {
            if (input.contains(actions[i])) {
                actionIndex = i;
                firstOperandIndex = input.indexOf(actions[i]);
                break;
            }

        }
        if (actionIndex == -1) {
            System.out.println("Некорректное выражение");
            return null;
        }
        if (lastActionIndex != -2 & (firstOperandIndex != lastOperandIndex)) {
            System.out.println("Ввод более двух операндов");
            return null;
        }

        String[] data = input.split(regexActions[actionIndex]);
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

            } else {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            if (a > 10 | b > 10 | a < 1 | b < 1) {
                System.out.println("числа больше 10 или меньше 1");
                return null;
            }
            int result;
            switch (actions[actionIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                default:
                    result = a / b;
                    break;
            }
            if (isRoman) {
                return converter.intToRoman(result);
            } else {
                return Integer.toString(result);
            }
        } else {
            System.out.println("Числа должны быть в одном формате");
        }
        return null;
    }
}

