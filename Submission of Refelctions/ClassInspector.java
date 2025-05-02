import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ClassInspector {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter fully qualified class name: ");
        String className = scanner.nextLine();
        
        try {
            Class<?> clazz = Class.forName(className);
            System.out.println("\nClass: " + clazz.getName());

            System.out.println("\nConstructors:");
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println(constructor);
            }

            System.out.println("\nMethods:");
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(method);
            }

            System.out.println("\nFields:");
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + className);
        }
    }
}
