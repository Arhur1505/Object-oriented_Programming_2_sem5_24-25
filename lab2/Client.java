public class Client {
    private int clientId;
    private String clientName;
    public static int count = 0;

    // Blok inicjalizacyjny, zwiększa count po każdym stworzeniu obiektu klasy
    {
        count++;
        clientId = count;
        System.out.println("Blok inicjalizacyjny: clientId ustawione na " + clientId);
    }

    // Konstruktor bezargumentowy
    public Client() {
        this.clientName = "empty";
        System.out.println("Konstruktor bezargumentowy: clientName ustawione na 'empty'");
    }

    // Konstruktor z argumentem clientName, który ustawia pole clientName na clientName
    public Client(String clientName) {
        this.clientName = clientName;
        System.out.println("Konstruktor z argumentem: clientName ustawione na '" + clientName + "'");
    }

    // Getter dla clientName, nic nie przyjmuje, zwraca imię klienta
    public String getName() {
        return this.clientName;
    }

    // Getter dla clientId, nic nie przyjmuje, zwraca id klienta
    public int getID() {
        return this.clientId;
    }

    // Metoda do wyświetlania clientId i clientName, nie nie przyjmuje, nic nie zwraca
    public void print() {
        System.out.println("Client ID: " + clientId + ", Client Name: " + clientName);
    }

    // Statyczna metoda zwracająca liczbę klientów
    public static int countClients() {
        return count;
    }

    // Metoda main do testowania
    public static void main(String[] args) {
        Client client1 = new Client("John Doe");
        Client client2 = new Client("Jane Smith");
        Client client3 = new Client(); // Klient bezargumentowy

        client1.print();
        client2.print();
        client3.print();

        System.out.println("Liczba utworzonych klientow: " + Client.countClients());
    }
}
