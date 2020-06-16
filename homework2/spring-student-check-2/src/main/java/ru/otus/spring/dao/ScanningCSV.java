package ru.otus.spring.dao;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ScanningCSV {

    private Scanner scan;
    private String allChecks = "";
    private String filePathName;

    public ScanningCSV(String filePathName) {
        this.filePathName = filePathName;
    }

    public String getScannedCSV(){
        try{
            InputStream is = new ClassPathResource(filePathName).getInputStream();
            scan = new Scanner(is);
            while(scan.hasNextLine()){
                allChecks += scan.nextLine() + System.lineSeparator();
            }
        }
        catch(IOException ex){
            System.out.println (ex.toString());
        }
        return allChecks;
    }
}
