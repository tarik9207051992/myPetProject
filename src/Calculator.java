package scr;

import java.util.*;

public class Calculator {

    private static Hashtable<Character, Integer> romanHashTable = new Hashtable<>();
    private final static TreeMap<Integer, String> romanTreeMap = new TreeMap<Integer, String>();

    private static final int numberLimit = 2;

    static {
        romanHashTable.put('I',1);
        romanHashTable.put('X',10);
        romanHashTable.put('V',5);
        romanTreeMap.put(10, "X");
        romanTreeMap.put(9, "IX");
        romanTreeMap.put(5, "V");
        romanTreeMap.put(4, "IV");
        romanTreeMap.put(1, "I");
    }
    private static int currentSum = 0;
    private static NumberType numberType;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        List<Part> partList = parse(string);
        currentSum = ( (Value) partList.get(0)).getValue();
        for (int i = 0; i < partList.size(); i++) {
            Part currentPart = partList.get(i);
            if (currentPart instanceof Action) {
                currentSum = ((Action) currentPart).doAction(currentSum, ((Value) partList.get(i + 1)).getValue());
            }
        }
        if (numberType == NumberType.ROMAN) {
            if (currentSum < 1) {
                throw new ArithmeticException("Roman calculation result < 0");
            }
            System.out.println(Value.toRoman(currentSum, romanTreeMap));
        } else {
            System.out.println(currentSum);
        }
    }

    private static List<Part> parse(String inputString) {
        String[] splitedInputString = inputString.split(" ");
        List<Part> partList = new ArrayList<>();
        int valueCount = 0;
        for (String part:
             splitedInputString) {
            if (Action.isAction(part)) {
                char actionChar = part.charAt(0);
                partList.add(new Action(actionChar));
            } else if (Value.isValue(part)) {
                if (++valueCount > numberLimit) {
                    throw new IllegalArgumentException("number limit is " + numberLimit);
                }
                if (Value.isRoman(part)) {
                    if (numberType == null) {
                        numberType = NumberType.ROMAN;
                    }
                    if (numberType == NumberType.ARABIC) {
                        throw new IllegalArgumentException("Current type is Roman");
                    }
                    int parsedRomanValue = Value.parseRoman(part, romanHashTable);
                    partList.add(new Value(parsedRomanValue));
                } else {
                    if (numberType == null) {
                        numberType = NumberType.ARABIC;
                    }
                    if (numberType == NumberType.ROMAN) {
                        throw new IllegalArgumentException("Current type is Arabic");
                    }

                    partList.add(new Value(Integer.parseInt(part)));
                }
                Value value = (Value) partList.get(partList.size()-1);
                if (value.getValue() > 10 || value.getValue() < 1) {
                    throw new IllegalArgumentException("number max value is 10 & min is 1");
                }
            } else {
                throw new IllegalArgumentException("writed wrong String");
            }

        }
        return partList;
    }
    private enum NumberType {
        ROMAN,
        ARABIC
    }
}
