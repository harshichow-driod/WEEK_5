import java.lang.reflect.Field;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class JsonRepresentation {

    public static String toJson(Object obj) throws IllegalAccessException {
        StringBuilder json = new StringBuilder("{");
        Class<?> objClass = obj.getClass();
        
        Field[] fields = objClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            json.append("\"").append(field.getName()).append("\": \"")
                .append(field.get(obj)).append("\"");
            if (i < fields.length - 1) {
                json.append(", ");
            }
        }
        json.append("}");
        return json.toString();
    }

    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person("Alice", 30);
        String jsonRepresentation = toJson(person);
        System.out.println(jsonRepresentation);
    }
}
