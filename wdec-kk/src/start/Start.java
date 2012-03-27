package start;

import gui.mainPanel;

public class Start {
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new mainPanel().setVisible(true);
            }
        });
    }
}
