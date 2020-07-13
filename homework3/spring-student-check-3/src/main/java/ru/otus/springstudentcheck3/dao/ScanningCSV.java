package ru.otus.springstudentcheck3.dao;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Repository
public class ScanningCSV implements Scanning{

    private Scanner scan;
    private String allChecks = "";
    private String filePathName;

    @Override
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

    @Override
    public void setFilePathName(String filePathName){
        this.filePathName = filePathName;
    }
}
