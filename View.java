/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;
import java.lang.Integer;

/* Java swing untuk membuat window */


// TODO: add checking validity of the expression when clicking equals
// TODO: fix entering with keyboard
// TODO: allow leading minus for negative numbers.

/**
 * This class displays a simple calculator.
 *
 * @author DZAKIRA RIZKA
 * @author Luka Kralj
 */
public class View extends KeyAdapter {

    /*Panjang Lebar Button*/
    private static final int BUTTON_WIDTH = 60;
    private static final int BUTTON_WIDTH2 = 60*2 + 6;
    private static final int BUTTON_HEIGHT = 60;

    //private static final char[] validChars = {0,1,2,3,4,5,6,7,8,9,'(',')','+','-','*','/', '%', Evaluator.SQUARE, Evaluator.SQRT};

    private JTextArea displayArea; // Results shown here.
    private JTextField inputField; // Expressions entered here.
    // on entering '(' increase by 1, on entering ')' decrease by 1; check if 0 before adding.
    // for expression to be valid, counter must equal 0
    private int bracketCounter;
    //  if a decimal point has just been entered we cannot enter it again until we reach the next operator
    private boolean decimalPointEntered;
    // used for undo; stores previous entries
    private Stack<String> previousExpressions;

    /**
     * Construct the basic calculator.
     */
    public View() {
        decimalPointEntered = false;
        bracketCounter = 0;
        previousExpressions = new Stack<>();

        JFrame frame = new JFrame("DR. Calculator");
        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        /*Container yang mewadahi semuanya*/
        Container contentPane = frame.getContentPane();
        
        /* Panel itu semacam box */
        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
        all.setBorder(new EmptyBorder(5, 5, 5, 5)); // 7 itu ukuran margin
        //all.setBackground(Color.BLACK);

        JPanel labels = new JPanel(new BorderLayout());
       // labels.setBorder(new LineBorder(Color.GRAY, 1));

        displayArea = new JTextArea();
        displayArea.setOpaque(true);
        displayArea.setBackground(Color.WHITE);
        displayArea.setLineWrap(false);
        displayArea.setEditable(false);

        /*SROLL*/
        JScrollPane display = new JScrollPane(displayArea);
        display.setPreferredSize(new Dimension(frame.getWidth(), 100));
        display.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        display.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        labels.add(display, BorderLayout.CENTER);

        /*INPUT AREA*/
        inputField = new JTextField("");
        inputField.setPreferredSize(new Dimension(frame.getWidth(), 30));
        inputField.setBackground(Color.WHITE);
        inputField.setEditable(false);
        inputField.setHorizontalAlignment(SwingConstants.RIGHT);
        labels.add(inputField, BorderLayout.SOUTH);

        all.add(Box.createVerticalStrut(10)); // buat jaraknya
        all.add(labels);
        all.add(Box.createVerticalStrut(10)); // buat jaraknya

        JPanel buttons = createButtons();
        all.add(buttons);
        
        
        contentPane.add(all);
        all.setBackground(new Color(204,229,255));
        
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.addKeyListener(this);
        frame.setFocusableWindowState(true);
        frame.setAutoRequestFocus(true);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * BUTTON untuk input
     *
     * @return Panel with all the buttons to be added to the calculator.
     */
    private JPanel createButtons() {

        JPanel allFlow = new JPanel(new FlowLayout());
        JPanel allAll = new JPanel();
        allAll.setLayout(new BoxLayout(allAll, BoxLayout.LINE_AXIS));

        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));


        JPanel topButtons = new JPanel();
        /*GridLayout(int rows, int columns, int hgap, int vgap)*/
        topButtons.setLayout(new GridLayout(4,6,2,2));
        

        //FIRST FISRT ROW
        
         JButton sin = new JButton("sin");
        sin.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        sin.setBackground(Color.BLACK);
        //sin.addActionListener(e -> updateInputField("sin("));
        sin.addActionListener(e -> trigonometryClicked("sin"));
        sin.setForeground(Color.WHITE);
        topButtons.add(sin);

        JButton cos = new JButton("cos");
        cos.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        cos.setBackground(Color.BLACK);
        cos.addActionListener(e -> trigonometryClicked("cos"));
        cos.setForeground(Color.WHITE);
        topButtons.add(cos);

        JButton tan = new JButton("tan");
        tan.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        tan.setBackground(Color.BLACK);
        tan.addActionListener(e -> trigonometryClicked("tan"));
        tan.setForeground(Color.WHITE);
        topButtons.add(tan);
//        JButton plus = new JButton("+");
//        plus.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
//        plus.setBackground(Color.BLACK);
//        plus.addActionListener(e -> operatorClicked("+"));
//        plus.setForeground(Color.WHITE);
//        topButtons.add(plus);
//        
//        JButton minus = new JButton("-");
//        minus.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
//        minus.setBackground(Color.BLACK);
//        minus.addActionListener(e -> operatorClicked("-"));
//        //minus.addActionListener(e -> updateInputField("-"));
//        minus.setForeground(Color.WHITE);
//        topButtons.add(minus);
//        
//        JButton multiply = new JButton("*");
//        multiply.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
//        multiply.setBackground(Color.BLACK);
//        multiply.addActionListener(e -> operatorClicked("*"));
//        multiply.setForeground(Color.WHITE);
//        topButtons.add(multiply);
        
        JButton clear = new JButton("C");
        clear.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        clear.setBackground(Color.WHITE);
        clear.addActionListener(e -> clearInputField());
        clear.setForeground(Color.BLACK);
        topButtons.add(clear);
        
//        JButton divide = new JButton("/");
//        divide.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
//        divide.setBackground(Color.BLACK);
//        divide.addActionListener(e -> operatorClicked("/"));
//        divide.setForeground(Color.WHITE);
//        topButtons.add(divide);
        
        JButton undo = new JButton();
        undo.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        undo.setBackground(Color.WHITE);
        ImageIcon undoIcon = new ImageIcon(getClass().getResource("undo_arrow.jpg"));
        undo.setIcon(undoIcon);
        undo.addActionListener(e -> undoClicked());
        topButtons.add(undo);

        JButton delete = new JButton();
        delete.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        delete.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        delete.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        delete.setBackground(Color.WHITE);
        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("delete.png"));
        delete.setIcon(deleteIcon);
        delete.addActionListener(e -> deleteClicked());
        topButtons.add(delete);
        
        
        // FIRST ROW:

        JButton seven = new JButton("7");
        seven.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        seven.setBackground(Color.GRAY);
        seven.addActionListener(e -> updateInputField("7"));
        seven.setForeground(Color.WHITE);
        topButtons.add(seven);

        JButton eight = new JButton("8");
        eight.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        eight.setBackground(Color.GRAY);
        eight.addActionListener(e -> updateInputField("8"));
        eight.setForeground(Color.WHITE);
        topButtons.add(eight);

        JButton nine = new JButton("9");
        nine.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        nine.setBackground(Color.GRAY);
        nine.addActionListener(e -> updateInputField("9"));
        nine.setForeground(Color.WHITE);
        topButtons.add(nine);
        
         JButton plus = new JButton("+");
        plus.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        plus.setBackground(Color.BLACK);
        plus.addActionListener(e -> operatorClicked("+"));
        plus.setForeground(Color.WHITE);
        topButtons.add(plus);
        
        JButton minus = new JButton("-");
        minus.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        minus.setBackground(Color.BLACK);
        minus.addActionListener(e -> operatorClicked("-"));
        //minus.addActionListener(e -> updateInputField("-"));
        minus.setForeground(Color.WHITE);
        topButtons.add(minus);
        
        JButton multiply = new JButton("*");
        multiply.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        multiply.setBackground(Color.BLACK);
        multiply.addActionListener(e -> operatorClicked("*"));
        multiply.setForeground(Color.WHITE);
        topButtons.add(multiply);


        // SECOND ROW:

        JButton four = new JButton("4");
        four.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        four.setBackground(Color.GRAY);
        four.addActionListener(e -> updateInputField("4"));
        four.setForeground(Color.WHITE);
        topButtons.add(four);

        JButton five = new JButton("5");
        five.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        five.setBackground(Color.GRAY);
        five.addActionListener(e -> updateInputField("5"));
        five.setForeground(Color.WHITE);
        topButtons.add(five);

        JButton six = new JButton("6");
        six.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        six.setBackground(Color.GRAY);
        six.addActionListener(e -> updateInputField("6"));
        six.setForeground(Color.WHITE);
        topButtons.add(six);

        JButton openingBracket = new JButton("(");
        openingBracket.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        openingBracket.setBackground(Color.BLACK);
        openingBracket.addActionListener(e -> openingBracketClicked());
        openingBracket.setForeground(Color.WHITE);
        topButtons.add(openingBracket);

        JButton closingBracket = new JButton(")");
        closingBracket.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        closingBracket.setBackground(Color.BLACK);
        closingBracket.addActionListener(e -> closingBracketClicked());
        closingBracket.setForeground(Color.WHITE);
        topButtons.add(closingBracket);
        
        JButton divide = new JButton("/");
        divide.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        divide.setBackground(Color.BLACK);
        divide.addActionListener(e -> operatorClicked("/"));
        divide.setForeground(Color.WHITE);
        topButtons.add(divide);
        
//        JButton factorial = new JButton("!");
//        factorial.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
//        factorial.setBackground(Color.BLACK);
//        factorial.addActionListener(e -> factorialClicked());
//        factorial.setForeground(Color.WHITE);
//        topButtons.add(factorial);

        // THIRD ROW:

        JButton one = new JButton("1");
        one.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        one.setBackground(Color.GRAY);
        one.addActionListener(e -> updateInputField("1"));
        one.setForeground(Color.WHITE);
        topButtons.add(one);

        JButton two = new JButton("2");
        two.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        two.setBackground(Color.GRAY);
        two.addActionListener(e -> updateInputField("2"));
        two.setForeground(Color.WHITE);
        topButtons.add(two);

        JButton three = new JButton("3");
        three.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        three.setBackground(Color.GRAY);
        three.addActionListener(e -> updateInputField("3"));
        three.setForeground(Color.WHITE);
        topButtons.add(three);

        JButton square = new JButton("<html>x<sup>2</sup></html>");
        square.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        square.setBackground(Color.BLACK);
        square.addActionListener(e -> squareClicked());
        square.setForeground(Color.WHITE);
        topButtons.add(square);
        
        JButton powers = new JButton("<html>x<sup>p</sup></html>");
        powers.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        powers.setBackground(Color.BLACK);
        powers.addActionListener(e -> operatorClicked("p"));
        powers.setForeground(Color.WHITE);
        topButtons.add(powers);

        JButton sqrt = new JButton("√");
        sqrt.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        sqrt.setBackground(Color.BLACK);
        sqrt.addActionListener(e -> sqrtClicked());
        sqrt.setForeground(Color.WHITE);
        topButtons.add(sqrt);

        all.add(topButtons);


        JPanel bottomRow = new JPanel();
        bottomRow.setLayout(new FlowLayout());

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1,9,2,1));

        
        JButton decimalPoint = new JButton(".");
        decimalPoint.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        decimalPoint.setBackground(Color.BLACK);
        decimalPoint.addActionListener(e -> decimalPointClicked());
        decimalPoint.setForeground(Color.WHITE);
        buttons.add(decimalPoint);
        
        
        JButton zero = new JButton("0");
        zero.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        zero.setBackground(Color.GRAY);
        zero.addActionListener(e -> updateInputField("0"));
        zero.setForeground(Color.WHITE);
        buttons.add(zero);

        JButton percent = new JButton("%");
        percent.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        percent.setBackground(Color.BLACK);
        percent.addActionListener(e -> percentClicked());
        percent.setForeground(Color.WHITE);
        buttons.add(percent);

        JButton factorial = new JButton("!");
        factorial.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        factorial.setBackground(Color.BLACK);
        factorial.addActionListener(e -> factorialClicked());
        factorial.setForeground(Color.WHITE);
        buttons.add(factorial);

        bottomRow.add(buttons);

        JButton equals = new JButton("=");
        equals.setPreferredSize(new Dimension(BUTTON_WIDTH2, BUTTON_HEIGHT));
        equals.setBackground(new Color(190, 34, 34));
        equals.addActionListener(e -> equalsClicked());
        equals.setForeground(Color.WHITE);
        bottomRow.add(equals);

        all.add(bottomRow);
        
        JPanel bottomRow2 = new JPanel();
        bottomRow.setLayout(new FlowLayout());


        JButton binary = new JButton("toBin");
        binary.setPreferredSize(new Dimension(BUTTON_WIDTH2, BUTTON_HEIGHT));
        binary.setBackground(new Color(190, 34, 34));
        binary.addActionListener(e -> convertClicked("bin"));
        binary.setForeground(Color.WHITE);
        bottomRow2.add(binary);

        JButton hexa = new JButton("toHexa");
        hexa.setPreferredSize(new Dimension(BUTTON_WIDTH2, BUTTON_HEIGHT));
        hexa.setBackground(new Color(190, 34, 34));
        hexa.addActionListener(e -> convertClicked("hexa"));
        hexa.setForeground(Color.WHITE);
        bottomRow2.add(hexa);
        
        JButton octa = new JButton("toOcta");
        octa.setPreferredSize(new Dimension(BUTTON_WIDTH2, BUTTON_HEIGHT));
        octa.setBackground(new Color(190, 34, 34));
        octa.addActionListener(e -> convertClicked("octal"));
        octa.setForeground(Color.WHITE);
        bottomRow2.add(octa);

        all.add(bottomRow2);
        
        topButtons.setBackground(new Color(204,229,255));
        all.setBackground(new Color(204,229,255));
        allAll.setBackground(new Color(204,229,255));
        allFlow.setBackground(new Color(204,229,255));
        bottomRow.setBackground(new Color(204,229,255));
        bottomRow2.setBackground(new Color(204,229,255));
        buttons.setBackground(new Color(204,229,255));
        

        allAll.add(all);
        allFlow.add(allAll);
        return allFlow;
    }
    
    
    /*BARU SAMPE SINI*/

    /* Clear input yangg dimasukan */
    private void clearInputField(){
        inputField.setText("");
        enableDecimalPoint();
    }

    /* Menambahkan oerator/operand ke ekspresi matematika (newCharacter) */
    private void updateInputField(String newCharacter) {
        inputField.setText(inputField.getText() + newCharacter);
    }

    /**
     * Menambahkan hasil setelah ekspresi matematika
     *
     * @param expression Expression to be added.
     * @param result Result of the expression.
     */
    private void updateDisplayField(String expression, String result) {
        displayArea.append(expression + "\n= " + result + "\n\n");
    }

    /* Ketika meng klik undo button. Apabila ada previousExpression maka ditampilkan */
    private void undoClicked() {
        if (previousExpressions.isEmpty()) {
            clearInputField();
        }
        else {
            inputField.setText(previousExpressions.pop());
        }
    }

     /* Menghapus 1 karakter ekspresi matematika */
    private void deleteClicked() {
        // Delete the last character.
        String str = inputField.getText();
        if (!str.equals("")) {
            inputField.setText(str.substring(0, str.length() - 1));
        }
    }

    /* Ketika klik pangkat 2 */
    private void squareClicked() {
        if (canEnterSymbol()) {
            inputField.setText(inputField.getText() + "^2");
        }
        else {
            showWarning();
        }
    }
    
    
    /* Ketika klik 'akar' */
    private void sqrtClicked() {
        if (!canEnterSymbol()) {
            inputField.setText(inputField.getText() + "√");
            bracketCounter++;
        }
        else {
            showWarning();
        }
    }

    /**
     * Tanda titik (untuk desimal) di klik.
     * It is added to the expression if and only if the expression would still be valid.
     */
    private void decimalPointClicked() {
        char last = ' '; // placeholder
        if (!inputField.getText().equals("")) {
            last = inputField.getText().charAt(inputField.getText().length()-1);
        }
        if (!decimalPointEntered && !(last == ' ' || last == '+' || last == '-' || last == '/' || last == '*' || last == '(' || last == ')' || last == '%' || last =='p' || last =='!')) {
            decimalPointEntered = true;
            updateInputField(".");
        }
        else {
            showWarning();
        }
    }

    /* Boleh mendambahkan angka desimal */
    private void enableDecimalPoint() {
        decimalPointEntered = false;
    }

    /**
     * Menambahkan simbol persen kedalam ekspresi matematika
     * Persentase akan diubah menjadi desimal ketika operasi matematika dilakukan
     * 50% = 0.5
     */
    private void percentClicked() {
        if (canEnterSymbol()) {
            updateInputField("%");
            enableDecimalPoint();
        }
        else {
            showWarning();
        }
    }
    
    /*Ketika klik faktorial */
    private void factorialClicked() {
        if (canEnterSymbol()) {
            updateInputField("!");
            enableDecimalPoint();
        }
        else {
            showWarning();
        }
    }
    
    
 /* Ketika simbol '=' di klik maka operasi matematika akan di proses */
    private void equalsClicked() {  
        String str = inputField.getText();
        System.out.println(str);
        if (str.equals("")) {
            return;
        }
        previousExpressions.push(str);
        Process proses = new Process(str);
        String result = proses.getResult();
        if (result.startsWith("-")) {
            result = result ;
        }
        updateDisplayField(str, result);
        clearInputField();
        updateInputField(result);

    }
    
    
    /* Ketika simbol '=' di klik maka operasi matematika akan di proses */
    private void convertClicked(String type) {
        String str = inputField.getText();
        //System.out.println(str);
        if (str.equals("")) {
            return;
        }
        previousExpressions.push(str);
        Process proses = new Process(str);
        String result = proses.getResult();
        String hasil = null;
        
        /*Ini biar kalau angkanya double di cast dulu ke int
          soalnya bin hexa octa bisa cuman kalau dari integer */
        double temp = Double.parseDouble(str);
        int num = (int)temp;
        
        switch(type){
            case "bin" : hasil = Integer.toBinaryString(num);
                         break;
            case "hexa" :hasil = Integer.toHexString(num);
                         break;
            case "octal" :hasil = Integer.toOctalString(num);
                         break;
        }
        
        if (result.startsWith("-")) {
            result = result ;
        }
        updateDisplayField(str, hasil);
        clearInputField();
        updateInputField(result);

    }

    /**
     * Menambahkan operator ke dalam ekspresi mtk
     *
     * @param newCharacter Operator to add.
     */
    private void operatorClicked(String newCharacter) {
        if(canEnterSymbol()) {
            //System.out.println("masuk1");
            updateInputField(newCharacter);
            enableDecimalPoint();
        }
        else if (newCharacter.equals("-") && (inputField.getText().equals("") || inputField.getText().charAt(inputField.getText().length()-1) == '(')) {
            // System.out.println("masuk2");
            updateInputField("-");
            enableDecimalPoint();
        }
        else {
            showWarning();
        }
    }
    
    
    private void trigonometryClicked(String newCharacter){ 
        if (!canEnterSymbol()) {
            inputField.setText(inputField.getText() + newCharacter  + "(");
            bracketCounter++;
            enableDecimalPoint();
        }
        else {
            showWarning();
        }
    
    }
    
    
    /* Menambah '(' */
    private void openingBracketClicked() {
        String expression = inputField.getText();
        if (expression.equals("")) {
            updateInputField("(");
            bracketCounter++;
            enableDecimalPoint();
            return;
        }

        char last = expression.charAt(expression.length()-1);
        if (last == '+' || last == '-' || last == '/' || last == '*' || last == '(' || last=='p') {
            updateInputField("(");
            bracketCounter++;
            enableDecimalPoint();
        }
        else {
            showWarning();
        }
    }

    /* Menambahkan ')' */
    private void closingBracketClicked() {
        if (bracketCounter > 0 && canEnterSymbol()) {
            updateInputField(")");
            bracketCounter--;
            enableDecimalPoint();
        }
        else {
            showWarning();
        }
    }

    /**
     * Validasi Operator dan Operand
     * @return True jika simbol bisa ditambahkan
     */
    private boolean canEnterSymbol() {
        String expression = inputField.getText();
        
        System.out.println(expression);
        if (expression.equals("")) {
            return false;
        }

        char last = expression.charAt(expression.length()-1);
        if (last == '+' /*|| last == '-'*/ || last == '/' || last == '*' || last == '(') {
            return false;
        }
        return true;
    }
    

    /* Ketika operator/operand tidak valid */
    private void showWarning() {
        JOptionPane.showMessageDialog(null, "Invalid operation.", "Invalid operator/operand.", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * This method is intended to accept inputs entered with the keyboard.
     * There is still quite a lot of problems with it. It seems that the key codes are not matching with the constants.
     * Also the focus is lost once the user clicks one of the buttons.
     *
     * TODO: debug
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (c == '+' ||
                c == '-' ||
                c == '*' ||
                c == '/' ||
                c == 'p'){
            operatorClicked("" + c);
        }
        else if (c == 's' ||
                    c == 'c' ||
                    c == 't' ){
            trigonometryClicked(c + "");
        }
        else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
            clearInputField();
        }
        else if (e.getKeyCode() == KeyEvent.VK_DECIMAL || c == '.') {
            decimalPointClicked();
        }
        else if (c == KeyEvent.VK_UNDO) {
            undoClicked();
        }
        else if (c == KeyEvent.VK_V) {
            sqrtClicked();
        }
//        else if (e.getKeyChar() == Evaluator.SQUARE) {
//            squareClicked();
//        }
        else if (c == KeyEvent.VK_LEFT_PARENTHESIS) {
            openingBracketClicked();
        }
        else if (c == KeyEvent.VK_RIGHT_PARENTHESIS) {
            closingBracketClicked();
        }
        else if (c == KeyEvent.VK_ENTER) {
            equalsClicked();
        }
        else if (e.getKeyChar() == '%') {
            percentClicked();
        }
        else if (e.getKeyChar() == '!') {
            factorialClicked();
        }
        else if (c == KeyEvent.VK_0 ||
                c == KeyEvent.VK_1 ||
                c == KeyEvent.VK_2 ||
                c == KeyEvent.VK_3 ||
                c == KeyEvent.VK_4 ||
                c == KeyEvent.VK_5 ||
                c == KeyEvent.VK_6 ||
                c == KeyEvent.VK_7 ||
                c == KeyEvent.VK_8 ||
                c == KeyEvent.VK_9 ||
                c == KeyEvent.VK_NUMPAD0 ||
                c == KeyEvent.VK_NUMPAD1 ||
                c == KeyEvent.VK_NUMPAD2 ||
                c == KeyEvent.VK_NUMPAD3 ||
                c == KeyEvent.VK_NUMPAD4 ||
                c == KeyEvent.VK_NUMPAD5 ||
                c == KeyEvent.VK_NUMPAD6 ||
                c == KeyEvent.VK_NUMPAD7 ||
                c == KeyEvent.VK_NUMPAD8 ||
                c == KeyEvent.VK_NUMPAD9) {
            updateInputField("" + e.getKeyChar());
        }
        else {
            // Do nothing.
        }
    

    }

}

