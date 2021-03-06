package Gui;

import Controller.Controller;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class MainFrame extends JFrame {

    private final TextPanel textPanel;
    private final ToolBar toolBar;
    private final FormPanel formPanel;
    private final JFileChooser fileChooser;
    private final Controller controller;
    private final TablePanel tablePanel;
    private final PrefsDialog prefsDialog;
    private Preferences preferences;


    public MainFrame() {

        super("Hello World");

        setLayout(new BorderLayout());

        this.toolBar = new ToolBar();
        this.textPanel = new TextPanel();
        this.formPanel = new FormPanel();
        this.fileChooser = new JFileChooser();
        this.controller = new Controller();
        this.tablePanel = new TablePanel();
        this.prefsDialog = new PrefsDialog(this);

        preferences = Preferences.userRoot().node("DB");

        tablePanel.setData(controller.getPeople());

        tablePanel.setPersonTableListener(new PersonTableListener() {
            public void rowDeleted(int row){
                controller.removePerson(row);
            }
        });

        prefsDialog.setPrefsListener(new PrefsListener(){
            @Override
            public void preferencesSet(String user, String password, int port) {
                preferences.put("user",user);
                preferences.put("password",password);
                preferences.putInt("port",port);
            }
        });

        String user = preferences.get("user","");
        String password = preferences.get("password","");
        Integer port = preferences.getInt("port",3306);

        prefsDialog.setDefault(user,password,port);
        fileChooser.addChoosableFileFilter(new PersonFileFilter()); //filtering the files to choose from

        setJMenuBar(createMenuBar());

        toolBar.setToolbarListener(new ToolbarListener() {
            @Override
            public void saveEventOccured() {
                try {
                    controller.connect();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(MainFrame.this,"Cannot connect to database ","Database connection problem ",JOptionPane.ERROR_MESSAGE);
                }
                try {
                    controller.save();
                } catch (SQLException throwables) {
                    JOptionPane.showMessageDialog(MainFrame.this,"Unable to save to database ","Database connection problem ",JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void refreshEventOccured() {
                try {
                    controller.connect();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(MainFrame.this,"Cannot connect to database ","Database connection problem ",JOptionPane.ERROR_MESSAGE);
                }
                try {
                    controller.load();
                } catch (SQLException throwables) {
                    JOptionPane.showMessageDialog(MainFrame.this,"Unable to load from database ","Database connection problem ",JOptionPane.ERROR_MESSAGE);
                }
                tablePanel.refresh();
            }
        });

        formPanel.setFormListener(new FormListener() {

            @Override
            public void formEventOccurred(FormEvent e) {
                controller.addPerson(e);
                tablePanel.refresh(); //Method refreshes the data
            }
        });

        add(toolBar, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
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
        JMenuItem prefsItem = new JMenuItem("Preferences...");

        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);

        showMenu.add(showFormItem);
        windowMenu.add(showMenu);
        windowMenu.add(prefsItem);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        prefsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prefsDialog.setVisible(true);
            }
        });

        showFormItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();

                formPanel.setVisible(menuItem.isSelected());
            }
        });

        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);

        prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK)); //adding new accelerator. Basically shortcut

        //This method will open the systems files
        importDataItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //this tells the dialog where to appear and what its parent class is
               if (fileChooser.showOpenDialog(MainFrame.this) ==JFileChooser.APPROVE_OPTION){
                   try {
                       controller.loadFromFile(fileChooser.getSelectedFile());
                       tablePanel.refresh();
                   } catch (IOException ioException) {
                       JOptionPane.showMessageDialog(MainFrame.this,"Could not load data from file","ERROR", JOptionPane.ERROR_MESSAGE);
                   }
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
                   try {
                       controller.saveToFile(fileChooser.getSelectedFile());
                   } catch (IOException ioException) {
                       JOptionPane.showMessageDialog(MainFrame.this,"Could not save data to file","ERROR", JOptionPane.ERROR_MESSAGE);
                   }
                   System.out.println(fileChooser.getSelectedFile()); //Returns file object
               }

            }
        });

        //This method will is able to exit the programme. User will be prompted to confirm action
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

