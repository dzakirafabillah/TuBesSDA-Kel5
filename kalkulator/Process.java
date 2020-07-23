package kalkulator;

/**
 *Evaluator class created the binary tree according to the expression given.
 * Internal nodes hold operators and external nodes hold numbers.
 * The tree is then traversed to produce a single value result (type double)
 * @author Luka Kralj
 * @author Dzakira Rizka
 */

import java.util.ArrayList;
import java.util.List;


public class Process {
    public static final char SQRT = 'V';
    public static final char SIN = 'N';
    public static final char COS = 'C';
    public static final char TAN = 'T';
    public static final char PLUSMIN = 'L';
    public static final char LOG = 'G';
    public static final char LN ='X';
    public static final char EXP ='E';

    private static final char[] nonNumeric = {'+', '-', '/', '*', '%', '!', '(', ')','^', SQRT, SIN, COS, TAN, PLUSMIN, LOG, LN, EXP};
    private BinaryTree tree;
    private String result;

   / * membuat proses baru dari ekspresi matematika yang diinputkan.
     * simbol trigonometri, '%', '~', '^', log, ln, dan exp akan diganti sesuai konstanta pada kelas process.
     * tanda kurung pada ekspresi matematika akan dihapus.
     * ekpresi matematika yang berupa string akan dikonversi menjadi double sebelum dikalkulasi 
     */
    public Process(String expression) {
        String str = replace(expression);
        str = getValidSubstring(str);
        if (getOperator(str) == -1) {
            result = "" + extractNumber(str);
        }
        else {
            makeTree(str);
            result = "" + evaluateTree(tree.root());
        }
        
    }

    /** 
     * Mengembalikan value dari ekspresi matematika sebagai string.
     * Mengembalikan "Err" jika ada error saat memproses ekspersi matematika.
     */
    public String getResult(){
        if (result == null) {
            return "Err";
        }
        return result;
    }

    /**
     * Extracts the number from the string. The string can be in format of ((34.5)) for example.
     *
     * @param str String with the numerical value. It can include brackets.
     * @return Value parsed from the string as double.
     */
    private double extractNumber(String str) {
        str = str.replace('(', ' ');
        str = str.replace(')', ' ');
        str = str.trim();
        return Double.parseDouble(str);
    }

    /**
     * Creates new tree.
     *
     * @param expression Expression to be traversed to make an appropriate tree.
     */
    private void makeTree(String expression) {
        expression = getValidSubstring(expression);
        int index = getOperator(expression);
        if (index == -1) {
            // There are no operators. The expression is a single value.
            double value = extractNumber(expression);
            tree = new BinaryTree(new Node(value, null, null));
        }
        else {
            char c = expression.charAt(index);
            if (isOther(c)) {
                // If the operator is a other operator the node will only have one child.
                String newExpression = "";
                if (c == SQRT || c == SIN || c == COS || c == TAN || c == PLUSMIN || c == LOG || c == LN || c == EXP) {
                    newExpression = expression.substring(index + 1, expression.length());
                }
                else {
                    newExpression = expression.substring(0, index);
                }
                tree = new BinaryTree(new Node(c, getNewChild(getValidSubstring(newExpression)), null));
            }
            else {
                String leftExpression = getValidSubstring(expression.substring(0, index));
                String rightExpression = getValidSubstring(expression.substring(index + 1, expression.length()));
                tree = new BinaryTree(new Node(c, getNewChild(leftExpression), getNewChild(rightExpression)));
            }
        }
    }

    /**
     * Returns the position of the next operator. First it checks for pluses and minuses since they
     * are evaluated the last. Then it checks for multiplication and division and finally for unary operators.
     *
     * In the expression 1 - 2 + 3 the last operator is firstly returned to produce the result
     * same as (1-2) + 3.
     *
     * @param str Expression on which we search for the next operator.
     * @return Position of the operator in the given string. -1 if no operator is found.
     */
    private int getOperator(String str) {
        int bracketCounter = 0;
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            
            
            if (c == '(') { bracketCounter++; }
            else if (c == ')') { bracketCounter--; }
            else if ((c == '+' || c == '-' ) && bracketCounter == 0) {
                indexes.add(i);
            }
        }

        if (indexes.size() > 0) {
            return indexes.get(indexes.size()-1);
        }

        bracketCounter = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') { bracketCounter++; }
            else if (c == ')') { bracketCounter--; }
            else if ((c == '*' || c == '/' || c == '^') && bracketCounter == 0) {
                indexes.add(i);
            }
        }

        if (indexes.size() > 0) {
            return indexes.get(indexes.size()-1);
        }

        bracketCounter = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') { bracketCounter++; }
            else if (c == ')') { bracketCounter--; }
            else if (isOther(c) && bracketCounter == 0) {
                indexes.add(i);
            }
        }

        if (indexes.size() > 0) {
            return indexes.get(indexes.size()-1);
        }

        return -1;
    }

    /**
     *
     * @param operator Operator to check.
     * @return True if the operator is unary operator.
     */
    private boolean isOther(char operator) {
        return operator == '!' || operator == '%' || operator == SQRT || 
                operator == SIN || operator == COS || operator == TAN || 
                operator == PLUSMIN  || operator == LOG || operator == EXP 
                || operator == LN;
    }

    /**
     * Membuat node baru untuk dimasukkan kedalam tree. Anak dari setiap node dibuat dengan cara rekursif
     * untuk membentuk full tree
     *
     * @param expression adalah Ekspresi matematika yang akan di generate menjadi binary tree
     * @return root dari substree
     */
    private Node getNewChild(String expression) {
        expression = getValidSubstring(expression);
        int index = getOperator(expression);
        /*Apabila tidak terdapat operator*/
        if (index == -1) {
            double value = extractNumber(expression);
            return new Node(value, null, null);
        }
        else {
            char c = expression.charAt(index);
            if (isOther(c)) {
                String newExpression = "";
                if (c == SQRT || c == SIN || c == COS || c == TAN || c == PLUSMIN
                        || c == LOG || c == LN || c == EXP) {
                    newExpression = expression.substring(index + 1, expression.length());
                }
                else {
                    newExpression = expression.substring(0, index);
                }
                return new Node(c, getNewChild(getValidSubstring(newExpression)), null);
            }
            else {
                String leftExpression = getValidSubstring(expression.substring(0, index));
                String rightExpression = getValidSubstring(expression.substring(index + 1, expression.length()));
                return new Node(c, getNewChild(leftExpression), getNewChild(rightExpression));
            }
        }
    }

    /**
     * Menghitung operasi matematika bedasarkan tree yang root nya adalah v. Method akan berjalan rekursif.
     *
     * Penghtiungan dilakukan secara preorder (dari parent ke anak kiri dan kanan)
     * @param v adalah root dari tree/parent dari substree yang akan kita hitung hasilnya.
     * @return hasil operasi matematika dari expression binary tree dengan tipe double
     */
    private double evaluateTree(Node v) {     
        if (tree.isNotLeaf(v)) {
            char op = (char)v.getValue();
            /*Jika Unary Operator, hanya memiliki 1 anak*/
            if (isOther(op)) {
                if (op == SQRT) {
                    return Math.sqrt(evaluateTree(v.getLeftChild()));
                }
                else if (op == '%') {
                    return evaluateTree(v.getLeftChild())/100;
                }
                else if (op == '!') {
                    double i = faktorial((int) evaluateTree(v.getLeftChild()));
                    return i;
                }
                else if(op == PLUSMIN) {
                    double i = evaluateTree(v.getLeftChild());
                    return i*-1;
                }
                else if (op == SIN) {
                    return Math.sin(Math.toRadians(evaluateTree(v.getLeftChild())));
                }
                else if (op == COS) {
                    return Math.cos(Math.toRadians(evaluateTree(v.getLeftChild())));
                }
                else if (op == TAN) {
                    return Math.tan(Math.toRadians(evaluateTree(v.getLeftChild())));
                }
                else if (op == LOG) {
                    return Math.log10(evaluateTree(v.getLeftChild()));
                }
                else if (op == LN) {
                    double i = evaluateTree(v.getLeftChild());
                    return Math.log(i);
                }
                else if (op == EXP) {
                    return Math.exp(evaluateTree(v.getLeftChild()));
                }
                
            }
            /*Jika Binary operator, memiliki 2 anak.*/
            else {
                if (op == '+') {
                    return evaluateTree(v.getLeftChild()) + evaluateTree(v.getRightChild());
                }
                else if (op == '-') {
                    return evaluateTree(v.getLeftChild()) - evaluateTree(v.getRightChild());
                }
                else if (op == '*') {
                    return evaluateTree(v.getLeftChild()) * evaluateTree(v.getRightChild());
                }
                else if (op == '/') {
                    return evaluateTree(v.getLeftChild()) / evaluateTree(v.getRightChild());
                }
                else if (op == '^' ) {
                    double i = Math.pow(evaluateTree(v.getLeftChild()), evaluateTree(v.getRightChild()));
                    return i;
                }
                
            }
        }
        return (double)v.getValue();
    }

    /**
     * Beberapa operator di replace dengan konstanta agar lebih mudah dibaca
     *
     * @param str adalah Initial State dari String, berisikan ekspresi matematika.
     * @return String yang sudah valid.
     */
    private String replace(String str){
        if(str.charAt(0)=='-'){
            str = PLUSMIN + str.substring(1);
        }
        str = str.replaceAll("√", "" + SQRT);
        str = str.replaceAll("sin", SIN + "");
        str = str.replaceAll("cos", COS + "");
        str = str.replaceAll("tan", TAN + "");
        str = str.replaceAll("~", "" + PLUSMIN);
        str = str.replaceAll("log", LOG + "");
        str = str.replaceAll("ln", LN + "");
        str = str.replaceAll("exp", EXP + "");
        
        return str;
    }

    /**
     * Menghapus tanda buka kurung dan tutup kurung
     * Misalnya input adalah (((1 + (2 - 3)))), maka output expression nya 1 + (2 - 3).
     *
     * @param str yaitu ekspresi matematika yang akan di validasi
     * @return string yang valid untuk di lakukan operasi matematika.
     */
    private String getValidSubstring(String str) {
        while (str.charAt(0) == '(' && str.charAt(str.length() -1) == ')') {
            str = str.substring(1, str.length() - 1);
        }
        return str;
    }
    
    /*Method untuk menghitung nilai faktorial
    * @param num adalah integer yang akan di hitung nilai faktorialnya
    * Method bekerja secara rekursif
    */
    static int faktorial(int num) {
        if(num == 0){
            return 1;
        }
        return num * faktorial(num-1);
  }

}


