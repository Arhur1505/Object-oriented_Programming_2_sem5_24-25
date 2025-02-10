import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MagpieDetector {
    private BufferedImage referenceImage;
    private BufferedImage testImage;
    private int[][] binaryTestArray;
    private int[][] binaryRefArray;
    private ArrayList<int[]> magpieCoordinates;

    //Metoda do zaladowania obrazu
    public final void loadImage(String fileName, boolean isReference) {
        try {
            BufferedImage img = ImageIO.read(new File(fileName));
            if (isReference) {
                this.referenceImage = img;
            } else {
                this.testImage = img;
            }
        } catch (IOException e) {
            System.err.println("Error: Unable to load image - " + fileName);
        }
    }

    //Metoda do konweersji
    private int[][] imageToArray(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int[][] binaryArray = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int p = img.getRGB(x, y);
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;
                binaryArray[x][y] = (r == 0 && g == 0 && b == 0) ? 0 : 1;
            }
        }
        return binaryArray;
    }

    //Metoda do zliczania
    public void getMagpies() {
        binaryTestArray = imageToArray(testImage);
        binaryRefArray = imageToArray(referenceImage);
        magpieCoordinates = new ArrayList<>();

        int refWidth = binaryRefArray.length;
        int refHeight = binaryRefArray[0].length;
        int testWidth = binaryTestArray.length;
        int testHeight = binaryTestArray[0].length;

        for (int x = 0; x <= testWidth - refWidth; x++) {
            for (int y = 0; y <= testHeight - refHeight; y++) {
                if (matchesReference(x, y)) {
                    magpieCoordinates.add(new int[]{x, y});
                }
            }
        }
        System.out.println("Number of magpies detected: " + magpieCoordinates.size());
    }

    //metoda, która sprawdza sroki
    private boolean matchesReference(int startX, int startY) {
        int refWidth = binaryRefArray.length;
        int refHeight = binaryRefArray[0].length;

        for (int x = 0; x < refWidth; x++) {
            for (int y = 0; y < refHeight; y++) {
                if (binaryRefArray[x][y] != binaryTestArray[startX + x][startY + y]) {
                    return false;
                }
            }
        }
        return true;
    }

    //metoda ktora tworzy nowa tablice zawierajaca obraz z usunietymi wszystkimi ptakami innymi niż sroki
    public void clearImage() {
        BufferedImage clearedImage = new BufferedImage(testImage.getWidth(), testImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        for (int[] coords : magpieCoordinates) {
            clearedImage.setRGB(coords[0], coords[1], Color.WHITE.getRGB());
        }

        displayImage(clearedImage, "clearedImage.tif");
    }

    //metoda, ktora zapisuje obraz
    public void displayImage(BufferedImage img, String fileName) {
        try {
            ImageIO.write(img, "tif", new File(fileName));
            System.out.println("Image " + fileName + " saved");
        } catch (IOException e) {
            System.err.println("Error: Unable to save image - " + fileName);
        }
    }

    public static void main(String[] args) {
        MagpieDetector detector = new MagpieDetector();
        detector.loadImage("refImage.tif", true);
        detector.loadImage("testImage.tif", false);
        detector.getMagpies();
        detector.clearImage();
    }
}
