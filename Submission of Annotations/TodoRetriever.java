import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Todo {
    String task();
    String assignedTo();
    String priority() default "MEDIUM";
}

class FeatureModule {

    @Todo(task = "Implement login functionality", assignedTo = "Alice", priority = "HIGH")
    public void login() {
        System.out.println("Login feature pending...");
    }

    @Todo(task = "Add user profile page", assignedTo = "Bob")
    public void userProfile() {
        System.out.println("User profile feature pending...");
    }

    @Todo(task = "Optimize search", assignedTo = "Charlie", priority = "LOW")
    public void search() {
        System.out.println("Search optimization pending...");
    }

    public void helper() {
        System.out.println("Helper method - no pending tasks.");
    }
}

public class TodoRetriever {

    public static void main(String[] args) {

        Method[] methods = FeatureModule.class.getDeclaredMethods();

        System.out.println("Pending Tasks:");

        for (Method method : methods) {
            if (method.isAnnotationPresent(Todo.class)) {
                Todo todo = method.getAnnotation(Todo.class);
                System.out.println("- Method: " + method.getName());
                System.out.println("  Task: " + todo.task());
                System.out.println("  Assigned To: " + todo.assignedTo());
                System.out.println("  Priority: " + todo.priority());
            }
        }
    }
}
