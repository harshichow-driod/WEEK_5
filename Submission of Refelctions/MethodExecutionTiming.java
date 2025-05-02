import java.lang.reflect.Method;

class SampleClass {
    public void methodOne() throws InterruptedException {
        Thread.sleep(500);
    }

    public void methodTwo() throws InterruptedException {
        Thread.sleep(300);
    }
}

public class MethodExecutionTiming {

    public static void measureExecutionTime(Object obj, String methodName, Class<?>... parameterTypes) throws Exception {
        Method method = obj.getClass().getMethod(methodName, parameterTypes);

        long startTime = System.nanoTime();
        method.invoke(obj);
        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        System.out.println("Execution time of " + methodName + ": " + duration + " nanoseconds");
    }

    public static void main(String[] args) throws Exception {
        SampleClass sample = new SampleClass();

        measureExecutionTime(sample, "methodOne");
        measureExecutionTime(sample, "methodTwo");
    }
}
