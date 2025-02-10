class Adder<T extends Number, U extends Number> {
    public Double add(T num1, U num2) {
        return num1.doubleValue() + num2.doubleValue();
    }
}

public class Main1 {
    public static void main(String[] args) {
        Adder<Integer, Integer> adder1 = new Adder<>();
        System.out.println("2 + 3 = " + adder1.add(2, 3));

        Adder<Double, Double> adder2 = new Adder<>();
        System.out.println("2.5 + 3.4 = " + adder2.add(2.5, 3.4));

        Adder<Double, Integer> adder3 = new Adder<>();
        System.out.println("2.3 + 3 = " + adder3.add(2.3, 3));

        Adder<Long, Long> adder4 = new Adder<>();
        System.out.println("10 + 20 = " + adder4.add(10L, 20L));

        Adder<Integer, Long> adder5 = new Adder<>();
        System.out.println("5 + 20 = " + adder5.add(5, 20L));

        Adder<Double, Long> adder6 = new Adder<>();
        System.out.println("5.3 + 20 = " + adder6.add(5.3, 20L));
    }
}