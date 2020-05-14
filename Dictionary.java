import java.io.BufferedReader;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileReader; // Read file
import java.io.PrintWriter; // Import this class to write file
import java.util.ArrayList; // Array list
//import java.util.zip.ZipEntry;
import java.util.zip.ZipFile; // Zip File

public class Dictionary {
    public static void main(String[] args) throws Exception {
        BufferedReader reader;
        ArrayList<Long> Sizefolder = new ArrayList<>();
        ArrayList<Long> SizeZip = new ArrayList<>();
        ArrayList<String> NameFile = new ArrayList<>();
        try {
                //ex.1 - 4
                reader = new BufferedReader(new FileReader("/Job-Java/Job-Java/words.txt"));
                
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
                Report ReportStep = new Report();
                File[] filesNames = file.listFiles();
                
                ReportStep.GetReportSize(5); // call head table
                if (file.isDirectory()) {
                    // File[] filesNames = file.listFiles();
                    count = 1;
                    for (File temp : filesNames) {
                        long size;
                        if (temp.isDirectory()) {
                            File dirs = new File(temp.getPath());
                            size = ReportStep.CalulateSize(dirs); // sum size file in folder
                            NameFile.add(temp.getName());
                            System.out.println("|\t"+ count++ + "\t|" + temp.getName() + "\t\t\t|" + ReportStep.FileSize(size) + "\t\t\t\t|");
                            Sizefolder.add(ReportStep.FileSize(size));
                        }
                    }
                    System.out.println("-------------------------------------------------------------------------");
                }
                // finish code ex.5

                //ex.6 
                Zip zipDirectory = new Zip();
                for (File temp : filesNames) {// loop list directory in path
                    if (temp.isDirectory()) { // check type direvtory or not
                        System.out.println("Entry "+ temp.getPath() + " Zipping to /Job-Java/Job-Java/" + temp.getName() + ".zip");
                        zipDirectory.zipDirectory("/Job-Java/Job-Java/" + temp.getName());// call funtion zip from class zip
                        System.out.println("Finish to zip file: " + temp.getName() + ".zip");
                        ZipFile zip_file = new ZipFile("/Job-Java/Job-Java/" + temp.getName() + ".zip");
                        long Szip = zip_file.size(); 
                        SizeZip.add(Szip);
                    }// end if
                }// end loop for
                //ZipFile.close();

                ReportStep.GetReportSize(6);
                for(int i = 0; i < SizeZip.size(); i++){
                    System.out.println("|\t"+ i + "\t|" + NameFile.get(i) + "\t\t\t|" + Sizefolder.get(i) + "\t\t\t\t\t|" + SizeZip.get(i) + "\t\t\t\t\t|\t\t" + (100 * SizeZip.get(i) / Sizefolder.get(i)) + "\t\t\t|");
                }   
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
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