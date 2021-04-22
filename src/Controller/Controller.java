package Controller;


import Gui.FormEvent;
import Model.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

//Mainframe will call this class when added data to the database
public class Controller {

    Database database = new Database();

    public List<Person> getPeople (){
        return database.getPeople();
    }

    public void removePerson(int row) {
        database.removePerson(row);
    }

    public void addPerson(FormEvent ev) {

        String name = ev.getName();
        String occupation = ev.getOccupation();
        int ageCategoryID = ev.getAgeCategory();
        String empCat = ev.getEmployment();
        boolean citizen = ev.getSaCitizen();
        String taxID = ev.getTaxID();
        String gender = ev.getGender();

        AgeCategory ageCategory = null ;

        switch (ageCategoryID) {
            case 0:
                ageCategory = AgeCategory.minor;
                break;
            case 1:
                ageCategory = AgeCategory.adult;
                break;
            case 2:
                ageCategory = AgeCategory.senior;
                break;

        }

        EmploymentCategory employmentCategory = null;

        switch (empCat) {
            case "employed":
                employmentCategory = EmploymentCategory.employed;
                break;
            case "self-employed":
                employmentCategory = EmploymentCategory.selfEmployed;
                break;
            case "unemployed":
                employmentCategory = EmploymentCategory.unemployed;
                break;
        }

        Gender genderCat;

        if(gender.equals("male")){
            genderCat = Gender.male;
        }else {
            genderCat = Gender.female;
        }

        Person person = new Person(name, occupation,ageCategory, employmentCategory,taxID,citizen,genderCat);
        database.addPerson(person);
    }

    //This method will call the serializable method in the Database class. It will enable the user to save data
    public void saveToFile(File file) throws IOException{
       database.saveToFile(file);
    }

    public void loadFromFile(File file)throws IOException{
        database.loadFormFile(file);
    }
}
