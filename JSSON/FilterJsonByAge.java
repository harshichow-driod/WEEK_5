import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilterJsonByAge {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File("people.json"));

        List<JsonNode> filtered = new ArrayList<>();
        for (JsonNode node : root) {
            if (node.has("age") && node.get("age").asInt() > 25) {
                filtered.add(node);
            }
        }

        String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(filtered);
        System.out.println(result);
    }
}
