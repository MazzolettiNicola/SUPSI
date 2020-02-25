package Esercizio1;

import java.time.Year;

public class Person {
    /**
     * Nome della persona
     */
    private String firstName = "";
    /**
     * Cognome della persona
     */
    private String lastName = "";
    /**
     * Anno di nascita della persona
     */
    private int yearOfBirth;

    /**
     * Costruttore della classe Esercizio1.Person
     * @param firstName
     * @param lastName
     * @param yearOfBirth
     */
    public Person(String firstName, String lastName, int yearOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /**
     * Metodo per calcolare l'età della persona
     * @return age, età calcolata
     */
    public int calculateAge(){
         int age = Year.now().getValue() - this.yearOfBirth;

         if(age < 0) return 0;
         return age;
    }

    /**
     * Metodo per stampare le informazioni di una persona
     * @return firstName+lastName+age
     */
    public String printInfos(){
        return this.firstName + " " + this.lastName + ", età: " + this.calculateAge();
    }
}
