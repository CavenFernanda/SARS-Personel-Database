package Controller;


import Gui.FormEvent;
import Model.*;

import java.util.List;

//Mainframe will call this class when added data to the database
public class Controller {

    Database database = new Database();

    public List<Person> getPeople (){
        return database.getPeople();
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

        EmploymentCategory employmentCategory;

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

        Person person = new Person(name, occupation,ageCategory, empCat,taxID,citizen,genderCat);
        database.addPerson(person);
    }
}
