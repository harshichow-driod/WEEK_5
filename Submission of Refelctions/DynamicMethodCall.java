import java.lang.reflect.Method;
import java.util.Scanner;

class MathOperations {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}

public class DynamicMethodCall {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        MathOperations operations = new MathOperations();

        System.out.print("Enter method name (add, subtract, multiply): ");
        String methodName = scanner.nextLine();

        System.out.print("Enter first integer: ");
        int num1 = scanner.nextInt();

        System.out.print("Enter second integer: ");
        int num2 = scanner.nextInt();

        Method method = MathOperations.class.getMethod(methodName, int.class, int.class);
        Object result = method.invoke(operations, num1, num2);

        System.out.println("Result: " + result);
    }
}
