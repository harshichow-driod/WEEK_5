import java.io.*;

public class CSVRecordCounter{
    public static void main(String[]args){
        String filePath = "employees.csv";
        int count =0;

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;

            br.readLine();

            while((line=br.readLine())!= null){
                count++;
            }

            System.out.println("Number of records (excluding header): "+ count);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}