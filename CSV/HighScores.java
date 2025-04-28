import java.io.*;

public class HighScores{
    public static void main(String[]args){
        String filePath = "student.csv";

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;

            br.readLine();
            while((line = br.readLine())!= null){
                String[]columns = line.split(",");

                String id = columns[0];
                String name = columns[1];
                int marks = Integer.parseInt(columns[3]);

                if(marks>80){
                    System.out.println("ID: "+ id + ",Name: "+ name+",Marks: "+ marks);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}