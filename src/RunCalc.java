import javax.swing.*;


public class RunCalc {

    public static void main(String[] args){

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch (Exception e){
            System.out.println("Look and Feel not set");
        }

        new MainFrame();

    }
}
