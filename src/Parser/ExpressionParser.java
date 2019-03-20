package Parser;

import Tree.TreeElement;

import java.util.HashMap;
import java.util.Map;

public class ExpressionParser {

    private TreeElement treeElement;

    public ExpressionParser(TreeElement treeElement) {
        this.treeElement = treeElement;
    }

    public int inflectionPointSearch() {
        String lexeme = treeElement.getLexeme();
        int start = 0;
        int countOfPairsOfBrackets = countOfBrackets(lexeme);
        return findInflectionPoint(lexeme, start, countOfPairsOfBrackets);
    }

    private int findInflectionPoint (String lexeme, int start, int countOfPairsOfBrackets){
        int id;
        int positionOfTheFirstLeftBracket = lexeme.indexOf('(');
        int positionOfTheLastRightBracket = lexeme.lastIndexOf(')');
        if(countOfPairsOfBrackets > 0){
            if (positionOfTheFirstLeftBracket == 0){
                if (positionOfTheLastRightBracket == lexeme.length() - 1){
                    if (areTheseBracketsAPair(positionOfTheFirstLeftBracket, positionOfTheLastRightBracket, lexeme)) {
                        start++;
                        int end = lexeme.length()-1;
                        String newLexeme = lexeme.substring(start,end);
                        countOfPairsOfBrackets = countOfBrackets(newLexeme);
                        id =findInflectionPoint(newLexeme,start,countOfPairsOfBrackets);
                    } else {
                        int pair = findRightPair(lexeme)+1;
                        int end = lexeme.length();
                        String newLexeme = lexeme.substring(pair,end);
                        start+=pair;
                        countOfPairsOfBrackets = countOfBrackets(newLexeme);
                        id = findInflectionPoint(newLexeme,start,countOfPairsOfBrackets);
                    }
                } else {
                    id = findPointInSimpleExpression(lexeme, start);
                }

            } else if(positionOfTheLastRightBracket == lexeme.length() - 1){
                int pair = findLeftPair(lexeme);
                String newLexeme = lexeme.substring(0,pair);
                countOfPairsOfBrackets = countOfBrackets(newLexeme);
                id = findInflectionPoint(newLexeme,start,countOfPairsOfBrackets);
            } else
                id = findPointInSimpleExpression(lexeme, start);

        } else
            id = findPointInSimpleExpression(lexeme, start);
        return id;
    }

    private int findPointInSimpleExpression(String lexeme, int start) {
        int id = 0;
        int priority = 0;
        char[] charsLexem = lexeme.toCharArray();
        int end = lexeme.length()-1;
        for (int i = 0; i<=end; i++){
            if(charsLexem[i] == '('){
                i = findRightPair(lexeme,i);
                continue;
            }
            String key = Character.toString(charsLexem[i]);
            if(MATH_OPERATIONS.get(key) != null){
                int localPriority = MATH_OPERATIONS.get(key);
                if(localPriority >= priority){
                    id = i;
                    priority = localPriority;
                }
            }
        }
        return id+start;
    }

    private int findLeftPair( String lexeme) {
        char[] charsLexem = lexeme.toCharArray();
        int opened =0;
        int end = lexeme.length()-2;
        int i;
        for (i = end; i>0; i--){
            if (charsLexem[i] ==')')
                opened++;
            if((opened == 0) && (charsLexem[i] == '('))
                return i;
            if((opened != 0) && (charsLexem[i] == '('))
                opened--;
        }
        return i;
    }

    private int findRightPair( String lexeme) {
        int start = 0;
        return findRightBracket(lexeme,start);
    }

    private int findRightBracket(String lexeme, int start) {
        int i;
        int opened =0;
        char[] charsLexem = lexeme.toCharArray();
        for (i = start+1; i< lexeme.length(); i++){
            if (charsLexem[i] =='(')
                opened++;
            if((opened == 0) && (charsLexem[i] == ')'))
                return i;
            if((opened != 0) && (charsLexem[i] == ')'))
                opened--;
        }
        return i;
    }

    private int findRightPair(String lexeme, int start) {
        return findRightBracket(lexeme,start);
    }

    private boolean areTheseBracketsAPair(int left, int right, String lexeme) {
        char[] charsLexem = lexeme.toCharArray();
        int opened = 0;
        for (int i = left+1; i <= right; i++){
            if (charsLexem[i] == ')' && opened == 0)
                return true;
            else if(charsLexem[i] == '(')
                opened++;
            else if(charsLexem[i] == ')' && opened != 0)
                opened--;
        }
        return false;
    }

    private int countOfBrackets(String lexeme ){
        int count = 0;
        char[] lexemeChars = lexeme.toCharArray();
        for (int i=0; i<lexeme.length(); i++){
            if (lexemeChars[i] == '(')
                count++;
        }
        return count;
    }

    private static final Map<String, Integer> MATH_OPERATIONS;
    static {
        MATH_OPERATIONS = new HashMap<String, Integer>();
        MATH_OPERATIONS.put("^", 1);
        MATH_OPERATIONS.put("*", 2);
        MATH_OPERATIONS.put("/", 2);
        MATH_OPERATIONS.put("+", 3);
        MATH_OPERATIONS.put("-", 3);
    }
}
