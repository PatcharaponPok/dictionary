import java.io.File;

public class Report {
    
    public void GetReportSize(int c){

        if(c == 5){
            System.out.println("Report folder size");
            System.out.println("--------------------------------------------------------");
            System.out.println("|   No.   |   Folder Name   |       Folder size (Kbyte)|");
            System.out.println("--------------------------------------------------------");
        }else if(c == 6){
            System.out.println("Report folder size before and after zip");
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            System.out.println("|   No.   |   Folder Name   |   Before zip size (Kbyte)   |   After zip size (Kbyte)   |   Different file size (%)   |");
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
        }
    }

    public long CalulateSize(File dirs){
        long size = 0;
        for (File file : dirs.listFiles()) {
            if (file.isFile())
                size += file.length();
            else
                size += CalulateSize(file);
        }
        return size;
    }

    public long FileSize(long size) {
        return size / 1024;
    }
}