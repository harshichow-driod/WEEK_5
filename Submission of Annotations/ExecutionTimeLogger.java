import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface LogExecutionTime {
}

class TaskRunner {

    @LogExecutionTime
    public void fastTask() throws InterruptedException {
        Thread.sleep(100);
        System.out.println("Fast task completed.");
    }

    @LogExecutionTime
    public void slowTask() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Slow task completed.");
    }

    public void noLogTask() throws InterruptedException {
        Thread.sleep(200);
        System.out.println("No-log task completed.");
    }
}

public class ExecutionTimeLogger {

    public static void main(String[] args) throws Exception {

        TaskRunner runner = new TaskRunner();

        Method[] methods = TaskRunner.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(LogExecutionTime.class)) {
                long startTime = System.nanoTime();
                method.invoke(runner);
                long endTime = System.nanoTime();
                long duration = (endTime - startTime) / 1_000_000;
                System.out.println("Execution time for " + method.getName() + ": " + duration + " ms");
            }
        }
    }
}
