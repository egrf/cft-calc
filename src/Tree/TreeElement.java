package Tree;

import Parser.ExpressionParser;

public class TreeElement{
    private TreeElement parent;
    private TreeElement leftChild;
    private TreeElement rightChild;
    private String lexeme;
    private char sign;
    private Double value;

    public TreeElement(String lexeme) {
        this.lexeme = lexeme;
    }

    public String getResult(){

        buildTree(this);
        calculate(this);
        return this.getValue().toString();
    }

    private void buildTree(TreeElement treeElement) {
        this.setLexeme(this.lexeme.replaceAll("--", "+"));

        int inflectionPoint;
        ExpressionParser parser = new ExpressionParser();
        inflectionPoint = parser.inflectionPointSearch(treeElement);
        if(inflectionPoint != 0){
            TreeElement leftChild = new TreeElement(treeElement.getLexeme().substring(0,inflectionPoint));
            TreeElement rightChild = new TreeElement(treeElement.getLexeme().substring(inflectionPoint+1));
            treeElement.setLeftChild(leftChild);
            treeElement.setRightChild(rightChild);
            treeElement.setSign(treeElement.getLexeme().charAt(inflectionPoint));
            rightChild.setParent(treeElement);
            leftChild.setParent(treeElement);
            buildTree(leftChild);
            buildTree(rightChild);
        } else {
            if (treeElement.getLexeme().indexOf('(') == 1) {
                TreeElement leftChild = new TreeElement("0");
                TreeElement rightChild = new TreeElement(treeElement.getLexeme().substring(1));
                treeElement.setLeftChild(leftChild);
                treeElement.setRightChild(rightChild);
                leftChild.setParent(treeElement);
                leftChild.setValue(0.0);
                treeElement.setSign(treeElement.getLexeme().charAt(0));
                rightChild.setParent(treeElement);
                buildTree(rightChild);
                buildTree(leftChild);
            }
            else {
                treeElement.setValue(Double.parseDouble(treeElement.getLexeme()));
            }
        }
    }

    private void calculate(TreeElement treeElement) {
        if(treeElement.getLeftChild() != null && treeElement.getRightChild() != null){
            if(treeElement.getLeftChild().getValue() == null)
                calculate(treeElement.getLeftChild());
            if(treeElement.getRightChild().getValue() == null)
                calculate(treeElement.getRightChild());
            if(treeElement.getLeftChild().getValue() != null && treeElement.getRightChild().getValue() != null){
                treeElement.setValue(count(treeElement.getLeftChild(),treeElement.getRightChild(),treeElement.getSign()));
            }
        }
    }

    private Double count(TreeElement leftChild, TreeElement rightChild, char sign) {
        switch (sign) {
            case ('+'): {
                return leftChild.getValue()+rightChild.getValue();
            }
            case ('-'): {
                return leftChild.getValue()-rightChild.getValue();
            }
            case ('*'): {
                return leftChild.getValue()*rightChild.getValue();
            }
            case ('/'): {
                return leftChild.getValue()/rightChild.getValue();
            }
            default:{
                return 0.0;
            }
        }
    }

    private TreeElement getLeftChild() {
        return leftChild;
    }

    private void setLeftChild(TreeElement leftChild) {
        this.leftChild = leftChild;
    }

    private TreeElement getRightChild() {
        return rightChild;
    }

    private void setRightChild(TreeElement rightChild) {
        this.rightChild = rightChild;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    private Double getValue() {
        return value;
    }

    private void setValue(Double value) {
        this.value = value;
    }

    public TreeElement getParent() {
        return parent;
    }

    private void setParent(TreeElement parent) {
        this.parent = parent;
    }

    private char getSign() {
        return sign;
    }

    private void setSign(char sign) {
        this.sign = sign;
    }
}
