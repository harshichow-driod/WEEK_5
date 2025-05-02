import java.lang.reflect.Field;

class Configuration {
    private static String API_KEY = "ORIGINAL_KEY";

    public static String getApiKey() {
        return API_KEY;
    }
}

public class StaticFieldAccess {
    public static void main(String[] args) throws Exception {
        Class<Configuration> configClass = Configuration.class;

        Field apiKeyField = configClass.getDeclaredField("API_KEY");
        apiKeyField.setAccessible(true);

        System.out.println("Original API_KEY: " + Configuration.getApiKey());

        apiKeyField.set(null, "NEW_SECRET_KEY");

        System.out.println("Modified API_KEY: " + Configuration.getApiKey());
    }
}
