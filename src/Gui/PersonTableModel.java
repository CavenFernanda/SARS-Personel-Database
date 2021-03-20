package Gui;

import Model.Person;

import javax.swing.table.AbstractTableModel;
import java.util.List;

//Consider this class like a wrapper. It will present the data received from the GUI in my table
public class PersonTableModel extends AbstractTableModel {

    private List<Person> database;
    private String [] colNames = {"ID", "Name", "Occupation", "Age Category", "Employment Status", "Citizenship", "Tax ID"};

    public PersonTableModel(){
    }

    //receives the index of a column and returns the name of a column
    public String getColumnName(int column){
        return colNames[column];
    }

    public void setData(List<Person> database){
        this.database = database;
    }

    @Override
    public int getRowCount() {
        return database.size(); //number of rows in the table
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    //returning the data that will be in the table
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Person person = database.get(rowIndex);

        switch (columnIndex){
            case 0:
                return person.getId();
            case 1:
                return person.getName();
            case 2:
                return person.getOccupation();
            case 3:
                return person.getAgeCategory();
            case 4:
                return person.getEmployment();
            case 5:
                return person.isSaCitizen();
            case 6:
                return person.getTaxID();
        }
        return null;
    }
}
