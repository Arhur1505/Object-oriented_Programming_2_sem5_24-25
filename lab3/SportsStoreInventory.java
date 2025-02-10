import java.util.Scanner;

public class SportsStoreInventory {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ile przedmiotow? ");
        int itemCount = scanner.nextInt(); //zapisanie liczby elementów do zmiennej
        scanner.nextLine();

        double totalPrice = 0; //zmienna przechowująca cene

        for (int i = 0; i < itemCount; i++) {
            System.out.print("Wprowadz nazwe: ");
            String itemName = scanner.nextLine(); //Zapisanie nazwy przedmiotu do zmiennej

            System.out.print("Wprowadz cene: ");
            double price = scanner.nextDouble(); //Zapisanie ceny przedmiotu do zmiennej

            System.out.print("Wprowadz kategorie (1 - Pilka nozna, 2 - Koszykowka, 3 - Tenis, 4 - Plywanie): ");
            int category = scanner.nextInt(); //zapisanie kategorii do zmiennej
            scanner.nextLine();

            switch (category) { //switch ktory wypisuje rozny komunikat zaleznie od kategorii
                case 1:
                    System.out.println("Kicking it into gear!");
                    break;
                case 2:
                    System.out.println("Nothing but net!");
                    break;
                case 3:
                    System.out.println("Game, set, match!");
                    break;
                case 4:
                    System.out.println("Dive into excellence!");
                    break;
                default:
                    System.out.println("Nieznana kategoria!");
                    break;
            }

            totalPrice += price; //dodanie ceny to sumy
        }

        if (totalPrice < 100) { //instrukcja if który podaje cena ostateczna zaleznie od widełek
            System.out.println("Ostetczna cena wynosi: " + totalPrice);
        } else if (totalPrice < 200) {
            System.out.println("Ostateczna cena po rabacie 10% wynosi: " + totalPrice*0.9);
        } else {
            System.out.println("Ostateczna cena po rabacie 20% wynosi: " + totalPrice*0.8);
        }
        
        scanner.close();
    }
}