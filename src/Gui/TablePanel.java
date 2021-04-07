package Gui;

import Model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

//Class that has the table Model
public class TablePanel extends JPanel {

    private JTable table;
    private PersonTableModel tableModel;
    private JPopupMenu popupMenu;

    public TablePanel() {

        this.tableModel = new PersonTableModel();
        this.table = new JTable(tableModel);
        this.popupMenu = new JPopupMenu();

        JMenuItem removeItem = new JMenuItem("Delete row");
        popupMenu.add(removeItem);

        //Similar to Event Listener. Menu will be brought up if right mouse button is clicked
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON3){
                    popupMenu.show(table, e.getX(),e.getY());
                }
            }
        });

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
