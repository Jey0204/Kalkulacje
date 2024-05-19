package ja;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        Interface ok = new Interface();

        ok.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ustawienie domyślnego zachowania aplikacji po zamknięciu
                                                           // okna
        ImageIcon icon = new ImageIcon("C:\\Users\\jessi\\OneDrive\\Pulpit\\projekt_inzynierski\\folder.jpg");
        ok.setIconImage(icon.getImage()); // Ustawienie ikony dla okna

        ok.setVisible(true);
    }
}
