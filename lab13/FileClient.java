import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class FileClient {

    private static final int PORT = 12345;
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj nazwę pliku, który chcesz pobrać z serwera:");
        String requestedFileName = scanner.nextLine();

        try (Socket socket = new Socket(HOST, PORT);
                DataOutputStream out = new DataOutputStream(
                        new BufferedOutputStream(socket.getOutputStream()));
                DataInputStream in = new DataInputStream(
                        new BufferedInputStream(socket.getInputStream()))) {

            out.writeUTF(requestedFileName);
            out.flush();

            long fileSize = in.readLong();
            InetAddress localAddress = socket.getLocalAddress();
            InetAddress serverAddress = socket.getInetAddress();
            System.out.println("Połączono z serwerem: " + serverAddress.getHostAddress());
            System.out.println("Adres lokalny klienta: " + localAddress.getHostAddress());

            if (fileSize == -1) {
                System.out.println("Serwer zgłosił: żądany plik nie istnieje.");
                return;
            }

            String actualFileName = in.readUTF();
            System.out.println("Odebrano nazwę pliku: " + actualFileName);

            String outputFileName = "received_" + actualFileName;

            try (FileOutputStream fos = new FileOutputStream(outputFileName);
                    BufferedOutputStream bos = new BufferedOutputStream(fos)) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                long totalBytesReceived = 0;

                while (totalBytesReceived < fileSize &&
                        (bytesRead = in.read(buffer)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                    totalBytesReceived += bytesRead;
                }
                bos.flush();

                System.out.println("Odebrano " + totalBytesReceived + " bajtów z oczekiwanych " + fileSize);
                System.out.println("Plik został zapisany lokalnie jako: " + outputFileName);
            }

            System.out.println("Zawartość pliku (pierwsze kilkaset znaków):");
            printFileContent(outputFileName, 1024);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printFileContent(String fileName, int maxBytes) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))) {
            byte[] buffer = new byte[maxBytes];
            int bytesRead = bis.read(buffer, 0, maxBytes);
            if (bytesRead > 0) {
                String content = new String(buffer, 0, bytesRead);
                System.out.println(content);
            }
        } catch (IOException e) {
            System.err.println("Błąd podczas odczytu pliku w celu wyświetlenia zawartości: " + e.getMessage());
        }
    }
}
