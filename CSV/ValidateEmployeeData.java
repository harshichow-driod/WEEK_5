import java.io.*;
import java.util.regex.*;

public class ValidateEmployeeData {
    public static void main(String[] args) {
        String filePath = "employees.csv";
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        String phoneRegex = "\\d{10}";
        Pattern phonePattern = Pattern.compile(phoneRegex);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                String id = columns[0];
                String name = columns[1];
                String email = columns[2];
                String phone = columns[3];

                boolean validEmail = emailPattern.matcher(email).matches();
                boolean validPhone = phonePattern.matcher(phone).matches();

                if (!validEmail || !validPhone) {
                    System.out.println("Invalid row:");
                    System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phone);
                    if (!validEmail) {
                        System.out.println(" -> Invalid Email Format");
                    }
                    if (!validPhone) {
                        System.out.println(" -> Invalid Phone Number");
                    }
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
