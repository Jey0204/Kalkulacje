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

public class Interface extends JFrame implements Dodaj.OnServiceAddedListener {

    private File desktopDir;

    private JLabel napis;
    private JLabel napisUlica;
    private JTextField Ulica;
    private JLabel napisblad;
    private JButton plus;
    private JButton addButton;

    private JLabel jed;
    private JLabel danem;
    private JLabel danezl;

    private int id = 0;
    private int ilosc = 12;
    private JTextField searchField;
    private JList<String> suggestionList;
    private DefaultListModel<String> listModel;

    private JLabel[] napisP = new JLabel[ilosc];
    private JLabel[] wyk = new JLabel[ilosc];
    private JTextField[] jednostki = new JTextField[ilosc];
    private JTextField[] zloty = new JTextField[ilosc];
    private JTextField[] m = new JTextField[ilosc];
    private JTextField[] tynk = new JTextField[ilosc];
    private JLabel[] m2 = new JLabel[ilosc];
    private JLabel[] usunP = new JLabel[ilosc];
    private JLabel[] zl = new JLabel[ilosc];

    private String[] suggestions = { "Tynkowanie", "Malowanie", "Kafelkowanie", "Układanie paneli", "Szpachlowanie",
            "Sucha zabudowa", "Termoizolacja ", "Murowanie", "Montaż stolarki drzwiowej", "Montaż parapetów",
            "Montaż listew przypodłogowych", "Osadzenie narożników" };

    public Interface() {
        setSize(700, 820);
        setTitle("Wycena");

        setLayout(new BorderLayout());

        danem = new JLabel("metry");
        danezl = new JLabel("cena");
        jed = new JLabel("mb/szt/m");

        napis = new JLabel("Witaj Lukas!");
        napisblad = new JLabel("Wprowadź nazwę ulicy");
        napisUlica = new JLabel("Ulica: ");

        searchField = new JTextField();
        Ulica = new JTextField();
        addButton = new JButton("Dodaj");
        plus = new JButton("+");

        danem.setBounds(315, 220, 50, 20);
        danezl.setBounds(455, 220, 50, 20);
        jed.setBounds(377, 220, 60, 20);

        plus.setBounds(571, 125, 49, 40);
        napis.setBounds(280, 20, 150, 40);
        napisUlica.setBounds(75, 70, 200, 40);
        Ulica.setBounds(150, 70, 474, 40);
        napisblad.setBounds(150, 97, 200, 40);
        napisblad.setForeground(Color.RED);
        napisblad.setVisible(false);
        addButton.setBounds(250, 725, 200, 40);
        searchField.setBounds(70, 125, 500, 40);

        Font font = new Font("Arial", Font.BOLD, 25);
        napis.setFont(font);
        Font font1 = new Font("Arial", Font.PLAIN, 20);
        napisUlica.setFont(font1);

        Ulica.setFont(font1);
        searchField.setFont(font1);
        plus.setFont(font1);
        addButton.setFont(font1);

        for (int i = 0; i < ilosc; i++) {
            napisP[i] = new JLabel("");
            napisP[i].setBounds(72, 240 + i * 40, 205, 40);
            napisP[i].setFont(font1);
            add(napisP[i]);
        }

        for (int i = 0; i < ilosc; i++) {
            wyk[i] = new JLabel("!");
            wyk[i].setBounds(60, 240 + i * 40, 205, 40);
            wyk[i].setFont(font1);
            wyk[i].setForeground(Color.RED);
            wyk[i].setVisible(false);
            add(wyk[i]);
        }

        for (int i = 0; i < ilosc; i++) {
            m[i] = new JTextField();
            m[i].setBounds(280, 240 + i * 40, 100, 40);
            m[i].setFont(font1);
            m[i].setVisible(false);
            m[i].setHorizontalAlignment(JTextField.RIGHT);
            add(m[i]);
        }

        for (int i = 0; i < ilosc; i++) {
            zloty[i] = new JTextField();
            zloty[i].setBounds(425, 240 + i * 40, 100, 40);
            zloty[i].setFont(font1);
            zloty[i].setVisible(false);
            zloty[i].setHorizontalAlignment(JTextField.RIGHT);
            add(zloty[i]);
        }

        for (int i = 0; i < ilosc; i++) {
            tynk[i] = new JTextField();
            tynk[i].setBounds(180, 240 + i * 40, 100, 40);
            tynk[i].setFont(font1);
            tynk[i].setVisible(false);
            tynk[i].setHorizontalAlignment(JTextField.RIGHT);
            add(tynk[i]);
        }

        for (int i = 0; i < ilosc; i++) {
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

        for (int i = 0; i < ilosc; i++) {
            zl[i] = new JLabel("zł");
            zl[i].setBounds(530, 240 + i * 40, 100, 40);
            zl[i].setFont(font1);
            zl[i].setVisible(false);
            add(zl[i]);
        }

        for (int i = 0; i < ilosc; i++) {
            jednostki[i] = new JTextField();
            jednostki[i].setBounds(381, 240 + i * 40, 43, 40);
            jednostki[i].setFont(font1);
            jednostki[i].setVisible(false);
            jednostki[i].setHorizontalAlignment(JTextField.RIGHT);
            add(jednostki[i]);
        }

        danem.setVisible(false);
        danezl.setVisible(false);
        jed.setVisible(false);

        add(danem);
        add(danezl);
        add(jed);

        repaint();

        add(napis);
        add(Ulica);
        add(addButton);
        add(napisUlica);
        add(plus);
        add(napisblad);

        for (int i = 0; i < ilosc; i++) {
            final int index = i;
            usunP[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    if (id == 1) {
                        System.out.println(id);

                        danem.setVisible(false);
                        danezl.setVisible(false);
                        jed.setVisible(false);
                        zl[index].setVisible(false);
                        usunP[index].setVisible(false);
                        jednostki[index].setVisible(false);
                        napisP[index].setVisible(false);
                        tynk[index].setVisible(false);
                        m[index].setVisible(false);
                        zloty[index].setVisible(false);
                        id = 0;
                        System.out.println(id);
                    } else {
                        for (int k = index; k < id; k++) {
                            napisP[k].setText(napisP[k + 1].getText());
                            zloty[k].setText(zloty[k + 1].getText());
                            tynk[k].setText(tynk[k + 1].getText());
                            m[k].setText(m[k + 1].getText());

                        }
                        napisP[id - 1].setVisible(false);
                        zloty[id - 1].setVisible(false);
                        tynk[id - 1].setVisible(false);
                        m[id - 1].setVisible(false);
                        usunP[id - 1].setVisible(false);
                        jednostki[id - 1].setVisible(false);
                        zl[id - 1].setVisible(false);

                        id--;
                    }

                }
            });
        }

        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dodaj dodajFrame = new Dodaj(Interface.this);
                ImageIcon icon = new ImageIcon("C:\\Users\\jessi\\OneDrive\\Pulpit\\projekt_inzynierski\\folder.jpg");
                dodajFrame.setIconImage(icon.getImage()); // Ustawienie ikony dla okna

                dodajFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                dodajFrame.setVisible(true);
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
                        while (id < 15) {
                            napisP[id].setText(selectedItem);
                            napisP[id].setVisible(true);

                            if (selectedItem.equals("Tynkowanie")) {
                                tynk[id].setVisible(true);
                            }
                            jednostki[id].setVisible(true);
                            m[id].setVisible(true);
                            zl[id].setVisible(true);
                            zloty[id].setVisible(true);
                            usunP[id].setVisible(true);

                            danem.setVisible(true);
                            danezl.setVisible(true);
                            jed.setVisible(true);

                            id++;
                            System.out.println(id);

                            break;
                        }

                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(suggestionList);
        scrollPane.setBounds(70, 168, 550, 50);
        setLayout(null);

        add(searchField);
        add(scrollPane);
        setVisible(true);
        setLocationRelativeTo(null);

        desktopDir = new File("C:\\Users\\jessi\\OneDrive\\Pulpit\\Kalkulacje");
        // przycisk dodawania

        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                for (int k = 0; k < id; k++) {
                    if (Ulica.getText().equals("")) {
                        napisblad.setVisible(true);
                    } else if (m[k].getText().equals("") || zl[k].getText().equals("")) {
                        wyk[k].setVisible(true);
                    } else {
                        wyk[k].setVisible(false);
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

                            run1.setText("Kosztorys Wykonana usługa na ulicy " + nazwa + ".\n");
                            run1.setFontFamily("Arial");
                            run1.setFontSize(14);
                            Double sumaCalkowita = 0.0;

                            XWPFParagraph paragraf = document.createParagraph();
                            XWPFRun runn = textParagraph.createRun();

                            runn.setText(" ");
                            runn.setFontFamily("Arial");
                            runn.setFontSize(14);

                            for (int i = 0; i < id; i++) {

                                XWPFParagraph paragraph = document.createParagraph();
                                XWPFRun run = paragraph.createRun();

                                String mText = m[i].getText().replace(",", ".");
                                String zlotyText = zloty[i].getText().replace(",", ".");

                                Double mValue = Double.parseDouble(mText);
                                Double zlotyValue = Double.parseDouble(zlotyText);
                                // nie tobi tego
                                if (jednostki[i].getText().equals("m") || jednostki[i].getText().equals("")) {
                                    jednostki[i].setText("m²");
                                } else if (jednostki[i].getText().equals("szt")) {
                                    jednostki[i].setText("szt.");
                                } else if (jednostki[i].getText().equals("mb")) {
                                    jednostki[i].setText("mb.");
                                }
                                String jedno = jednostki[i].getText();

                                Double suma = mValue * zlotyValue;
                                sumaCalkowita += suma;
                                run.setFontFamily("Arial");
                                if (napisP[i].getText().equals("Tynkowanie")) {
                                    if (tynk[i].getText().equals("")) {
                                        run.setText(
                                                napisP[i].getText() + " "
                                                        + String.format(Locale.getDefault(), "%,.2f", mValue) + jedno
                                                        + " x "
                                                        + String.format(Locale.getDefault(), "%,.2f", zlotyValue)
                                                        + "zł = "
                                                        + String.format(Locale.getDefault(), "%,.2f", suma) + "zł");
                                    } else {
                                        run.setText(
                                                napisP[i].getText() + " " + tynk[i].getText() + " "
                                                        + String.format(Locale.getDefault(), "%,.2f", mValue) + jedno
                                                        + " x "
                                                        + String.format(Locale.getDefault(), "%,.2f", zlotyValue)
                                                        + "zł = "
                                                        + String.format(Locale.getDefault(), "%,.2f", suma) + "zł");
                                    }

                                } else {
                                    run.setText(
                                            napisP[i].getText() + " "
                                                    + String.format(Locale.getDefault(), "%,.2f", mValue) + jedno
                                                    + " x "
                                                    + String.format(Locale.getDefault(), "%,.2f", zlotyValue) + "zł = "
                                                    + String.format(Locale.getDefault(), "%,.2f", suma) + "zł");
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
                            run3.setBold(true);

                            File file = new File(desktopDir, nazwa + ".docx");

                            // nie calkiem działa

                            // int counter = 1;
                            // while (file.exists()) {
                            // String newFileName = nazwa + " (" + counter + ").docx";
                            // counter++;
                            // System.out.println(newFileName);
                            // file = new File(desktopDir, newFileName);

                            // }

                            FileOutputStream out = new FileOutputStream(file);
                            document.write(out);
                            out.close();

                            Desktop desktop = Desktop.getDesktop();

                            if (desktop.isSupported(Desktop.Action.OPEN)) {
                                File folder = new File(desktopDir.getAbsolutePath());

                                try {
                                    desktop.open(folder);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });

    }

    @Override
    public void onServiceAdded(String serviceName) {
        for (int i = 0; i < 15; i++) {
            if (napisP[i].getText().isEmpty()) {
                napisP[i].setText(serviceName);
                m[i].setVisible(true);
                zloty[i].setVisible(true);
                jednostki[i].setVisible(true);
                zl[i].setVisible(true);
                usunP[i].setVisible(true);
                id++;
                break;
            }
        }
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
