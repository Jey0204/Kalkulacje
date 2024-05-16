package ja;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Dodaj extends JFrame {
    private JTextField Nazwa;
    private JLabel NapisNazwa;
    private JButton dodaj;
    private JLabel blad;

    public Dodaj(String tekst) {
        setSize(400, 400);
        setTitle("Dodaj");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        Nazwa = new JTextField();
        NapisNazwa = new JLabel("Usługa:");
        dodaj = new JButton("Dodaj");
        blad = new JLabel("Uzupełnij");

        dodaj.setBounds(100, 250, 200, 40);
        Nazwa.setBounds(50, 150, 300, 40);
        blad.setBounds(50, 180, 100, 40);
        NapisNazwa.setBounds(50, 100, 200, 40);

        Font font1 = new Font("Arial", Font.PLAIN, 20);
        dodaj.setFont(font1);
        Nazwa.setFont(font1);
        NapisNazwa.setFont(font1);

        blad.setForeground(Color.RED);
        blad.setVisible(false);

        panel.add(blad);
        panel.add(dodaj);
        panel.add(Nazwa);
        panel.add(NapisNazwa);

        add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(null);

        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Nazwa.getText().equals("")) {
                    blad.setVisible(true);
                } else {
                    // Nazwa.setText(tekst);
                    dispose();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Interface frame = new Interface();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
