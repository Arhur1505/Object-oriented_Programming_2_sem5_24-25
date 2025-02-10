import java.lang.reflect.*;

public class Reflection {
   public static void main(String[] args) throws Exception {
      Book bookInstance = new Book(1, "Title", "Author", "Genre", "Publisher");
      Class<?> bookClass = bookInstance.getClass();

      System.out.println("Class Name: " + Class.forName("Book").getName());
      System.out.println("Class via getClass(): " + bookClass.getName());
      System.out.println("Class via .class: " + Book.class.getName());

      System.out.println("\n--- Declared Methods (using getClass) ---");
      Method[] methods = bookClass.getDeclaredMethods();
      for (Method method : methods) {
         System.out.println("Method: " + method.getName() + ", Return Type: " + method.getReturnType());
         int modifiers = method.getModifiers();
         System.out.println("Access Modifier: " + Modifier.toString(modifiers));
      }

      System.out.println("\n--- Adding Private Field 'pagesNr' ---");
      Field pagesNr = bookClass.getDeclaredField("pagesNr");
      pagesNr.setAccessible(true);

      pagesNr.set(bookInstance, 200);
      System.out.println("Private field 'pagesNr' value: " + pagesNr.get(bookInstance));

      System.out.println("\n--- Declared Fields ---");
      Field[] fields = bookClass.getDeclaredFields();
      for (Field field : fields) {
         System.out.println("Field: " + field.getName() + ", Type: " + field.getType());
         System.out.println("Access Modifier: " + Modifier.toString(field.getModifiers()));
      }

      System.out.println("\n--- Superclass Details ---");
      Class<?> superclass = bookClass.getSuperclass();
      System.out.println("Superclass: " + superclass.getName());

      Method[] superclassMethods = superclass.getDeclaredMethods();
      for (Method method : superclassMethods) {
         System.out.println("Superclass Method: " + method.getName());
      }

      Field[] superclassFields = superclass.getDeclaredFields();
      for (Field field : superclassFields) {
         System.out.println("Superclass Field: " + field.getName());
      }

      System.out.println("\n--- Declared Constructors ---");
      Constructor<?>[] constructors = bookClass.getDeclaredConstructors();
      for (Constructor<?> constructor : constructors) {
         System.out.println("Constructor: " + constructor);
         int modifiers = constructor.getModifiers();
         System.out.println("Access Modifier: " + Modifier.toString(modifiers));
      }

      Constructor<?> privateConstructor = bookClass.getDeclaredConstructor(String.class);
      privateConstructor.setAccessible(true);
      Object privateInstance = privateConstructor.newInstance("Private Author");
      System.out.println("Private Constructor invoked successfully.");
   }
}