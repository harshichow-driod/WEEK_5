import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Target(ElementType.METHOD) 
@Retention(RetentionPolicy.RUNTIME) 
 @interface TaskInfo {
    int priority(); 
    String assignedTo(); 
}

class TaskManager {

    @TaskInfo(priority = 1, assignedTo = "Alice")
    public void performTask() {
        System.out.println("Performing task...");
    }
}

public class TaskInfoRetriever {

    public static void main(String[] args) throws Exception {
        
        TaskManager taskManager = new TaskManager();

        
        Method method = TaskManager.class.getMethod("performTask");

        
        if (method.isAnnotationPresent(TaskInfo.class)) {
            
            
            TaskInfo taskInfo = method.getAnnotation(TaskInfo.class);
            
           
            System.out.println("Task Priority: " + taskInfo.priority());
            System.out.println("Assigned To: " + taskInfo.assignedTo());
        }

        
        taskManager.performTask();
    }
}
