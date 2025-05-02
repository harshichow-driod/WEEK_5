import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Inject {
}

class ServiceA {
    public void serve() {
        System.out.println("ServiceA is serving...");
    }
}

class ServiceB {
    public void serve() {
        System.out.println("ServiceB is serving...");
    }
}

class Client {
    @Inject
    private ServiceA serviceA;

    @Inject
    private ServiceB serviceB;

    public void start() {
        serviceA.serve();
        serviceB.serve();
    }
}

class DIContainer {
    private final Map<Class<?>, Object> services = new HashMap<>();

    public DIContainer() {
        services.put(ServiceA.class, new ServiceA());
        services.put(ServiceB.class, new ServiceB());
    }

    public <T> T getInstance(Class<T> clazz) throws Exception {
        T instance = clazz.getDeclaredConstructor().newInstance();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                Object dependency = services.get(field.getType());
                field.set(instance, dependency);
            }
        }
        return instance;
    }
}

public class DependencyInjectionExample {
    public static void main(String[] args) throws Exception {
        DIContainer container = new DIContainer();
        Client client = container.getInstance(Client.class);
        client.start();
    }
}