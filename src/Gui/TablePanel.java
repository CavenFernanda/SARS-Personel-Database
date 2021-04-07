package Gui;

import Model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

                int row = table.rowAtPoint(e.getPoint()); //gives me the table row
                table.getSelectionModel().setSelectionInterval(row,row); //selecting the row that was clicked

                System.out.println(row);
                if(e.getButton() == MouseEvent.BUTTON3){
                    popupMenu.show(table, e.getX(),e.getY());
                }
            }
        });

        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();

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
