import java.io.*;
import java.util.*;

public class TopPaidEmployees {
    public static void main(String[] args) {
        String filePath = "employees.csv";
        List<String[]> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                records.add(columns);
            }

            records.sort((a, b) -> {
                double salaryA = Double.parseDouble(a[3]);
                double salaryB = Double.parseDouble(b[3]);
                return Double.compare(salaryB, salaryA);
            });

            System.out.println("Top 5 highest-paid employees:");
            for (int i = 0; i < Math.min(5, records.size()); i++) {
                String[] emp = records.get(i);
                System.out.println("ID: " + emp[0] +
                                   ", Name: " + emp[1] +
                                   ", Department: " + emp[2] +
                                   ", Salary: " + emp[3]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
