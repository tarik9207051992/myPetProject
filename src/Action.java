package scr;

public class Action implements Part {

    private char actionChar;

    public Action(char actionChar) {
        this.actionChar = actionChar;
    }

    public int doAction(int operand1, int operand2) {
        switch (actionChar) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '/':
                return operand1 / operand2;
            case '*':
                return operand1 * operand2;
        }
        throw new IllegalStateException("can't execute action with " + actionChar);
    }

    public static boolean isAction(String symbol) {
        for (char actionChar:
             charList) {
            if (symbol.equals(Character.toString(actionChar)))
                return true;
        }
        return false;
    }

    private static final char[] charList = {'+', '-', '/', '*'};
}
