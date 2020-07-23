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
     * mengonversi dari angka yang bertipe data string menjadi double
     * jika string berformat misalnya, ((34.5)). tanda kurung akan diganti oleh spasi.
     * spasi yang terletak paling awal dan paling akhir pada string akan dihapus
     */
    private double extractNumber(String str) {
        str = str.replace('(', ' ');
        str = str.replace(')', ' ');
        str = str.trim();
        return Double.parseDouble(str);
    }

    /* Membuat tree baru dengan operator sebagai rootnya
     * ekspresi matematika di validasi oleh method getValidSubstring dengan cara menghapus tanda kurung pada ekspresi matematika.
     * index berisi posisi operator yang didapatkan dari method getOperator.
     * jika operator adalah unary operator maka anak kanan akan null
     * tree akan terbentuk dengan operator sebagai root dan operand sebagai anak kiri.
     * jika operator bukan unary maka anak kiri dan anak kanan akan terisi
     * dengan leftExpression berisi ekspresi sebelum operator dan rightExpression berisi ekspresi sesudah operator.
     * tree akan terbentuk dengan operator sebagai root, leftExpression sebagai anak kiri, dan rightExpression berisi anak kanan.
     */
    private void makeTree(String expression) {
        expression = getValidSubstring(expression);
        int index = getOperator(expression);
        if (index == -1) 
            /* jika index berisi -1 artinya tidak ada operator, hanya operand */
            double value = extractNumber(expression);
            tree = new BinaryTree(new Node(value, null, null));
        }
        else {
            char c = expression.charAt(index);
            if (isOther(c)) {
                // Jika unary operator
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
                //Jika binary operator
                String leftExpression = getValidSubstring(expression.substring(0, index));
                String rightExpression = getValidSubstring(expression.substring(index + 1, expression.length()));
                tree = new BinaryTree(new Node(c, getNewChild(leftExpression), getNewChild(rightExpression)));
            }
        }
    }

    /** 
     * mengembalikan posisi operator. 
     * operator yang ada di dalam tanda kurung tidak akan di return(akan dilewat) karena operatornya akan diproses terakhir.
     * operator yang pertama di cek adalah plus dan minus,lalu cek perkalian, pembagian, dan pangkat, lalu operator unary.
     * Jika operator berada dalam kurung maka tidak akan didahulukan karena derajatnya paling tinggi
     * jika tidak ada operator maka akan mengembalikan nilai -1.
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
     * @param operator adalah operator yang akan di cek
     * @return True jika operator adalah unary operator
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


