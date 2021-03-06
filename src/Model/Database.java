package Model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.io.*;
import java.sql.*;

//database class that contains an arrayList of people
public class Database {

    private final List<Person> peopleList;
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
        con = DriverManager.getConnection(url,"root","0826888397Cf%");

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
        PreparedStatement checkStmt = con.prepareStatement(checkSql);//select mySql command

        String insertSql = "insert into People (id, name,age, employment_status, tax_id,sa_citizenship, gender, occupation) values (?,?,?,?,?,?,?,?)";
        PreparedStatement insertStatement = con.prepareStatement(insertSql);//insert swl command

        String updateSql = "update People set name =? ,age = ?, employment_status = ?, tax_id = ?,sa_citizenship = ?, gender = ?, occupation = ? where id = ?";
        PreparedStatement updateStatement = con.prepareStatement(updateSql);//update sql command

        //looping through all the values in the linked list of Person
        for(Person person: peopleList){
            int id = person.getId();
            String name = person.getName();
            String occupation = person.getOccupation();
            AgeCategory age = person.getAgeCategory();
            EmploymentCategory employment = person.getEmployment();
            String tax = person.getTaxID();
            boolean isSa = person.isSaCitizen();
            Gender gender = person.getGender();

            checkStmt.setInt(1,id);

            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();

            int count = checkResult.getInt(1);

            if(count == 0){
                System.out.println("Inserting person with ID " + id);

                int col = 1;
                insertStatement.setInt(col++,id);
                insertStatement.setString(col++,name);
                insertStatement.setString(col++,age.name());
                insertStatement.setString(col++,employment.name());
                insertStatement.setString(col++,tax);
                insertStatement.setBoolean(col++,isSa);
                insertStatement.setString(col++, gender.name());
                insertStatement.setString(col++,occupation);

                insertStatement.executeUpdate();
            }
            else {
                System.out.println("Updating person with ID " + id);

                int col = 1;
                updateStatement.setString(col++,name);
                updateStatement.setString(col++,age.name());
                updateStatement.setString(col++,employment.name());
                updateStatement.setString(col++,tax);
                updateStatement.setBoolean(col++,isSa);
                updateStatement.setString(col++, gender.name());
                updateStatement.setString(col++,occupation);
                updateStatement.setInt(col++,id);

                updateStatement.executeUpdate();

            }

        }
        updateStatement.close();
        insertStatement.close();
        checkStmt.close();
    }

    public void load() throws SQLException {
        peopleList.clear();

        String sql = "select id, name,age, employment_status, tax_id,sa_citizenship, gender, occupation from people order by name";
        Statement selectStatement = con.createStatement();
        ResultSet results = selectStatement.executeQuery(sql);

        while(results.next()){
           int id = results.getInt("id");
           String name = results.getString("name");
           String age = results.getString("age");
           String  employment_status = results.getString("employment_status");
           String tax_id = results.getString("tax_id");
           boolean sa_citizenship = results.getBoolean("sa_citizenship");
           String gender = results.getString("gender");
           String occupation = results.getString("occupation");

           Person person = new Person(id,name,occupation,AgeCategory.valueOf(age),EmploymentCategory.valueOf(employment_status),tax_id,sa_citizenship,Gender.valueOf(gender));
           peopleList.add(person);

           System.out.println(person);
        }

        results.close();
        selectStatement.close();
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
