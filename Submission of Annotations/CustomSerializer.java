import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface JsonField {
    String name();
}

class User {
    @JsonField(name = "user_name")
    private String username;

    @JsonField(name = "user_age")
    private int age;

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

   
    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }
}

public class CustomSerializer {

    public static String toJson(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Map<String, Object> jsonMap = new HashMap<>();
        
        
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonField.class)) {
                JsonField jsonField = field.getAnnotation(JsonField.class);
                field.setAccessible(true);  
                
                
                jsonMap.put(jsonField.name(), field.get(obj));
            }
        }
        
        
        StringBuilder jsonString = new StringBuilder("{");
        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
            jsonString.append("\"").append(entry.getKey()).append("\": \"")
                      .append(entry.getValue()).append("\", ");
        }
        
       
        if (jsonString.length() > 1) {
            jsonString.setLength(jsonString.length() - 2);  
        }
        jsonString.append("}");
        
        return jsonString.toString();
    }

    public static void main(String[] args) throws IllegalAccessException {
        User user = new User("Alice", 30);
        
       
        String jsonString = toJson(user);
        System.out.println(jsonString);
    }
}
