package Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private String expression;

    public Validator(String str) {
        this.expression = str;
    }

    public String validate() {
        String message = "";
        if (expression.isEmpty())
            message += "Input TextField is empty!\n";
        if (!charactersValidator())
            message += "Your expression contains invalid characters!\n";
        else {
            if (!expressionValidator())
                message += "Your expression has an invalid form!\n";
            if (!bracketsCheck())
                message += "You have an error in brackets!\n";
            message = correctExpression(message);
        }

        return message;
    }


    private String correctExpression(String message) {
        message = countOfSigns(message);
        char[] expChars = expression.toCharArray();
        for (int i = 0; i < expression.length(); i++) {
            if (expChars[i] == '-' || expChars[i] == '+' || expChars[i] == '/' || expChars[i] == '*') {
                if (expChars[i + 1] == '-' || expChars[i + 1] == '+' || expChars[i + 1] == '/' || expChars[i + 1] == '*') {
                    message += "Two consecutive signs!\n";
                    break;
                } else if (expChars[i + 1] != '(' && !(Character.toString(expChars[i + 1]).matches("[\\d]+"))) {
                    message += "Wrong symbol after sign!";
                    break;
                }
            } else if (expChars[i] == '(') {
                if (expChars[i + 1] != '(' && expChars[i + 1] != '-' && !(Character.toString(expChars[i + 1]).matches("[\\d]+"))) {
                    message += "Wrong symbol after bracket!";
                    break;
                }
            } else if ((Character.toString(expChars[i]).matches("[\\d]+"))&&(i!=expression.length()-1)) {
                if (expChars[i + 1] == '(') {
                    message += "Missing sign after number!";
                    break;
                }
            }
        }
        return message;
    }

    private String countOfSigns(String message) {
        char[] expChars = expression.toCharArray();
        int equals = 0;
        int exclamation = 0;
        int greater = 0;
        int less = 0;
        int colon = 0;
        int question = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expChars[i] == '=')
                equals++;
            if (expChars[i] == '!')
                exclamation++;
            if (expChars[i] == '>')
                greater++;
            if (expChars[i] == '<')
                less++;
            if (expChars[i] == ':')
                colon++;
            if (expChars[i] == '?')
                question++;
        }
        if (question > 1)
            message += "There are a lot of '?' in expression!\n";
        if (colon > 1)
            message += "There are a lot of ':' in expression!\n";
        if (exclamation > 1)
            message += "There are a lot of '!' in expression!\n";
        if (equals > 2)
            message += "There are a lot of '=' in expression!\n";
        if (greater > 1)
            message += "There are a lot of '>' in expression!\n";
        if (less > 1)
            message += "There are a lot of '<' in expression!\n";
        if (greater == 1 && less == 1)
            message += "Choose '>' or '<'!\n";
        if (exclamation == 1 && equals == 2)
            message += "Choose '==' or '!='!\n";
        if (exclamation != 1 && equals == 1)
            message += "Choose '==' or '!='!\n";
        if (question == 1 && colon != 1) {
            message += "Missed sign ':'!\n";
        }
        return message;
    }

    private boolean expressionValidator() {
        Pattern p = Pattern.compile("^[0-9.*/+\\-()]+[\\s]*[<>=!]*[\\s]*[0-9.*/+\\-()]*[\\s]*[?]?[\\s]*[0-9.*/+\\-()]*[\\s]*[:]?[\\s]*[0-9.*/+\\-()]*");
        Matcher m = p.matcher(expression);
        return m.matches();
    }

    private boolean charactersValidator() {
        Pattern p = Pattern.compile("^[0-9.*/+\\-!=:?<>()\\s]+");
        Matcher m = p.matcher(expression);
        return m.matches();
    }

    private boolean bracketsCheck() {
        int countOfBrackets = 0;
        for (int i = 0; i < expression.length(); i++) {
            String symbol = expression.substring(i, i + 1);
            if (symbol.equals("("))
                countOfBrackets++;
            else if (symbol.equals(")"))
                countOfBrackets--;
            if (countOfBrackets < 0)
                return false;
        }
        return countOfBrackets == 0;
    }
}