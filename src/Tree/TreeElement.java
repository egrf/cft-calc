package Tree;

import Parser.LexemeParser;

public class TreeElement {
    private TreeElement leftChild;
    private TreeElement rightChild;
    private String lexeme;
    private char sign;
    private Double value;

    public TreeElement(String lexeme) {
        this.lexeme = lexeme;
        buildTree(this);
        calculate(this);
    }

    private void buildTree(TreeElement treeElement) {

        int inflectionPoint = new LexemeParser().inflectionPointSearch(treeElement);
        if (inflectionPoint != 0) {
            TreeElement leftChild = new TreeElement(treeElement.getLexeme().substring(0, inflectionPoint));
            TreeElement rightChild = new TreeElement(treeElement.getLexeme().substring(inflectionPoint + 1));
            treeElement.leftChild = leftChild;
            treeElement.rightChild = rightChild;
            treeElement.sign = treeElement.getLexeme().charAt(inflectionPoint);

            buildTree(leftChild);
            buildTree(rightChild);
        } else {
            if (treeElement.getLexeme().indexOf('(') == 1) {
                TreeElement leftChild = new TreeElement("0");
                TreeElement rightChild = new TreeElement(treeElement.getLexeme().substring(1));
                treeElement.leftChild = leftChild;
                treeElement.rightChild = rightChild;
                treeElement.sign = treeElement.getLexeme().charAt(0);

                leftChild.value = 0.0;
                buildTree(rightChild);
                buildTree(leftChild);
            } else {
                treeElement.value = Double.parseDouble(treeElement.getLexeme());
            }
        }
    }

    private void calculate(TreeElement treeElement) {
        if (treeElement.leftChild != null && treeElement.rightChild != null) {
            if (treeElement.leftChild.value == null)
                calculate(treeElement.leftChild);
            if (treeElement.rightChild.value == null)
                calculate(treeElement.rightChild);
            if (treeElement.leftChild.value != null && treeElement.rightChild.value != null) {
                treeElement.value = getAnswer(treeElement.leftChild, treeElement.rightChild, treeElement.sign);
            }
        }
    }

    private Double getAnswer(TreeElement leftChild, TreeElement rightChild, char sign) {
        switch (sign) {
            case ('+'): {
                return leftChild.value + rightChild.value;
            }
            case ('-'): {
                return leftChild.value - rightChild.value;
            }
            case ('*'): {
                return leftChild.value * rightChild.value;
            }
            case ('/'): {
                return leftChild.value / rightChild.value;
            }
            default: {
                return 0.0;
            }
        }
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
