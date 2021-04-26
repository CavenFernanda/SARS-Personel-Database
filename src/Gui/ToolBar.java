package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToolBar extends JPanel implements ActionListener {

    private final JButton saveButton;
    private final JButton refreshButton;

    private ToolbarListener textListener;

    public ToolBar() {
        
        setBorder(BorderFactory.createEtchedBorder()); //creating a border for the toolBar. Its essenstially a line
        this.saveButton = new JButton("Save");
        this.refreshButton = new JButton("Refresh");

        this.saveButton.addActionListener(this);
        this.refreshButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(this.saveButton);
        add(this.refreshButton);
    }

    //accepts any object that implements the StringLister interface
    public void setToolbarListener(ToolbarListener textListener) {

        this.textListener = textListener;
    }

    //
    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clicked = (JButton) e.getSource();

        if (clicked == this.saveButton) {
            if (textListener != null) {
                textListener.saveEventOccured();
            }
        } else if (clicked == this.refreshButton) {
            if (textListener != null) {
                textListener.refreshEventOccured();
            }
        }
    }

}
