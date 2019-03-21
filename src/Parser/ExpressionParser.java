package Parser;

public class ExpressionParser {
    private String expression;

    public ExpressionParser(String expression) {
        this.expression = expression;
    }

    public String getStringResult() {
        String result = "";
        expression = expression.replaceAll(" ","");
        if(expression.contains("?") && (expression.contains(":"))){
        }
        return result;
    }
}
