import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class MergeJsonFiles {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json1 = objectMapper.readTree(new File("file1.json"));
        JsonNode json2 = objectMapper.readTree(new File("file2.json"));
        ((ObjectNode) json1).setAll((ObjectNode) json2);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json1));
    }
}
