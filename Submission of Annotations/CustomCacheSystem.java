import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface CacheResult {
}

class ExpensiveComputation {

    private Map<String, Integer> cache = new HashMap<>();

    @CacheResult
    public int computeExpensiveResult(String input) {
        if (cache.containsKey(input)) {
            System.out.println("Returning cached result for input: " + input);
            return cache.get(input);
        }

        
        int result = input.length() * 1000;  
        cache.put(input, result);
        System.out.println("Computing result for input: " + input);
        return result;
    }
}

public class CustomCacheSystem {

    public static void main(String[] args) throws Exception {

        ExpensiveComputation computation = new ExpensiveComputation();
        Method method = ExpensiveComputation.class.getMethod("computeExpensiveResult", String.class);

        String input1 = "apple";
        String input2 = "banana";

        
        System.out.println("Result: " + computation.computeExpensiveResult(input1)); 
        System.out.println("Result: " + computation.computeExpensiveResult(input1)); 
        System.out.println("Result: " + computation.computeExpensiveResult(input2)); 
        System.out.println("Result: " + computation.computeExpensiveResult(input2)); 
    }
}
