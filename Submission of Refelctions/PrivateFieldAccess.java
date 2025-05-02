import java.lang.reflect.Field;

class Person {
    private int age;

    public Person(int age) {
        this.age = age;
    }
}

public class PrivateFieldAccess {
    public static void main(String[] args) throws Exception {
        Person person = new Person(25);

        Field ageField = Person.class.getDeclaredField("age");
        ageField.setAccessible(true);

        System.out.println("Original age: " + ageField.get(person));

        ageField.set(person, 40);

        System.out.println("Modified age: " + ageField.get(person));
    }
}
