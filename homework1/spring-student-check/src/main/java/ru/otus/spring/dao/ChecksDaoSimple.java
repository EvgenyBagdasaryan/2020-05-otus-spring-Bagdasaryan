package ru.otus.spring.dao;

import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ChecksDaoSimple implements ChecksDao  {

    private String fileName;

    public ChecksDaoSimple(String fileName) {
        this.fileName = fileName;
    }

    public String getAllChecks() throws IOException {

        String allChecks = "";
        InputStream is = new ClassPathResource(fileName).getInputStream();
        Scanner scan = new Scanner(is);
        while(scan.hasNextLine())
            allChecks += scan.nextLine() + System.lineSeparator();

        return allChecks;
    }
}
