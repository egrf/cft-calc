package Parser;

import Tree.TreeElement;

public class ExpressionParser {
    private String expression;

    public ExpressionParser(String expression) {
        this.expression = expression;
    }

    public String getStringResult() {
        String result;
        expression = expression.replaceAll(" ", "");
        if (expression.contains(">") || expression.contains("<") || expression.contains("==") || expression.contains("!=")) {
            if (expression.contains("?")) {
                result = splitIntoFourTrees();
            } else {
                result = splitIntoTwoTrees();
            }
        } else {
            result = new TreeElement(expression).getValue().toString();
        }
        return result;
    }

    private String splitIntoFourTrees() {
        String result = "";
        String[] parts = expression.split("[<>!=]+");
        String firstPart = parts[0];
        parts = parts[1].split("[?]");
        String secondPart = parts[0];
        parts = parts[1].split("[:]");
        String thirdPart = parts[0];
        String fourthPart = parts[1];

        if (expression.contains(">")) {
            if (new TreeElement(firstPart).getValue() > new TreeElement(secondPart).getValue()) {
                result = Double.toString(new TreeElement(thirdPart).getValue());

            } else {
                result = Double.toString(new TreeElement(fourthPart).getValue());
            }
        } else if (expression.contains("<")) {
            if (new TreeElement(firstPart).getValue() < new TreeElement(secondPart).getValue()) {
                result = Double.toString(new TreeElement(thirdPart).getValue());

            } else {
                result = Double.toString(new TreeElement(fourthPart).getValue());
            }
        } else if (expression.contains("==")) {
            if (new TreeElement(firstPart).getValue().equals(new TreeElement(secondPart).getValue())) {
                result = Double.toString(new TreeElement(thirdPart).getValue());

            } else {
                result = Double.toString(new TreeElement(fourthPart).getValue());
            }
        } else if (expression.contains("!=")) {
            if (!new TreeElement(firstPart).getValue().equals(new TreeElement(secondPart).getValue())) {
                result = Double.toString(new TreeElement(thirdPart).getValue());

            } else {
                result = Double.toString(new TreeElement(fourthPart).getValue());
            }
        }


        return result;
    }


    private String splitIntoTwoTrees() {
        String[] parts;
        parts = expression.split("[<>!=]+");
        String leftPart = parts[0];
        String rightPart = parts[1];

        if (expression.contains(">"))
            return Boolean.toString(new TreeElement(leftPart).getValue() > new TreeElement(rightPart).getValue());
        else if (expression.contains("<"))
            return Boolean.toString(new TreeElement(leftPart).getValue() < new TreeElement(rightPart).getValue());
        else if (expression.contains("=="))
            return Boolean.toString(new TreeElement(leftPart).getValue().equals(new TreeElement(rightPart).getValue()));
        else
            return Boolean.toString(!(new TreeElement(leftPart).getValue().equals(new TreeElement(rightPart).getValue())));


    }
}
