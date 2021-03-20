package Gui;

import Model.Person;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//Class that has the table Model
public class TablePanel extends JPanel {

    private JTable table;
    private PersonTableModel tableModel;

    public TablePanel() {

        this.tableModel = new PersonTableModel();
        this.table = new JTable(tableModel);

        setLayout(new BorderLayout());

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<Person> database){
        tableModel.setData(database);
    }

    public void refresh() {
        tableModel.fireTableDataChanged();
    }
}
