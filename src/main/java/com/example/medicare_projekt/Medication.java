package com.example.medicare_projekt;

import java.io.Serializable;
import java.util.ArrayList;

public class Medication implements Serializable {

    Patient patient;

    private String medicationName;
    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private ArrayList<String> nebenwirkungen;
    public ArrayList<String> getNebenwirkungen() {
        return nebenwirkungen;
    }

    public void setNebenwirkungen(ArrayList<String> nebenwirkungen) {
        this.nebenwirkungen = nebenwirkungen;
    }

    public Medication(String medicationName, int index, ArrayList<String> nebenwirkungen){
        this.medicationName = medicationName;
        this.index = index;
        this.nebenwirkungen = nebenwirkungen;
    }

    @Override
    public String toString() {
        return "Medikament: " + medicationName + ", Index: " + index + ", Nebenwirkungen: " + nebenwirkungen;
    }


}
