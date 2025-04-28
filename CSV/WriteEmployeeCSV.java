import java.io.FileWriter;
import java.io.IOException;

public class WriteEmployeeCSV{
    public static void main(String[]args){
        String filePath = "employees.csv";

        try(FileWriter writer = new FileWriter(filePath)){
            writer.append("ID, Name, Department, Salary\n");

            writer.append("101, John Doe, Engineering, 60000\n");
            writer.append("102, Jane Smith, HR, 50000\n");
            writer.append("103, Michael Brown, Marketing, 55000\n");
            writer.append("104, Emily Davis, Finance, 40000\n");
            writer.append("105, David Wilson, Sales, 58000\n");

            System.out.println("CSV file written successfully!");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}



