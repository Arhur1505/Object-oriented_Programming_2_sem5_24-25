public class MainClass{
    public MainClass(){
        System.out.println("Main Class Constructor");
    }

    public void method1() {
        System.out.println("Main Class Method 1");
    }
    public static void main(String[] args) {
        MainClass mainClass = new MainClass();
        mainClass.method1();

        Class1 class1 = new Class1();
        class1.method1();
    }
}