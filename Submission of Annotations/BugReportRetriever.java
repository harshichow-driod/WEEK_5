import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Repeatable;
import java.lang.reflect.Method;

@Repeatable(BugReports.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface BugReport {
    String description();
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface BugReports {
    BugReport[] value();
}

class MyClass {

    @BugReport(description = "Null pointer exception when input is null")
    @BugReport(description = "Array index out of bounds when index exceeds limit")
    public void buggyMethod() {
        System.out.println("Executing buggy method...");
    }
}

public class BugReportRetriever {

    public static void main(String[] args) throws Exception {

        MyClass myClass = new MyClass();

        Method method = MyClass.class.getMethod("buggyMethod");

        BugReport[] bugReports = method.getAnnotationsByType(BugReport.class);

        System.out.println("Bug Reports for method: " + method.getName());

        for (BugReport bugReport : bugReports) {
            System.out.println("- " + bugReport.description());
        }

        myClass.buggyMethod();
    }
}
