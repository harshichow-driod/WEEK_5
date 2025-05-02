import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface ImportantMethod {
    String level() default "HIGH";
}

class Service {

    @ImportantMethod
    public void processData() {
        System.out.println("Processing data...");
    }

    @ImportantMethod(level = "CRITICAL")
    public void saveData() {
        System.out.println("Saving data...");
    }

    public void helperMethod() {
        System.out.println("Helping...");
    }
}

public class ImportantMethodRetriever {

    public static void main(String[] args) {

        Method[] methods = Service.class.getDeclaredMethods();

        System.out.println("Important Methods:");

        for (Method method : methods) {
            if (method.isAnnotationPresent(ImportantMethod.class)) {
                ImportantMethod annotation = method.getAnnotation(ImportantMethod.class);
                System.out.println("- Method: " + method.getName() + ", Level: " + annotation.level());
            }
        }
    }
}
