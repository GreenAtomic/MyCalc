import javax.swing.*;
import java.awt.*;

public class Pad extends JPanel {


    JButton b1 = new JButton("1");
    JButton b2 = new JButton("2");
    JButton b3 = new JButton("3");
    JButton b4 = new JButton("4");
    JButton b5 = new JButton("5");
    JButton b6 = new JButton("6");
    JButton b7 = new JButton("7");
    JButton b8 = new JButton("8");
    JButton b9 = new JButton("9");
    JButton b0 = new JButton("0");
    JButton bAddition = new JButton("+");
    JButton bSubtract = new JButton("\u2212");     //unicode for minus
    JButton bMulti = new JButton("\u00D7");      //unicode for multiply
    JButton bdivide = new JButton("\u00F7" );  // unicode for divide
    JButton bequal = new JButton("=");
    JButton bClear = new JButton("C");
    JButton bLeftBracket = new JButton("(");
    JButton bRightBracket = new JButton(")");
    JButton bper = new JButton("%");
    JButton bsign = new JButton("\u00B1");
    JButton bdot = new JButton(".");
    JButton bRemoveLast = new JButton("<");

    public Pad() {


        this.setLayout(new GridLayout(6,4));


        add(bRemoveLast);
        add(bper);
        add(bClear);
        add(bdivide);

        add(b7);
        add(b8);
        add(b9);
        add(bMulti);

        add(b4);
        add(b5);
        add(b6);
        add(bSubtract);

        add(b1);
        add(b2);
        add(b3);
        add(bAddition);

        add(bsign);
        add(b0);
        add(bdot);
        add(bequal);

        add(bLeftBracket);
        add(bRightBracket);
    }

}
