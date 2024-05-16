package ja;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Locale;

import javax.swing.event.*;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Interface extends JFrame {

    private File desktopDir;

    private JLabel napis;
    private JLabel napisUlica;
    private JTextField Ulica;
    private JLabel napisblad;
    private JButton plus;
    private JButton addButton;

    private int id = 0;
    private JTextField searchField;
    private JList<String> suggestionList;
    private DefaultListModel<String> listModel;

    private JLabel[] napisP = new JLabel[15];
    private JLabel[] wyk = new JLabel[15];
    private JTextField[] zloty = new JTextField[15];
    private JTextField[] m = new JTextField[15];
    private JTextField[] tynk = new JTextField[15];
    private JLabel[] m2 = new JLabel[15];
    private JLabel[] usunP = new JLabel[15];
    private JLabel[] zl = new JLabel[15];
    private int index;

    private int ileUsunietych = 0;
    private String nowe;

    private String[] suggestions = { "Tynkowanie", "Malowanie", "Kafelkowanie", "Układanie paneli", "Szpachlowanie",
            "Sucha zabudowa", "Termoizolacja ", "Murowanie", "Montaż stolarki drzwiowej", "Montaż parapetów",
            "Montaż listew przypodłogowych", "Osadzenie narożników" };

    public Interface() {
        setSize(700, 1000);
        setTitle("Wycena");

        setLayout(new BorderLayout());

        napis = new JLabel("Witaj Lukas!");
        napisblad = new JLabel("Wprowadź nazwę ulicy");
        napisUlica = new JLabel("Ulica: ");

        searchField = new JTextField();
        Ulica = new JTextField();
        addButton = new JButton("Dodaj");
        plus = new JButton("+");

        plus.setBounds(571, 150, 49, 40);
        napis.setBounds(290, 30, 150, 40);
        napisUlica.setBounds(75, 90, 200, 40);
        Ulica.setBounds(150, 90, 474, 40);
        napisblad.setBounds(150, 117, 200, 40);
        napisblad.setForeground(Color.RED);
        napisblad.setVisible(false);
        addButton.setBounds(250, 680, 200, 40);
        searchField.setBounds(70, 150, 500, 40);

        Font font = new Font("Arial", Font.BOLD, 25);
        napis.setFont(font);
        Font font1 = new Font("Arial", Font.PLAIN, 20);
        napisUlica.setFont(font1);

        Ulica.setFont(font1);
        searchField.setFont(font1);
        plus.setFont(font1);
        addButton.setFont(font1);

        for (int i = 0; i < 15; i++) {
            napisP[i] = new JLabel("");
            napisP[i].setBounds(75, 240 + i * 40, 205, 40);
            napisP[i].setFont(font1);
            add(napisP[i]);
        }

        for (int i = 0; i < 15; i++) {
            wyk[i] = new JLabel("!");
            wyk[i].setBounds(60, 240 + i * 40, 205, 40);
            wyk[i].setFont(font1);
            wyk[i].setForeground(Color.RED);
            wyk[i].setVisible(false);
            add(wyk[i]);
        }

        for (int i = 0; i < 15; i++) {
            m[i] = new JTextField();
            m[i].setBounds(280, 240 + i * 40, 100, 40);
            m[i].setFont(font1);
            m[i].setVisible(false);
            m[i].setHorizontalAlignment(JTextField.RIGHT);
            add(m[i]);
        }

        for (int i = 0; i < 15; i++) {
            zloty[i] = new JTextField();
            zloty[i].setBounds(420, 240 + i * 40, 100, 40);
            zloty[i].setFont(font1);
            zloty[i].setVisible(false);
            zloty[i].setHorizontalAlignment(JTextField.RIGHT);
            add(zloty[i]);
        }

        for (int i = 0; i < 15; i++) {
            tynk[i] = new JTextField();
            tynk[i].setBounds(180, 240 + i * 40, 100, 40);
            tynk[i].setFont(font1);
            tynk[i].setVisible(false);
            add(tynk[i]);
        }

        for (int i = 0; i < 15; i++) {
            usunP[i] = new JLabel("usuń");
            usunP[i].setBounds(560, 240 + i * 40, 100, 40);
            usunP[i].setFont(font1);
            usunP[i].setVisible(false);
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
            zl[i] = new JLabel("zł");
            zl[i].setBounds(522, 240 + i * 40, 100, 40);
            zl[i].setFont(font1);
            zl[i].setVisible(false);
            add(zl[i]);
        }

        for (int i = 0; i < 15; i++) {
            m2[i] = new JLabel("m²");
            m2[i].setBounds(390, 240 + i * 40, 100, 40);
            m2[i].setFont(font1);
            m2[i].setVisible(false);
            add(m2[i]);
        }

        repaint();

        add(napis);
        add(Ulica);
        add(addButton);
        add(napisUlica);
        add(plus);
        add(napisblad);

        for (int i = 0; i < 15; i++) {
            index = i; // Zapamiętaj indeks dla ActionListenera wewnątrz pętli
            usunP[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Usuń odpowiednie etykiety, pola tekstowe i zaktualizuj zmienne

                    String napis;
                    String metry;
                    String tynki;
                    String zlo;

                    if (m[index + 1].isVisible()) {
                        if (tynk[index + 1].isVisible()) {
                            tynk[index].setVisible(true);
                            m[index].setVisible(true);
                            zloty[index].setVisible(true);
                            m2[index].setVisible(true);
                            zl[index].setVisible(true);
                            usunP[index].setVisible(true);
                        } else {

                            m[index].setVisible(true);
                            zloty[index].setVisible(true);
                            m2[index].setVisible(true);
                            zl[index].setVisible(true);
                            usunP[index].setVisible(true);

                        }

                    } else {
                        tynk[index].setVisible(false);
                        m[index].setVisible(false);
                        zloty[index].setVisible(false);
                        m2[index].setVisible(false);
                        zl[index].setVisible(false);
                        usunP[index].setVisible(false);

                    }

                    napis = napisP[index + 1].getText();
                    metry = m[index + 1].getText();
                    tynki = tynk[index + 1].getText();
                    zlo = zl[index + 1].getText();

                    napisP[index].setText(napis);
                    m[index].setText(metry);
                    tynk[index].setText(tynki);
                    zl[index].setText(zlo);

                    ileUsunietych++;

                }
            });
        }

        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Dodaj ok = new Dodaj(nowe);

                ok.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ok.setVisible(true);
            }
        });

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
                                if (selectedItem.equals("Tynkowanie")) {
                                    tynk[i].setVisible(true);
                                }
                                m2[i].setVisible(true);
                                m[i].setVisible(true);
                                zl[i].setVisible(true);
                                zloty[i].setVisible(true);
                                usunP[i].setVisible(true);
                                id++;
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
                if (Ulica.getText().equals("")) {
                    napisblad.setVisible(true);
                } else {
                    napisblad.setVisible(false);

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

                        XWPFRun run1 = textParagraph.createRun();
                        run1.setText("Wykonana usługa na ulicy " + nazwa + ".\n");
                        run1.setFontFamily("Arial");
                        run1.setFontSize(14);
                        Double sumaCalkowita = 0.0;
                        for (int i = 0; i < id - ileUsunietych; i++) {
                            XWPFParagraph paragraph = document.createParagraph();
                            XWPFRun run = paragraph.createRun();

                            String mText = m[i].getText().replace(",", ".");
                            String zlotyText = zloty[i].getText().replace(",", ".");

                            if (!mText.isEmpty() && !zlotyText.isEmpty()) {
                                Double mValue = Double.parseDouble(mText);
                                Double zlotyValue = Double.parseDouble(zlotyText);

                                Double suma = mValue * zlotyValue;
                                sumaCalkowita += suma;
                                run.setFontFamily("Arial");
                                if (napisP[i].getText().equals("Tynkowanie")) {
                                    run.setText(
                                            napisP[i].getText() + " " + tynk[i].getText() + " "
                                                    + String.format(Locale.getDefault(), "%,.2f", mValue) + " m² x "
                                                    + String.format(Locale.getDefault(), "%,.2f", zlotyValue) + "zł = "
                                                    + String.format(Locale.getDefault(), "%,.2f", suma) + "zł");
                                } else {
                                    run.setText(
                                            napisP[i].getText() + " "
                                                    + String.format(Locale.getDefault(), "%,.2f", mValue) + " m² x "
                                                    + String.format(Locale.getDefault(), "%,.2f", zlotyValue) + "zł = "
                                                    + String.format(Locale.getDefault(), "%,.2f", suma) + "zł");
                                }
                            }
                            run.setFontSize(14);
                        }

                        XWPFParagraph paragraph1 = document.createParagraph();
                        XWPFRun run2 = paragraph1.createRun();
                        run2.setText("_________________________________________________________");
                        run2.setFontFamily("Arial");
                        run2.setFontSize(14);

                        XWPFParagraph paragraph2 = document.createParagraph();
                        XWPFRun run3 = paragraph2.createRun();
                        run3.setText("Suma: "
                                + String.format(Locale.getDefault(), "%,.2f", sumaCalkowita) + "zł");
                        run3.setFontFamily("Arial");
                        run3.setFontSize(14);

                        // Zapisywanie dokumentu do pliku
                        File file = new File(desktopDir, nazwa + ".docx");
                        FileOutputStream out = new FileOutputStream(file);
                        document.write(out);
                        out.close();

                        // Pobierz deskryptor pulpitu
                        Desktop desktop = Desktop.getDesktop();

                        // Sprawdź, czy deskryptor pulpitu obsługuje otwieranie folderów
                        if (desktop.isSupported(Desktop.Action.OPEN)) {
                            // Utwórz obiekt File dla folderu, w którym został zapisany plik
                            File folder = new File(desktopDir.getAbsolutePath());

                            // Spróbuj otworzyć folder za pomocą deskryptora pulpitu
                            try {
                                desktop.open(folder);
                            } catch (IOException ex) {
                                // Obsłuż wyjątek, jeśli nie udało się otworzyć folderu
                                ex.printStackTrace();
                            }
                        } else {
                            // Obsłuż sytuację, gdy deskryptor pulpitu nie obsługuje otwierania folderów
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
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
