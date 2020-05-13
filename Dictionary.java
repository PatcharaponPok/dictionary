import java.io.BufferedReader;
import java.io.File; // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileReader;
import java.io.PrintWriter; // Import this class to write file

public class Dictionary {
    public static void main(String[] args) throws Exception {
        BufferedReader reader;
        try {
                //ex.1 - 4
                String executionPath = System.getProperty("user.dir");
                reader = new BufferedReader(new FileReader(executionPath + "\\test.txt"));
                
                int count = 1;
                String line;
                
                while((line = reader.readLine()) != null){
                    SetDirectory(line, count++);
                }//while read line in file
                reader.close();
                System.out.println("The operation is finished.");
                // finish code ex.1 -4

                //ex.5
                File file = new File(executionPath);
                Report ReportStep5 = new Report();
                File[] filesNames = file.listFiles();
                
                if (file.isDirectory()) {
                    // File[] filesNames = file.listFiles();
                    ReportStep5.GetReportSize(5); // call head table
                    count = 1;
                    for (File temp : filesNames) {
                        long size;
                        if (temp.isDirectory()) {
                            File dirs = new File(temp.getPath());
                            size = ReportStep5.CalulateSize(dirs); // sum size file in folder
                            System.out.println("|   "+ count++ + "     |      " + temp.getName() + "          |     " + ReportStep5.FileSize(size) + "     |");
                        }
                    }
                } else {
                    System.out.println("THIS IS NOT A FILE LOCATION!");
                }
                // finish code ex.5

                //ex.6 
                Zip zipDirectory = new Zip();
                for (File temp : filesNames) {
                    if (temp.isDirectory()) {
                        System.out.println(executionPath + "\\" + temp.getName());
                        zipDirectory.zipDirectory(executionPath + "\\" + temp.getName());
                    }
                }
                


          } catch (FileNotFoundException e) {
                System.out.println("Operation failed");
                e.printStackTrace();
          }    
    }// main

    private static void SetDirectory(String data, int count) throws FileNotFoundException {
        String word = data.toLowerCase();
        String firstF = word.substring(0,1); // substr for set directory
        File firstL = new File(firstF);
        String executionPath = System.getProperty("user.dir");
        //BufferedWriter write;
        
        System.out.println("No." + count + " Word: " + word) ;

        if (!firstL.exists()) {
            firstL.mkdir();
        }// if check file name

        if(word.length() > 1){
            String secondF = word.substring(1,2); // substr for set subdirectory
            String SubPath = executionPath + "\\" + firstF + "\\" + secondF;
            File SubDiectory = new File(SubPath);

            SubDiectory.mkdirs();
            PrintWriter WriteFile = new PrintWriter(executionPath + "\\" + firstF + "\\" + secondF + "\\" + word + ".txt");
            
            for(int i = 0; i < 100; i++){
                WriteFile.write(word + ", ");
            }// for write word to file

            WriteFile.close();
            System.out.println("Save file successfully");
        }else{
            PrintWriter WriteFile = new PrintWriter(executionPath + "\\" + firstF + "\\" + word + ".txt");

            for(int i = 0; i < 100; i++){
                WriteFile.write(word + ", ");
            }// for write word to file

            WriteFile.close();
            System.out.println("Save file successfully"); 
        }// if check length word for set directory
    }; // function SetDirectory
}