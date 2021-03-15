package Model;

public class Person {

    private static int count = 0;//just to count how many people there are and to make an ID

    private int id;
    private String name;
    private String occupation;
    private EmploymentCategory ageCategory;
    private String employment;
    private String taxID;
    private boolean saCitizen;
    private Gender gender;

    public Person(String name, String occupation, EmploymentCategory ageCategory, String employment, String taxID, boolean saCitizen, Gender gender) {
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.employment = employment;
        this.taxID = taxID;
        this.saCitizen = saCitizen;
        this.gender = gender;

        this.id = count;
        count++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public EmploymentCategory getAgeCategory() {
        return ageCategory;
    }

    public String getEmployment() {
        return employment;
    }

    public String getTaxID() {
        return taxID;
    }

    public boolean isSaCitizen() {
        return saCitizen;
    }

    public Gender getGender() {
        return gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setAgeCategory(EmploymentCategory ageCategory) {
        this.ageCategory = ageCategory;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public void setSaCitizen(boolean saCitizen) {
        this.saCitizen = saCitizen;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
