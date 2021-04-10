package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrefsDialog extends JDialog {

    private JButton okButton, cancelButton;
    private JSpinner portSpinner;
    private SpinnerNumberModel spinnerModel;
    private JTextField userField;
    private JPasswordField passwordField;
    private PrefsListener prefsListener;

    public PrefsDialog(JFrame parent){
        super(parent, "Preferences", false);

        okButton = new JButton("Okay");
        cancelButton = new JButton("Cancel");

        spinnerModel = new SpinnerNumberModel(3306,0,9999,1);
        portSpinner = new JSpinner(spinnerModel);

        userField = new JTextField(10);
        passwordField = new JPasswordField(10);

        passwordField.setEchoChar('*');

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridy =0;

        /////////////FIRST ROW////////////////////////
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;

        add(new JLabel("User: "), gc);

        gc.gridx++;
        add(userField,gc);

        /////////////////Next Row////////////////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;

        add(new JLabel("Password: "), gc);

        gc.gridx++;
        add(passwordField,gc);

        /////////////////Next Row////////////////////
        gc.gridy++;

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
                Integer port = (Integer)portSpinner.getValue(); //Get value returns an object and portspinner returns an int. Hence portspinner is being casted to an int

                String user = userField.getText();
                char[] password = passwordField.getPassword();

                if(prefsListener != null){
                    prefsListener.preferencesSet(user,new String(password),port);
                }

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

    public void setDefault(String user, String password, int port){
        userField.setText(user);
        passwordField.setText(password);
        portSpinner.setValue(port);
    }

    public void setPrefsListener(PrefsListener prefsListener) {
        this.prefsListener = prefsListener;
    }
}
