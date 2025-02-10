import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {

    private static final int PORT = 12345;
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        System.out.println("Serwer uruchomiony. Oczekiwanie na połączenia...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                Socket clientSocket = serverSocket.accept();

                new Thread(new ClientHandler(clientSocket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {

        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String clientAddress = socket.getInetAddress().getHostAddress();
            System.out.println("Połączono z klientem: " + clientAddress);

            try (DataInputStream in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
                    DataOutputStream out = new DataOutputStream(
                            new BufferedOutputStream(socket.getOutputStream()))) {

                String requestedFileName = in.readUTF();
                System.out.println("Klient " + clientAddress + " żąda pliku: " + requestedFileName);

                File file = new File(requestedFileName);

                if (file.exists() && file.isFile()) {
                    long fileSize = file.length();
                    out.writeLong(fileSize);

                    out.writeUTF(file.getName());

                    try (FileInputStream fis = new FileInputStream(file);
                            BufferedInputStream bis = new BufferedInputStream(fis)) {

                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        long totalBytesSent = 0;

                        while ((bytesRead = bis.read(buffer)) != -1) {
                            out.write(buffer, 0, bytesRead);
                            totalBytesSent += bytesRead;
                        }

                        out.flush();
                        System.out.println("Wysłano " + totalBytesSent + " bajtów do klienta " + clientAddress);
                    }
                } else {
                    out.writeLong(-1);
                    System.out.println("Plik " + requestedFileName + " nie istnieje. Informacja wysłana do klienta.");
                }

            } catch (IOException e) {
                System.err.println("Błąd w komunikacji z klientem " + clientAddress + ": " + e.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    
                }
            }
        }
    }
}
