import java.lang.reflect.Method;

class Calculator {
    private int multiply(int a, int b) {
        return a * b;
    }
}

public class PrivateMethodInvoker {
    public static void main(String[] args) throws Exception {
        Calculator calculator = new Calculator();

        Method multiplyMethod = Calculator.class.getDeclaredMethod("multiply", int.class, int.class);
        multiplyMethod.setAccessible(true);

        Object result = multiplyMethod.invoke(calculator, 5, 10);

        System.out.println("Result of multiply: " + result);
    }
}

