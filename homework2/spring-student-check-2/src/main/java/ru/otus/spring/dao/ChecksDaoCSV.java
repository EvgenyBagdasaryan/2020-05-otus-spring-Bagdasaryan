package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Check;

@Repository
public class ChecksDaoCSV implements ChecksDao  {

    @Value("${filePath}")
    private String fileName;

    @Value("${successfullyNumberValidChecks}")
    private int successfullyNumberValidChecks;

    private final Scanning scan;

    public ChecksDaoCSV(Scanning scan) {
        this.scan = scan;
    }

    @Override
    public int getMinNumberSuccessValidChecks(){
        return successfullyNumberValidChecks;
    }

    @Override
    public Check getCheckByNum(int numCheck) throws CheckNotFoundException{

        scan.setFilePathName(fileName);
        for(String oneS : scan.getScannedCSV().split("\n")){
            String[] check = oneS.split("\\*\\*\\*");
            if(numCheck == Integer.valueOf(check[0].trim())){
                return (new Check(numCheck, check[1], check[2]));
            }
        }
        return null;
    }
}
