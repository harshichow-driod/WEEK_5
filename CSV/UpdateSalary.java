import java.io.*;

public class UpdateSalary {
    public static void main(String[] args) {
        String inputFilePath = "employees.csv";
        String outputFilePath = "updated_employees.csv";

        try (
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))
        ) {
            String line;

            
            String header = br.readLine();
            bw.write(header);
            bw.newLine();

            
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                String id = columns[0];
                String name = columns[1];
                String department = columns[2];
                double salary = Double.parseDouble(columns[3]);

                
                if (department.equalsIgnoreCase("IT")) {
                    salary = salary * 1.10;  
                }

                
                String updatedLine = id + "," + name + "," + department + "," + String.format("%.2f", salary);
                bw.write(updatedLine);
                bw.newLine();
            }

            System.out.println("Updated file saved as: " + outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
