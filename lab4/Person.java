import java.util.Scanner;
import java.util.Arrays;

public class Person{
    private String name;
    private String street;
    private int number;

    public Person(String name, String street, int number) { //konstruktor
        this.name = name;
        this.street = street;
        this.number = number;
    }

    public Person(Person other) { //konstruktor kopiujacy
        this.name = other.name;
        this.street = other.street;
        this.number = other.number;
    }

    public Person(Person other, boolean deepCopy) { //konstruktor do kopii glebokiej
        this.name = new String(other.name);
        this.street = new String(other.street);
        this.number = other.number;
    }

    public String getName(){ //metoda zwracajaca name
        return this.name;
    }

    public String getStreet(){ //metoda zwracajaca street
        return this.street;
    }

    public int getNumber(){ //metoda zwracajaca number
        return this.number;
    }

    public void setName(String name){ //metoda zmieniajaca name
        this.name = name;
    }

    public void setStreet(String street){ //metoda zmieniajaca street
        this.street = street;
    }

    public void setNumber(int number){ //metoda zmieniajaca number
        this.number = number;
    }

    public void printDetails(){ //metoda printujaca
        System.out.println("Imie: " + name + " Ulica: " + street + " Numer: " + number);
    }
}