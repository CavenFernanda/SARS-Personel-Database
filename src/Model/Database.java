package Model;

import java.util.*;
import java.io.*;

//database class that contains an arrayList of people
public class Database {

    private List<Person> peopleList;

    public Database() {
        peopleList = new LinkedList<Person>();
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
