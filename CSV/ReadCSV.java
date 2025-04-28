import java.io.*;

public class ReadCSV{
    public static void main(String[]args){
        String filePath = "student.csv";
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            boolean isFirstLine = true;

            while((line = br.readLine())!= null){
                if(isFirstLine){
                    isFirstLine = false;
                    continue;
                }

                String[] columns = line.split(",");
                String id = columns[0];
                String name = columns[1];
                String age = columns[2];
                String marks = columns[3];

                System.out.println("Student Record:");
                System.out.println("  ID    : " + id);
                System.out.println("  Name  : " + name);
                System.out.println("  Age   : " + age);
                System.out.println("  Marks : " + marks);
                System.out.println("--------------------------");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}