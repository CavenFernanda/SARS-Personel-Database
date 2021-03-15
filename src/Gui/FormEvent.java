package Gui;

import java.util.EventObject;

public class FormEvent extends EventObject {

    private String name;
    private String occupation;
    private int ageCategory;
    private String employment;
    private String taxID;
    private boolean saCitizen;
    private String gender;

    public FormEvent(Object source) {
        super(source); //source will be the button from FormPanel that will raise the event

    }

    public FormEvent(Object source, String name, String occupation, int ageCategory, String employment, String taxID, boolean saCitizen,String gender) {
        super(source); //source will be the button from FormPanel that will raise the event
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.employment = employment;
        this.taxID = taxID;
        this.saCitizen = saCitizen;
        this.gender = gender;

    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public int getAgeCategory() {
        return ageCategory;
    }

    public String getEmployment() {
        return employment;
    }

    public String getTaxID() {
        return taxID;
    }

    public boolean getSaCitizen() {

        return saCitizen;
    }
    
    public String getGender(){
        return gender;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setAgeCategory(int ageCategory) {

        this.ageCategory = ageCategory;
    }

    public void setEmployment(String employment) {

        this.employment = employment;
    }
    
    public void setTaxID(String taxID){
        this.taxID  = taxID;
    }
    
    public void setSaCitizen(boolean saCitizen){
        this.saCitizen = saCitizen;
    }
    
    public void getGender(String gender){
        this.gender = gender;
    }
}