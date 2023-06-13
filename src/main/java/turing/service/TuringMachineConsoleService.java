package turing.service;

import turing.avltree.AVLTree;
import turing.band.Band;
import turing.band.Cell;
import turing.constant.Constants;
import turing.head.Head;
import turing.service.lambda.Lambda;
import turing.service.lambda.LambdaService;

import java.util.Scanner;

public class TuringMachineConsoleService {

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        AVLTree<Character> alphabet = setAlphabet(scanner);
        Head head = setHead(scanner);
        setTransitions(scanner, head, alphabet);
        Band band = setBand(scanner, alphabet);
        startMachine(head, band);
        printResult(band);
    }

    private static AVLTree<Character> setAlphabet(Scanner scanner) {
        System.out.println("Turing Machine.");
        System.out.println("Set Alphabet of Symbols without spaces and delimeters.\nBlank symbol is in Alphabet by default:");
        AVLTree<Character> alphabet = new AVLTree<>(stringToCharacterArray(scanner.next()));
        alphabet.add(Constants.BLANK);
        return alphabet;
    }

    private static Head setHead(Scanner scanner) {
        System.out.println("Set Count of States of the Head:");
        int countOfStates = tryGetValueByCondition(scanner, "Count of States", LambdaService.getCountOfStatesLambda());
        System.out.println("Set Start State Number of the Head(first state number is 1):");
        int startStateNum = tryGetValueByCondition(scanner, "Start State Number", LambdaService.getStartStateNumLambda(countOfStates));
        return new Head(countOfStates, startStateNum);
    }

    private static void setTransitions(Scanner scanner, Head head, AVLTree<Character> alphabet) {
        System.out.println("Set Instructions for States.\n" +
                "Example: " + Constants.STATE + "1a" + Constants.ARROW_START + Constants.ARROW_END + Constants.STATE + "2b" + Constants.RIGHT + "\n" +
                "Means that if Head in State '1' and current Cell at Band is with Symbol 'a'\n" +
                "Then Write to current Cell Symbol 'b', set State to State '2' and Move Head to the Right\n" +
                "Directions are " + Constants.RIGHT + "(Right), " + Constants.LEFT + "(Left), " +
                Constants.NONE + "(None). Blank Symbol is '" + Constants.BLANK + "'.\n" +
                "When you finish write 'end'");
        String input = "";
        while (!input.equals("end")) {
            input = tryGetValueByCondition(scanner, "Instruction", LambdaService.getTransitionLambda(head, alphabet));
        }
    }

    private static Band setBand(Scanner scanner, AVLTree<Character> alphabet) {
        System.out.println("Set values of Cells in Band without spaces and delimeters (Blank Symbol is '" + Constants.BLANK + "'):");
        return tryGetValueByCondition(scanner, "Band", LambdaService.getBandTransition(alphabet));
    }

    private static void startMachine(Head head, Band band) {
        System.out.println("Starting machine...");
        head.setBand(band);
        head.start();
    }

    private static void printResult(Band band) {
        System.out.println("Machine finished working. Result band:");
        for (Cell cell : band) {
            System.out.print(cell.getSymbol());
        }
    }

    private static Character[] stringToCharacterArray(String str) {
        Character[] arr = new Character[str.length()];
        for (int i = 0; i < str.length(); i++) {
            arr[i] = str.charAt(i);
        }
        return arr;
    }

    private static <T> T tryGetValueByCondition(Scanner scanner, String variableName, Lambda<T> lambda) {
        String value = null;
        while (value == null) {
            try {
                value = scanner.next();
                if (!lambda.conditionCheck(value)) {
                    throw new IllegalArgumentException("Incorrect value");
                }
                return lambda.getValue();
            } catch (Exception e) {
                value = null;
                System.out.println("Incorrect " + variableName + ": " + e.getMessage());
            }
        }
        return null;
    }

}
