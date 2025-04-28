import java.io.*;
import java.util.*;

public class MergeCSV {
    public static void main(String[] args) {
        String file1 = "students1.csv";
        String file2 = "students2.csv";
        String outputFile = "merged_students.csv";

        Map<Integer, String[]> map1 = new HashMap<>();

        try (
            BufferedReader br1 = new BufferedReader(new FileReader(file1));
            BufferedReader br2 = new BufferedReader(new FileReader(file2));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String line;
            br1.readLine();
            while ((line = br1.readLine()) != null) {
                String[] columns = line.split(",");
                int id = Integer.parseInt(columns[0]);
                String name = columns[1];
                String age = columns[2];
                map1.put(id, new String[]{name, age});
            }

            bw.write("ID,Name,Age,Marks,Grade");
            bw.newLine();

            br2.readLine();
            while ((line = br2.readLine()) != null) {
                String[] columns = line.split(",");
                int id = Integer.parseInt(columns[0]);
                String marks = columns[1];
                String grade = columns[2];

                if (map1.containsKey(id)) {
                    String[] details = map1.get(id);
                    String name = details[0];
                    String age = details[1];
                    String mergedLine = id + "," + name + "," + age + "," + marks + "," + grade;
                    bw.write(mergedLine);
                    bw.newLine();
                }
            }

            System.out.println("Merged file created: " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
