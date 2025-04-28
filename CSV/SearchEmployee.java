import java.io.*;
import java.util.Scanner;

public class SearchEmployee{
    public static void main(String[]args){
        String inputFilePath = "employees.csv";
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter employee name to search: ");
        String searchName = sc.nextLine();
        boolean found = false;

        try(BufferedReader br = new BufferedReader(new FileReader(inputFilePath))){
            String line;

            br.readLine();

            while((line=br.readLine())!=null){
                String[]columns = line.split(",");
                String name = columns[1];
                String department = columns[2];
                String salary = columns[3];

                if (name.equalsIgnoreCase(searchName)) {
                    System.out.println("Department: " + department);
                    System.out.println("Salary: " + salary);
                    found = true;
                    break;  
                }
            }
            if(found){
                System.out.println("Employee not found");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}