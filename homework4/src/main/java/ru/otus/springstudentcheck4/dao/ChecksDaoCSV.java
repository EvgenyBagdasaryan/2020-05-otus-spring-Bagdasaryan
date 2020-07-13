package ru.otus.springstudentcheck4.dao;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import ru.otus.springstudentcheck4.configs.YamlProps;
import ru.otus.springstudentcheck4.domain.Check;

@Repository
public class ChecksDaoCSV implements ChecksDao  {

    private final Scanning scan;
    private final YamlProps props;
    private final MessageSource messageSource;

    public ChecksDaoCSV(Scanning scan, MessageSource messageSource, YamlProps props) {
        this.scan = scan;
        this.props = props;
        this.messageSource = messageSource;
    }

    @Override
    public Check getCheckByNum(int numCheck) throws CheckNotFoundException{
        scan.setFilePathName(messageSource.getMessage("filePath", new String[]{}, props.getLocale()));
        for(String oneS : scan.getScannedCSV().split("\n")){
            String[] check = oneS.split("\\*\\*\\*");
            if(numCheck == Integer.valueOf(check[0].trim())){
                return (new Check(numCheck, check[1], check[2]));
            }
        }
        return null;
    }
}
