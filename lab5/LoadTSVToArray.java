import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadTSVToArray {
    public static void main(String[] args) {
        String filePath = "maze_txt.txt";

        // Use an ArrayList to dynamically store rows (each row is an array of strings)
        ArrayList<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line by the tab character
                String[] values = line.split("\t");
                // Add the array of values (a row) to the ArrayList
                data.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert ArrayList to a 2D array
        String[][] dataArray = new String[data.size()][];
        data.toArray(dataArray);

        // Print the 2D array to check if it's loaded correctly
        for (String[] row : dataArray) {
            for (String value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
