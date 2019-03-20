package Tree;

import Parser.ExpressionParser;

public class TreeElement implements ITree{
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
                TreeElement leftChild = new TreeElement("");
                TreeElement rightChild = new TreeElement(treeElement.getLexeme().substring(1));
                treeElement.setLeftChild(leftChild);
                treeElement.setRightChild(rightChild);
                leftChild.setParent(treeElement);
                leftChild.setValue(0.0);
                treeElement.setSign(treeElement.getLexeme().charAt(0));
                rightChild.setParent(treeElement);
                buildTree(rightChild);
            }
            treeElement.setValue(Double.parseDouble(treeElement.getLexeme()));
        }
    }

    @Override
    public void calculate(TreeElement treeElement) {
        if(treeElement.getLeftChild() != null && treeElement.getRightChild() != null){

        }
        else{
        }
    }

    public TreeElement getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeElement leftChild) {
        this.leftChild = leftChild;
    }

    public TreeElement getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeElement rightChild) {
        this.rightChild = rightChild;
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

    public TreeElement getParent() {
        return parent;
    }

    public void setParent(TreeElement parent) {
        this.parent = parent;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }
}
