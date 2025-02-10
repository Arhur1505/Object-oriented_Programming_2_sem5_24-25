import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] var0){
        Person [] people = new Person[3];
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.print("Wprowadz imie: ");
            String name = scanner.nextLine();
            System.out.print("Wprowadz ulice: ");
            String street = scanner.nextLine();
            System.out.print("Wprowadz numer: ");
            int number = scanner.nextInt();
            scanner.nextLine();

            people[i] = new Person(name, street, number);
        }

        System.out.println("List: ");
        for(int i = 0; i < 3; i++){
            people[i].printDetails();
        }

        Arrays.sort(people, (p1, p2) -> p1.getName().compareTo(p2.getName()));

        System.out.println("Lista posortowanych osob: ");
        for(int i = 0; i < 3; i++){
            people[i].printDetails();
        }

        Person shallowCopy = new Person(people[0]);
        shallowCopy.setStreet("Zmieniona ulica");
        shallowCopy.setNumber(999);
        System.out.println("\nOryginal:");
        people[0].printDetails();
        System.out.println("Plytka kopia:");
        shallowCopy.printDetails();


        Person deepCopy = new Person(people[0], true);
        deepCopy.setStreet("Inna ulica");
        deepCopy.setNumber(888);
        System.out.println("\nOryginal:");
        people[0].printDetails();
        System.out.println("Gleboka kopia:");
        deepCopy.printDetails();
    }
}
