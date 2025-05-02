class Student {
    private String name;
    private int age;

    public Student() {
        this.name = "Default Name";
        this.age = 0;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

public class DynamicObjectCreation {
    public static void main(String[] args) throws Exception {
        Class<?> studentClass = Class.forName("Student");

        Object studentObj = studentClass.getDeclaredConstructor().newInstance();

        studentClass.getMethod("display").invoke(studentObj);

        Object paramStudent = studentClass.getDeclaredConstructor(String.class, int.class)
                                          .newInstance("Alice", 22);

        studentClass.getMethod("display").invoke(paramStudent);
    }
}
