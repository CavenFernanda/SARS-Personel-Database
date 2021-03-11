package swing.pkg3.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToolBar extends JPanel implements ActionListener {

    private final JButton helloButton;
    private final JButton byeButton;

    private StringListener textListener;

    public ToolBar() {
        
        setBorder(BorderFactory.createEtchedBorder()); //creating a border for the toolBar. Its essenstially a line
        this.helloButton = new JButton("Hello");
        this.byeButton = new JButton("Button");

        this.helloButton.addActionListener(this);
        this.byeButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(this.helloButton);
        add(this.byeButton);
    }

    //accepts any object that implements the StringLister interface
    public void setStringLister(StringListener textListener) {

        this.textListener = textListener;
    }

    //
    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clicked = (JButton) e.getSource();

        if (clicked == this.helloButton) {
            if (textListener != null) {
                textListener.textEmitted("Hello!\n");
            }
        } else if (clicked == this.byeButton) {
            if (textListener != null) {
                textListener.textEmitted("Bye!\n");
            }
        }
    }

}
