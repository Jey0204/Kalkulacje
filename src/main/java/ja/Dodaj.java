// Dodaj.java
package ja;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dodaj extends JFrame {
    private JTextField serviceNameField;
    private JButton addButton;
    private OnServiceAddedListener listener;

    public Dodaj(OnServiceAddedListener listener) {
        this.listener = listener;
        initUI();
    }

    private void initUI() {
        serviceNameField = new JTextField(20);
        addButton = new JButton("Dodaj");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serviceName = serviceNameField.getText();
                if (listener != null) {
                    listener.onServiceAdded(serviceName);
                }
                dispose();
            }
        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Usługa:"));
        add(serviceNameField);
        add(addButton);

        pack();
        setLocationRelativeTo(null);
    }

    public interface OnServiceAddedListener {
        void onServiceAdded(String serviceName);
    }
}
