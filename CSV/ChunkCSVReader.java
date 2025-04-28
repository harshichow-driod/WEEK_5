import java.io.*;

public class ChunkCSVReader {
    public static void main(String[] args) {
        String filePath = "large_file.csv";
        int chunkSize = 100;
        int totalProcessed = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            int count = 0;

            while ((line = br.readLine()) != null) {
                count++;
                totalProcessed++;

                if (count == chunkSize) {
                    System.out.println("Processed " + totalProcessed + " records so far.");
                    count = 0;
                }
            }

            if (count > 0) {
                System.out.println("Processed " + totalProcessed + " records in total.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
