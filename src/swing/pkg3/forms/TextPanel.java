package swing.pkg3.forms;

import javax.swing.*;
import java.awt.*;

public class TextPanel extends JPanel{
    
    

    private JTextArea textArea;

    public TextPanel() {

        this.textArea = new JTextArea();
        setLayout(new BorderLayout());

        add(new JScrollPane(textArea), BorderLayout.CENTER); //wrapping/putting the whole text area into a scroll bar. Anything you want to scroll just wrap it

    }

    public void apendText(String text) {

        textArea.append(text);
    }

   
}
