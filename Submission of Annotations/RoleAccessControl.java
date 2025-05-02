import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface RoleAllowed {
    String value();
}

class SecureService {

    @RoleAllowed("ADMIN")
    public void adminTask() {
        System.out.println("Admin task executed.");
    }

    @RoleAllowed("USER")
    public void userTask() {
        System.out.println("User task executed.");
    }

    public void openTask() {
        System.out.println("Open task executed (no restrictions).");
    }
}

public class RoleAccessControl {

    public static void main(String[] args) throws Exception {

        String currentUserRole = "USER";  
        SecureService service = new SecureService();
        Method[] methods = SecureService.class.getDeclaredMethods();

        for (Method method : methods) {
            System.out.println("Trying to execute: " + method.getName());
            if (method.isAnnotationPresent(RoleAllowed.class)) {
                RoleAllowed roleAllowed = method.getAnnotation(RoleAllowed.class);
                if (roleAllowed.value().equals(currentUserRole)) {
                    method.invoke(service);
                } else {
                    System.out.println("Access Denied!");
                }
            } else {
                method.invoke(service);
            }
            System.out.println();
        }
    }
}
