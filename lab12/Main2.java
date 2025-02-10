import java.util.Arrays;

class GenericBubbleSort<T extends Comparable<T>> {
    public void bubbleSort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}

public class Main2 {
    public static void main(String[] args) {
        GenericBubbleSort<Integer> intSort = new GenericBubbleSort<>();
        Integer[] int1 = { 4, -12, 1, 3, 18, -6 };
        intSort.bubbleSort(int1);
        System.err.println("Sorted int array: " + Arrays.toString(int1));

        GenericBubbleSort<Double> doubleSort = new GenericBubbleSort<>();
        Double[] double1 = { 5.15, 3.14, -6.11, 11.2, -12.3, 0.0 };
        doubleSort.bubbleSort(double1);
        System.err.println("Sorted double array: " + Arrays.toString(double1));

        GenericBubbleSort<String> stringSort = new GenericBubbleSort<>();
        String[] string1 = {"efg", "bhijkl", "abcd", "gmn", "o", "aprstuwx" };
        stringSort.bubbleSort(string1);
        System.err.println("Sorted string array: " + Arrays.toString(string1));
    }
}