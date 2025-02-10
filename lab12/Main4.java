import java.util.ArrayList;
import java.util.List;

class WildcardSum {
    public static double sumList(List<? extends Number> list) {
        double sum = 0.0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }
}

public class Main4 {
    public static void main(String[] args) {
        List<Integer> intAddList = new ArrayList<>();
        intAddList.add(2);
        intAddList.add(0);
        intAddList.add(8);
        intAddList.add(-3);
        intAddList.add(4);
        System.out.println("Sum of integers: " + WildcardSum.sumList(intAddList));

        List<Double> doubleAddList = new ArrayList<>();
        doubleAddList.add(4.0);
        doubleAddList.add(7.0);
        doubleAddList.add(4.1);
        doubleAddList.add(7.1);
        doubleAddList.add(-4.0);
        System.out.println("Sum of doubles: " + WildcardSum.sumList(doubleAddList));

        List<Long> longAddList = new ArrayList<>();
        longAddList.add(32L);
        longAddList.add(12L);
        longAddList.add(-11L);
        longAddList.add(-28L);
        System.out.println("Sum of longs: " + WildcardSum.sumList(longAddList));

        List<Number> mixedAddList = new ArrayList<>();
        mixedAddList.add(3);
        mixedAddList.add(4.1);
        mixedAddList.add(32L);
        mixedAddList.add(-30);

        System.out.println("Sum of mixed types: " + WildcardSum.sumList(mixedAddList));
    }
}
