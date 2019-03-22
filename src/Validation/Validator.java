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
        if (!bracketsCheck())
            message += "You have an error in brackets.\n";
        if (!expressionValidator())
            message += "Your string contains wrong symbols!\n";
        if (expression.isEmpty()) {
            message += "Input TextField is empty!";
        }
        return message;
    }

    private boolean expressionValidator() {
        Pattern p = Pattern.compile("^[0-9+:?!<>()\\s\\-./*={2}]+");
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