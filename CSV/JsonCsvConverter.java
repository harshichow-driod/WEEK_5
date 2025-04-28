import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class JsonCsvConverter {

    static class Student {
        int id;
        String name;
        int age;
        int marks;
    }

    public static void jsonToCsv(String jsonFilePath, String csvFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(jsonFilePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {

            Gson gson = new Gson();
            List<Student> students = gson.fromJson(br, new TypeToken<List<Student>>() {}.getType());

            bw.write("ID,Name,Age,Marks");
            bw.newLine();

            for (Student s : students) {
                String line = s.id + "," + s.name + "," + s.age + "," + s.marks;
                bw.write(line);
                bw.newLine();
            }

            System.out.println("Converted JSON to CSV: " + csvFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void csvToJson(String csvFilePath, String jsonFilePath) {
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(jsonFilePath))) {

            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                Student s = new Student();
                s.id = Integer.parseInt(columns[0]);
                s.name = columns[1];
                s.age = Integer.parseInt(columns[2]);
                s.marks = Integer.parseInt(columns[3]);
                students.add(s);
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(students);
            bw.write(json);

            System.out.println("Converted CSV to JSON: " + jsonFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String jsonFile = "students.json";
        String csvFile = "students.csv";
        String newJsonFile = "students_converted.json";

        jsonToCsv(jsonFile, csvFile);
        csvToJson(csvFile, newJsonFile);
    }
}
