package com.example.medicare_projekt;

import java.io.*;
import java.util.ArrayList;

public class MedicationModel {
    private static final String medicationFile = "medication.ser";

    private static ArrayList<Medication> medicationList = new ArrayList<>();

    public static ArrayList<Medication> getMedication() {
        return medicationList;
    }

    public MedicationModel() {
        medicationList = new ArrayList<>();
        loadDataFromFile();
    }
    public void medicationSerialize() {
        try (FileOutputStream fileOut = new FileOutputStream(medicationFile);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(medicationList);
            System.out.println("Serialized Medication in List!");
            medicationList.clear();
            //clearMedicationFile();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void medicationDeserealize() {
        try (FileInputStream fileIn = new FileInputStream(medicationFile);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            medicationList = (ArrayList<Medication>) in.readObject();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Medication class not found");
            c.printStackTrace();
        }
    }

    public static void addMedication(String medicationName, int index, ArrayList<String> nebenwirkungen){
        medicationList.add(new Medication(medicationName, index, nebenwirkungen));

        System.out.println(medicationList);
    }

    public void loadDataFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(medicationFile))) {
            medicationList = (ArrayList<Medication>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            medicationList = new ArrayList<>();
        }
    }

    public static void clearMedicationFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(medicationFile))) {
            out.writeObject(new ArrayList<Patient>());
            System.out.println("Medication file cleared.");
        } catch (IOException e) {
            System.err.println("Error clearing patient file: " + e.getMessage());
        }
    }



}
