package Model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.io.*;
import java.sql.*;

//database class that contains an arrayList of people
public class Database {

    private List<Person> peopleList;
    private Connection con;

    public Database() {
        peopleList = new LinkedList<Person>();
    }

    public void connect() throws Exception{

        if(con != null) return;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
           throw new Exception("Driver not found");
        }
        String url = "jdbc:mysql://localhost:3306/swingtest";
        con = DriverManager.getConnection(url,"root","0826888397Cf%%");

    }
    public void disconnect(){
        if(con != null){
            try {
                con.close();
            } catch (SQLException throwables) {
                System.out.println("Unable to close connection");            }
        }
    }
    //will save the whole database into the mysql database
    public void save() throws SQLException {

        String checkSql = "Select count(*) as count from people where id =?";//value '?' is ued to protect the database from malicious sql injections

        PreparedStatement checkStmt = con.prepareStatement(checkSql);//executing mySql commands

        //looping through all the values in the linked list of Person
        for(Person person: peopleList){
            int id = person.getId();

            checkStmt.setInt(1,id);

            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();

            int count = checkResult.getInt(1);

            System.out.println("Count for person with ID: "+id+ " is "+count);
        }

        checkStmt.close();
    }

    public void addPerson(Person person) {
        peopleList.add(person);
    } //By clicking "OKAY" button it will trigger the add person method

    public void removePerson(int row) {
        peopleList.remove(row);
    }

    public List<Person> getPeople() {
        return Collections.unmodifiableList(peopleList); // Will prevent other methods from modifying the list
    }

    //will accept arraylist of people and write it into a file. This should take care of saving and loading of Person List via serialization
    public void saveToFile(File file) throws IOException{
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Person [] personsArray = peopleList.toArray(new Person[peopleList.size()]);

        oos.writeObject(personsArray); //

        oos.close();
    }

    public void loadFormFile(File file) throws IOException{
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            Person [] personsArray = (Person[])ois.readObject();//casting the input stream to type Person []
            peopleList.clear();

            peopleList.addAll(Arrays.asList(personsArray)); //converted Person[] into a list


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ois.close();
    }
}
