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
                reader = new BufferedReader(new FileReader("/Job-Java/Job-Java/test.txt"));
                
                int count = 1;
                String line;
                
                while((line = reader.readLine()) != null){
                    SetDirectory(line, count++);
                }//while read line in file
                reader.close();
                System.out.println("The operation is finished.");
                // finish code ex.1 -4

                //ex.5
                File file = new File("/Job-Java/Job-Java");
                Report ReportStep5 = new Report();
                File[] filesNames = file.listFiles();
                
                ReportStep5.GetReportSize(5); // call head table
                if (file.isDirectory()) {
                    // File[] filesNames = file.listFiles();
                    count = 1;
                    for (File temp : filesNames) {
                        long size;
                        if (temp.isDirectory()) {
                            File dirs = new File(temp.getPath());
                            size = ReportStep5.CalulateSize(dirs); // sum size file in folder
                            System.out.println("|   "+ count++ + "     |      " + temp.getName() + "          |     " + ReportStep5.FileSize(size) + "     |");
                        }
                    }
                }
                // finish code ex.5

                //ex.6 
                Zip zipDirectory = new Zip();
                for (File temp : filesNames) {// loop list directory in path
                    if (temp.isDirectory()) { // check type direvtory or not
                        System.out.println("Entry /Job-Java/Job-Java/" + temp.getName() + "Zipping to /Job-Java/Job-Java/" + temp.getName() + ".zip");
                        zipDirectory.zipDirectory("/Job-Java/Job-Java/" + temp.getName());// call funtion zip from class zip
                        System.out.println("Finish to zip: " + temp.getName() + ".zip");
                    }// end if
                }// end loop for
                // finish ex.6


          } catch (FileNotFoundException e) {
                System.out.println("Operation failed");
                e.printStackTrace();
          }    
    }// main

    private static void SetDirectory(String data, int count) throws FileNotFoundException {
        String word = data.toLowerCase();
        String firstF = word.substring(0,1); // substr for set directory
        File firstL = new File(firstF);
        //String executionPath = System.getProperty("user.dir");
        //BufferedWriter write;
        
        System.out.println("No." + count + " Word: " + word) ;

        if (!firstL.exists()) {
            firstL.mkdir();
        }// if check file name

        if(word.length() > 1){
            String secondF = word.substring(1,2); // substr for set subdirectory
            String SubPath = "/Job-Java/Job-Java/" + firstF + "/" + secondF;
            File SubDiectory = new File(SubPath);

            SubDiectory.mkdirs();
            PrintWriter WriteFile = new PrintWriter("/Job-Java/Job-Java/" + firstF + "/" + secondF + "/" + word + ".txt");
            
            for(int i = 0; i < 100; i++){
                WriteFile.write(word + ", ");
            }// for write word to file

            WriteFile.close();
            System.out.println("Save file successfully");
        }else{
            PrintWriter WriteFile = new PrintWriter("/Job-Java/Job-Java/" + firstF + "/" + word + ".txt");

            for(int i = 0; i < 100; i++){
                WriteFile.write(word + ", ");
            }// for write word to file

            WriteFile.close();
            System.out.println("Save file successfully"); 
        }// if check length word for set directory
    }; // function SetDirectory
}