package Tree;

import Parser.ExpressionParser;

public class TreeElement implements ITree{
    private TreeElement parent;
    private TreeElement leftChild;
    private TreeElement rightChild;
    private String lexeme;
    private Integer value;

    public TreeElement(String lexeme) {
        this.lexeme = lexeme;
    }

    public String getResult(){

        return buildTree(this);
    }

    private String buildTree(TreeElement treeElement) {
        ExpressionParser parser = new ExpressionParser(this);
        return Integer.toString(parser.inflectionPointSearch());
    }

    @Override
    public void calculate() {

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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public TreeElement getParent() {
        return parent;
    }

    public void setParent(TreeElement parent) {
        this.parent = parent;
    }
}
