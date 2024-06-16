package com.example.medicare_projekt;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;
    ArrayList<Patient> patients = new ArrayList<>();

    private String name;
    private LocalDate birthday;

    private ArrayList<Integer> index;

    public ArrayList<Integer> getIndex() {
        return index;
    }

    public void setIndex(ArrayList<Integer> index) {
        this.index = index;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Patient(String name, LocalDate birthday, ArrayList<Integer> index) {
        this.name = name;
        this.birthday = birthday;
        this.index = index;
    }

    @Override
    public String toString() {
        return "Patient: " + name + ", " + birthday + ", " + index;
    }
}

