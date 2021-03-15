package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    private final TextPanel textPanel;
    private final ToolBar toolBar;
    private final FormPanel formPanel;
    private JFileChooser fileChooser;

    public MainFrame() {

        super("Hello World");
        this.toolBar = new ToolBar();
        this.textPanel = new TextPanel();
        this.formPanel = new FormPanel();
        this.fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter()); //filtering the files to choose from


        setJMenuBar(createMenuBar());

        toolBar.setStringLister(textPanel::apendText);

        formPanel.setFormListener(new FormListener() {

            @Override
            public void formEventOccurred(FormEvent e) {

                String name = e.getName();
                String occupation = e.getOccupation();
                int ageCategory = e.getAgeCategory();
                String employment = e.getEmployment();
                String gender = e.getGender();

                textPanel.apendText("Name: " + name + " \nOccupation: " + occupation + "\nAge Category: " + ageCategory + "\nEmployment Status: " + employment + "\nGender: " + gender + "\n-----------------------------\n");
            }
        });

        add(toolBar, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        add(formPanel, BorderLayout.WEST);

        setMinimumSize(new Dimension(500,400)); //setting up the minimum size
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //This class creates the menu
    private JMenuBar createMenuBar() {

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exportDataItem = new JMenuItem("Export Data...");
        JMenuItem importDataItem = new JMenuItem("Import Data...");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();//create a space between groups
        fileMenu.add(exitItem);

        JMenu windowMenu = new JMenu("Window");
        JMenu showMenu = new JMenu("Show");

        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);

        showMenu.add(showFormItem);
        windowMenu.add(showMenu);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        showFormItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();

                formPanel.setVisible(menuItem.isSelected());
            }
        });

        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        //This method will open the systems files
        importDataItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //this tells the dialog where to appear and what its parent class is
               if (fileChooser.showOpenDialog(MainFrame.this) ==JFileChooser.APPROVE_OPTION){
                   System.out.println(fileChooser.getSelectedFile()); //Returns file object
               } 
                
            }  
        });
        
        exportDataItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //this tells the dialog where to appear and what its parent class is
               if (fileChooser.showSaveDialog(MainFrame.this) ==JFileChooser.APPROVE_OPTION){
                   System.out.println(fileChooser.getSelectedFile()); //Returns file object
               } 
                
            }  
        });
        
        //This method will is able to exit the programe. User will be prompted to confirmr action
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to exit the application?", "Confirm Exit",JOptionPane.OK_CANCEL_OPTION);
               
               if (action == JOptionPane.OK_OPTION){
                System.exit(0);
               }
            }
        });

        return menuBar;
    }
}
