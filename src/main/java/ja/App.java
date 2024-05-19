package ja;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        Interface ok = new Interface();

        ok.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Wyświetlenie okna
        ok.setVisible(true);
    }
}
