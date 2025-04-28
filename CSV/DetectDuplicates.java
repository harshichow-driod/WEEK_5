
import java.io.*;
import java.util.*;

public class DetectDuplicates {
    public static void main(String[] args) {
        String filePath = "employees.csv";
        Set<String> seenIds = new HashSet<>();
        List<String> duplicates = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                String id = columns[0];

                if (seenIds.contains(id)) {
                    duplicates.add(line);
                } else {
                    seenIds.add(id);
                }
            }

            if (duplicates.isEmpty()) {
                System.out.println("No duplicate records found.");
            } else {
                System.out.println("Duplicate records:");
                for (String record : duplicates) {
                    System.out.println(record);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


