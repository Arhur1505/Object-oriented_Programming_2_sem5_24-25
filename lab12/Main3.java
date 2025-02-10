import java.util.Arrays;

class CustomBubbleSort<T> {
    public void bubbleSort(T[] array, java.util.Comparator<T> comparator) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}

public class Main3 {
    public static void main(String[] args) {
        CustomBubbleSort<Integer> intSort = new CustomBubbleSort<>();
        Integer[] int1 = { 12, 130, 103, 1500, 7, 9000 };
        intSort.bubbleSort(int1, (a, b) -> Integer.compare(
                countNonZero(a), countNonZero(b)));
        System.out.println("Custom sorted ints: " + Arrays.toString(int1));

        CustomBubbleSort<Double> doubleSort = new CustomBubbleSort<>();
        Double[] double1 = { 12.345, 0.170, 155.0, 5679.9, 7600.1, 0.0 };
        doubleSort.bubbleSort(double1, (a, b) -> Double.compare(
                Mantissa(a), Mantissa(b)));
        System.out.println("Custom sorted doubles: " + Arrays.toString(double1));

        CustomBubbleSort<String> stringSort = new CustomBubbleSort<>();
        String[] string1 = { "abcd", "efg", "hijkl", "mn", "o", "prstuwx" };
        stringSort.bubbleSort(string1, (a, b) -> Integer.compare(a.length(), b.length()));
        System.out.println("Custom sorted strings: " + Arrays.toString(string1));
    }

    private static int countNonZero(Integer num) {
        return (int) String.valueOf(num).chars().filter(c -> c != '0').count();
    }

    private static double Mantissa(Double num) {
        String scientific = String.format("%e", num);
        String mantissaPart = scientific.split("e")[0];
        return Double.parseDouble(mantissaPart);
    }
}