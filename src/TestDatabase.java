import Model.*;

import java.sql.SQLException;

public class TestDatabase {
    public static void main(String[] args) {
        System.out.println("Running database test");

        Database db = new Database();
        try {
            db.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        db.addPerson(new Person("Siya","Financial Analyst", AgeCategory.adult, EmploymentCategory.employed,"777777",true, Gender.male));
        db.addPerson(new Person("Jenny","Banker", AgeCategory.adult, EmploymentCategory.selfEmployed,"515177",true, Gender.female));

        try {
            db.save();
        } catch (SQLException throwables) {
            System.out.println("Unable to save");
        }

        try {
            db.load();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        db.disconnect();
    }
}
