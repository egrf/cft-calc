package Validation;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private String expression;


    public Validator(String str){
        this.expression = str;
    }

    public String valudate(){
        String message = "";
        if(!bracketsChecking())
            message+=" You have an error in brackets.\n";
        if(expressionValidator())
            message+= "Your string  contains wrong symbols!";
        return message;
    }

    private boolean expressionValidator(){
        Pattern p = Pattern.compile("[a-zA-Zа-яА-Я@!#$%^&|`'\"<>{}]+");
        Matcher m = p.matcher(expression);
        return m.matches();
    }

    private boolean bracketsChecking()
    {
        int countOfBrackets = 0;
        for (int i = 0; i < expression.length(); i++)
        {
            String symbol = expression.substring(i, i + 1);
            if (symbol.equals("("))
                countOfBrackets++;
            else if(symbol.equals(")"))
                countOfBrackets--;
            if (countOfBrackets < 0)
                return false;
        }
        return countOfBrackets==0;
    }

}


