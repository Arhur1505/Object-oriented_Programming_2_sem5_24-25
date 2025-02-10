import java.util.ArrayList;

public class Store {
    private ArrayList<Client> clients;

    // Konstruktor
    public Store() {
        clients = new ArrayList<>();
    }

    // Metoda dodająca nowego klienta, przyjmuje klienta, którego dodaje do listy, nic nie zwraca
    public void add(Client aClient) {
        clients.add(aClient);
        System.out.println("Dodano klienta: " + aClient.getName());
    }

    // Metoda do wyświetlania wszystkich klientów, nie przyjmuje żadnego argumentu, nic nie zwraca
    public void print() {
        System.out.println("Lista klientow w sklepie:");
        for (Client client : clients) {
            client.print();
        }
    }

    // Metoda do znajdowania klienta po ID, przyjmuje id kilenta do znalezienia, zwraca referencje do kilenta o podanym id lub null jak nie znajdzie
    public Client find(int ID) {
        for (Client client : clients) {
            if (client.getID() == ID) {
                return client;
            }
        }
        return null;
    }

    // Metoda main do testowania
    public static void main(String[] args) {
        Store store = new Store();

        Client client1 = new Client("John Doe");
        Client client2 = new Client("Jane Smith");
        Client client3 = new Client();

        store.add(client1);
        store.add(client2);
        store.add(client3);

        store.print();

        // Szukanie klienta po ID
        int searchId = 2;
        Client foundClient = store.find(searchId);
        if (foundClient != null) {
            System.out.println("Znaleziono klienta o ID " + searchId + ": " + foundClient.getName());
        } else {
            System.out.println("Nie znaleziono klienta o ID " + searchId);
        }
    }
}
