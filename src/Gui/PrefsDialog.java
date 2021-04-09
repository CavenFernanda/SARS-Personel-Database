package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrefsDialog extends JDialog {

    private JButton okButton, cancelButton;
    private JSpinner portSpinner;
    private SpinnerNumberModel spinnerModel;

    public PrefsDialog(JFrame parent){
        super(parent, "Preferences", false);

        okButton = new JButton("Okay");
        cancelButton = new JButton("Cancel");

        spinnerModel = new SpinnerNumberModel(3306,0,9999,1);
        portSpinner = new JSpinner(spinnerModel);

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridy =0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;

        add(new JLabel("Port: "), gc);

        gc.gridx++;
        add(portSpinner,gc);

        //////////////////NEXT ROW/////////////////////

        gc.gridy++;

        gc.gridx =0;
        add(okButton,gc);

        gc.gridx++;
        add(cancelButton,gc);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer value = (Integer)portSpinner.getValue(); //Get value returns an object and portspinner returns an int. Hence portspinner is being casted to an int

                System.out.println(value);
                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setSize(400,300);
        setLocationRelativeTo(parent); //Preferences menu will pop up relative to the programme
    }
}
