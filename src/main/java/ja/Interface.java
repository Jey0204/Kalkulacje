package ja;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.event.*;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class Interface extends JFrame {

    private File desktopDir;

    private JLabel napis;
    private JLabel napisUlica;
    private JTextField Ulica;

    private JButton plus;
    private JLabel m2;
    private JLabel zl;
    private JButton addButton;

    private JTextField searchField;
    private JList<String> suggestionList;
    private DefaultListModel<String> listModel;

    private String[] suggestions = { "Tynkowanie", "Malowanie", "Kafelkowanie", "Układanie paneli", "Szpachlowanie",
            "Sucha zabudowa", "Termoizolacja ", "Murowanie", "Montaż stolarki drzwiowej", "Montaż parapetów",
            "Montaż listew przypodłogowych", "Osadzenie narożników" };

    public Interface() {
        setSize(700, 1000);
        setTitle("Wycena");

        setLayout(new BorderLayout());

        napis = new JLabel("Witaj Lukas!");
        napisUlica = new JLabel("Ulica: ");

        searchField = new JTextField();
        Ulica = new JTextField();
        addButton = new JButton("Dodaj");
        plus = new JButton("+");

        plus.setBounds(571, 150, 49, 40);
        napis.setBounds(290, 30, 150, 40);
        napisUlica.setBounds(75, 100, 200, 40);
        Ulica.setBounds(150, 100, 474, 40);
        addButton.setBounds(250, 880, 200, 40);
        searchField.setBounds(70, 150, 500, 40);

        Font font = new Font("Arial", Font.BOLD, 25);
        napis.setFont(font);
        Font font1 = new Font("Arial", Font.PLAIN, 20);
        napisUlica.setFont(font1);

        Ulica.setFont(font1);
        searchField.setFont(font1);
        plus.setFont(font1);
        addButton.setFont(font1);

        JLabel[] napisP = new JLabel[15];

        for (int i = 0; i < 15; i++) {
            napisP[i] = new JLabel("");
            napisP[i].setBounds(75, 240 + i * 40, 200, 40);
            napisP[i].setFont(font1);
            add(napisP[i]);
        }

        JLabel[] usunP = new JLabel[15];

        for (int i = 0; i < 15; i++) {
            usunP[i] = new JLabel("usuń");
            usunP[i].setBounds(550, 240 + i * 40, 100, 40);
            usunP[i].setFont(font1);
            usunP[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    ((JLabel) e.getSource()).setForeground(Color.RED);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    ((JLabel) e.getSource()).setForeground(null);
                }
            });
            add(usunP[i]);
        }

        for (int i = 0; i < 15; i++) {
            zl = new JLabel("zł");
            zl.setBounds(440, 240 + i * 40, 100, 40);
            zl.setFont(font1);
            add(zl);
        }

        for (int i = 0; i < 15; i++) {
            m2 = new JLabel("m²");
            m2.setBounds(320, 240 + i * 40, 100, 40);
            m2.setFont(font1);
            add(m2);
        }

        repaint();

        add(napis);
        add(Ulica);
        add(addButton);
        add(napisUlica);
        add(plus);

        listModel = new DefaultListModel<>();
        for (String suggestion : suggestions) {
            listModel.addElement(suggestion);
        }

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSuggestions();
            }
        });

        suggestionList = new JList<>(listModel);
        suggestionList.setFont(new Font("Arial", Font.PLAIN, 20));
        suggestionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        suggestionList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList<String> list = (JList<String>) evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    if (index >= 0) {
                        // po nacisnieciu
                        String selectedItem = listModel.getElementAt(index);
                        searchField.setText("");
                        for (int i = 0; i < 16; i++) {
                            if (napisP[i].getText().isEmpty()) {
                                napisP[i].setText(selectedItem);
                                break;
                            }
                        }

                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(suggestionList);
        scrollPane.setBounds(70, 190, 550, 50);
        setLayout(null);

        add(searchField);
        add(scrollPane);
        setVisible(true);
        setLocationRelativeTo(null);

        desktopDir = new File("C:\\Users\\jessi\\OneDrive\\Pulpit\\Faktura");
        // przycisk dodawania
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nazwa = Ulica.getText();
                    XWPFDocument document = new XWPFDocument();

                    XWPFParagraph imageParagraph = document.createParagraph();
                    imageParagraph.setAlignment(ParagraphAlignment.RIGHT);
                    XWPFRun imageRun = imageParagraph.createRun();
                    String imagePath = "C:\\Users\\jessi\\OneDrive\\Pulpit\\Lukas_projekt\\lukas_projekt\\logojpg.jpg";
                    try (FileInputStream inputStream = new FileInputStream(imagePath)) {
                        imageRun.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_JPEG, "nazwa_obrazu",
                                Units.toEMU(100),
                                Units.toEMU(100));
                    } catch (IOException ed) {
                        ed.printStackTrace();
                    }

                    XWPFParagraph textParagraph = document.createParagraph();
                    XWPFRun textRun = textParagraph.createRun();

                    XWPFRun run1 = textParagraph.createRun();
                    run1.setText("Wykonana usługa na ulicy " + nazwa + ".\nWykonane usługi:");
                    run1.setFontFamily("Arial");
                    run1.setFontSize(14);

                    // Dodawanie tekstu z użyciem znaków nowej linii i innego formatowania
                    XWPFRun run2 = textParagraph.createRun();
                    run2.setText("\nTo jest kolejna linia tekstu.");
                    run2.setFontFamily("Arial");
                    run2.setFontSize(12);

                    // Zapisywanie dokumentu do pliku
                    File file = new File(desktopDir, nazwa + ".docx");
                    FileOutputStream out = new FileOutputStream(file);
                    document.write(out);
                    out.close();

                    System.out.println("Dokument DOCX został pomyślnie utworzony.");
                } catch (Exception ex) {
                    System.out.println("Wystąpił błąd podczas tworzenia dokumentu DOCX: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }

    private void updateSuggestions() {
        listModel.clear();
        String searchText = searchField.getText().toLowerCase();
        for (String suggestion : suggestions) {
            if (suggestion.toLowerCase().startsWith(searchText)) {
                listModel.addElement(suggestion);
            }
        }
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
