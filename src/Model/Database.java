package Model;

import java.util.*;

//database class that contains an arrayList of people
public class Database {

    private ArrayList<Person> people;

    public Database() {
        people = new ArrayList<Person>();
    }

    public void addPerson(Person person) {
        people.add(person);
    } //By clicking "OKAY" button it will trigger the add person method

    public List<Person> getPeople() {
        return people;
    }
}
