import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;


public class MainFrame extends JFrame implements KeyListener, ActionListener {

    //Specifying the size of the window
    public static final  int DEFAULT_HEIGHT = 400;
    public static final  int DEFAULT_WIDTH = 500;
    private Pad pad = new Pad();

    JTextField field = new JTextField();
    private int counter = 0;

    public MainFrame() throws HeadlessException {
        super("Simple CALC");

        setLocation(550,300);
        //setDefaultLookAndFeelDecorated(true);
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creating panel and set layout
        JPanel panel = new JPanel();
        GridLayout grid = new GridLayout(1,1);
        panel.setLayout(grid);

        //Settings TextField -> field
        field.setEnabled(false);
        field.setBackground(Color.LIGHT_GRAY);
        field.setHorizontalAlignment(JTextField.RIGHT);
        field.setFont(new Font(field.getFont().getName(),Font.PLAIN,60));

        //Ddd KeyListener
        pad.b1.addKeyListener(this);
        pad.b2.addKeyListener(this);
        pad.b3.addKeyListener(this);
        pad.b4.addKeyListener(this);
        pad.b5.addKeyListener(this);
        pad.b6.addKeyListener(this);
        pad.b7.addKeyListener(this);
        pad.b8.addKeyListener(this);
        pad.b9.addKeyListener(this);
        pad.b0.addKeyListener(this);
        pad.bAddition.addKeyListener(this);
        pad.bSubtract.addKeyListener(this);
        pad.bMulti.addKeyListener(this);
        pad.bdivide.addKeyListener(this);
        pad.bequal.addKeyListener(this);
        pad.bClear.addKeyListener(this);
        pad.bLeftBracket.addKeyListener(this);
        pad.bRightBracket.addKeyListener(this);
        pad.bper.addKeyListener(this);
        pad.bsign.addKeyListener(this);
        pad.bdot.addKeyListener(this);
        pad.bRemoveLast.addKeyListener(this);

        //Add ActionListener
        pad.b1.addActionListener(this);
        pad.b2.addActionListener(this);
        pad.b3.addActionListener(this);
        pad.b4.addActionListener(this);
        pad.b5.addActionListener(this);
        pad.b6.addActionListener(this);
        pad.b7.addActionListener(this);
        pad.b8.addActionListener(this);
        pad.b9.addActionListener(this);
        pad.b0.addActionListener(this);
        pad.bClear.addActionListener(this);
        pad.bAddition.addActionListener(this);
        pad.bSubtract.addActionListener(this);
        pad.bMulti.addActionListener(this);
        pad.bdivide.addActionListener(this);
        pad.bequal.addActionListener(this);
        pad.bLeftBracket.addActionListener(this);
        pad.bRightBracket.addActionListener(this);
        pad.bRightBracket.setEnabled(false);
        pad.bper.addActionListener(this);
        pad.bsign.addActionListener(this);
        pad.bdot.addActionListener(this);
        pad.bRemoveLast.addActionListener(this);

        //Focus on the bequal button
        pad.b1.setFocusable(false);
        pad.b2.setFocusable(false);
        pad.b3.setFocusable(false);
        pad.b4.setFocusable(false);
        pad.b5.setFocusable(false);
        pad.b6.setFocusable(false);
        pad.b7.setFocusable(false);
        pad.b8.setFocusable(false);
        pad.b9.setFocusable(false);
        pad.b0.setFocusable(false);
        pad.bClear.setFocusable(false);
        pad.bAddition.setFocusable(false);
        pad.bSubtract.setFocusable(false);
        pad.bMulti.setFocusable(false);
        pad.bdivide.setFocusable(false);
        pad.bLeftBracket.setFocusable(false);
        pad.bRightBracket.setFocusable(false);
        pad.bper.setFocusable(false);
        pad.bsign.setFocusable(false);
        pad.bdot.setFocusable(false);
        pad.bRemoveLast.setFocusable(false);
//        pad.bequal.setFocusable(false);

        panel.add(field);
        add(panel, BorderLayout.NORTH);
        add(pad);

        //configure frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String po = e.getActionCommand();

        //Making first number negative or positive
        if(e.getActionCommand() == "\u00B1"){
            if (field.getText().charAt(0) != '-'){
                field.setText('-' + field.getText());
            }else if (field.getText().charAt(0) == '-'){
                field.setText(field.getText().substring(1));
            }
        }

        //Removing last insert number/sign
        if(e.getActionCommand() == "<"){
            field.setText(field.getText().substring(0,field.getText().length()-1));
        }

        //Clear textField
        if (e.getActionCommand() == "C"){
            counter -= counter;
        }

        //Increase the left bracket counter when is clicked
        if (e.getActionCommand() == "("){
            counter += 1;

        }
        //Decrease left bracket counter when right bracket is clicked
        if (e.getActionCommand() == ")" && counter >= 0){
            counter -= 1;

        }

        //If left bracket counter is 0, right bracket is disable
        //If left bracket counter is greater then 0, right bracket is enabled
        if (counter == 0){
            pad.bRightBracket.setEnabled(false);
        }else if (counter > 0){
            pad.bRightBracket.setEnabled(true);
        }

        //Convert UNICODE character to simple character
        if (e.getActionCommand() == "\u00D7"){
            po = "*";
        }
        if (e.getActionCommand() == "\u00F7"){
            po = "/";
        }if (e.getActionCommand() == "\u2212"){
            po = "-";
        }

        //Creating a proper string of characters that can be passed to the method of calculation result
        if (e.getActionCommand() != "=" && e.getActionCommand() != "<" && e.getActionCommand() != "\u00B1") {

            if (e.getActionCommand() == "+" && field.getText().length() == 0){  //First sign ca't be '+'
                field.setText("");

            }else if (e.getActionCommand() == "+" && field.getText().charAt(field.getText().length()-1) == '+'
            || e.getActionCommand() == "\u2212" && field.getText().charAt(field.getText().length()-1) == '-'){  //Can't add mor them one '-' or '+'
                field.setText(field.getText());

            }else if(e.getActionCommand() == "+" && field.getText().charAt(field.getText().length()-1) == '-'){ //If last sign is '-' and user input '+' the sign are exchange
                field.setText(field.getText().substring(0,field.getText().length()-1));
                field.setText(field.getText() + '+');

            }else if (e.getActionCommand() == "\u2212" && field.getText().charAt(field.getText().length()-1) == '+'){ //If last sign is '+' and user input '-' the sign are exchange
                field.setText(field.getText().substring(0,field.getText().length()-1));
                field.setText(field.getText() + '-');

            }else {
                field.setText(field.getText() + po); //Adding next character entered by the user to the TextField
            }
        }

        //Creating String
        String text = field.getText();

        switch (e.getActionCommand()) {
            case "=":
                //Check last sign is a number
                if (text.charAt(text.length()-1) == '+' || text.charAt(text.length()-1) == '-' || text.charAt(text.length()-1) == '*'
                    || text.charAt(text.length()-1) == '/' || text.charAt(text.length()-1) == '.') {
                    text = text.substring(0, text.length() - 1);
                }

                sumList(bracketsList(bracketsCheck(text))); //Transfer the expression to be calculated
                break;
            case "C":
                field.setText("");
                break;
        }
    }

    public void sumList(String text){

        //Create lists to hold characters and numbers
        ArrayList<Double> numberList = new ArrayList<>();
        ArrayList<Character> oprList = new ArrayList<>();
        String num = "";    //String to create numbers and save them to numberList

        for (int i = 0; i < text.length(); i++){
            if (text.charAt(i) != '+' && text.charAt(i) != '-' && text.charAt(i) != '*' && text.charAt(i) != '/'
                    && text.charAt(i) != '=' && text.charAt(i) != '(' && text.charAt(i) != ')' && text.charAt(i) != '%') {
                num += text.charAt(i);  //Creating numbers in a variable - num
            }else if (num != ""){
                numberList.add(Double.parseDouble(num));    //Adding number created in variable 'num' to the numList

                if (text.charAt(i) != '=') {
                    oprList.add(text.charAt(i));    //Adding an operator to the oprList
                }
                num = "";   //Resetting the 'num' variable after adding the number and operator

                if (i < text.length()-1 && text.charAt(i+1) == '-'){    //Creating negative numbers
                    num += text.charAt(i+1);
                    num += text.charAt(i+2);
                    i += 2;
                }
            }

            //Creating negative numbers when first character in variable 'text' is minus
            if (i == 0 && text.charAt(0) == '-' && text.charAt(1) != '+' && text.charAt(1) != '-' &&
                    text.charAt(1) != '*' && text.charAt(1) != '/' && text.charAt(1) != '=' && text.charAt(1) != '('
                    && text.charAt(1) != ')'){
                num += text.charAt(0);
                num += text.charAt(1);
                i = 1;
            }
        }

        sum(numberList, oprList); //Submission of created lists to the method for calculating the result
    }

    public double sum(ArrayList<Double> num, ArrayList<Character> opr){

        Double sum = 0.0;
        ArrayList<Character> idexOpr = opr;
        ArrayList<Double> idexNum = num;

        //Calculate percent of number
        for (int i = 0; i < idexOpr.size(); i++){
            if (idexOpr.get(i) == '%' && idexOpr.size() < idexNum.size()){  //example: 30%10 = 3
                idexNum.set(i+1,(idexNum.get(i+1)*10/100)*idexNum.get(i)/10);
                idexNum.remove(i);
                idexOpr.remove(i);
                i -= 1;
            }else if (idexOpr.get(i) == '%' && idexOpr.size() >= idexNum.size() && idexOpr.get(idexOpr.size()-1) == '%'){   //example: 10+30% = 13
                idexNum.set(i,(idexNum.get(i-1)*10/100)*idexNum.get(i)/10);
                idexOpr.remove(i);
                i -= 1;
            }
        }

        //Multiplying numbers
        for (int i = 0; i < idexOpr.size(); i++){
            if (idexOpr.get(i) == '*'){
                idexNum.set(i+1,idexNum.get(i) * idexNum.get(i + 1));
                idexNum.remove(i);
                idexOpr.remove(i);
                i -= 1;
            }
        }

        //Dividing numbers
        for (int i = 0; i < idexOpr.size(); i++){
            if (idexOpr.get(i) == '/'){
                idexNum.set(i+1,idexNum.get(i) / idexNum.get(i + 1));
                idexNum.remove(i);
                idexOpr.remove(i);
                i -= 1;
            }
        }

        //Calculate adding and subtraction
        for (int i = 0; i < idexOpr.size(); i++){
            if (i < idexOpr.size() & idexOpr.get(i) == '+'){
                sum = idexNum.get(i) + idexNum.get(i + 1);
                idexNum.set(i + 1,sum);
            }
            if (i < idexOpr.size() & idexOpr.get(i) == '-'){
                sum = (idexNum.get(i)*10 - idexNum.get(i + 1)*10)/10;
                idexNum.set(i + 1,sum);
            }
        }

        //If in idexNum left one number,this is the result
        if (idexNum.size() > 0 && idexOpr.size() == 0){
            sum = idexNum.get(0);
        }

        //If the result is not floating point, it is shown as int, otherwise it is double
        if (sum == sum.intValue()){
            field.setText(String.valueOf(sum.intValue()));
        }else {
            field.setText(String.valueOf(sum));
        }

        return sum;
    }
    public String bracketsList(String text){    //Calculate result  inside the bracket

        ArrayList<Double> numberList = new ArrayList<>();
        ArrayList<Character> oprList = new ArrayList<>();
        String num = "";
        String text2 = "";

        for (int j = 0; j < text.length(); j++) {
            if (text.charAt(j) != '(' && text.charAt(j) != ')') {   //Adding numbers to text2 that are outside of parentheses
                text2 += text.charAt(j);
            }else if (text.charAt(j+1) == '('){ //Adding to text2 '(' if there is another after first one
                text2 += text.charAt(j);
            }else if (text.charAt(j-1) == ')'){
                text2 += text.charAt(j);
            }
            else if (text.charAt(j) == '(' && text.charAt(j+1) != '(') {    //
                for (int i = j+1; i < text.length(); i++) {
                    if (text.charAt(i) == ')'){ //calculating activities from the most internal brackets and adding the result to text2 as the next digit
                        if(num != "") {
                            numberList.add(Double.parseDouble(num));
                            num = "";
                        }
                        text2 += sum(numberList, oprList);
                        numberList.clear();
                        oprList.clear();
                        j = i;
                        break;
                    }else if (text.charAt(i) != '+' && text.charAt(i) != '-' && text.charAt(i) != '*' &&
                            text.charAt(i) != '/' && text.charAt(i) != '=' && text.charAt(i) != '%') {
                        num += text.charAt(i);  //Extracting numbers from inside the parentheses and assigning them to the 'num' variable
                    }else if (text.charAt(i) == '-' && text.charAt(i+1) >=0){   //Adding to variable sum negative numbers
                        num += text.charAt(i);
                        num += text.charAt(i+1);
                        i += 1;
                    }else {
                        numberList.add(Double.parseDouble(num));    //Adding numbers created in variable 'num' to numberList
                        if (text.charAt(i) != '=') {
                            oprList.add(text.charAt(i));        //Adding operator to oprList
                        }
                        num = "";   //Resetting variable 'num'
                    }
                }
            }else {
                continue;
            }
        }

        // checking if there are brackets in the character string,
        // if there are any brackets, the string of characters will be returned to the bracketList
        // method in order to remove them
        for (int i = 0; i < text2.length(); i++){
            if (text2.charAt(i) == '(' || text2.charAt(i) == ')'){
                return bracketsList(text2);
            }
        }
        return text2;   //returs String without brackets
    }

    private String bracketsCheck(String text){  //Check whether at the end of the equation there are no brackets missing
        String text2 = text;

        for (int i = 0; i < this.counter;i++){
            text2 += ')';
        }
        this.counter -= this.counter;
        text2 +='=';
        return text2;
    }

    @Override
    public void keyTyped(KeyEvent e) {  //Use keyboard to insert numbers and operator

        if (e.getKeyChar() == '1') {
            pad.b1.doClick();
        }
        if (e.getKeyChar() == '2') {
            pad.b2.doClick();
        }
        if (e.getKeyChar() == '3') {
            pad.b3.doClick();
        }
        if (e.getKeyChar() == '4') {
            pad.b4.doClick();
        }
        if (e.getKeyChar() == '5') {
            pad.b5.doClick();
        }
        if (e.getKeyChar() == '6') {
            pad.b6.doClick();
        }
        if (e.getKeyChar() == '7') {
            pad.b7.doClick();
        }
        if (e.getKeyChar() == '8') {
            pad.b8.doClick();
        }
        if (e.getKeyChar() == '9') {
            pad.b9.doClick();
        }
        if (e.getKeyChar() == '0') {
            pad.b0.doClick();
        }
        if (e.getKeyChar() == KeyEvent.VK_DELETE) {
            pad.bClear.doClick();
        }
        if (e.getKeyChar() == '+') {
            pad.bAddition.doClick();
        }
        if (e.getKeyChar() == '-') {
            pad.bSubtract.doClick();
        }
        if (e.getKeyChar() == '*') {
            pad.bMulti.doClick();
        }
        if (e.getKeyChar() == '/') {
            pad.bdivide.doClick();
        }
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            pad.bequal.doClick();
        }
        if (e.getKeyChar() == '(') {
            pad.bLeftBracket.doClick();
        }
        if (e.getKeyChar() ==')') {
            pad.bRightBracket.doClick();
        }
        if (e.getKeyChar() == '%') {
            pad.bper.doClick();
        }
        if (e.getKeyChar() == '\u00B1') {
            pad.bsign.doClick();
        }
        if (e.getKeyChar() =='.') {
            pad.bdot.doClick();
        }
        if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
            pad.bRemoveLast.doClick();
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
