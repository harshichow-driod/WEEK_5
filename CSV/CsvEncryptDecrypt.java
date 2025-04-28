import java.io.*;
import java.util.*;
import java.util.Base64;

public class CsvEncryptDecrypt {

    static class Employee {
        int id;
        String name;
        String department;
        String salary; 
        String email;  
    }

    public static String encrypt(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    public static String decrypt(String data) {
        return new String(Base64.getDecoder().decode(data));
    }

    public static void writeCsv(String filePath, List<Employee> employees) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("ID,Name,Department,Salary,Email");
            bw.newLine();

            for (Employee e : employees) {
                String encryptedSalary = encrypt(e.salary);
                String encryptedEmail = encrypt(e.email);
                String line = e.id + "," + e.name + "," + e.department + "," + encryptedSalary + "," + encryptedEmail;
                bw.write(line);
                bw.newLine();
            }

            System.out.println("CSV written with encrypted fields: " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readCsv(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                int id = Integer.parseInt(columns[0]);
                String name = columns[1];
                String department = columns[2];
                String salary = decrypt(columns[3]);
                String email = decrypt(columns[4]);

                System.out.println("ID: " + id + ", Name: " + name + ", Department: " + department +
                        ", Salary: " + salary + ", Email: " + email);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        Employee e1 = new Employee();
        e1.id = 101;
        e1.name = "John Doe";
        e1.department = "IT";
        e1.salary = "60000";
        e1.email = "john.doe@example.com";

        Employee e2 = new Employee();
        e2.id = 102;
        e2.name = "Jane Smith";
        e2.department = "HR";
        e2.salary = "55000";
        e2.email = "jane.smith@example.com";

        employees.add(e1);
        employees.add(e2);

        String csvFile = "encrypted_employees.csv";

        writeCsv(csvFile, employees);
        readCsv(csvFile);
    }
}
