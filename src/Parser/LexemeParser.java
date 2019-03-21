package Parser;

import java.util.HashMap;
import java.util.Map;

public class LexemeParser {
    private static final Map<Character, Integer> MATH_OPERATIONS = new HashMap<>();

    static {
        MATH_OPERATIONS.put('^', 1);
        MATH_OPERATIONS.put('*', 2);
        MATH_OPERATIONS.put('/', 2);
        MATH_OPERATIONS.put('+', 3);
        MATH_OPERATIONS.put('-', 3);
    }

    public int inflectionPointSearch(Tree.TreeElement treeElement) {
        String lexeme = treeElement.getLexeme();
        int pointer = 0;
        int doubleBracketsCount = countOfBrackets(lexeme);
        return findInflectionPoint(treeElement, lexeme, pointer, doubleBracketsCount);
    }

    private int findInflectionPoint(Tree.TreeElement treeElement, String lexeme, int pointer, int doubleBracketsCount) {
        int id;
        int firstLeftBracketPosition = lexeme.indexOf('(');
        int lastRightBracketPosition = lexeme.lastIndexOf(')');
        if (doubleBracketsCount > 0) {
            if (firstLeftBracketPosition == 0) {
                if (lastRightBracketPosition == lexeme.length() - 1) {
                    if (areDoubleBrackets(firstLeftBracketPosition, lastRightBracketPosition, lexeme)) {
                        pointer++;
                        int end = lexeme.length() - 1;
                        String newLexeme = lexeme.substring(pointer, end);
                        treeElement.setLexeme(newLexeme);
                        doubleBracketsCount = countOfBrackets(newLexeme);
                        id = findInflectionPoint(treeElement, newLexeme, pointer, doubleBracketsCount) - 1;
                    } else {
                        int pair = findRightBracket(lexeme, 0) + 1;
                        int end = lexeme.length();
                        String newLexeme = lexeme.substring(pair, end);
                        pointer += pair;
                        doubleBracketsCount = countOfBrackets(newLexeme);
                        id = findInflectionPoint(treeElement, newLexeme, pointer, doubleBracketsCount);
                    }
                } else {
                    id = findPointInSimpleExpression(lexeme, pointer);
                }

            } else if (lastRightBracketPosition == lexeme.length() - 1) {
                int pair = findLeftBracket(lexeme);
                String newLexeme = lexeme.substring(0, pair);
                doubleBracketsCount = countOfBrackets(newLexeme);
                id = findInflectionPoint(treeElement, newLexeme, pointer, doubleBracketsCount);
            } else
                id = findPointInSimpleExpression(lexeme, pointer);

        } else
            id = findPointInSimpleExpression(lexeme, pointer);
        return id;
    }

    private int findPointInSimpleExpression(String lexeme, int pointer) {
        int id = 0;
        int priority = 0;
        char[] charsLexeme = lexeme.toCharArray();
        int end = lexeme.length() - 1;
        for (int i = 0; i <= end; i++) {
            if (charsLexeme[i] == '(') {
                i = findRightBracket(lexeme, i);
                continue;
            }
            if (MATH_OPERATIONS.containsKey(charsLexeme[i])) {
                if (MATH_OPERATIONS.get(charsLexeme[i]) >= priority) {
                    id = i;
                    priority = MATH_OPERATIONS.get(charsLexeme[i]);
                }
            }
        }
        return id + pointer;
    }

    private int findLeftBracket(String lexeme) {
        char[] charsLexeme = lexeme.toCharArray();
        int opened = 0;
        int end = lexeme.length() - 2;
        int index;
        for (index = end; index > 0; index--) {
            if (charsLexeme[index] == ')') {
                opened++;
            }
            if ((opened == 0) && (charsLexeme[index] == '(')) {
                break;
            }
            if ((opened != 0) && (charsLexeme[index] == '(')) {
                opened--;
            }
        }
        return index;
    }

    private int findRightBracket(String lexeme, int pointer) {
        int index;
        int opened = 0;
        char[] charsLexeme = lexeme.toCharArray();
        for (index = pointer + 1; index < lexeme.length(); index++) {
            if (charsLexeme[index] == '(') {
                opened++;
            }
            if ((opened == 0) && (charsLexeme[index] == ')')) {
                break;
            }
            if ((opened != 0) && (charsLexeme[index] == ')')) {
                opened--;
            }
        }
        return index;
    }

    private boolean areDoubleBrackets(int left, int right, String lexeme) {
        char[] charsLexeme = lexeme.toCharArray();
        int opened = 0;
        for (int i = left + 1; i <= right; i++) {
            if (charsLexeme[i] == ')' && opened == 0) {
                return i == right;
            } else if (charsLexeme[i] == '(') {
                opened++;
            } else if (charsLexeme[i] == ')' && opened != 0) {
                opened--;
            }
        }
        return true;
    }

    private int countOfBrackets(String lexeme) {
        int count = 0;
        char[] charsLexeme = lexeme.toCharArray();
        for (int i = 0; i < lexeme.length(); i++) {
            if (charsLexeme[i] == '(')
                count++;
        }
        return count;
    }
}
