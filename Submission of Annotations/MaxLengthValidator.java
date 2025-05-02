import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface MaxLength {
    int value();
}

class User {

    @MaxLength(10)
    private String username;

    public User(String username) throws Exception {
        this.username = username;
        validateMaxLength();
    }

    private void validateMaxLength() throws Exception {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(MaxLength.class)) {
                MaxLength maxLength = field.getAnnotation(MaxLength.class);
                if (field.getType() == String.class) {
                    field.setAccessible(true);
                    String value = (String) field.get(this);
                    if (value != null && value.length() > maxLength.value()) {
                        throw new IllegalArgumentException(
                            "Field '" + field.getName() + "' exceeds max length of " + maxLength.value()
                        );
                    }
                }
            }
        }
    }
}

public class MaxLengthValidator {

    public static void main(String[] args) {
        try {
            User user1 = new User("Alice");
            System.out.println("User1 created successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            User user2 = new User("VeryLongUsername123");
            System.out.println("User2 created successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
